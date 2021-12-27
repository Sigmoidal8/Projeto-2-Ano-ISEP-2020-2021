/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.repositories.TaskRepository;
import javax.persistence.TypedQuery;

/**
 *
 * @author migue
 */
public class JpaTaskRepository extends BasepaRepositoryBase<Task, Long, Long> implements TaskRepository {
     
    public JpaTaskRepository () {
        super("uniqueCode");
    }
}
