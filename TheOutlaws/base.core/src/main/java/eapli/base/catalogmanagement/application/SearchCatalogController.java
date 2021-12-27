/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.catalogmanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.domain.Keyword;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.Title;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author José Forno
 */
public class SearchCatalogController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();

    public Iterable<Catalog> catalogs() {
        Collaborator collaborator = null;
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            collaborator = optional.get();
        }

        Iterable<Team> teamL = teamRepository.findByCollaborator(collaborator);

        if (teamL == null){
            return null;
        }

        List<Catalog> catalogLGeral = new ArrayList<>();

        for (Team team : teamL) {
            Iterable<Catalog> catalogL = catalogRepository.findByTeams(team);
            if(catalogL==null){
                return null;
            }
            catalogL.forEach(catalogLGeral::add);

        }

        return catalogLGeral;
    }

    public List<Service> searchByKeyword(Catalog c, String keyword) {
        List<Service> ret = new ArrayList<>();
        List<Service> servicesList = c.services();
        for (Service service : servicesList) {
            Boolean contains = false;
            List<Keyword> keywordsL = service.keywords();
            for (Keyword key : keywordsL) {
                if (key.toString().contains(keyword)) {
                    contains = true;
                }
            }
            if (contains) {
                ret.add(service);
            }
        }

        return ret;
    }

    public List<Service> searchByTitle(Catalog c, String title) {

        List<Service> ret = new ArrayList<>();
        List<Service> servicesList = c.services();
        for (Service service : servicesList) {
            Title titleAux = service.title();
            if (titleAux.toString().contains(title)) {
                ret.add(service);
            }
        }

        return ret;
    }

}
