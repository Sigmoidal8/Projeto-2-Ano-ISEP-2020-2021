/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.controller.AplicationPOT;
import lapr.project.data.ClientDB;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    
    @Mock
    private final Client clientDB;

    public ClientTest() {
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        clientDB = new Client("id", "name", "password", "email", "12", s1);
    }

    /**
     * Test of getId method, of class Client.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        String expResult = "id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Client.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Client.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNIF method, of class Client.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        int expResult = 12;
        int result = instance.getNIF();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCredits method, of class Client.
     */
    @Test
    public void testGetCredits() {
        System.out.println("getCredits");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12",23, s1);
        double expResult = 23.0;
        double result = instance.getCredits();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getAdress method, of class Client.
     */
    @Test
    public void testGetAdress() {
        System.out.println("getAdress");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        Address expResult = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Address result = instance.getAdress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Client.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name2";
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        instance.setName(name);
        assertEquals(name, instance.getName());

    }

    /**
     * Test of setPassword method, of class Client.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "password2";
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of setEmail method, of class Client.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "email2";
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of setNIF method, of class Client.
     */
    @Test
    public void testSetNIF() {
        System.out.println("setNIF");
        int NIF = 567;
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        instance.setNIF(NIF);
        assertEquals(NIF, instance.getNIF());
    }

    /**
     * Test of setCredits method, of class Client.
     */
    @Test
    public void testSetCredits() {
        System.out.println("setCredits");
        double credits = 24.3;
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        instance.setCredits(credits);
        assert (credits == instance.getCredits());
    }

    /**
     * Test of setAdress method, of class Client.
     */
    @Test
    public void testSetAdress() {
        System.out.println("setAdress");
        Address adress = new Address("street2", 34, "postalCode2", "locality2", 22.362, -25.232);
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        instance.setAdress(adress);
        assertEquals(adress, instance.getAdress());
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        String expResult = "id, name, password, email, 12, 0.00, street, 42.36500000, -85.23600000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Address s2 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Object otherObjeto = new Client("id", "name", "password", "email", "12", s2);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        boolean expResult = true;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals2() {
        System.out.println("not equals");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Address s2 = new Address("street2", 34, "postalCode2", "locality2", 42.365, -85.236);
        Object otherObjeto = new Client("id2", "name2", "password2", "email2", "123", s2);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals3() {
        System.out.println("not equals");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Address s2 = new Address("street2", 34, "postalCode2", "locality2", 42.365, -85.236);
        Object otherObjeto = new Client("id2", "name2", "password2", "email2", "123", s2);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals4() {
        System.out.println("not equals");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Address s2 = new Address("street2", 34, "postalCode2", "locality2", 42.365, -85.236);
        Object otherObjeto = new Client("id2", "name2", "password2", "email2", "123", s2);
        Client instance = new Client("id", "name", "password", "email", "12", s1);
        boolean expResult = false;
        boolean result = instance.equals(new Courier ("Name", "12345", "54321", "em@il", "abc"));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getClient method, of class Client.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        ClientDB dbMock = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock);
        when(dbMock.getClient("id")).thenReturn(clientDB);
        
        Client cl = clientDB.getClient("id");
        
        assertEquals(clientDB,cl);
    }
    /**
     * Test of save method, of class Client.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        
        ClientDB dbMock = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock);
        when(dbMock.getClient("id")).thenReturn(clientDB);
        doNothing().when(dbMock).addClient(clientDB);
        
        clientDB.save();
        Client c = clientDB.getClient("id");
        assertEquals(c, clientDB);
    }

    /**
     * Test of save method, of class Client.
     */
    @Test
    public void testSave2() {
        System.out.println("save2");
        
        ClientDB dbMock = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock);
        doNothing().when(dbMock).addClient(clientDB);
        when(dbMock.getClient("id")).thenThrow(new IllegalArgumentException());
        
        clientDB.save();
        assertThrows(IllegalArgumentException.class, ()->
                {clientDB.getClient("id");});
    }
    
    /**
     * Test of update method, of class Client.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        ClientDB dbMock = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock);
        when(dbMock.getClient("id")).thenReturn(clientDB);
        doNothing().when(dbMock).updateClient(clientDB);
        clientDB.update();
        
        Client c = clientDB.getClient("id");
        assertEquals(c, clientDB);
    }
    
}
