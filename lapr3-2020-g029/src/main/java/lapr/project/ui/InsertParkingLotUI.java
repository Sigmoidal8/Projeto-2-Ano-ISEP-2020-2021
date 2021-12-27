/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.AplicationPOT;
import lapr.project.controller.InsertParkingLotController;


public class InsertParkingLotUI {
    static final Scanner sc = new Scanner(System.in);

    private final MainWindowUI main;

    private final InsertParkingLotController controller;

    public InsertParkingLotUI(MainWindowUI main) throws Exception {
        this.main = main;
        controller = main.getInsertParkingLotController();
        start();
    }

    public void start() throws Exception {
        try {
            boolean registered = false;
            while (!registered) {
                System.out.println("Insert a parking lot");
                System.out.println("Enter the data:");
                boolean correctPharmacy = false;
                while (!correctPharmacy) {
                    System.out.println(AplicationPOT.getInstance().getPlatform().getPharmacyList().getPharmacyList());
                    System.out.println("1- Pharmacy ID");
                    String pharmacyID = sc.nextLine();
                    controller.getPharmacyFromID(pharmacyID);
                    System.out.println(controller.getPharmacy());
                    System.out.println("Is this the correct pharmacy?\n1-Yes\n2-No\n");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        correctPharmacy = true;
                    }
                }
                System.out.println("2- Parking lot total spots");
                int parkingLotSpots = Integer.parseInt(sc.nextLine());
                System.out.println("3- Parking lot charging spots");
                int parkingLotChargeSpots = Integer.parseInt(sc.nextLine());
                System.out.println("4- Parking lot type\n1-Scooter\n2-Drone\n");
                int choice2 = Integer.parseInt(sc.nextLine());
                String parkingLotType = "";
                if (choice2 == 1) {
                    parkingLotType = "Scooter";
                } else {
                    parkingLotType = "Drone";
                }
                controller.createParkingLot(parkingLotSpots, parkingLotChargeSpots, parkingLotType);
                System.out.println(controller.getParkingLot());
                System.out.println("You confirm that information?\n1-Yes\n2-No\n");
                int choice3 = Integer.parseInt(sc.nextLine());
                if (choice3 == 1) {
                    controller.registerParkingLot();
                    registered = true;
                    System.out.println("Parking lot registered successfully");
                } else {
                    System.out.println("Introduce all data again and change the incorrect data");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}
