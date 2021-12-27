package eapli.base.persistence.impl.jpa;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.UniqueCode;
import eapli.base.servicemanagement.repositories.ServiceRepository;

import javax.persistence.TypedQuery;

public class JpaServiceRepository extends BasepaRepositoryBase<Service,UniqueCode,UniqueCode> implements ServiceRepository {
    public JpaServiceRepository () {
        super("uniqueCode");
    }

    @Override
    public Iterable<Service> findUnfinishedServiceByCollaborator(Collaborator collaborator) {
        final TypedQuery<Service> query = entityManager().createQuery("" +
                "SELECT s FROM Service s WHERE s.isDraft = 1 AND s.collaborator = :colab", Service.class);
        query.setParameter("colab", collaborator);
        return query.getResultList();
    }
}
