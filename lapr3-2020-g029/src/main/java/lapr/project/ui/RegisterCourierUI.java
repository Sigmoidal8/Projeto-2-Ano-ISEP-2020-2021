/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.RegisterCourierController;


public class RegisterCourierUI {

    private final MainWindowUI main;
    private final RegisterCourierController controller;

    public RegisterCourierUI(MainWindowUI main) throws Exception {
        this.main = main;
        controller = main.getRegisterCourierController();
        start();
    }

    private void start() throws Exception {
        try {
            Scanner sc = new Scanner(System.in);
            boolean registered = false;
            while (!registered) {
                System.out.println("Register a courier.\n");
                System.out.println("Enter the data:");
                System.out.println("1- Name:");
                String name = sc.nextLine();
                System.out.println("2- NIF:");
                String nif = sc.nextLine();
                System.out.println("3- Social Security Number:");
                String ssc = sc.nextLine();
                System.out.println("4- Email:");
                String email = sc.nextLine();
                System.out.println("5- Password:");
                String pass = sc.nextLine();

                boolean existCourier = controller.existsEmail(email);
                if (!existCourier) {
                    controller.newCourier(name, nif, ssc, email, pass);
                    System.out.printf("Courier information:%n%s", controller.getCourier().toString());
                    System.out.println("%nYou confirm that information?\n1-Yes\n2-No\n");
                    int choise = Integer.parseInt(sc.nextLine());
                    if (choise == 1) {
                        controller.registerCourier();
                        registered = true;
                        System.out.println("Courier registered sucefully");
                    } else {
                        System.out.println("Introduce all data again and change the incorrect data");
                    }
                } else {
                    System.out.println("Duplicate information. This Courier already exists in the system");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}
