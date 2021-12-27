/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.repositories;

import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.criticitymanagement.domain.Value;
import eapli.framework.domain.repositories.DomainRepository;


/**
 *
 * @author migue
 */
public interface CriticityRepository extends DomainRepository<Value,Criticity> {
    
}
