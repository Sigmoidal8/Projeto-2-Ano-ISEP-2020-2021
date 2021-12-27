/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.AddScooterController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;


public class AddScooterUI {

    static final Scanner ler = new Scanner(System.in);
    private final MainWindowUI main;
    private final AddScooterController ctrl;

    public AddScooterUI(MainWindowUI main) throws Exception {
        this.main = main;
        ctrl = main.getGetAddScooterController();
        menu();
    }

    public void menu() throws Exception {
        try {

            System.out.println("These are the Scooters that have no Pharmacy associated.\n");
            System.out.println(ctrl.getScooterList().getPharmacyLessScooters());
            System.out.println("Insert the ID of the Scooter you want to add.");
            int scotID = Integer.parseInt(ler.nextLine());
            Scooter scot = ctrl.getScooter(scotID);
            
            System.out.println("These are the Pharmacys registered in our system.\n");
            System.out.println(ctrl.getPharmacyList().getPharmacyList());
            System.out.println("Insert the ID of the Pharmacy where you want to add the scooter.");
            String pharID = ler.nextLine();
            Pharmacy phar = ctrl.getPharmacy(pharID);

            boolean bol = ctrl.addAvailableScooterList(scot, phar);
            if (bol) {
                System.out.println("Scooter Added!");
            }

        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }

}
