package eapli.base.taskmanagement.domain;

import eapli.base.collaboratormanagement.domain.*;
import eapli.base.servicemanagement.domain.*;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class ManualTaskTest {
    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullForm() {
        System.out.println("Ensure ManualTask Cant Have Null Form");
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(new Label("label"), new Name("name"), Description.valueOf("description"), DataType.String, new RegularExpression("[A-Za-z]+")));
        Form form = new Form(new Name("form1"), new Script("src/test/resources/scripts/script1.txt"), attributes);
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        List<Collaborator> colabs = new ArrayList<>();
        colabs.add(colab);

        List<Team> teams = new ArrayList<>();
        teams.add(new Team(new UniqueCode("t1"),new Acronym("acr"), new Designation("designation"),
                new TeamType(new UniqueCode("code1"), Description.valueOf("description"),
                        new Color(255,255,255,"black")), colabs));

        ManualTask t = new ManualTask(TaskType.Realization, 0, null, teams);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullTeams() {
        System.out.println("Ensure ManualTask Cant Have Null Teams");
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(new Label("label"), new Name("name"), Description.valueOf("description"), DataType.String, new RegularExpression("[A-Za-z]+")));
        Form form = new Form(new Name("form1"), new Script("src/test/resources/scripts/script1.txt"), attributes);
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        List<Collaborator> colabs = new ArrayList<>();
        colabs.add(colab);

        List<Team> teams = new ArrayList<>();
        teams.add(new Team(new UniqueCode("t1"),new Acronym("acr"), new Designation("designation"),
                new TeamType(new UniqueCode("code1"), Description.valueOf("description"),
                        new Color(255,255,255,"black")), colabs));

        ManualTask t = new ManualTask(TaskType.Realization, 0, form, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskExecutionCantHaveNullCollaborators() {
        System.out.println("Ensure ManualTask Cant Have Null Form");
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(new Label("label"), new Name("name"), Description.valueOf("description"), DataType.String, new RegularExpression("[A-Za-z]+")));
        Form form = new Form(new Name("form1"), new Script("src/test/resources/scripts/script1.txt"), attributes);
        Collaborator colab = new Collaborator(new MecanographicNumber(234), ShortName.valueOf("shortName"), CompleteName.valueOf("completeName"), EmailAddress.valueOf("email@gmail.com"), MobileNumber.valueOf("99999999"), BirthDate.valueOf(new Date(10, 11, 2020)), new Address("Portugal", "Lisboa", "Almada", "Rua da esquina"),new ArrayList<Role>(), null);
        List<Collaborator> colabs = new ArrayList<>();
        colabs.add(colab);

        List<Team> teams = new ArrayList<>();
        teams.add(new Team(new UniqueCode("t1"),new Acronym("acr"), new Designation("designation"),
                new TeamType(new UniqueCode("code1"), Description.valueOf("description"),
                        new Color(255,255,255,"black")), colabs));

        ManualTask t = new ManualTask(TaskType.Realization, 0, null, form);
    }
}
