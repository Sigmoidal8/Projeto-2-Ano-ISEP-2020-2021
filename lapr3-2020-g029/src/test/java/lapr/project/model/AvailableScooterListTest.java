/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailableScooterListTest {

    public AvailableScooterListTest() {
    }

    /**
     * Test of addScooter method, of class AvailableScooterList.
     */
    @Test
    public void testAddScooter() {
        System.out.println("addScooter");
        Scooter s = new Scooter(2, 80.0, 90.0, 0);
        AvailableScooterList instance = new AvailableScooterList();
        boolean expResult = true;
        boolean result = instance.addScooter(s);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of addScooter method, of class AvailableScooterList.
     */
    @Test
    public void testAddScooter2() {
        System.out.println("addScooter2");
        Scooter s = new Scooter(2, 80.0, 90.0, 0);
        AvailableScooterList instance = new AvailableScooterList();
        instance.addScooter(s);
        boolean expResult = false;
        boolean result = instance.addScooter(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeScooter method, of class AvailableScooterList.
     */
    @Test
    public void testRemoveScooter() {
        System.out.println("addScooter");
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        AvailableScooterList instance = new AvailableScooterList();
        instance.addScooter(s);
        boolean expResult = true;
        boolean result = instance.removeScooter(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooter method, of class AvailableScooterList.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");
        int exclusiveNumber = 1;
        AvailableScooterList instance = new AvailableScooterList();
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        instance.addScooter(s);
        Scooter expResult = s;
        Scooter result = instance.getScooter(exclusiveNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getScooter method, of class AvailableScooterList.
     */
    @Test
    public void testGetScooter2() {
        System.out.println("getScooterNull");
        int exclusiveNumber = 1;
        AvailableScooterList instance = new AvailableScooterList();
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        Scooter expResult = null;
        Scooter result = instance.getScooter(exclusiveNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getScooter method, of class AvailableScooterList.
     */
    @Test
    public void testGetScooter3() {
        System.out.println("getScooter");
        int exclusiveNumber = 2;
        AvailableScooterList instance = new AvailableScooterList();
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        instance.addScooter(s);
        Scooter expResult = null;
        Scooter result = instance.getScooter(exclusiveNumber);
        assertEquals(expResult, result);
    }
}
