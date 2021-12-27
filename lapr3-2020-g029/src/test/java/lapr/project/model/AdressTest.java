/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.controller.AplicationPOT;
import lapr.project.data.AdressDB;
import java.lang.IllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdressTest {

    @Mock
    private final Address adressDB;
    private final Address adressDB2;
    
    public AdressTest() {
        adressDB = new Address("2104150-417", "R. Dr. António Granjo", 207, "4400-032", "Porto", 41.13522572, -8.62001961);
        adressDB2 = new Address("R. Dr. António Granjo", 41.13522572, -8.62001961);
    }

    /**
     * Test of getId method, of class Adress.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        String expResult = "45postalCode";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStreet method, of class Adress.
     */
    @Test
    public void testGetStreet() {
        System.out.println("getStreet");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        String expResult = "street";
        String result = instance.getStreet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDoorNumber method, of class Adress.
     */
    @Test
    public void testGetDoorNumber() {
        System.out.println("getDoorNumber");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        int expResult = 45;
        int result = instance.getDoorNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostalCode method, of class Adress.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        String expResult = "postalCode";
        String result = instance.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocality method, of class Adress.
     */
    @Test
    public void testGetLocality() {
        System.out.println("getLocality");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        String expResult = "locality";
        String result = instance.getLocality();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDecimalDegree1 method, of class Adress.
     */
    @Test
    public void testGetDecimalDegree1() {
        System.out.println("getDecimalDegree1");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        Double expResult = 34.567;
        Double result = instance.getDecimalDegree1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDecimalDegree2 method, of class Adress.
     */
    @Test
    public void testGetDecimalDegree2() {
        System.out.println("getDecimalDegree2");
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        Double expResult = -32.456;
        Double result = instance.getDecimalDegree2();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStreet method, of class Adress.
     */
    @Test
    public void testSetStreet() {
        System.out.println("setStreet");
        String street = "street2";
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        instance.setStreet(street);
        assertEquals(street, instance.getStreet());

    }

    /**
     * Test of setdoorNumber method, of class Adress.
     */
    @Test
    public void testSetdoorNumber() {
        System.out.println("setdoorNumber");
        int doorNumber = 78;
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        instance.setdoorNumber(doorNumber);
        assertEquals(doorNumber, instance.getDoorNumber());
    }

    /**
     * Test of setPostalCode method, of class Adress.
     */
    @Test
    public void testSetPostalCode() {
        System.out.println("setPostalCode");
        String postalCode = "postalCode2";
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        instance.setPostalCode(postalCode);
        assertEquals(postalCode, instance.getPostalCode());
    }

    /**
     * Test of setLocality method, of class Adress.
     */
    @Test
    public void testSetLocality() {
        System.out.println("setLocality");
        String locality = "locality2";
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        instance.setLocality(locality);
        assertEquals(locality, instance.getLocality());
    }

    /**
     * Test of setDecimalDegree1 method, of class Adress.
     */
    @Test
    public void testSetDecimalDegree1() {
        System.out.println("setDecimalDegree1");
        Double decimalDegree1 = 32.256;
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        instance.setDecimalDegree1(decimalDegree1);
        assertEquals(decimalDegree1, instance.getDecimalDegree1());
    }

    /**
     * Test of setDecimalDegree2 method, of class Adress.
     */
    @Test
    public void testSetDecimalDegree2() {
        System.out.println("setDecimalDegree2");
        Double decimalDegree2 = -54.25696;
        Address instance = new Address("street", 45, "postalCode", "locality", 34.567, -32.456);
        instance.setDecimalDegree2(decimalDegree2);
        assertEquals(decimalDegree2, instance.getDecimalDegree2());
    }

    /**
     * Test of generateId method, of class Adress.
     */
    @Test
    public void testGenerateId() {
        System.out.println("generateId");
        int doorNumber = 23;
        String postalCode = "postalCode";
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        String expResult = "23postalCode";
        String result = instance.generateId(doorNumber, postalCode);
        assertEquals(expResult, result);
        assertEquals(doorNumber, instance.getDoorNumber());
    }

    /**
     * Test of getAdress method, of class Adress.
     */
    @Test
    public void testGetAdress() {
        System.out.println("getAdress");

        AdressDB dbMock = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock);
        when(dbMock.getAdress("2104150-417")).thenReturn(adressDB);

        String id = "2104150-417";
        Address result = adressDB.getAdress(id);

        assertEquals(adressDB, result);
    }

    /**
     * Test of save method, of class Adress.
     */
    @Test
    public void testSave() {
        System.out.println("save");

        AdressDB dbMock = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock);

        doNothing().when(dbMock).addAdress(adressDB);
        when(dbMock.getAdress("2104150-417")).thenReturn(adressDB);

        adressDB.save();
        Address result = adressDB.getAdress("2104150-417");

        assertEquals(adressDB, result);
    }

    /**
     * Test of save method, of class Adress.
     */
    @Test
    public void testSave2() {
        System.out.println("save2");

        AdressDB dbMock = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock);

        doNothing().when(dbMock).addAdress(adressDB);
        when(dbMock.getAdress("2104150-417")).thenThrow(new IllegalArgumentException());

        adressDB.save();
        assertThrows(IllegalArgumentException.class, ()->
                {adressDB.getAdress("2104150-417");});
    }

    /**
     * Test of update method, of class Adress.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");

        AdressDB dbMock = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock);

        doNothing().when(dbMock).updateAdress(adressDB);
        when(dbMock.getAdress("2104150-417")).thenReturn(adressDB);

        adressDB.update();
        Address result = adressDB.getAdress("2104150-417");

        assertEquals(adressDB, result);
    }

    /**
     * Test of toString method, of class Adress.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        String expResult = "street, 34.56700000, -32.45600000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Adress.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObjeto = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        boolean expResult = true;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Adress.
     */
    @Test
    public void testEquals2() {
        System.out.println("not equals");
        Object otherObjeto = new Address("street2", 233, "postalCode2", "locality2", 34.567, -32.456);
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals3() {
        System.out.println("not equals null");
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Adress.
     */
    @Test
    public void testEquals4() {
        System.out.println("not equals different object");
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        boolean expResult = false;
        boolean result = instance.equals(new Courier("Name", "12345", "54321", "em@il", "abc"));
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals5() {
        System.out.println("equals same object");
        Address instance = new Address("street", 23, "postalCode", "locality", 34.567, -32.456);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
}
