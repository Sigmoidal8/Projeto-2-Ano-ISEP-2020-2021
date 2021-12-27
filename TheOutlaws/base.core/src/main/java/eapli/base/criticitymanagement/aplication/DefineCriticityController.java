/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.aplication;

import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.criticitymanagement.domain.Label;
import eapli.base.criticitymanagement.domain.Objective;
import eapli.base.criticitymanagement.domain.Value;
import eapli.base.criticitymanagement.repositories.CriticityRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Color;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.time.LocalTime;

/**
 *
 * @author migue
 */
public class DefineCriticityController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CriticityRepository criticityRepository = PersistenceContext.repositories().criticities();

    public Criticity registerCriticity(String label, int value, int red, int green, int blue, String colorName, LocalTime averageApprovalTime, LocalTime maxApprovalTime, LocalTime averageResolutionTime, LocalTime maxResolutionTime){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SERVICES_MANAGER);

        Color color = new Color(red, green, blue, colorName);
        Objective objectives = new Objective(averageApprovalTime, maxApprovalTime, averageResolutionTime, maxResolutionTime);

        Criticity criticity = new Criticity(Label.valueOf(label), Value.valueOf(value), color, objectives);
        return criticityRepository.save(criticity);
    }
}
