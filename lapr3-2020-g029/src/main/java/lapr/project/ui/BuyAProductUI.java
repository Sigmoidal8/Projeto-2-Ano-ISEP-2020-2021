/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.mail.MessagingException;
import lapr.project.controller.AplicationPOT;
import lapr.project.controller.BuyAProductController;
import lapr.project.data.InvoiceDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.utils.MailUtil;

public class BuyAProductUI {

    static final Scanner opcao = new Scanner(System.in);
    private final BuyAProductController controller;

    public BuyAProductUI(MainWindowUI main) throws IOException, MessagingException {
        controller = main.getBuyAProductController();
        start();
    }

    public void start() throws IOException, MessagingException {
        List<Pharmacy> pl = controller.getPharmacyList().getPharmacyList();
        Platform plat=AplicationPOT.getInstance().getPlatform();
        
        System.out.println("This is the recommended pharmacy for you.");
        
        Client client = controller.getClient();
        Address clientAdress = client.getAdress();
        Pharmacy p =controller.fowardDeliveryToClosestPharmacy(pl, clientAdress);
        String pharmacyID = p.getId();
        System.out.println(p);
        System.out.println("Is this the pharmacy you want to buy from?\n      1 = Yes | 2 = No");
        int confirmation = Integer.parseInt(opcao.nextLine());
        
        if(confirmation == 2){
            int aux = 0;
            System.out.println("Select the number corresponding to the pharmacy");
            for (Pharmacy aaa : pl) {
                System.out.println(aux + " - " + aaa.getDesignation());
                 aux++;
             }
            int comando = Integer.parseInt(opcao.nextLine());
            pharmacyID = pl.get(comando).getId();
            p = controller.getPharmacy(pharmacyID);
        }
        
        String menu;
        Map<Product, Integer> mp = new HashMap<>();
        do {
            System.out.println("1- select a product");
            System.out.println("exit- finish buying process\n");
            System.out.println("Introduce the option");
            menu = opcao.nextLine();
            switch (menu) {

                case "1":
                    List<Product> prl = controller.getPharmacy(pharmacyID).getProductList().getProductList();
                    int aux2 = 0;
                    System.out.println("Select the number corresponding to the product");
                    System.out.println("-1 :Exit");
                    for (Product aa : prl) {
                        System.out.println(aux2 + " - " + aa.getNome() + " " + aa.getPrice() + " € ");
                        aux2++;
                    }
                    int comando2 = Integer.parseInt(opcao.nextLine());
                    String productID = prl.get(comando2).getID();

                    Product pr = controller.getProduct(productID);
                    int st = controller.getStock(pr.getID(), p.getId());

                    System.out.println("\n" + pr.getNome() + " (stock: " + st + ")");
                    System.out.println("Introduce the quantity");

                    int quantity = Integer.parseInt(opcao.nextLine());

                    if (quantity <= st) {
                        mp = controller.preencherMapa(pr, quantity);

                        controller.updateStock(p.getId(), pr.getID(), st, quantity);

                    } else {
                        int numberOfProductsTransfered = quantity - st;
                        Pharmacy closestPharmacy = controller.closestPharmacyWithStock(plat.getPharmacyList().getPharmacyList(), p, pr, numberOfProductsTransfered);
                        if (closestPharmacy != null) {
                            int st2 = controller.getStock(pr.getID(), closestPharmacy.getId());
                            if (st2 < numberOfProductsTransfered) {
                                System.out.println("This product is not available in this pharmacy, neither in a pharmacy close enough to get it in one of our scooters!");
                            } else {
                                controller.updateStock(p.getId(), pr.getID(), st, st);
                                controller.updateStock(closestPharmacy.getId(), pr.getID(), st2, numberOfProductsTransfered);//pharmacy that will lend the product
                                MailUtil.sendEmail(closestPharmacy.getEmail(),
                                        "This pharmacy will be sending a total of" + numberOfProductsTransfered + " " + pr.getNome() + ". The receiving pharmacy ID:" + p.getId(),
                                        "Transfer Note",
                                        p);
                                MailUtil.sendEmail(p.getEmail(),
                                        "This pharmacy will be receiving a total of" + numberOfProductsTransfered + " " + pr.getNome() + ". The providing pharmacy ID: " + closestPharmacy.getId(),
                                        "Delivery Note",
                                        closestPharmacy);
                            }
                        } else {
                            System.out.println("Currently, there is no scooter with enough charge to make this trip.");
                        }
                    }

                    break;
                case "-1":
                    break;

                case "EXIT":
                    break;

                default:

                    System.out.println();
                    System.out.println();
                    System.out.println("Command not found!");
                    break;

            }

        } while (!"exit".equalsIgnoreCase(menu) && !"-1".equals(menu));

        Client c = controller.getClient();

        Invoice inv = controller.newInvoice(mp, c);

        for (Product prrr : mp.keySet()) {
            System.out.println("\n" + prrr.getNome() + " quantity: " + mp.get(prrr));

        }
        System.out.println("\n Total Price: " + inv.getPrice());

        System.out.println("Do you want to pay the taxes with credits?");
        System.out.println("1- Yes");
        System.out.println("2- No");

        int cred = Integer.parseInt(opcao.nextLine());

        if (cred == 1) {
            double vCred = controller.verifyCredits();
            if (vCred > 0) {

                double iPrice = inv.getPrice();
                inv.setPrice(iPrice - controller.TAX);
                controller.addCredits(vCred);

            } else {
                System.out.println("Not enough credits");
            }
        }

        System.out.println("\n Insert the following credit card information: \n     Number/Validity Date/CCV   (f.e 11111111/12:05:2024/1234)"); //atualmente não serve para nada

        String ccInfo = opcao.nextLine();

        //meter na base de dados a invoice
        new InvoiceDB().addInvoice(inv);

        String invID = inv.getId();
        for (Product prod : mp.keySet()) {
            String prodID = prod.getID();
            new InvoiceDB().addInvoiceProduct(invID, prodID, mp.get(prod));

        }

        double credits = controller.calcCredits(inv);

        controller.addCredits(credits);

        controller.newDelivery(inv, p);

        controller.registerDelivery();

        System.out.println("\nPurchase successful");

        //codigo que chama o envio do email
        try {
            controller.sendEmail(inv, p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
