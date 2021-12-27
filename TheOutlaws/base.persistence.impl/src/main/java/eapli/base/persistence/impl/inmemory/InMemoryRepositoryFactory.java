package eapli.base.persistence.impl.inmemory;

import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.servicemanagement.repositories.ServiceRepository;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.base.taskmanagement.repositories.TaskRepository;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.teammanagement.repositories.TeamTypeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public CatalogRepository catalogs() {
        return new InMemoryCatalogRepository();
    }
    
    @Override
    public CollaboratorRepository collaborators() {
        return new InMemoryCollaboratorRepository();
    }

    @Override
    public TeamRepository teams() {
        return new InMemoryTeamRepository();
    }

    public TeamTypeRepository teamTypes(){
        return new InMemoryTeamTypeRepository();
    }

    @Override
    public CriticityRepository criticities() {
        return new InMemoryCriticityRepository();
    }

    @Override
    public ServiceRepository services() {
        return new InMemoryServiceRepository();
    }

    //@Override
    //public AttributeRepository attributes() {
      //  return new InMemoryAttributeRepository();
   // }

    //@Override
    //public FormRepository forms() {
     //   return new InMemoryFormRepository();
    //}

    @Override
    public TaskRepository tasks() {
        return new InMemoryTaskRepository();
    }

    @Override
    public RequestRepository requests() {
        return new InMemoryRequestRepository();
    }

    @Override
    public TaskExecutionRepository taskExecutions() {
        return new InMemoryTaskExecutionRepository();
    }

}
