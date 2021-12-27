/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 *
 * @author José Forno
 */
public class InMemoryTeamRepository extends InMemoryDomainRepository<Team, UniqueCode> implements TeamRepository{

    @Override
    public Iterable<Team> findByCollaborator(Collaborator collaborator) {
        return match(e -> e.collaborators().contains(collaborator));
    }
    
    
}
