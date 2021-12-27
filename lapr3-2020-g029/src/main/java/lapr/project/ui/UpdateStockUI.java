/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.List;
import java.util.Scanner;
import lapr.project.controller.UpdateStockController;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;


public class UpdateStockUI {

    static final Scanner opcao = new Scanner(System.in);
    private final UpdateStockController controller;

    public UpdateStockUI(MainWindowUI main) {
        controller = main.getUpdateStockController();
        menu();
    }

    public void menu() {

        List<Pharmacy> plist = controller.getPharmacyList().getPharmacyList();
        int auxiliar = 0;
        System.out.println("Select the number of a Pharmacy");
        for (Pharmacy aaa : plist) {
            System.out.println(auxiliar + " - " + aaa.getDesignation());
            auxiliar++;
        }
        int index = Integer.parseInt(opcao.nextLine());
        String pharmacyID = plist.get(index).getId();
        Pharmacy p = controller.getPharmacy(pharmacyID);

        List<Product> prl = controller.getPharmacy(pharmacyID).getProductList().getProductList();
        int aux2 = 0;
        System.out.println("Select the number of a Product");
        for (Product aa : prl) {
            System.out.println(aux2 + " - " + aa.getNome());
            aux2++;
        }
        int comando2 = Integer.parseInt(opcao.nextLine());
        String productID = prl.get(comando2).getID();

        Product pr = controller.getProduct(productID);
        int st = controller.getStock(pr.getID(), p.getId());
        System.out.println(pr.getNome() + " stock:" + st);
        System.out.println("Insert the new stock value");

        int comando3 = Integer.parseInt(opcao.nextLine());

        controller.setStock(comando3, pr.getID(), p.getId());

        System.out.println("Stock value is now " + comando3 + ".");
    }
}
