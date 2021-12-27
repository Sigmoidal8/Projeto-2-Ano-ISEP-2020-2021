/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.UniqueCode;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.servicemanagement.domain.Service;
import java.util.Date;

import javax.persistence.TypedQuery;

/**
 *
 * @author José Forno
 */
public class JpaRequestRepository extends BasepaRepositoryBase<Request, UniqueCode, UniqueCode> implements RequestRepository{
    
    public JpaRequestRepository () {
        super("uniqueCode");
    }

    @Override
    public Iterable<Request> findIncompletedRequests() {
        final TypedQuery<Request> query = entityManager().createQuery("" +
                "SELECT r FROM Request r WHERE r.isDraft = 0 AND r.status <> 5 AND r.status <> 3", Request.class);
        return query.getResultList();
    }

    @Override
    public Iterable<Request> findColaboratorRequests(Collaborator collaborator) {
       final TypedQuery<Request> query = entityManager().createQuery("" +
                "SELECT r FROM Request r INNER JOIN Collaborator co on co =: colab  AND r.collaboratorRequest = co", Request.class);
       query.setParameter("colab", collaborator);
        return query.getResultList(); 
    }

    @Override
    public Iterable<Request> findRequestAvailableFeedback(Collaborator collaborator) {
        final TypedQuery<Request> query = entityManager().createQuery("" +
                "SELECT r FROM Request r INNER JOIN Collaborator co on co =: colab  AND r.collaboratorRequest = co AND r.status = 5", Request.class);
        query.setParameter("colab", collaborator);
        return query.getResultList();
    }
}
