/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.controller.AplicationPOT;
import lapr.project.data.DroneDB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DroneTest {
    
    @Mock
    private final Drone droneDB;

    public DroneTest() {
        droneDB = new Drone(1,50.0,60.0);
    }
    
    Drone drone = new Drone(1,50.0,60.0);
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getID method, of class Drone.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Drone instance = new Drone(1, 50.0, 60.0);
        int expResult = 1;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullCharge method, of class Drone.
     */
    @Test
    public void testGetFullCharge() {
        System.out.println("getFullCharge");
        Drone instance = new Drone(1, 50.0, 60.0);
        double expResult = 50.0;
        double result = instance.getFullCharge();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getCurrentCharge method, of class Drone.
     */
    @Test
    public void testGetCurrentCharge() {
        System.out.println("getCurrentCharge");
        Drone instance = new Drone(1, 50.0, 60.0);
        double expResult = 50.0;
        double result = instance.getCurrentCharge();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getPower method, of class Drone.
     */
    @Test
    public void testGetPower() {
        System.out.println("getPower");
        Drone instance = new Drone(1, 50.0, 60.0);
        double expResult = 60.0;
        double result = instance.getPower();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getAverageSpeed method, of class Drone.
     */
    @Test
    public void testGetAverageSpeed() {
        System.out.println("getAverageSpeed");
        Drone instance = new Drone(1, 50.0, 60.0);
        double expResult = 50.0;
        double result = instance.getAverageSpeed();
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of setFullCharge method, of class Drone.
     */
    @Test
    public void testSetFullCharge() {
        System.out.println("setFullCharge");
        double fullCharge = 30.0;
        Drone instance = new Drone(1,50.0,60.0);
        instance.setFullCharge(fullCharge);
        assertEquals(fullCharge,instance.getFullCharge(),0.01);
        
    }

    /**
     * Test of setCurrentCharge method, of class Drone.
     */
    @Test
    public void testSetCurrentCharge() {
        System.out.println("setCurrentCharge");
        double currentCharge = 30.0;
        Drone instance = new Drone(1,50.0,60.0);
        instance.setCurrentCharge(currentCharge);
        assertEquals(currentCharge,instance.getCurrentCharge(),0.01);
    }

    /**
     * Test of setPower method, of class Drone.
     */
    @Test
    public void testSetPower() {
        System.out.println("setPower");
        double power = 30.0;
        Drone instance = new Drone(1,50.0,60.0);
        instance.setPower(power);
        assertEquals(power,instance.getPower(),0.01);
    }

    /**
     * Test of toString method, of class Drone.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Drone instance = new Drone(1,50.0,60.0);
        String expResult = "Drone 1, Full Charge:50.00, Current Charge:50.00, Power:60.00";
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }

       /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEqualsSameObject() {
        System.out.println("equals");

        Drone instance = new Drone(1,50.0,60.0);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsNull() {
        System.out.println("equals");

        Drone instance = new Drone(1,50.0,60.0);
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsDiffObj() {
        System.out.println("equals");

        Drone instance = new Drone(1,50.0,60.0);
        boolean expResult = false;
        boolean result = instance.equals(new Courier ("Name", "12345", "54321", "em@il", "abc"));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsDiffScooters() {
        System.out.println("equals");

        Drone instance = new Drone(1,50.0,60.0);
        Drone d = new Drone(2,50.0,60.0);
        boolean expResult = false;
        boolean result = instance.equals(d);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Drone instance = new Drone(1,50.0,60.0);
        Drone d = new Drone(1,50.0,60.0);
        boolean expResult = true;
        boolean result = instance.equals(d);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCapacity method, of class Drone.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        Drone instance = new Drone(1,50.0,60.0);
        double expResult = 15.0;
        double result = instance.getCapacity();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getDrone method, of class Drone.
     */
    @Test
    public void testGetDrone() {
        System.out.println("getDrone");
        
        Drone instance = new Drone(1,50.0,60.0);
        
        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        when(dbMock.getDrone(1)).thenReturn(instance);
        
        Drone result = instance.getDrone(1);
        assertEquals(instance, result);
    }

    /**
     * Test of save method, of class Drone.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        when(dbMock.getDrone(1)).thenReturn(drone);
        doNothing().when(dbMock).addDrone(drone);
        
        drone.save();
        Drone result = drone.getDrone(1);
        assertEquals(drone, result);
    }
    
    @Test
    public void testSave2() {
        System.out.println("save2");

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).addDrone(droneDB);
        when(dbMock.getDrone(1)).thenThrow(new IllegalArgumentException());

        droneDB.save();
        assertThrows(IllegalArgumentException.class, ()-> 
                {droneDB.getDrone(1);
        });

    }
    
    /**
     * Test of update method, of class Drone.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Drone instance = new Drone(1,50.0,60.0);
        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        when(dbMock.getDrone(1)).thenReturn(instance);
        doNothing().when(dbMock).updateDrone(instance);
        
        instance.update();
        Drone result = instance.getDrone(1);
        assertEquals(instance, result);
    }
}
