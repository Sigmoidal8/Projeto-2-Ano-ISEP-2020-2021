/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.Identifier;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.teammanagement.domain.Team;
import java.util.List;

import javax.persistence.TypedQuery;

/**
 * @author raulcoelho
 */
public class JpaCatalogRepository extends BasepaRepositoryBase<Catalog, Identifier, Identifier> implements CatalogRepository {

    public JpaCatalogRepository() {
        super("identifier");
    }

    @Override
    public Iterable<Catalog> findByTeams(Team team) {

        final TypedQuery<Catalog> query = entityManager().createQuery(
        "SELECT c FROM eapli.base.catalogmanagement.domain.Catalog c INNER JOIN eapli.base.catalogmanagement.domain.Criteria ct ON c.criteria = ct INNER JOIN ct.teams t ON t = : teamSelected"
        , Catalog.class);
        query.setParameter("teamSelected", team);
        return query.getResultList();
    }

    @Override
    public Catalog findByService(Service service) {
        final TypedQuery<Catalog> query = entityManager().createQuery(
                "SELECT c FROM eapli.base.catalogmanagement.domain.Catalog c INNER JOIN c.services cs ON cs = : service"
                , Catalog.class);
        query.setParameter("service", service);
        return query.getSingleResult();
    }
}
