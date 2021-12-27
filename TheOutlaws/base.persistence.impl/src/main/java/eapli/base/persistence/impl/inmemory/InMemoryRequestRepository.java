/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.UniqueCode;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Date;

/**
 *
 * @author José Forno
 */
public class InMemoryRequestRepository extends InMemoryDomainRepository<Request, UniqueCode> implements RequestRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Request> findIncompletedRequests() {
        return null;
    }

    @Override
    public Iterable<Request> findColaboratorRequests(Collaborator collaborator) {
        return null;
    }

    @Override
    public Iterable<Request> findRequestAvailableFeedback(Collaborator collaborator) {
        return null;
    }
}
