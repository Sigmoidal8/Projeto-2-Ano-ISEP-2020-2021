/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.data.ClientDB;
import lapr.project.data.DeliveryDB;
import lapr.project.data.StockDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Delivery;
import lapr.project.model.Invoice;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ProductList;
import lapr.project.model.Scooter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BuyAProductControllerTest {

    private final BuyAProductController controller;

    @Mock
    private final BuyAProductController controllerDB;

    private final Platform plat;

    public BuyAProductControllerTest() {
        controllerDB = new BuyAProductController();
        controller = new BuyAProductController();
        plat = AplicationPOT.getInstance().getPlatform();
    }

    /**
     * Test of getPharmacyList method, of class BuyAProductController.
     */
    @Test
    public void testGetPharmacyList() {
        PharmacyList expResult = plat.getPharmacyList();
        PharmacyList res = controller.getPharmacyList();

        assertEquals(expResult, res);
    }

    /**
     * Test of getPharmacy method, of class BuyAProductController.
     */
    @Test
    public void testGetPharmacy() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy expResult = new Pharmacy("id", "designation", "email", "password", adress);

        controller.getPharmacyList().addPharmacy(expResult);
        Pharmacy result = controller.getPharmacy("id");

        assertEquals(expResult, result);
    }

    /**
     * Test of getProduct method, of class BuyAProductController.
     */
    @Test
    public void testGetProduct() {

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adress);

        controller.getPharmacyList().addPharmacy(pharmacy);
        Pharmacy ControllerPharmacy = controller.getPharmacy("id");

        ProductList prl = ControllerPharmacy.getProductList();

        Product expResult = new Product("12345", "Name", 2.0f, 0.5f);

        prl.addProduct(expResult);

        Product result = controller.getProduct("12345");

        assertEquals(expResult, result);
    }

    /**
     * Test of getStock method, of class BuyAProductController.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        String idProduct = "1";
        String idPharmacy = "1";

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock(idProduct, idPharmacy)).thenReturn(1);

        int result = controllerDB.getStock(idProduct, idPharmacy);
        int expResult = 1;

        assertEquals(expResult, result);
    }

    /**
     * Test of preencherMapa method, of class BuyAProductController.
     */
    @Test
    public void testPreencherMapa() {
        System.out.println("preencherMapa");
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        int quantity = 3;
        //BuyAProductController instance = new BuyAProductController();
        HashMap<Product, Integer> aux = new HashMap<>();
        aux.put(pr, 3);
        HashMap<Product, Integer> expResult = aux;

        Map<Product, Integer> result = controller.preencherMapa(pr, quantity);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClient method, of class BuyAProductController.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        plat.getAutorizationFacade().registerUserWithRole("name", "email", "pass", "cliente");
        Address s1 = new Address("street", 0, "postalCode", "locality", 0, 0);
        Client c1 = new Client("n22222", "name", "pass", "email", "22222", s1);
        AplicationPOT.getInstance().doLogin("email", "pass");

        plat.getClientRegister().addClient(c1);

        Client result = controller.getClient();

        assertEquals(c1, result);
    }
    
    /**
     * Test of setClient method, of class Invoice.
     */
    @Test
    public void testSetClient() {
        System.out.println("setClient");
        Address s1 = new Address("street", 0, "postalCode", "locality", 0, 0);
        Client c1 = new Client("n22222", "name", "pass", "email", "22222", s1);
        
        controller.setClient(c1);
        Client result = controller.getClient();
        assertEquals(c1, result);
    }

    /**
     * Test of newInvoice method, of class BuyAProductController.
     */
    @Test
    public void testNewInvoice() {
        System.out.println("newInvoice");
        HashMap<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c = new Client("id", "name", "password", "email", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);

        Invoice expResult = new Invoice(str, date, 2.0f, c);
        Invoice result = controller.newInvoice(mp, c);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of newInvoice method, of class BuyAProductController.
     */
    @Test
    public void testNewInvoice2() {
        System.out.println("newInvoice2");
        Map<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c = new Client("id", "name", "password", "email", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);

        Invoice expResult = new Invoice(str, date, 2.0f, c);
        Invoice result = controller.newInvoice(mp, c);
        
        Map<Product,Integer> resultMap = result.getMp();
        assertTrue(mp.keySet().equals(resultMap.keySet()));
        assertEquals(mp.size(),resultMap.size());
        assertTrue(mp.equals(resultMap));
        
        assertEquals(expResult, result);

    }
    
    /**
     * Test of generateId method, of class BuyAProductController.
     */
    @Test
    public void testGenerateId() {
        System.out.println("generateId");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String expResult = "INV/" + formatter2.format(date) + "/" + formatter.format(date);
        String result = controller.generateId(date);
        assertEquals(expResult, result);

    }

    /**
     * Test of calcPrice method, of class BuyAProductController.
     */
    @Test
    public void testCalcPrice() {
        System.out.println("calcPrice");
        HashMap<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);
        double expResult = 11.0;
        double result = controller.calcPrice(mp);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of updateStock method, of class BuyAProductController.
     */
    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        String idPharmacy = "";
        String idProduct = "";
        int st = 5;
        int quantity = 2;

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.setStock(idPharmacy, idProduct, st)).thenReturn(true);
        
        controllerDB.updateStock(idPharmacy, idProduct, st, quantity);

        when(dbMock.getStock(idPharmacy, idProduct)).thenReturn(st);

        int result = controller.getStock(idProduct, idPharmacy);

        assertEquals(st, result);
    }
    
    /**
     * Test of updateStock method, of class BuyAProductController.
     */
    @Test
    public void testUpdateStock2() {
        System.out.println("updateStock");
        String idPharmacy = "";
        String idProduct = "";
        int st = 5;
        int quantity = -2;

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.setStock(idPharmacy, idProduct, st)).thenReturn(true);

        controllerDB.updateStock(idPharmacy, idProduct, st, quantity);

        when(dbMock.getStock(idPharmacy, idProduct)).thenReturn(st-quantity);

        int result = controllerDB.getStock(idProduct, idPharmacy);
        
        assertEquals(st-quantity, result);
    }

    /**
     * Test of calcCredits method, of class BuyAProductController.
     */
    @Test
    public void testCalcCredits() {
        System.out.println("calcCredits");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c = new Client("id", "name", "password", "email", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);

        Invoice inv = new Invoice(str, date, 2.0f, c);
        double expResult = 0.4;
        double result = controller.calcCredits(inv);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addCredits method, of class BuyAProductController.
     */
    @Test
    public void testAddCredits() {
        System.out.println("addCredits");
        double credits = 2.0;

        BuyAProductController controllerDB1 = new BuyAProductController();
        Address adress = new Address("street", 0, "postalCode", "locality", 0, 0);
        Client expResult = new Client("n22222", "name", "pass", "email", "123", adress);

        plat.getAutorizationFacade().registerUserWithRole("name", "email", "pass", "client");
        AplicationPOT.getInstance().doLogin("email", "pass");
        plat.getClientRegister().addClient(expResult);
        controllerDB1.getClient();

        ClientDB dbMock = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock);
        doNothing().when(dbMock).updateClient(expResult);

        Client result = controllerDB1.addCredits(credits);

        double resultCredits = result.getCredits();
        double resultCredits2 = controllerDB1.getClient().getCredits();
        assertEquals(resultCredits,resultCredits2,0.0);
        assertEquals(credits,resultCredits,0.0);
        
        //assertEquals(expResult, result);
    }

    /**
     * Test of newDelivery method, of class BuyAProductController.
     */
    @Test
    public void testNewDelivery() {
        System.out.println("newDelivery");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c = new Client("id", "name", "password", "email", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "DEL/" + formatter2.format(date) + "/" + formatter.format(date);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password", adress);

        controller.getPharmacyList().addPharmacy(p);

        HashMap<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);

        Invoice inv = new Invoice(str, date, 2.0f, c);

        inv.setMp(mp);

        Delivery expResult = new Delivery(str, date, 1.5, inv, p);

        Delivery result = controller.newDelivery(inv, p);

        assertEquals(expResult, result);

    }

    /**
     * Test of registerDelivery method, of class BuyAProductController.
     */
    @Test
    public void testRegisterDelivery() {
        System.out.println("registerDelivery");

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        Product product = new Product("id", "name", 1, 1);
        HashMap<Product, Integer> map = new HashMap<>();
        map.put(product, 1);
        invoice.setMp(map);

        DeliveryDB dbMock = mock(DeliveryDB.class);
        AplicationPOT.getInstance().getPlatform().setDdb(dbMock);
        doNothing().when(dbMock).addDelivery(delivery);
        when(dbMock.getDelivery("123")).thenReturn(delivery);

        plat.getPharmacyList().addPharmacy(pharmacy);
        controllerDB.getPharmacyList();
        controllerDB.getPharmacy("id");
        controllerDB.newDelivery(invoice, pharmacy);

        controllerDB.registerDelivery();

        assertEquals(delivery, dbMock.getDelivery("123"));

    }

    /**
     * Test of verifyCredits method, of class BuyAProductController.
     */
    @Test
    public void testVerifyCredits() {
        System.out.println("verifyCredits");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c = new Client("id", "name", "password", "email", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);

        controller.getClient().setCredits(40);

        double expResult = 35;
        double result = controller.verifyCredits();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of sendEmail method, of class BuyAProductController.
     */
    @Test
    public void testSendEmail() throws Exception {
        System.out.println("sendEmail");

        Address s1 = new Address("Street", 5, "2000-130", "Locality", 0.0, 0.0);
        Client c = new Client("id", "name", "password", "lapr3g029@gmail.com", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);

        Pharmacy p = new Pharmacy("Pharmacy", "lapr3g029@gmail.com", "Lapr32021", s1);

        controller.getPharmacyList().addPharmacy(p);

        HashMap<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);

        Invoice inv = new Invoice(str, date, 2.0f, c);

        inv.setMp(mp);

        BuyAProductController instance = new BuyAProductController();
        instance.sendEmail(inv, p);
    }

    /**
     * Test of closestPharmacyWithStock method, of class BuyAProductController.
     */
    @Test
    public void closestPharmacyWithStock() throws IOException {

        BuyAProductController instance = new BuyAProductController();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);
        Scooter s = new Scooter(1, 1000.0, 1000.0, 60.0, 1);
        startingPharmacy1.getAsl().addScooter(s);

        instance.getPharmacyList();
        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of FowardDeliveryToClosestPharmacy method, of class
     * BuyAProductController.
     */
    @Test
    public void fowardDeliveryToClosestPharmacy() throws IOException {

        BuyAProductController instance = new BuyAProductController();
        instance.getPharmacyList();
        Address clientAdress = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);

        Address adressPharmacy1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy1 = new Pharmacy("1", "designation", "email", "password", adressPharmacy1);

        Address adressPharmacy2 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(pharmacy1);
        pharmacyList.add(pharmacy2);

        Pharmacy result = instance.fowardDeliveryToClosestPharmacy(pharmacyList, clientAdress);
        assertEquals(pharmacy1, result);
    }

    /**
     * Test of FowardDeliveryToClosestPharmacy method, of class
     * BuyAProductController.
     */
    @Test
    public void fowardDeliveryToClosestPharmacy2() throws IOException {

        BuyAProductController instance = new BuyAProductController();
        instance.getPharmacyList();
        Address clientAdress = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);

        Address adressPharmacy1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy1 = new Pharmacy("1", "designation", "email", "password", adressPharmacy1);

        Address adressPharmacy2 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("4", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(pharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Pharmacy result = instance.fowardDeliveryToClosestPharmacy(pharmacyList, clientAdress);
        assertEquals(pharmacy1, result);
    }
}
