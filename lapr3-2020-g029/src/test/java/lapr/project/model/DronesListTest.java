/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.DroneDB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class DronesListTest {
    
    public DronesListTest() {
    }

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
     * Test of addDrone method, of class DronesList.
     */
    @Test
    public void testAddDrone() {
        System.out.println("addDrone");
        Drone d= new Drone(1, 50.0, 60.0);
        DronesList instance = new DronesList();
        boolean expResult = true;
        boolean result = instance.addDrone(d);
        assertEquals(expResult, result);
    }
    
      /**
     * Test of addDrone method, of class DronesList.
     */
    @Test
    public void testAddDroneFalse() {
        System.out.println("addDroneFalse");
        Drone d= new Drone(1, 50.0, 60.0);
        DronesList instance = new DronesList();
        instance.addDrone(d);
        boolean expResult = false;
        boolean result = instance.addDrone(d);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDroneList method, of class DronesList.
     */
    @Test
    public void testGetDroneList() {
        System.out.println("getDroneList");
        DronesList instance = new DronesList();
        List<Drone> expResult = new ArrayList<>();
        List<Drone> result = instance.getDroneList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyLessDrones method, of class DronesList.
     */
    @Test
    public void testGetPharmacyLessDrones() {
        AplicationPOT ap=new AplicationPOT();
        System.out.println("getPharmacyLessDrones");
        DronesList instance = new DronesList();
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", adress); 
        PharmacyList pl = ap.getInstance().getPlatform().getPharmacyList();  
        pl.addPharmacy(p);
        Drone d= new Drone(1, 50.0, 60.0);
        Drone d2= new Drone(2, 50.0, 60.0);
        p.getAvailableDroneList().addDrone(d);
        instance.getDroneList().add(d);
        instance.getDroneList().add(d2);
        boolean expResult=true;
        List<Drone> newList=instance.getPharmacyLessDrones();
        boolean result=!newList.contains(d) && newList.contains(d2);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDrone method, of class DronesList.
     */
    @Test
    public void testGetDrone() {
        System.out.println("getDrone");
        DronesList instance = new DronesList();
        Drone d= new Drone(1, 50.0, 60.0);
        instance.getDroneList().add(d);
        Drone result = instance.getDrone(d.getID());
        assertEquals(d, result);
    }
    
     /**
     * Test of getDrone method, of class DronesList.
     */
    @Test
    public void testGetDrone2() {
        System.out.println("getDrone2");
        DronesList instance = new DronesList();
        Drone d= new Drone(2, 50.0, 60.0);
        instance.getDroneList().add(d);
        Drone result = instance.getDrone(1);
        assertEquals(null, result);
    }

    /**
     * Test of generateID method, of class DronesList.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        DronesList instance = new DronesList();
        int expResult = 1;
        int result = instance.generateID();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of generateID method, of class DronesList.
     */
    @Test
    public void testGenerateID2() {
        System.out.println("generateID2");
        DronesList instance = new DronesList();
        Drone d= new Drone(1, 50.0, 60.0);
        instance.addDrone(d);
        int expResult = 2;
        int result = instance.generateID();
        assertEquals(expResult, result);
    }

    /**
     * Test of newDrone method, of class DronesList.
     */
    @Test
    public void testNewDrone() {
        System.out.println("newDrone");
        int id = 1;
        double fullCharge = 30.0;
        double power = 40.0;
        DronesList instance = new DronesList();
        Drone expResult=new Drone(1,30.0,40.0);
        Drone result = instance.newDrone(id, fullCharge, power);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone() {
        System.out.println("validateDrone");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,50.0,60.0);
        instance.addDrone(d);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone2() {
        System.out.println("validateDrone2");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,-50.0,60.0);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone3() {
        System.out.println("validateDrone3");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,50.0,-60.0);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone4() {
        System.out.println("validateDrone4");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,50.0,60.0);
        boolean expResult = true;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone5() {
        System.out.println("validateDrone5");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,-50.0,-60.0);
        instance.addDrone(d);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone6() {
        System.out.println("validateDrone6");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,-50.0,-60.0);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of validateDrone method, of class DronesList.
     */
    @Test
    public void testValidateDrone7() {
        System.out.println("validateDrone7");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,-50.0,60.0);
        instance.addDrone(d);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    
     @Test
    public void testValidateDrone8() {
        System.out.println("validateDrone8");
        DronesList instance = new DronesList();
        Drone d = new Drone(1,50.0,-60.0);
        instance.addDrone(d);
        boolean expResult = false;
        boolean result = instance.validateDrone(d);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of registerDrone method, of class DronesList.
     */
    @Test
    public void testRegisterDroneTrue() {
        System.out.println("registerDrone");
        Drone d = new Drone(1,40.0,30.0);
        DronesList instance = new DronesList();
        boolean expResult = true;
        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).addDrone(d);
        boolean result = instance.registerDrone(d);
        assertEquals(expResult, result);
    }
    /**
     * Test of registerDrone method, of class DronesList.
     */
    @Test
    public void testRegisterDroneFalse() {
        System.out.println("registerDrone");
        Drone d = new Drone(1,40.0,30.0);
        DronesList instance = new DronesList();
        boolean expResult = false;
        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).addDrone(d);
        instance.registerDrone(d);
        boolean result = instance.registerDrone(d);
        assertEquals(expResult, result);
    }
}
