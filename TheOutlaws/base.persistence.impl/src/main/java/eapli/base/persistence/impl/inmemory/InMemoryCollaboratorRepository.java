/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 *
 * @author migue
 */
public class InMemoryCollaboratorRepository extends InMemoryDomainRepository<Collaborator, MecanographicNumber> implements CollaboratorRepository{
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Collaborator> findByEmail(EmailAddress email) {
        return Optional.of(data().get(email));
    }
}
