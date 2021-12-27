/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.collaboratormanagement.aplication.AddTeamCollaboratorController;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.application.CreateTeamController;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.TeamType;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.teammanagement.repositories.TeamTypeRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author migue
 */
public class TeamsBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(TeamsBootstrapper.class);
    private final TeamTypeRepository TeamTypeRepo = PersistenceContext.repositories().teamTypes();
    private final CollaboratorRepository CollaboratorRepo = PersistenceContext.repositories().collaborators();
    private final TeamRepository TeamRepo = PersistenceContext.repositories().teams();

    private TeamType getTeamType(final String code) {
        return TeamTypeRepo.ofIdentity(new UniqueCode(code)).orElseThrow(IllegalStateException::new);
    }

    private Collaborator getCollaborator(final MecanographicNumber mechanographicNumber) {
        return CollaboratorRepo.ofIdentity(mechanographicNumber).orElseThrow(IllegalStateException::new);
    }

    private Team getTeam(final UniqueCode uniqueCode) {
        return TeamRepo.ofIdentity(uniqueCode).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute() {
        final TeamType type1 = getTeamType(TestDataConstants.TEAM_TYPE_1);
        final TeamType type2 = getTeamType(TestDataConstants.TEAM_TYPE_2);

        final Collaborator colab1 = getCollaborator(TestDataConstants.COLLABORATOR_1);
        final Collaborator colab2 = getCollaborator(TestDataConstants.COLLABORATOR_2);
        final Collaborator colab3 = getCollaborator(TestDataConstants.COLLABORATOR_3);
        final Collaborator colab4 = getCollaborator(TestDataConstants.COLLABORATOR_4);
        final Collaborator colab5 = getCollaborator(TestDataConstants.COLLABORATOR_5);
        final Collaborator colab6 = getCollaborator(TestDataConstants.COLLABORATOR_6);
        final Collaborator colab7 = getCollaborator(TestDataConstants.COLLABORATOR_7);
        final Collaborator colab8 = getCollaborator(TestDataConstants.COLLABORATOR_8);

        final List<Collaborator> list1 = new ArrayList<>();
        list1.add(colab1);
        list1.add(colab2);
        final List<Collaborator> list2 = new ArrayList<>();
        list2.add(colab1);
        final List<Collaborator> list3 = new ArrayList<>();
        list3.add(colab1);
        list3.add(colab3);
        list3.add(colab4);
        list3.add(colab6);
        list3.add(colab7);
        final List<Collaborator> list4 = new ArrayList<>();
        list4.add(colab2);
        list4.add(colab5);
        list4.add(colab8);

        registerTeam("team1", "vendas", "The Sales Team", type1, list1);
        registerTeam("team2", "reparações", "The HR Team", type2, list2);

        final Team team1 = getTeam(TestDataConstants.TEAM_1);
        final Team team2 = getTeam(TestDataConstants.TEAM_2);

        addCollaboratorTeam(team1, list3);
        addCollaboratorTeam(team2, list4);

        return false;
    }

    private void registerTeam(final String uniqueCode, final String acronym, final String designation, final TeamType teamType, final List<Collaborator> collaboratorsResponsiblefinal) {
        final CreateTeamController controller = new CreateTeamController();
        try {
            controller.createTeam(uniqueCode, acronym, designation, teamType, collaboratorsResponsiblefinal);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", uniqueCode);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    private void addCollaboratorTeam(Team team, List<Collaborator> colabs) {
        final AddTeamCollaboratorController controller = new AddTeamCollaboratorController();
        try {
            controller.addTeamCollaborator(team, colabs);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", team.identity());
            LOGGER.trace("Assuming existing record", ex);
        }
    }

}
