/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.RegisterClientController;


public class RegisterClientUI {

    private final RegisterClientController controller;

    public RegisterClientUI(MainWindowUI main) {
        controller = main.getRegisterClientController();
        start();
    }

    public void start() {
        try {
            Scanner sc = new Scanner(System.in);
            boolean registered = false;
            while (!registered) {
                System.out.println("Register a Client.\n");
                System.out.println("Enter the data:");
                System.out.println("1- Name:");
                String name = sc.nextLine();
                System.out.println("2- Password:");
                String password = sc.nextLine();
                System.out.println("3- Email:");
                String email = sc.nextLine();
                System.out.println("4- NIF:");
                String nif = sc.nextLine();
                System.out.println("Client adress data:");
                System.out.println("5- Street:");
                String street = sc.nextLine();
                System.out.println("6- Door Number:");
                int doorNumber = Integer.parseInt(sc.nextLine());
                System.out.println("7- Locality:");
                String locality = sc.nextLine();
                System.out.println("8- Postal Code:");
                String postalCode = sc.nextLine();
                System.out.println("9- Decimal Degree (latitude):");
                double decimalDegree1 = Double.parseDouble(sc.nextLine());
                System.out.println("10- Decimal Degree (longitude)");
                double decimalDegree2 = Double.parseDouble(sc.nextLine());

                boolean existClient = controller.existsEmail(email);
                if (!existClient) {
                    controller.newClient(name, password, email, nif, street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
                    System.out.printf("Client information:%n%s", controller.getClient().toString());
                    System.out.println("\nYou confirm that information?\n1-Yes\n2-No\n");
                    int choise = Integer.parseInt(sc.nextLine());
                    if (choise == 1) {
                        controller.registerClient();
                        controller.addClientToGraph();
                        registered = true;
                        System.out.println("Client registered sucefully");
                    } else {
                        System.out.println("Introduce all data again and change the incorrect data");
                    }
                } else {
                    System.out.println("Duplicate information. This Client already exists in the system");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            MainWindowUI.menu();
        }
    }
}
