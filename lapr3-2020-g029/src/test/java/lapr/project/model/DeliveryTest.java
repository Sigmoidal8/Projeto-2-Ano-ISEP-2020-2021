/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryTest {
    
    public DeliveryTest() {
    }

    /**
     * Test of getId method, of class Delivery.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        String expResult = "123";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateDelivery method, of class Delivery.
     */
    @Test
    public void testGetDateDelivery() {
        System.out.println("getDateDelivery");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f,invoice, pharmacy);
        
        Date expResult = new Date();
        Date result = instance.getDateDelivery();
        assertEquals(0, expResult.compareTo(result));
    }

    /**
     * Test of getTotalWeight method, of class Delivery.
     */
    @Test
    public void testGetTotalWeight() {
        System.out.println("getTotalWeight");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        double expResult = 2.0;
        double result = instance.getTotalWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPharmacy method, of class Delivery.
     */
    @Test
    public void testGetPharmacy() {
        System.out.println("getPharmacy");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        Pharmacy expResult = pharmacy;
        Pharmacy result = instance.getPharmacy();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Delivery.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "123novo";
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of setDateDelivery method, of class Delivery.
     */
    @Test
    public void testSetDateDelivery() {
        System.out.println("setDateDelivery");
        Date date_delivery = new Date(10-01-2021);
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        instance.setDateDelivery(date_delivery);
        assertEquals(date_delivery, instance.getDateDelivery());
    }

    /**
     * Test of setTotalWeight method, of class Delivery.
     */
    @Test
    public void testSetTotalWeight() {
        System.out.println("setTotalWeight");
        double totalWeight = 10.0;
        
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        instance.setTotalWeight(totalWeight);
        assertEquals(totalWeight, instance.getTotalWeight(), 0.00);
    }

    /**
     * Test of setPharmacy method, of class Delivery.
     */
    @Test
    public void testSetPharmacy() {
        System.out.println("setPharmacy");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacyNew = new Pharmacy("idnew", "designation", "email", "password",adress);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        instance.setPharmacy(pharmacyNew);
        assertEquals(pharmacyNew, instance.getPharmacy());
    }

    /**
     * Test of toString method, of class Delivery.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        @SuppressWarnings("deprecation")
        Date date = new Date(120, 00, 01, 12, 30, 00);
        
        Courier courier = new Courier ("Name", "12345", "54321", "em@il", "abc");
        Scooter scooter = new Scooter (1,50.0,60.0,0);
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", date, 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", date, 2.0f, invoice, pharmacy);
        
        String expResult = "123, Wed Jan 01 12:30:00 UTC 2020, 2.000000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoice method, of class Delivery.
     */
    @Test
    public void testGetInvoice() {
        System.out.println("getInvoice");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        Invoice expResult = invoice;
        Invoice result = instance.getInvoice();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInvoice method, of class Delivery.
     */
    @Test
    public void testSetInvoice() {
        System.out.println("setInvoice");
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoiceNew = new Invoice ("2", new Date(), 20, client);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        instance.setInvoice(invoiceNew);
        assertEquals(invoiceNew, instance.getInvoice());
    }
//    

    /**
     * Test of equals method, of class Delivery.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        Object obj = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Delivery.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        Object obj = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        Delivery instance = new Delivery("111", new Date (), 2.0f, invoice, pharmacy);
        
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
     @Test
    public void testEquals3() {
        System.out.println("equals");
        
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        Delivery instance = new Delivery("111", new Date (), 2.0f, invoice, pharmacy);
        
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }
    
     @Test
    public void testEquals4() {
        System.out.println("equals");
        
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        Delivery instance = new Delivery("111", new Date (), 2.0f, invoice, pharmacy);
        
        boolean expResult = false;
        boolean result = instance.equals(adress);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Delivery.
     */
    @Test
    public void testEquals5() {
        System.out.println("equals");
        
        Address adress = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", adress);
        Invoice invoice = new Invoice ("1", new Date(), 20, client);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        Delivery instance = new Delivery("123", new Date (), 2.0f, invoice, pharmacy);
        
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
}
