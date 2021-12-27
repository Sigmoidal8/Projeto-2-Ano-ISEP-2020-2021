/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.File;
import java.util.IllegalFormatException;
import java.util.Scanner;
import lapr.project.controller.RegisterScooterController;
import lapr.project.model.Scooter;


public class RegisterScooterUI {

    static final Scanner ler = new Scanner(System.in);
    private final MainWindowUI main;
    private final RegisterScooterController ctrl;

    public RegisterScooterUI(MainWindowUI main) throws Exception {
        this.main = main;
        ctrl = main.getGetRegisterScooterController();
        menu();

    }

    private void menu() throws Exception {
        try {
            System.out.println("Insert the data needed for the registration of a Scooter");
            int exclusiveNumber = ctrl.generateExclusiveNumber();
            File image = ctrl.generateQRCode(exclusiveNumber);

            System.out.println("Insert the capacity of the battery (W/h)");
            double fullCharge = Double.parseDouble(ler.nextLine());

            System.out.println("Insert the potency of the scooter (W)");
            double power = Double.parseDouble(ler.nextLine());
            
            System.out.println("Is the Scooter operational at the moment?(0 if not, 1 if yes)");
            int operational = Integer.parseInt(ler.nextLine());
            Scooter s = ctrl.newScooter(exclusiveNumber, fullCharge, power, operational, image);
            ctrl.registerScooter(s);
        } catch (IllegalFormatException e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}
