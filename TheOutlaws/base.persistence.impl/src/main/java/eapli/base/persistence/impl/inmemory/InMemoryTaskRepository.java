/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.repositories.TaskRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Optional;

/**
 *
 * @author migue
 */
public class InMemoryTaskRepository extends InMemoryDomainRepository<Task, Long> implements TaskRepository{
    static {
        InMemoryInitializer.init();
    }
}
