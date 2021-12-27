/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.repositories;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;

import java.util.Optional;

/**
 *
 * @author miguel
 */
public interface CollaboratorRepository extends DomainRepository<MecanographicNumber,Collaborator> {

    Optional<Collaborator> findByEmail(EmailAddress email);
}
