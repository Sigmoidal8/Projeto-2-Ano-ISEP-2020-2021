/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.ScooterDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class ScootersListTest {
    
    public ScootersListTest() {
    }

    /**
     * Test of addScooter method, of class ScootersList.
     */
    @Test
    public void testAddScooter() {
        System.out.println("addScooter");
        Scooter s = new Scooter(1,50.0,60.0,0);
        ScootersList instance = new ScootersList();
        boolean expResult = true;
        boolean result = instance.addScooter(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addScooter method, of class ScootersList.
     */
    @Test
    public void testAddScooterFalse() {
        System.out.println("addScooter");
        Scooter s = new Scooter(1,50.0,60.0,0);
        ScootersList instance = new ScootersList();
        instance.addScooter(s);
        boolean expResult = false;
        boolean result = instance.addScooter(s);
        assertEquals(expResult, result);
    }


    /**
     * Test of getScooter method, of class ScootersList.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");
        int exclusiveNumber = 1;
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        instance.addScooter(s);
        Scooter expResult = s;
        Scooter result = instance.getScooter(exclusiveNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getScooter method, of class ScootersList.
     */
    @Test
    public void testGetScooter2() {
        System.out.println("getScooter");
        int exclusiveNumber = 3;
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        instance.addScooter(s);
        Scooter expResult = null;
        Scooter result = instance.getScooter(exclusiveNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of generateExclusiveNumber method, of class ScootersList.
     */
    @Test
    public void testGenerateExclusiveNumber() {
        System.out.println("generateExclusiveNumber");
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        instance.addScooter(s);
        int expResult = 2;
        int result = instance.generateExclusiveNumber();
        assertEquals(expResult, result);

    }

    
    @Test
    public void testGenerateExclusiveNumberEmpty() {
        System.out.println("generateExclusiveNumber");
        ScootersList instance = new ScootersList();
        int expResult = 1;
        int result = instance.generateExclusiveNumber();
        assertEquals(expResult, result);

    }
    /**
     * Test of newScooter method, of class ScootersList.
     */
    @Test
    public void testNewScooter() {
        System.out.println("newScooter");
        int excNumb = 1;
        double fullCharge = 50.0;
        double power = 60.0;
        int operational = 0;
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        Scooter expResult = s;
        Scooter result = instance.newScooter(excNumb, fullCharge, power, operational);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of newScooter method, of class ScootersList.
     */
    @Test
    public void testNewScooter2() {
        System.out.println("newScooter2");
        int excNumb = 1;
        double fullCharge = 50.0;
        double power = 60.0;
        int operational = 1;
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,1);
        Scooter expResult = s;
        Scooter result = instance.newScooter(excNumb, fullCharge, power, operational);
        assertEquals(expResult, result);

    }

    /**
     * Test of validateScooter method, of class ScootersList.
     */
    @Test
    public void testValidateScooter() {
        System.out.println("validateScooter");
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        instance.addScooter(s);
        boolean expResult = false;
        boolean result = instance.validateScooter(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateScooter method, of class ScootersList.
     */
    @Test
    public void testValidateScooter2() {
        System.out.println("validateScooter");
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(2,2,-2,0);
        boolean expResult = false;
        boolean result = instance.validateScooter(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateScooter method, of class ScootersList.
     */
    @Test
    public void testValidateScooter3() {
        System.out.println("validateScooter");
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        boolean expResult = true;
        boolean result = instance.validateScooter(s);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of validateScooter method, of class ScootersList.
     */
    @Test
    public void testValidateScooter4() {
        System.out.println("validateScooter");
        ScootersList instance = new ScootersList();
        Scooter s = new Scooter(2,-2,2,0);
        boolean expResult = false;
        boolean result = instance.validateScooter(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterList method, of class ScootersList.
     */
    @Test
    public void testGetScooterList() {
        System.out.println("getScooterList");
        ScootersList instance = new ScootersList();
        List<Scooter> expResult = new ArrayList<>();
        List<Scooter> result = instance.getScooterList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPharmacyLessScooters method, of class ScootersList.
     */
    @Test
    public void testGetPharmacyLessScooters() {
        System.out.println("getPharmacyLessScooters");
        ScootersList instance = new ScootersList();
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("id", "designation", "email", adress);
        PharmacyList pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();  
        pl.addPharmacy(p);
        Scooter s=new Scooter(1,50.0,60.0,0.0,0);
        Scooter s2=new Scooter(2,50.0,60.0,0.0,0);
        p.getAsl().getAvailableScooterList().add(s);
        instance.getScooterList().add(s);
        instance.getScooterList().add(s2);
        boolean expResult = true;
        boolean result;
        List<Scooter> newList=instance.getPharmacyLessScooters();
        result= !newList.contains(s) && newList.contains(s2);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerScooter method, of class ScootersList.
     */
    @Test
    public void testRegisterScooter() {
        System.out.println("registerScooter");
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("id", "designation", "email", adress);
        Scooter s=new Scooter(1,50.0,60.0,0.0,0);
        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).addScooter(s);
        
        ScootersList instance = new ScootersList();
        boolean expResult = true;
        boolean result = instance.registerScooter(s);
        assertEquals(expResult, result);
    }
}
