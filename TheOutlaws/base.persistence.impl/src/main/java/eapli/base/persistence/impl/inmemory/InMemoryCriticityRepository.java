/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.criticitymanagement.domain.Value;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 *
 * @author migue
 */
public class InMemoryCriticityRepository extends InMemoryDomainRepository<Criticity, Value> implements CriticityRepository{
    static {
        InMemoryInitializer.init();
    }
    
}
