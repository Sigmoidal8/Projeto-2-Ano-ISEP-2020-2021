package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.servicemanagement.repositories.ServiceRepository;
import eapli.base.taskmanagement.repositories.TaskExecutionRepository;
import eapli.base.taskmanagement.repositories.TaskRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaCatalogRepository catalogs() {
        return new JpaCatalogRepository();
    }

    @Override
    public JpaCollaboratorRepository collaborators() {
        return new JpaCollaboratorRepository();
    }

    @Override
    public JpaTeamRepository teams() {
        return new JpaTeamRepository();
    }

    public JpaTeamTypeRepository teamTypes(){
        return new JpaTeamTypeRepository();
    }

    @Override
    public CriticityRepository criticities() {
        return new JpaCriticityRepository();
    }

    @Override
    public ServiceRepository services() {
        return new JpaServiceRepository();
    }

    //@Override
    //public AttributeRepository attributes() {
      //  return new JpaAttributeRepository();
    //}

    //@Override
    //public FormRepository forms() {
      //  return new JpaFormRepository();
    //}

    @Override
    public TaskRepository tasks() {
        return new JpaTaskRepository();
    }

    @Override
    public RequestRepository requests() {
        return new JpaRequestRepository();
    }

    @Override
    public TaskExecutionRepository taskExecutions() {
        return new JpaTaskExecutionRepository();
    }


}
