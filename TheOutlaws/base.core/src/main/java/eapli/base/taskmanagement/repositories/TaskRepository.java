/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.taskmanagement.repositories;

import eapli.base.taskmanagement.domain.Task;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author migue
 */
public interface TaskRepository extends DomainRepository<Long,Task> {
    
}
