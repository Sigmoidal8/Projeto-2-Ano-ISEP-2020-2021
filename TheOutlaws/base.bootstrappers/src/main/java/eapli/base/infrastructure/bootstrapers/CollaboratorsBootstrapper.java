/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.collaboratormanagement.aplication.RegisterCollaboratorController;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.teammanagement.domain.Team;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author migue
 */
public class CollaboratorsBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(CollaboratorsBootstrapper.class);

    @Override
    public boolean execute() {
        Set<Role> roleTypesCollaborator = new HashSet<>();
        Set<Role> roleTypesHRManager = new HashSet<>();
        Set<Role> roleTypesServiceManager = new HashSet<>();

        roleTypesCollaborator.add(BaseRoles.COLLABORATOR);
        roleTypesHRManager.add(BaseRoles.HR_MANAGER);
        roleTypesServiceManager.add(BaseRoles.SERVICES_MANAGER);

        try {
            Collaborator colabResp = registerCollaborator(9999, "Tiago Pimentel", "Tiago Pimentel Silva", "t27@outlook.pt", "+351934806040", new GregorianCalendar(2001, 2, 13).getTime(), "Portugal", "Lisboa", "Almada", "Rua de Freitas", roleTypesHRManager, null, new ArrayList<>());

            registerCollaborator(1, "Jose Silva", "Jose Miguel Silva", "1190778@isep.ipp.pt", "+351934806040", new GregorianCalendar(2001, 2, 13).getTime(), "Portugal", "Porto", "Santo Tirso", "Rua de Freitas", roleTypesHRManager, colabResp, new ArrayList<>());
            registerCollaborator(2, "Jose Forno", "Jose Miguel Forno", "1190780@isep.ipp.pt", "+351934943646", new GregorianCalendar(2001, 5, 21).getTime(), "Portugal", "Porto", "Santo Tirso", "Rua Fernando Pesooa", roleTypesHRManager, colabResp, new ArrayList<>());
            registerCollaborator(3, "Tiago Magalhaes", "Tiago Miguel Magalhaes", "1191091@isep.ipp.pt", "+351915146541", new GregorianCalendar(2001, 3, 16).getTime(), "Portugal", "Porto", "Felgueiras", "Rua S.Pedro", roleTypesServiceManager, colabResp, new ArrayList<>());
            registerCollaborator(4, "Raul Coelho", "Raul Reis Coelho", "1190986@isep.ipp.pt", "+351927399638", new GregorianCalendar(2001, 4, 6).getTime(), "Portugal", "Porto", "Santo Tirso", "Rua Facho", roleTypesServiceManager, colabResp, new ArrayList<>());
            registerCollaborator(5, "Sandro Barraca", "Sandro Emanuel Barraca", "miguelsilvadrag2001@gmail.com", "+351934806040", new GregorianCalendar(2001, 8, 5).getTime(), "Portugal", "Porto", "Maia", "Rua da Maria", roleTypesCollaborator, colabResp, new ArrayList<>());
            registerCollaborator(6, "Jacinto Pereira", "Jacinto Pedro Perreira", "josemiguelforno2001@gmail.com", "+351934943646", new GregorianCalendar(2001, 11, 26).getTime(), "Portugal", "Porto", "Gaia", "Rua Anibel", roleTypesCollaborator, colabResp, new ArrayList<>());
            registerCollaborator(7, "Andre Silva", "Andre Martins da Silva", "tiagomaga16@gmail.com", "+351915146541", new GregorianCalendar(1995, 1, 12).getTime(), "Portugal", "Porto", "Famalicão", "Rua da pedra", roleTypesCollaborator, colabResp, new ArrayList<>());
            registerCollaborator(8, "Ana Maria", "Ana Maria Costa", "raulreiscoelho2011@gmail.com", "+351927399638", new GregorianCalendar(1980, 11, 24).getTime(), "Portugal", "Liscoa", "Lisboa", "Rua de cima", roleTypesCollaborator, colabResp, new ArrayList<>());

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CollaboratorsBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Collaborator registerCollaborator(final long mechanographicNumber, String shortName, String completeName, String email, String mobileNumber, Date birthDate,
            String country, String district, String county, String adress, Set<Role> role, Collaborator collaborator, List<Team> teams) throws IOException {
        final RegisterCollaboratorController controller = new RegisterCollaboratorController();
        try {
            return controller.registerCollaborator(mechanographicNumber, shortName, completeName, email, mobileNumber, birthDate, country, district, county, adress, role, collaborator, teams);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", mechanographicNumber);
            LOGGER.trace("Assuming existing record", ex);
        }
        return null;
    }

}
