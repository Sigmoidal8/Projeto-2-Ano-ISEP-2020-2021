/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.catalogmanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.repositories.ServiceRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.time.LocalTime;

/**
 *
 * @author migue
 */
public class AssignCriticalityController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private final CriticityRepository criticityRepository = PersistenceContext.repositories().criticities();
    private final ServiceRepository serviceRepository = PersistenceContext.repositories().services();

    public void assignCriticality(Catalog catalog, Criticity criticity) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SERVICES_MANAGER);

        Catalog theCatalogcatalog = catalog.changeCriticality(criticity);
        
        for (Service s : theCatalogcatalog.services()) {
                    assignCriticalityToService(s, criticity);
                }

        catalogRepository.save(theCatalogcatalog);
    }

    public Iterable<Catalog> catalogs() {
        return catalogRepository.findAll();
    }

    public Iterable<Criticity> criticalities() {
        return criticityRepository.findAll();
    }

    public void changeAverageApprovalTime(LocalTime averageApprovalTime, Criticity criticity) {
        criticity.objectives().changeAverageApprovalTime(averageApprovalTime);

    }

    public void changeMaxApprovalTime(LocalTime maxApprovalTime, Criticity criticity) {
        criticity.objectives().changeAverageApprovalTime(maxApprovalTime);

    }

    public void changeAverageResolutionTime(LocalTime averageResolutionTime, Criticity criticity) {
        criticity.objectives().changeAverageApprovalTime(averageResolutionTime);
    }

    public void changeMaxResolutionTime(LocalTime maxResolutionTime, Criticity criticity) {
        criticity.objectives().changeAverageApprovalTime(maxResolutionTime);
    }

    public void assignCriticalityToService(Service theService, Criticity theCriticality2) {
        Service theService2 = theService.changeCriticality(theCriticality2);

    }
    
    public void assignCriticalityToServices(Service theService, Criticity theCriticality2) {
        Service theService2 = theService.changeCriticality(theCriticality2);
        
        serviceRepository.save(theService2);

    }
}
