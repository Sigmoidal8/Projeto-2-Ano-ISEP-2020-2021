package eapli.base.catalogmanagement.domain;

import eapli.base.collaboratormanagement.domain.Address;
import eapli.base.collaboratormanagement.domain.BirthDate;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.CompleteName;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.domain.MobileNumber;
import eapli.base.collaboratormanagement.domain.ShortName;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class CatalogTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureCatalogCantHaveNullTitle() {
        System.out.println("Ensure Catalog Cant Have Null Title");
        Catalog instance = new Catalog(Identifier.valueOf(1L), Description.valueOf("briefDescription"), Description.valueOf("completeDescription"),null,
                Icone.valueOf("icon"), new Criteria(new ArrayList<>()), new Collaborator(new MecanographicNumber(234),new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10,11,2020)), new Address("Portugal","Lisboa","Almada","Rua da esquina"), new ArrayList<Role>(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCatalogCantHaveNullBriefDescription() {
        System.out.println("Ensure Catalog Cant Have Null Brief Description");
        Catalog instance = new Catalog(Identifier.valueOf(1L), null, Description.valueOf("completeDescription"),Title.valueOf("title"),
                Icone.valueOf("icon"), new Criteria(new ArrayList<>()), new Collaborator(new MecanographicNumber(234),new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10,11,2020)), new Address("Portugal","Lisboa","Almada","Rua da esquina"), new ArrayList<Role>(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCatalogCantHaveNullCompleteDescription() {
        System.out.println("Ensure Catalog Cant Have Null Complete Description");
        Catalog instance = new Catalog(Identifier.valueOf(1L), Description.valueOf("briefDescription"), null,Title.valueOf("title"),
                Icone.valueOf("icon"), new Criteria(new ArrayList<>()), new Collaborator(new MecanographicNumber(234),new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10,11,2020)), new Address("Portugal","Lisboa","Almada","Rua da esquina"), new ArrayList<Role>(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCatalogCantHaveNullIcon() {
        System.out.println("Ensure Catalog Cant Have Null Icon");
        Catalog instance = new Catalog(Identifier.valueOf(1L), Description.valueOf("briefDescription"), Description.valueOf("completeDescription"),Title.valueOf("title"),
               null, new Criteria(new ArrayList<>()),new Collaborator(new MecanographicNumber(234),new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10,11,2020)), new Address("Portugal","Lisboa","Almada","Rua da esquina"), new ArrayList<Role>(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCatalogCantHaveBriefDescriptionOversize(){
        System.out.println("Ensure Catalog Cant Have briefDescription oversized");
        Catalog instance = new Catalog(Identifier.valueOf(1L), Description.valueOf("briefDescription------------------------"), Description.valueOf("completeDescription"),Title.valueOf("title"),
                null, new Criteria(new ArrayList<>()), new Collaborator(new MecanographicNumber(234),new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10,11,2020)), new Address("Portugal","Lisboa","Almada","Rua da esquina"), new ArrayList<Role>(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCatalogCantHaveCompleteDescriptionOversize(){
        System.out.println("Ensure Catalog Cant Have completeDescription oversized");
        Catalog instance = new Catalog(Identifier.valueOf(1L), Description.valueOf("briefDescription"), Description.valueOf("completeDescription------------------------------------------------------------------------------------"),Title.valueOf("title"),
                null, new Criteria(new ArrayList<>()), new Collaborator(new MecanographicNumber(234),new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10,11,2020)), new Address("Portugal","Lisboa","Almada","Rua da esquina"), new ArrayList<Role>(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCriteriaHasNoNullFields(){
        System.out.println("Ensure Criteria cannot have null field");
        Criteria instance = new Criteria(null);
    }

}
