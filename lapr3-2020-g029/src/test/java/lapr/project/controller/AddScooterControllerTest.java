/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.ScootersList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class AddScooterControllerTest {
    
    private final AddScooterController controller;
    
    public AddScooterControllerTest() {
        controller = new AddScooterController();
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
     * Test of getScooterList method, of class AddScooterController.
     */
    @Test
    public void testGetScooterList() {
        System.out.println("getScooterList");
        Platform plat=controller.getPlat();
        ScootersList expResult = plat.getScootersList();
        ScootersList result = controller.getScooterList();
        assertEquals(expResult, result);
    }


    /**
     * Test of getScooter method, of class AddScooterController.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");
        int scooterID =1;
        Platform plat=controller.getPlat();
        ScootersList sctl=plat.getScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);     
        sctl.addScooter(s);
        Scooter expResult = s;
        Scooter result = controller.getScooter(scooterID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyList method, of class AddScooterController.
     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("getPharmacyList");
        Platform plat=controller.getPlat();
        PharmacyList expResult = plat.getPharmacyList();
        PharmacyList result = controller.getPharmacyList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacy method, of class AddScooterController.
     */
    @Test
    public void testGetPharmacy() {
        System.out.println("getPharmacy");
        Platform plat=controller.getPlat();
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        plat.getPharmacyList().addPharmacy(p);
        Pharmacy expResult = p;
        Pharmacy result = controller.getPharmacy("15");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailableScooterList method, of class AddScooterController.
     */
    @Test
    public void testGetAvailableScooterList() {
        System.out.println("getAvailableScooterList");
        Platform plat=controller.getPlat();
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        plat.getPharmacyList().addPharmacy(p);
        Scooter s = new Scooter(1,50.0,60.0,0); 
        p.getAsl().addScooter(s);
        Scooter result = controller.getAvailableScooterList(p).getAvailableScooterList().get(0);
        assertEquals(s, result);
    }

    /**
     * Test of addAvailableScooterList method, of class AddScooterController.
     */
     @Test
    public void testAddAvailableScooterList() {
        System.out.println("addAvailableScooterList");
        
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        
        Scooter sc = new Scooter(1,10,10,10,1,p);
        ScooterDB dbMock=mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        AddScooterController instance = new AddScooterController();
        doNothing().when(dbMock).updateScooter(sc);
        
        
        boolean expResult = true;
        boolean result = instance.addAvailableScooterList(sc, p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addAvailableScooterList method, of class AddScooterController.
     */
     @Test
    public void testAddAvailableScooterList2() {
        System.out.println("addAvailableScooterList2");
        
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        
        Scooter sc = new Scooter(1,10,10,10,1,p);
        ScooterDB dbMock=mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        AddScooterController instance = new AddScooterController();
        doNothing().when(dbMock).updateScooter(sc);
        
        instance.addAvailableScooterList(sc, p);
        boolean expResult = false;
        boolean result = instance.addAvailableScooterList(sc, p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPlat method, of class AddScooterController.
     */
     @Test
    public void testGetPlat() {
        System.out.println("getPlat");
        AddScooterController instance = new AddScooterController();
        Platform expResult = AplicationPOT.getInstance().getPlatform();
        Platform result = instance.getPlat();
        assertEquals(expResult, result);
    }
    
}
