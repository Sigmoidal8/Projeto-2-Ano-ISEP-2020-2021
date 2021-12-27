/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.RemoveScooterController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;


public class RemoveScooterUI {

    static final Scanner ler = new Scanner(System.in);
    private final MainWindowUI main;
    private final RemoveScooterController ctrl;

    public RemoveScooterUI(MainWindowUI main) throws Exception {
        this.main = main;
        ctrl = main.getGetRemoveScooterController();
        menu();
    }

    private void menu() throws Exception {
        try {
            System.out.println("These are the Pharmacys registered in our system.\n");
            System.out.println(ctrl.getPharmacyList().getPharmacyList());
            System.out.println("Insert the ID of the Pharmacy where you want to remove the scooter.");
            String pharID = ler.nextLine();
            Pharmacy phar = ctrl.getPharmacy(pharID);
            
            System.out.println("These are the Scooters that belong to the Pharmacy choosen.\n");
            System.out.println(ctrl.getAvailableScooterList(phar));
            System.out.println("Insert the ID of the Scooter you want to remove.");
            int scotID = Integer.parseInt(ler.nextLine());
            Scooter scot = ctrl.getScooter(scotID,phar);

            boolean bol = ctrl.removeScooterFromAvailableScooterList(scot, phar);
            if (bol) {
                System.out.println("Scooter Removed!");
            }

        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }

}
