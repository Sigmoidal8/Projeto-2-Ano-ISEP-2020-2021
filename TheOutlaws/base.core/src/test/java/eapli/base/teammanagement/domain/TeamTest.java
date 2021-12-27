/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.domain;


import eapli.base.collaboratormanagement.domain.Address;
import eapli.base.collaboratormanagement.domain.BirthDate;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.CompleteName;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.domain.MobileNumber;
import eapli.base.collaboratormanagement.domain.ShortName;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.base.teammanagement.domain.UniqueCode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Forno
 */
public class TeamTest {
    
    private final Color color = new Color(255,255,255,"black");
    private final Collaborator colab = new Collaborator(new MecanographicNumber(234), new ShortName("shortName"), new CompleteName("completeName"), EmailAddress.valueOf("email@gmail.com"), new MobileNumber("99999999"), new BirthDate(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
    
   @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullUniqueCode() {
        System.out.println("Ensure Team  Cant Have Null Unique Code");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        Team instance = new Team(null, new Acronym("acronym"), new Designation("designation"), new TeamType(new UniqueCode("code"), Description.valueOf("description"),color),colabList);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullAcronym() {
        System.out.println("Ensure Team  Cant Have Null Unique Code");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        Team instance = new Team(new UniqueCode("Code"), null,new Designation ("designation"), new TeamType(new UniqueCode("code"), Description.valueOf("description"),color),colabList);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveNullDesignation() {
        System.out.println("Ensure Team  Cant Have Null Unique Code");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        Team instance = new Team(new UniqueCode("Code"),new Acronym("acronym"), null, new TeamType(new UniqueCode("code"), Description.valueOf("description"),color),colabList);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamCantHaveUniqueCodeOversize() {
        System.out.println("Ensure Team  Cant Have Null Unique Code");
        List<Collaborator> colabList = new ArrayList<>();
        colabList.add(colab);
        Team instance = new Team(new UniqueCode("Code----------------"),new Acronym ("acronym"), new Designation("designation"), new TeamType(new UniqueCode("code"), Description.valueOf("description"),color),colabList);
    }


}
