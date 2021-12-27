/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.catalogmanagement.repositories;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.Identifier;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.teammanagement.domain.Team;
import eapli.framework.domain.repositories.DomainRepository;
import java.util.List;

/**
 *
 * @author raulcoelho
 */
public interface CatalogRepository extends DomainRepository<Identifier,Catalog> {
    
    @Override
    Iterable<Catalog> findAll();
    
    Iterable<Catalog> findByTeams(Team team);

    Catalog findByService(Service service);
}
