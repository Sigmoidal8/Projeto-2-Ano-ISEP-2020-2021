/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.repositories;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.UniqueCode;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author Utilizador
 */
public interface ServiceRepository extends DomainRepository<UniqueCode,Service>{
    
    @Override
    Iterable<Service> findAll();

    Iterable<Service> findUnfinishedServiceByCollaborator(Collaborator collaborator);

}
