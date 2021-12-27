package eapli.base.persistence.impl.inmemory;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.UniqueCode;
import eapli.base.servicemanagement.repositories.ServiceRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryServiceRepository extends InMemoryDomainRepository<Service,UniqueCode> implements ServiceRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Service> findUnfinishedServiceByCollaborator(Collaborator collaborator) {
        return match(e -> e.collaboratorWhoDefined().equals(collaborator) && !e.isDraft());
    }
}
