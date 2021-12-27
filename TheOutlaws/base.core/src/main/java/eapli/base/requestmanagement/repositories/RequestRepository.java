/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.repositories;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.UniqueCode;
import eapli.framework.domain.repositories.DomainRepository;
import java.util.Date;

/**
 *
 * @author José Forno
 */
public interface RequestRepository extends DomainRepository<UniqueCode,Request>{
    
    @Override
    Iterable<Request> findAll();

    Iterable<Request> findIncompletedRequests();

    Iterable<Request> findColaboratorRequests(Collaborator collaborator);

    Iterable<Request> findRequestAvailableFeedback(Collaborator collaborator);
}
