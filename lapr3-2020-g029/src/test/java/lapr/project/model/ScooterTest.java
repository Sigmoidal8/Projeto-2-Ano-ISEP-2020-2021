/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.File;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.ScooterDB;
import lapr.project.utils.GenerateQRCode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScooterTest {

    @Mock
    private final Scooter scooterDB;

    public ScooterTest() {
        scooterDB = new Scooter(2, 50.0, 60.0, 0);
    }

    Scooter scooter = new Scooter(2, 50.0, 60.0, 0);

    /**
     * Test of getExclusiveNumber method, of class Scooter.
     */
    @Test
    public void testGetExclusiveNumber() {
        System.out.println("getExclusiveNumber");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        int result = scooter.getExclusiveNumber();
        assertEquals(2, result);
    }

    /**
     * Test of getFullCharge method, of class Scooter.
     */
    @Test
    public void testGetFullCharge() {
        System.out.println("getFullCharge");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        double result = scooter.getFullCharge();
        assertEquals(50.0, result, 0.1);
    }

    /**
     * Test of getCurrentCharge method, of class Scooter.
     */
    @Test
    public void testGetCurrentCharge() {
        System.out.println("getCurrentCharge");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        double result = scooter.getCurrentCharge();
        assertEquals(50.0, result, 0.1);
    }

    /**
     * Test of getPower method, of class Scooter.
     */
    @Test
    public void testGetPower() {
        System.out.println("getPower");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        double result = instance.getPower();
        assertEquals(60.0, result, 0.1);

    }

    /**
     * Test of isOperational method, of class Scooter.
     */
    @Test
    public void testIsOperational() {
        System.out.println("isOperational");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        boolean expResult = false;
        boolean result = instance.isOperational();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of isOperational method, of class Scooter.
     */
    @Test
    public void testIsOperational2() {
        System.out.println("isOperational");
        Scooter instance = new Scooter(1, 50.0, 50.0, 60.0, 0);
        boolean expResult = false;
        boolean result = instance.isOperational();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of isOperational method, of class Scooter.
     */
    @Test
    public void testIsOperational3() {
        System.out.println("isOperational");
        
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adressPharmacy);
        
        Scooter instance = new Scooter(1, 50.0, 50.0, 60.0, 0, pharmacy);
        boolean expResult = false;
        boolean result = instance.isOperational();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of isOperational method, of class Scooter.
     */
    @Test
    public void testIsOperational4() {
        System.out.println("isOperational");
        
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adressPharmacy);
        
        Scooter instance = new Scooter(1, 50.0, 50.0, 60.0, 1, pharmacy);
        boolean expResult = true;
        boolean result = instance.isOperational();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOperationalTrue() {
        System.out.println("isOperational");
        Scooter instance = new Scooter(1, 50.0, 60.0, 1);
        boolean expResult = true;
        boolean result = instance.isOperational();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOperational method, of class Scooter.
     */
    @Test
    public void testSetOperational() {
        System.out.println("setOperational");
        boolean operational = false;
        Scooter instance = new Scooter(1, 50.0, 60.0, 1);
        instance.setOperational(operational);
        assertEquals(operational, instance.isOperational());
    }

    /**
     * Test of toString method, of class Scooter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        String expResult = "Scooter 1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAverageSpeed method, of class Scooter.
     */
    @Test
    public void testGetAverageSpeed() {
        System.out.println("getAverageSpeed");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        double expResult = 30.0;
        double result = instance.getAverageSpeed();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getOperational method, of class Scooter.
     */
    @Test
    public void testGetOperational() {
        System.out.println("getOperational");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        int expResult = 0;
        int result = instance.getOperational();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOperational method, of class Scooter.
     */
    @Test
    public void testGetOperational2() {
        System.out.println("getOperational");
        Scooter instance = new Scooter(1, 50.0, 60.0, 1);
        int expResult = 1;
        int result = instance.getOperational();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExclusiveNumber method, of class Scooter.
     */
    @Test
    public void testSetExclusiveNumber() {
        System.out.println("setExclusiveNumber");
        int exclusiveNumber = 10;
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        instance.setExclusiveNumber(exclusiveNumber);
        double result = instance.getExclusiveNumber();
        assertEquals(exclusiveNumber, result, 0.1);
    }

    /**
     * Test of setFullCharge method, of class Scooter.
     */
    @Test
    public void testSetFullCharge() {
        System.out.println("setCurrentCharge");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        scooter.setFullCharge(10.0);
        double result = scooter.getFullCharge();
        assertEquals(10.0, result, 0.1);
    }

    /**
     * Test of setCurrentCharge method, of class Scooter.
     */
    @Test
    public void testSetCurrentCharge() {
        System.out.println("setCurrentCharge");
        double currentCharge = 10.0;
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        instance.setCurrentCharge(currentCharge);
        double result = instance.getCurrentCharge();
        assertEquals(currentCharge, result, 0.1);
    }

    /**
     * Test of setPower method, of class Scooter.
     */
    @Test
    public void testSetPower() {
        System.out.println("setPower");
        double power = 10.0;
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        instance.setPower(power);
        double result = instance.getPower();
        assertEquals(power, result, 0.1);
    }

    /**
     * Test of setQrCode method, of class Scooter.
     */
    @Test
    public void testSetQrCode() throws Exception {
        System.out.println("setQrCode");
        GenerateQRCode gqrc = new GenerateQRCode();
        File qrCode = gqrc.generateQRCode(1);
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        instance.setQrCode(qrCode);
        File result = instance.getQrCode();
        assertEquals(qrCode, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEqualsSameObject() {
        System.out.println("equals");

        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsNull() {
        System.out.println("equals");

        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsDiffObj() {
        System.out.println("equals");

        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        boolean expResult = false;
        boolean result = instance.equals(new Courier("Name", "12345", "54321", "em@il", "abc"));
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsDiffScooters() {
        System.out.println("equals");

        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        Scooter s = new Scooter(2, 50.0, 60.0, 0);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Scooter instance = new Scooter(1, 50.0, 60.0, 0);
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        boolean expResult = true;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooter method, of class Scooter.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");

        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        when(dbMock.getScooter(2)).thenReturn(scooter);

        int exclusiveNumber = 2;
        Scooter result = scooter.getScooter(exclusiveNumber);
        assertEquals(scooter, result);
    }

    /**
     * Test of save method, of class Scooter.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        when(dbMock.getScooter(2)).thenReturn(scooter);
        doNothing().when(dbMock).addScooter(scooter);

        scooter.save();
        Scooter s = scooter.getScooter(2);

        assertEquals(s, scooter);
    }

    /**
     * Test of save method, of class Scooter.
     */
    @Test
    public void testSave2() {
        System.out.println("save2");

        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).addScooter(scooterDB);
        when(dbMock.getScooter(2)).thenThrow(new IllegalArgumentException());

        scooterDB.save();
        assertThrows(IllegalArgumentException.class, ()-> 
                {scooterDB.getScooter(2);
        });

    }

    /**
     * Test of update method, of class Scooter.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        when(dbMock.getScooter(2)).thenReturn(scooter);
        doNothing().when(dbMock).updateScooter(scooter);

        scooter.update();
        Scooter s = scooter.getScooter(2);

        assertEquals(s, scooter);
    }
}
