/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.application;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Acronym;
import eapli.base.teammanagement.domain.Designation;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.TeamType;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.teammanagement.repositories.TeamTypeRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Forno
 */
public class CreateTeamController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TeamTypeRepository teamTypeRepository = PersistenceContext.repositories().teamTypes();
    
    public Team createTeam(String uniqueCode, String acronym, String designation,
            TeamType teamType, List<Collaborator> collaboratorL){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.HR_MANAGER);
        Team newTeam = new Team(new UniqueCode(uniqueCode), new Acronym(acronym), new Designation(designation), teamType,collaboratorL);//colaborators
        
        return teamRepository.save(newTeam);
    }
    
    public Iterable<Collaborator> collaborators(){
        return collaboratorRepository.findAll();
    }
    
    public Iterable<TeamType> teamTypes(){
        return teamTypeRepository.findAll();
    }
    
}