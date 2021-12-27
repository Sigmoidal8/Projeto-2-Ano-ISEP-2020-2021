/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.framework.general.domain.model.EmailAddress;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author migue
 */
public class JpaCollaboratorRepository extends BasepaRepositoryBase<Collaborator, MecanographicNumber, MecanographicNumber> implements CollaboratorRepository {
     
    public JpaCollaboratorRepository () {
        super("mechanographicNumber");
    }

    @Override
    public Optional<Collaborator> findByEmail(EmailAddress email) {
        final Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return matchOne("e.email=:email", params);
    }
}
