/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamRepository;
import javax.persistence.TypedQuery;

/**
 *
 * @author José Forno
 */
public class JpaTeamRepository extends BasepaRepositoryBase<Team, UniqueCode, UniqueCode> implements TeamRepository{
    
    public JpaTeamRepository(){
        super("uniqueCode");
    }

    @Override
    public Iterable<Team> findByCollaborator(Collaborator collaborator) {
       final TypedQuery<Team> query = entityManager().createQuery(
               "SELECT t FROM Team t INNER JOIN eapli.base.collaboratormanagement.domain.Collaborator c ON c = :collab INNER JOIN t.collaboratorsMembers cm ON cm = :collab",Team.class );
       query.setParameter("collab", collaborator);
       
       return query.getResultList();
       
    }
}
