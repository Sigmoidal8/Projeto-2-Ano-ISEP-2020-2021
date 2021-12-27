/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogmanagement.application.AssignCriticalityController;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.Identifier;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.criticitymanagement.aplication.DefineCriticityController;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.criticitymanagement.domain.Value;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import java.time.LocalTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author migue
 */
public class CriticalityBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(TeamTypeBootstrapper.class);
    private static final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private static final CriticityRepository criticityRepositoru = PersistenceContext.repositories().criticities();

    private Catalog getCatalog(final Identifier catalog){
        return catalogRepository.ofIdentity(catalog).orElseThrow(IllegalStateException::new);
    }

    private Criticity getCriticity(final Integer value){
        return criticityRepositoru.ofIdentity(new Value(value)).orElseThrow(IllegalStateException::new);
    }


    @Override
    public boolean execute() {

        final LocalTime time1 = LocalTime.of(0, 12, 50);
        final LocalTime time2 = LocalTime.of(0, 24, 31);
        final LocalTime time3 = LocalTime.of(1, 40, 50);
        final LocalTime time4 = LocalTime.of(1, 57, 31);
        final LocalTime time5 = LocalTime.of(4, 32, 43);
        final LocalTime time6 = LocalTime.of(5, 34, 15);
        final LocalTime time7 = LocalTime.of(5, 23, 54);
        final LocalTime time8 = LocalTime.of(6, 54, 21);
        final LocalTime time9 = LocalTime.of(7, 23, 45);
        final LocalTime time10 = LocalTime.of(7, 12, 45);
        

        registerCriticality("label1", 1, 255, 126, 26, "Laranja", time2,time3,time4,time5);
        registerCriticality("label2", 2, 123, 126, 234, "Roxo", time1,time2,time6,time7);
        registerCriticality("label3", 3, 0, 126, 255, "Azul Ciano", time3,time4,time5,time6);
        registerCriticality("label4", 4, 23, 45, 123, "Azul Escuro", time7,time8,time9,time10);
        registerCriticality("label5", 5, 0, 215, 115, "Verde Claro", time4,time6,time5,time7);
        addCriticalityToCatalog(getCatalog(TestDataConstants.CATALOG_1), getCriticity(2));
        addCriticalityToCatalog(getCatalog(TestDataConstants.CATALOG_2), getCriticity(3));

        return false;
    }

    private void registerCriticality(final String label, final int value, final int red, final int green, final int blue, final String colorName, final LocalTime averageApprovalTime, final LocalTime maxApprovalTime, final LocalTime averageResolutionTime, final LocalTime maxResolutionTime) {
        final DefineCriticityController controller = new DefineCriticityController();
        try {

            controller.registerCriticity(label, value, red, green, blue, colorName, averageApprovalTime, maxApprovalTime, averageResolutionTime, maxResolutionTime);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", value);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    private void addCriticalityToCatalog(final Catalog catal, final Criticity crit){
        final AssignCriticalityController controller = new AssignCriticalityController();
        try{
            controller.assignCriticality(catal, crit);
        }catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", crit);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

}
