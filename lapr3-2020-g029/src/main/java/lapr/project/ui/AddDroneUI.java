/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.AddDroneController;
import lapr.project.model.Drone;




public class AddDroneUI {
    
    static final Scanner ler = new Scanner(System.in);
    private final MainWindowUI main;
    private final AddDroneController ctrl;
    
    public AddDroneUI(MainWindowUI main) throws Exception {
        this.main = main;
        ctrl = main.getAddDroneController();
        menu();
    }
    
     public void menu() throws Exception {
        try {

            System.out.println("These are the Drones that have no Pharmacy associated.\n");
            System.out.println(ctrl.getDronesList().getPharmacyLessDrones());
            System.out.println("Insert the ID of the Drone you want to add in a Pharmacy.");
            int dID = Integer.parseInt(ler.nextLine());
            Drone d = ctrl.getDrone(dID);
            
            System.out.println("These are the Pharmacys registered in our system.\n");
            System.out.println(ctrl.getPharmacyList().getPharmacyList());
            System.out.println("Insert the ID of the Pharmacy where you want to add the drone.");
            String pharID = ler.nextLine();
            

            ctrl.setDronePharmacy(pharID,d);
                System.out.println("Drone Added!");
        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}
