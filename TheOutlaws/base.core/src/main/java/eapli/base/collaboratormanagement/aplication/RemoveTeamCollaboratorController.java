/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.aplication;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author migue
 */
public class RemoveTeamCollaboratorController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();

    public void removeTeamCollaborator(Team team, Collaborator colab) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER);

        team.collaborators().remove(colab);
        teamRepository.save(team);
    }

    public Iterable<Collaborator> collaborators(Team team) {
        return team.collaborators();
    }

    public Iterable<Team> teams() {
        return teamRepository.findAll();
    }

}
