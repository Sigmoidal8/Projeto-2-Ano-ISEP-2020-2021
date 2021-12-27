/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.RegisterProductController;
import lapr.project.data.PharmacyProductDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;


   public class RegisterProductUI {

    static final Scanner sc = new Scanner(System.in);
    private final MainWindowUI main;
    private final RegisterProductController controller;

    public RegisterProductUI(MainWindowUI main) throws Exception {
        this.main = main;
        controller = main.getRegisterProductController();
        start();
    }

    public void start() throws Exception {

        try {
            System.out.println("Register a product.\n");
            System.out.println("Enter the data:");
            System.out.println("1- Name");
            String name = sc.nextLine();
            System.out.println("2- Price");
            float price = Float.parseFloat(sc.nextLine());
            System.out.println("3- weight");
            float weight = Float.parseFloat(sc.nextLine());
            System.out.println("4- Stock");
            int stock = Integer.parseInt(sc.nextLine());
            
            List<Pharmacy> pl = controller.getPharmacyList().getPharmacyList();
            int aux = 0;
            System.out.println("Pharmacys available. Choose a number to select one.");
            for (Pharmacy phar : pl) {
                System.out.println(aux + " - " + phar.getDesignation());
                aux++;
            }
            int index = Integer.parseInt(sc.nextLine());
            String pharmacyID = pl.get(index).getId();
            Pharmacy p = controller.getPharmacyList().getPharmacy(pharmacyID);

            Product prod = controller.newProduct(name, price, weight, pharmacyID);
            controller.registerProduct(prod);
            new PharmacyProductDB().addPharmacyProduct(prod, p, stock);

        } catch (IllegalFormatException e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }
}
