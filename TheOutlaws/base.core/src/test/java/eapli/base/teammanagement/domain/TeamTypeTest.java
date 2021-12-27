package eapli.base.teammanagement.domain;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.Criteria;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.Test;

import java.util.ArrayList;

public class TeamTypeTest {

    private final Color color = new Color(255,255,255,"black");

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveNullCode() {
        System.out.println("Ensure Team Type Cant Have Null Code");
        TeamType instance = new TeamType(null, Description.valueOf("descricao"), color);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveNullDescription() {
        System.out.println("Ensure Team Type Cant Have Null Descritpion");
        TeamType instance = new TeamType(new UniqueCode("code"), null, color);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveRedOver255() {
        System.out.println("Ensure Team Type Cant Have Red Over 255");
        TeamType instance = new TeamType(new UniqueCode("code"), Description.valueOf("description"), new Color(265, 255,255,"colorName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveGreenOver255() {
        System.out.println("Ensure Team Type Cant Have Red Over 255");
        TeamType instance = new TeamType(new UniqueCode("code"), Description.valueOf("description"), new Color(255, 265,255,"colorName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveBluedOver255() {
        System.out.println("Ensure Team Type Cant Have Red Over 255");
        TeamType instance = new TeamType(new UniqueCode("code"), Description.valueOf("description"), new Color(255, 255,265,"colorName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveRedLower0() {
        System.out.println("Ensure Team Type Cant Have Red under 0");
        TeamType instance = new TeamType(new UniqueCode("code"), Description.valueOf("description"), new Color(-1, 255,255,"colorName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveGreenLower0() {
        System.out.println("Ensure Team Type Cant Have Green under 0");
        TeamType instance = new TeamType(new UniqueCode("code"), Description.valueOf("description"), new Color(255, -1,255,"colorName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeamTypeCantHaveBlueLower0() {
        System.out.println("Ensure Team Type Cant Have Blue under 0");
        TeamType instance = new TeamType(new UniqueCode("code"), Description.valueOf("description"), new Color(255, 255,-1,"colorName"));
    }
}
