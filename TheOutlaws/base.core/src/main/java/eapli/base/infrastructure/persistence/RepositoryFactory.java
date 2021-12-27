/**
 *
 */
package eapli.base.infrastructure.persistence;

import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;

import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.servicemanagement.repositories.ServiceRepository;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.base.taskmanagement.repositories.TaskRepository;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.teammanagement.repositories.TeamTypeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

	/**
	 * factory method to create a transactional context to use in the repositories
	 *
	 * @return
	 */
	TransactionalContext newTransactionalContext();

	/**
	 *
	 * @param autoTx the transactional context to enrol
	 * @return
	 */
	UserRepository users(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	UserRepository users();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	ClientUserRepository clientUsers(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	ClientUserRepository clientUsers();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	SignupRequestRepository signupRequests(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	SignupRequestRepository signupRequests();

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	CatalogRepository catalogs();
        
	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	CollaboratorRepository collaborators();
        
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	TeamRepository teams();


	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	TeamTypeRepository teamTypes();
        
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	CriticityRepository criticities();
        
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
        ServiceRepository services();
        
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
        TaskRepository tasks();
        
        
        /**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
        RequestRepository requests();

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
		TaskExecutionRepository taskExecutions();
}
