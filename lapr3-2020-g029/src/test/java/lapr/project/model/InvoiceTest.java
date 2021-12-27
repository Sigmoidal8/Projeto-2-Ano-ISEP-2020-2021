/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceTest {

    public InvoiceTest() {
    }

    /**
     * Test of getId method, of class Invoice.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Invoice instance = new Invoice("1", new Date(), 20, c1);
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Invoice.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "1";
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Invoice instance = new Invoice("1", new Date(), 20, c1);
        instance.setId(id);
    }

    /**
     * Test of getDate_invoice method, of class Invoice.
     */
    @Test
    public void testGetDate_invoice() {
        System.out.println("getDate_invoice");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Date d = new Date();
        Invoice instance = new Invoice("1", d, 20, c1);
        Date expResult = d;
        Date result = instance.getDateinvoice();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate_invoice method, of class Invoice.
     */
    @Test
    public void testSetDate_invoice() {
        System.out.println("setDate_invoice");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Date d = new Date();
        Invoice instance = new Invoice("1", d, 20, c1);

        @SuppressWarnings("deprecation")
        Date d1 = new Date(2021, 2, 3);
        Date dateinvoice = d1;
        instance.setDateinvoice(dateinvoice);
    }

    /**
     * Test of getPrice method, of class Invoice.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Date d = new Date();
        Invoice instance = new Invoice("1", d, 20, c1);

        double expResult = 20;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class Invoice.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Date d = new Date();
        Invoice instance = new Invoice("1", d, 20, c1);

        instance.setPrice(price);
    }

    /**
     * Test of getClient method, of class Invoice.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Date d = new Date();
        Invoice instance = new Invoice("1", d, 20, c1);
        Client expResult = c1;
        Client result = instance.getClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClient method, of class Invoice.
     */
    @Test
    public void testSetClient() {
        System.out.println("setClient");
        Address s2 = new Address("street2", 34, "postalCode2", "locality2", 42.3365, -85.36);
        Client c2 = new Client("id1", "name2", "password2", "email2", "122", s2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Date d = new Date();
        Invoice instance = new Invoice("1", d, 20, c1);
        instance.setClient(c2);
    }

    /**
     * Test of toString method, of class Invoice.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        Invoice instance = new Invoice("1", d, 20, c1);
        String expResult = "1, Wed Mar 02 00:00:00 UTC 3921, 20.000000, id, name, password, email, 12, 0.00, street, 42.36500000, -85.23600000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMp method, of class Invoice.
     */
    @Test
    public void testGetMp() {
        System.out.println("getMp");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        Invoice instance = new Invoice("1", d, 20, c1);

        Map<Product, Integer> expResult = null;
        Map<Product, Integer> result = instance.getMp();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMp method, of class Invoice.
     */
    @Test
    public void testSetMp() {
        System.out.println("setMp");
        @SuppressWarnings("unchecked")
        HashMap<Product, Integer> mp = new HashMap();
        mp.put(new Product ("12345", "Name", 2.0f, 0.5f),2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        Invoice instance = new Invoice("1", d, 20, c1);
        instance.setMp(mp);
    }

    /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEqualsSameObject() {
        System.out.println("equals");
        @SuppressWarnings("unchecked")
        HashMap<Product, Integer> mp = new HashMap();
        mp.put(new Product ("12345", "Name", 2.0f, 0.5f),2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        
        Invoice instance = new Invoice("1",d,20,c1);
        
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals");
        @SuppressWarnings("unchecked")
        HashMap<Product, Integer> mp = new HashMap();
        mp.put(new Product ("12345", "Name", 2.0f, 0.5f),2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        
        Invoice instance = new Invoice("1",d,20,c1);
        
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEqualsDiffObj() {
        System.out.println("equals");
        @SuppressWarnings("unchecked")
        HashMap<Product, Integer> mp = new HashMap();
        mp.put(new Product ("12345", "Name", 2.0f, 0.5f),2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        
        Invoice instance = new Invoice("1",d,20,c1);
        
        boolean expResult = false;
        boolean result = instance.equals(new Courier ("Name", "12345", "54321", "em@il", "abc"));
        assertEquals(expResult, result);
    }
    
    
     /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEqualsDiffId() {
        System.out.println("equals");
        @SuppressWarnings("unchecked")
        HashMap<Product, Integer> mp = new HashMap();
        mp.put(new Product ("12345", "Name", 2.0f, 0.5f),2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        
        Invoice instance2 = new Invoice("2",d,20,c1);
        Invoice instance = new Invoice("1",d,20,c1);
        
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        @SuppressWarnings("unchecked")
        HashMap<Product, Integer> mp = new HashMap();
        mp.put(new Product ("12345", "Name", 2.0f, 0.5f),2);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        @SuppressWarnings("deprecation")
        Date d = new Date(2021, 2, 2);
        
        Invoice instance = new Invoice("1",d,20,c1);
        Invoice instance2 = new Invoice("1",d,20,c1);
        
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
    

    
    
    
    
}
