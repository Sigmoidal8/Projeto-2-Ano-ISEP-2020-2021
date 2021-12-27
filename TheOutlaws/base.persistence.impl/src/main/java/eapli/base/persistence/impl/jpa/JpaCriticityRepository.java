/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.criticitymanagement.domain.Value;
import eapli.base.criticitymanagement.repositories.CriticityRepository;

/**
 *
 * @author migue
 */
public class JpaCriticityRepository extends BasepaRepositoryBase<Criticity, Value, Value> implements CriticityRepository {
     
    public JpaCriticityRepository () {
        super("value");
    }
}
