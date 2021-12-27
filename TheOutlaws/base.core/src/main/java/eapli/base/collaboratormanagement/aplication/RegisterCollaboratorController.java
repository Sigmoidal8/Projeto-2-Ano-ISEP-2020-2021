/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.aplication;

import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.domain.Address;
import eapli.base.collaboratormanagement.domain.BirthDate;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.CompleteName;
import eapli.base.collaboratormanagement.domain.MobileNumber;
import eapli.base.collaboratormanagement.domain.ScriptPassword;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import eapli.base.collaboratormanagement.domain.ShortName;
import eapli.framework.general.domain.model.EmailAddress;
import java.io.IOException;

/**
 *
 * @author miguel
 */
public class RegisterCollaboratorController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    private final AddUserController userController = new AddUserController();

    public Collaborator registerCollaborator(final long mechanographicNumber, String shortName, String completeName, String email, String mobileNumber,
            Date birthDate, String country, String district, String county, String adress, Set<Role> role, Collaborator collaborator, List<Team> teams) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER);

        //RandomRawPassword pass = new RandomRawPassword();
        String pass = ScriptPassword.generateStrongPassword();
        String shortNameCopy = shortName;
        String[] names = shortName.split(" ");
        userController.addUser(email, pass, names[0], names[1], email, role);

        Address address = new Address(country, district, county, adress);
        
        Collaborator newCollaborator = new Collaborator(MecanographicNumber.valueOf(mechanographicNumber), ShortName.valueOf(shortName), CompleteName.valueOf(completeName), 
                EmailAddress.valueOf(email), MobileNumber.valueOf(mobileNumber), BirthDate.valueOf(birthDate), address, new ArrayList<Role>(role), collaborator);

        Collaborator c = collaboratorRepository.save(newCollaborator);

        if (teams != null) {
            for (Team t : teams) {
                t.collaborators().add(c);
                teamRepository.save(t);
            }
        }

        SendEmailService emailHandler = new SendEmailService();
        emailHandler.sendEmail(email, "Registo de Colaborador", "Obrigado por se ter registado no grupo ArmisGroup. O seu registo foi concluído com sucesso para poder usufruir"
                + "do seu registo por favor entre no nosso site. A sua password de acesso é: " + pass);

        return c;
    }

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return a list of RoleTypes
     */
    public Role[] getRoleTypes() {
        return BaseRoles.collaboratorValues();
    }

    public Iterable<Collaborator> collaborators() {
        return collaboratorRepository.findAll();
    }

    public Iterable<Team> teams() {
        return teamRepository.findAll();
    }

}
