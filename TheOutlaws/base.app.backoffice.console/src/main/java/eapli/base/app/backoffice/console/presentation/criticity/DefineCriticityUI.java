package eapli.base.app.backoffice.console.presentation.criticity;

import eapli.base.criticitymanagement.aplication.DefineCriticityController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.time.LocalTime;

public class DefineCriticityUI extends AbstractUI {

    private final DefineCriticityController theController = new DefineCriticityController();

    @Override
    protected boolean doShow() {
        final String label = Console.readLine("Label:");
        final Integer value = Console.readInteger("Value:");
        final int red = Console.readInteger("Red color(0-255):");
        final int green = Console.readInteger("Green color(0-255):");
        final int blue = Console.readInteger("Blue color(0-255):");
        final String colorName = Console.readLine("Color name:");

        String auxString = Console.readLine("Average Approval Time (HH:MM:SS):");
        String[] auxStringArray = auxString.split(":");
        int[] time = timeConversion(auxStringArray);
        if(time == null){
            System.out.println("Aborting...");
            return false;
        }
        final LocalTime averageApprovalTime = LocalTime.of(time[0],time[1],time[2]);

        auxString = Console.readLine("Max Approval Time (HH:MM:SS):");
        auxStringArray = auxString.split(":");
        time = timeConversion(auxStringArray);
        if(time == null){
            System.out.println("Aborting...");
            return false;
        }
        final LocalTime maxApprovalTime = LocalTime.of(time[0],time[1],time[2]);

        auxString = Console.readLine("Average Resolution Time (HH:MM:SS):");
        auxStringArray = auxString.split(":");
        time = timeConversion(auxStringArray);
        if(time == null){
            System.out.println("Aborting...");
            return false;
        }
        final LocalTime averageResolutionTime = LocalTime.of(time[0], time[1], time[2]);

        auxString = Console.readLine("Max Resolution Time (HH:MM:SS):");
        auxStringArray = auxString.split(":");
        time = timeConversion(auxStringArray);
        if(time == null){
            System.out.println("Aborting...");
            return false;
        }
        final LocalTime maxResolutionTime = LocalTime.of(time[0], time[1], time[2]);


        try {
            this.theController.registerCriticity(label, value, red, green, blue, colorName, averageApprovalTime, maxApprovalTime, averageResolutionTime, maxResolutionTime);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a criticity which already exists in the database.");
        }
        return false;
    }

    public int[] timeConversion(String[] time){
        int[] averageApproval = {0,0,0};
        try {
            for (int i = 0; i < 3; i++) {
                averageApproval[i] = Integer.parseInt(time[i]);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You didn't insert the right format");
            return null;
        }
        return averageApproval;
    }

    @Override
    public String headline() {
        return "Define Criticity Level";
    }
}
