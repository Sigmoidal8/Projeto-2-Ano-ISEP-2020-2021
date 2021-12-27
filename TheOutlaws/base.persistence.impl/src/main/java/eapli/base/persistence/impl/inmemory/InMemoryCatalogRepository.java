/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.Identifier;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.List;

/**
 *
 * @author raulcoelho
 */
public class InMemoryCatalogRepository extends InMemoryDomainRepository<Catalog, Identifier> implements CatalogRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Catalog> findByTeams(Team team) {
        return match(e -> e.criteria().teams().contains(team));
    }

    @Override
    public Catalog findByService(Service service) {
        return matchOne(e -> e.services().contains(service)).get();
    }
}
