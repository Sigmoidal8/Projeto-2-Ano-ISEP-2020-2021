/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.IllegalFormatException;
import java.util.Scanner;
import lapr.project.controller.RegisterDroneController;
import lapr.project.model.Drone;


public class RegisterDroneUI {
    static final Scanner ler = new Scanner(System.in);
    private final MainWindowUI main;
    private final RegisterDroneController ctrl;

    public RegisterDroneUI(MainWindowUI main) throws Exception {
        this.main = main;
        ctrl = main.getRegisterDroneController();
        menu();

    }

    private void menu() throws Exception {
        try {
            System.out.println("Insert the data needed for the registration of a Drone");
            int id = ctrl.generateID();

            System.out.println("Insert the capacity of the battery (Wh)");
            double fullCharge = Double.parseDouble(ler.nextLine());

            System.out.println("Insert the potency of the drone (W)");
            double power = Double.parseDouble(ler.nextLine());
            
            Drone d = ctrl.newDrone(id, fullCharge, power);
            ctrl.registerDrone(d);
            System.out.println("Drone Registered!");
        } catch (IllegalFormatException e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}

