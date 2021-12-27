/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.catalogmanagement.application;

import eapli.base.catalogmanagement.domain.*;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

/**
 *
 * @author raulcoelho
 */
public class RegisterCatalogController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    
    public Catalog registerCatalog(final long identifier, String title, String briefDescription,
                                   String completeDescription, String icone, Collaborator collaborator, List<Team> teams){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SERVICES_MANAGER);

        Criteria criteria = new Criteria(teams);
        Catalog newCatalog = new Catalog(Identifier.valueOf(identifier), Description.valueOf(briefDescription), Description.valueOf(completeDescription), Title.valueOf(title), Icone.valueOf(icone),
                criteria,collaborator);
        
        return catalogRepository.save(newCatalog);
    }
    
    public Iterable<Collaborator> collaborators(){
        return collaboratorRepository.findAll();
    }

    public Iterable<Team> teams(){
        return teamRepository.findAll();
    }
}
