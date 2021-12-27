/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RemovePharmacyController;
import lapr.project.model.Pharmacy;


public class RemovePharmacyUI {

    private final RemovePharmacyController controller;
    private static final String SEPARATOR = ("=======================================================");

    public RemovePharmacyUI(MainWindowUI main) {
        controller = main.getRemovePharmacyController();
        start();
    }

    public void start() {
        try {
            System.out.println(SEPARATOR);
            System.out.println("\t\tRemove a Pharmacy:");
            System.out.println(SEPARATOR);

            Scanner sc = new Scanner(System.in);
            boolean removed = false;
            while (!removed) {
                //escolhe uma farmacia.
                List<Pharmacy> pl = controller.getPharmacyList();
                System.out.println("\nPharmacys available. Choose a number to select one.");
                System.out.println(SEPARATOR);
                int aux = 1;
                if (!pl.isEmpty()) {
                    for (Pharmacy phar : pl) {
                        System.out.println(aux + " - " + phar.getDesignation());
                        aux++;
                    }
                    int index = Integer.parseInt(sc.nextLine());
                    removed = controller.removePharmacy(pl.get(index - 1).getId());
                    if (removed) {
                        System.out.println("Pharmacy removed sucefully");
                    } else {
                        System.out.println("Something went wrong. Pharmacy don´t be removed!!");
                    }
                } else {
                    System.out.println("The List of Pharmacies is empty!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in the introduction of data");
            MainWindowUI.menu();
        }
    }
}
