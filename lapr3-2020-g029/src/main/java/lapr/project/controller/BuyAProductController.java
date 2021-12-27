/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import lapr.project.model.Address;
import lapr.project.ui.autorization.UserSession;
import lapr.project.model.Client;
import lapr.project.model.ClientRegister;
import lapr.project.model.Delivery;
import lapr.project.model.DeliveryList;
import lapr.project.model.Invoice;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ProductList;
import lapr.project.utils.MailUtil;


public class BuyAProductController {

    /**
     * Platform of the aplication
     */
    private final Platform plat;

    /**
     * Pharmacy selling the product
     */
    private Pharmacy p;

    /**
     * PharmacyList of the Platform
     */
    private PharmacyList pl;

    /**
     * Client buying the product
     */
    private Client c;

    /**
     * The list of deliverys
     */
    private DeliveryList delList;

    /**
     * Delivery of a product
     */
    private Delivery del;

    /**
     * The tax you need to pay when buying a product
     */
    public static final double TAX = 5;

    /**
     * A HashMap with the quantity of each product
     */
    final HashMap<Product, Integer> mp = new HashMap<>();

    /**
     * Creates an instance of BuyAProductController
     */
    public BuyAProductController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
    }

    /**
     * Returns the PharmacyList registered in the Platform
     *
     * @return PharmacyList
     */
    public PharmacyList getPharmacyList() {
        pl = plat.getPharmacyList();
        return pl;
    }

    /**
     * Returns a Pharmacy with the same id passed by parameter
     *
     * @param pharmacyID the pharmacy's id
     * @return Pharmacy
     */
    public Pharmacy getPharmacy(String pharmacyID) {
        p = pl.getPharmacy(pharmacyID);
        return p;
    }

    /**
     * Returns a Product with the same id passed by parameter
     *
     * @param productID the product's id
     * @return Product
     */
    public Product getProduct(String productID) {
        ProductList prl = p.getProductList();
        return prl.getProduct(productID);
    }

    /**
     * Returns the quantity of products in a specific pharmacy with the same id
     * product passed by parameter
     *
     * @param productID the product's id
     * @param pharmacyID the pharmacy's id
     *
     * @return quantity of stock
     */
    public int getStock(String idProduct, String idPharmacy) {
        return plat.getStdb().getStock(idPharmacy, idProduct);
    }

    /**
     * Returns the client that is currently logged in
     *
     * @return Client
     */
    public Client getClient() {
        AplicationPOT app = AplicationPOT.getInstance();
        UserSession session = app.getAtualSession();
        String email = session.getUserEmail();

        ClientRegister rcl = plat.getClientRegister();

        c = rcl.getClientByEmail(email);
        return c;
    }

    /**
     * Sets the instance of client as the client passed by parameter
     *
     * @param client
     */
    public void setClient(Client client) {
        c = client;
    }

    /**
     * Inserts a specific quantity of a specific product in the map of products
     *
     * @param pr the product to insert
     * @param quantity quantity of the product
     * @return Map
     */
    public Map<Product, Integer> preencherMapa(Product pr, int quantity) {
        mp.put(pr, quantity);
        return mp;
    }

    /**
     * Creates a Invoice for a specific client and the products he bought
     *
     * @param mp map with the products and their quantity
     * @param c client buying the products
     * @return Invoice
     */
    public Invoice newInvoice(Map<Product, Integer> mp, Client c) {

        Date date = new Date();
        String id = generateId(date);
        double price = calcPrice(mp);

        Invoice inv = new Invoice(id, date, price, c);

        inv.setMp(mp);

        return inv;

    }

    /**
     * Generates a id for a invoice
     *
     * @param date
     * @return the id
     */
    public String generateId(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        return "INV/" + formatter2.format(date) + "/" + formatter.format(date);
    }

    /**
     * Calculates the total price of a order using the product list and its
     * quantity
     *
     * @param mp map with the products and their quantity
     * @return the price
     */
    public double calcPrice(Map<Product, Integer> mp) {
        double price = 0;
        for(Map.Entry<Product, Integer> map : mp.entrySet()) {
            Product prod = map.getKey();
            float pri = prod.getPrice();
            double aux = Double.parseDouble(Float.toString(pri));
            price += (aux * (mp.get(prod)));
        }
        return price + TAX;
    }

    /**
     * Updates the stock of a product, for a specific pharmacy
     *
     * @param idPharmacy id of the pharmacy
     * @param st stock
     * @param idProduct id of the product
     * @param quantity of the product
     */
    public void updateStock(String idPharmacy, String idProduct, int st, int quantity) {
        int stock = st - quantity;
        plat.getStdb().setStock(idPharmacy, idProduct, stock);
    }

    /**
     * Calculates the total credits the customer will get
     *
     * @param inv
     * @return total credits
     */
    public double calcCredits(Invoice inv) {
        return (inv.getPrice() * 0.2);
    }

    /**
     * Updates the total credits of the client that is currently logged in
     *
     * @param credits
     * @return client
     */
    public Client addCredits(double credits) {

        c.setCredits(credits);
        plat.getCdb().updateClient(c);

        return c;

    }

    /**
     * Adds a new delivery to the delivery list of a pharmacy passed by
     * parameter
     *
     * @param inv invoice
     * @param pharmacy where the client is buying the products from
     * @return delivery
     */
    public Delivery newDelivery(Invoice inv, Pharmacy pharmacy) {

        delList = plat.getDeliveryList();

        del = delList.newDelivery(inv, pharmacy);

        return del;
    }

    /**
     * Registers a new delivery
     *
     */
    public void registerDelivery() {
        delList.registerDelivery(del);
    }

    /**
     * Verifies if a client can pay his tax using the credits he already has
     *
     * @return subtraction between the credits he has and the tax
     */
    public double verifyCredits() {
        double credits = c.getCredits();
        return credits - (TAX);
    }

    /**
     * Sends a email for a client with his invoice
     *
     * @param invoice for the client
     * @param pharmacy where he bought the products from
     */
    public void sendEmail(Invoice inv, Pharmacy p) throws MessagingException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String message = "Invoice: " + inv.getId() + "    Date:  " + formatter.format(inv.getDateinvoice()) + "\n\nTotal Price: " + inv.getPrice() + " €";
        String aux = "";
        for (Product produ : inv.getMp().keySet()) {
            aux = aux + "\n\n\nProduto" + produ.getNome() + "   Quantity: " + inv.getMp().get(produ);
        }

        message = message + aux + "\n\n\n      Thank You For Your Purchase";

        MailUtil.sendEmail(inv.getClient().getEmail(), message, "Invoice", p);
    }

    /**
     * Gets the closest pharmacy with stock of a specific product for a specific
     * phamacy
     *
     * @param listPharmacy list of pharmacys registered in the platform
     * @param startingPharmacy pharmacy that needs the product
     * @param product product the pharmacy needs
     * @param numberOfProductsTransfered number of products that need to be
     * transfered
     *
     * @return the closest pharmacy
     *
     */
    public Pharmacy closestPharmacyWithStock(List<Pharmacy> listPharmacy, Pharmacy startingPharmacy, Product product, int numberOfProductsTransfered) throws IOException {
        return pl.closestPharmacyWithStock(listPharmacy, startingPharmacy, product, numberOfProductsTransfered);
    }

    /**
     * Returns the closest pharmacy to the client who is currently logged in
     *
     * @param listPharmacy list of pharmacys registered in the platform
     * @param clientAdress adress of client who is currently logged in
     *
     * @return the closest pharmacy to the clients adress
     */
    public Pharmacy fowardDeliveryToClosestPharmacy(List<Pharmacy> listPharmacy, Address clientAdress) throws IOException {
        return pl.fowardDeliveryToClosestPharmacy(listPharmacy, clientAdress);
    }
}
