/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.InsertPharmacyDataController;


public class InsertPharmacyDataUI {
    static final Scanner sc = new Scanner(System.in);
    private final MainWindowUI main;
    private final InsertPharmacyDataController controller;

    public InsertPharmacyDataUI(MainWindowUI main) throws Exception {
        this.main = main;
        controller = main.getGetInsertPharmacyDataController();
        start();
    }
    
    public void start() throws Exception {
        try {
            boolean registered = false;
            boolean continueInserting=false;
            while(!registered) {
                System.out.println("Register a pharmacy.\n");
                System.out.println("Enter the data:");
                System.out.println("1- Designation");
                String designation = sc.nextLine();
                System.out.println("2- Email");
                String email = sc.nextLine();
                System.out.println("3- Password");
                String password = sc.nextLine();
                System.out.println("Pharmacy adress data:");
                System.out.println("4- Door number");
                int doorNumber = Integer.parseInt(sc.nextLine());
                System.out.println("5- Street");
                String street = sc.nextLine();
                System.out.println("6- Locality");
                String locality = sc.nextLine();
                System.out.println("7- Postal Code");
                String postalCode = sc.nextLine();
                System.out.println("8- Decimal Degree (latitude)");
                Double decimalDegree1 = Double.parseDouble(sc.nextLine());
                System.out.println("9- Decimal Degree (longitude)");
                Double decimalDegree2 = Double.parseDouble(sc.nextLine());
                controller.createAdress(doorNumber, street, locality, postalCode, decimalDegree1, decimalDegree2);
                controller.createPharmacy(designation,email,password);
                System.out.println(controller.getPharmacy().toString());
                System.out.println("You confirm that information?\n1-Yes\n2-No\n");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    controller.registerPharmacy();
                    controller.addPharmacyToGraph();
                    registered = true;
                    System.out.println("Pharmacy registered successfully");
                    continueInserting = true;
                } else {
                    System.out.println("Introduce all data again and change the incorrect data");
                    continueInserting = false;
                }
                
                while (continueInserting) {
                    System.out.println("10- Parking lot total spots");
                    int parkingLotSpots = Integer.parseInt(sc.nextLine());
                    System.out.println("11- Parking lot charging spots");
                    int parkingLotChargeSpots = Integer.parseInt(sc.nextLine());
                    System.out.println("12- Parking lot type");
                    System.out.println("1- Scooter");
                    System.out.println("2- Drone");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    String parkingLotType = "";
                    if(choice2 == 1) {
                        parkingLotType = "scooter";
                    } else {
                        parkingLotType = "drone";
                    }
                    controller.createParkingLot(parkingLotSpots, parkingLotChargeSpots,parkingLotType);
                    controller.getPharmacy().registerParkingLot(controller.getParkingLot());
                    System.out.println("Parking Lot Added");
                    System.out.println("Do you want to insert another parkingLot?");
                    System.out.println("1- Yes");
                    System.out.println("2- No");
                    int choice3 = Integer.parseInt(sc.nextLine());
                    if(choice3 != 1 ) {
                        continueInserting = false;
                        registered=true;
                    }
                }
            }
            

        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
    
}
