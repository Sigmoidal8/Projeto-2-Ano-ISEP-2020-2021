package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogmanagement.application.RegisterCatalogController;
import eapli.base.catalogmanagement.domain.Criteria;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatalogsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);
    
    private final RegisterCatalogController theController = new RegisterCatalogController();
    private final TeamRepository TeamRepo = PersistenceContext.repositories().teams();
    private final CollaboratorRepository CollaboratorRepo = PersistenceContext.repositories().collaborators();
    
    private Team getTeam(final UniqueCode uniqueCode) {
        return TeamRepo.ofIdentity(uniqueCode).orElseThrow(IllegalStateException::new);
    }

    private Collaborator getCollaborator(final MecanographicNumber mechanographicNumber) {
        return CollaboratorRepo.ofIdentity(mechanographicNumber).orElseThrow(IllegalStateException::new);
    }


    @Override
    public boolean execute() {
        final Team team1 = getTeam(TestDataConstants.TEAM_1);
        final Team team2 = getTeam(TestDataConstants.TEAM_2);

        final Collaborator colab1 = getCollaborator(TestDataConstants.COLLABORATOR_CATALOG_1);
        final Collaborator colab2 = getCollaborator(TestDataConstants.COLLABORATOR_CATALOG_2);

        final List<Team> list1 = new ArrayList<>();
        list1.add(team1);
        list1.add(team2);
        final List<Team> list2= new ArrayList<>();
        list2.add(team1);
         
        
        registerCatalog(1, "Catálogo de Vendas", "Catálogo que contém serviços de vendas", "Vendas", "/icones/icon1", colab1, list2);
        registerCatalog(2, "Catálogo de Férias", "Catálogo que permite gerir as férias", "Férias", "/icones/icon2", colab2, list1);
        return false;
    }

    private void registerCatalog(final long identifier, final String briefDescription, final String completeDescription,final String title, final String icone, final Collaborator collaborator, List<Team> teams){

        final RegisterCatalogController controller = new RegisterCatalogController();
        try {
            controller.registerCatalog(identifier, title, briefDescription, completeDescription, icone, collaborator ,teams);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", identifier);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}