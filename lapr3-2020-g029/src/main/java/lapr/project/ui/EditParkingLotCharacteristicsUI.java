/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.List;
import java.util.Scanner;
import lapr.project.controller.EditParkingLotCharacteristicsController;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;


public class EditParkingLotCharacteristicsUI {

    static final Scanner opcao = new Scanner(System.in);
    private final EditParkingLotCharacteristicsController controller;

    public EditParkingLotCharacteristicsUI(MainWindowUI main) {
        controller = main.getEditParkingLotCharacteristicsController();
        menu();
    }

    public void menu() {

        List<Pharmacy> pl = controller.getPharmacyList().getPharmacyList();
        int aux = 0;
        System.out.println("Select the number of a Pharmacy");
        for (Pharmacy aaa : pl) {
            System.out.println(aux + " - " + aaa.getDesignation());
            aux++;
        }
        int comando = Integer.parseInt(opcao.nextLine());
        String pharmacyID = pl.get(comando).getId();
        Pharmacy p = controller.getPharmacy(pharmacyID);

        List<ParkingLot> prklL = controller.getParkingLotList();
        
        int aux2=0;
        System.out.println("Select the number of the parking lot");
        for(ParkingLot prk : prklL){
            System.out.println(aux2+" - "+prk.getType()+" - "+prk.getId());
        }
        
        int cmd2 = Integer.parseInt(opcao.nextLine());
        String parkID = prklL.get(cmd2).getId();
        
        ParkingLot prkl = controller.getParkingLot(parkID);
        
        
        

        System.out.println("Parking lot");
        System.out.println("Total Spots   " + prkl.getTotalSpots() + "\nCharge Spots  " + prkl.getChargeSpots() +"  Type "+prkl.getType());

        System.out.println("\nSelect the atribute to change");
        System.out.println("1- Total Spots");
        System.out.println("2- Charge Spots");
        System.out.println("0- Exit");

        int comando2 = Integer.parseInt(opcao.nextLine());

        if (comando2 != 1 || comando2 != 2) {
            System.out.println("\nInsert the new value");
        }

        int comando3 = Integer.parseInt(opcao.nextLine());

        if (comando2 == 1) {
            prkl=controller.setTotalSpots(comando3);
            
            System.out.println("O novo valor é: "+prkl.getTotalSpots());
            
        }else{
            if(comando2 == 2){
                prkl=controller.setChargeSpots(comando3);
                
                System.out.println("O novo valor é: "+prkl.getChargeSpots());
            }
        }

    }

}
