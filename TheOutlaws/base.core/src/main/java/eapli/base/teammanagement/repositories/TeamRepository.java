/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.repositories;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author José Forno
 */
public interface TeamRepository extends DomainRepository<UniqueCode,Team>{
    
    @Override
    Iterable<Team> findAll();
    
    Iterable<Team> findByCollaborator(Collaborator collaborator);
}
