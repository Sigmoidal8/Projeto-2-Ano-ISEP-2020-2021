/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.RemoveDroneController;


public class RemoveDroneUI {
    
    static final Scanner ler = new Scanner(System.in);
    private final MainWindowUI main;
    private final RemoveDroneController ctrl;
    
    public RemoveDroneUI(MainWindowUI main) throws Exception {
        this.main = main;
        ctrl = main.getRemoveDroneController();
        menu();
    }
    
    private void menu() throws Exception {
        try {
            System.out.println("These are the Pharmacys registered in our system.\n");
            System.out.println(ctrl.getPharmacyList().getPharmacyList());
            System.out.println("Insert the ID of the Pharmacy where you want to remove the drone.");
            String pharID = ler.nextLine();
           
            
            System.out.println("These are the Scooters that belong to the Pharmacy choosen.\n");
            System.out.println(ctrl.getDronesPharmacy(pharID).getDroneList());
            System.out.println("Insert the ID of the Drone you want to remove.");
            int dID = Integer.parseInt(ler.nextLine());
            

            ctrl.removeDronePharmacy(dID, ctrl.getDronesPharmacy(pharID));         
                System.out.println("Drone Removed!");
           

        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}
