package eapli.base.app.backoffice.console.presentation.teams;

import eapli.base.teammanagement.application.RegisterTeamTypeController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RegisterTeamTypeUI extends AbstractUI {

    private final RegisterTeamTypeController theController = new RegisterTeamTypeController();

    @Override
    protected boolean doShow() {
        final String code = Console.readLine("Code:");
        final String description = Console.readLine("Description:");
        final int red = Console.readInteger("Red color(0-255):");
        final int green = Console.readInteger("Green color(0-255):");
        final int blue = Console.readInteger("Blue color(0-255):");
        final String colorName = Console.readLine("Color name:");

        try {
            this.theController.registerTeamType(code,description,red,green,blue,colorName);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a team type which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Team Type";
    }
}
