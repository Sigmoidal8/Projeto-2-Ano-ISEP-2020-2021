/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

/**
 *
 * @author migue
 */
public class CollaboratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNullMecanographicNumber() {
        System.out.println("Ensure Collaborator Cant Have Null MecanographicNumber");
        Collaborator colab = new Collaborator(null, new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNullShortName() {
        System.out.println("Ensure Collaborator Cant Have Null shortName");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), null, new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNullcompleteName() {
        System.out.println("Ensure Collaborator Cant Have Null completeName");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), null, EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNullEmail() {
        System.out.println("Ensure Collaborator Cant Have Null Email");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName"), null, new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNullNumber() {
        System.out.println("Ensure Collaborator Cant Have Null number");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), null, new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNullbirthDate() {
        System.out.println("Ensure Collaborator Cant Have Null birthDate");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), null, new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveNulladdress() {
        System.out.println("Ensure Collaborator Cant Have Null address");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), null,new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHaveshortnameOversize() {
        System.out.println("Ensure Collaborator Cant Have oversize shortname");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName----------------------------"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), null,new ArrayList<Role>(), null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCollaboratorCantHavecompleteNameOversize() {
        System.out.println("Ensure Collaborator Cant Have oversize shortname");
        Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName---------------------------------------------------------------------------------"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), null,new ArrayList<Role>(), null);
    }
}
