/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.AdressDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.utils.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class InsertPharmacyDataControllerTest {
    
    private final InsertPharmacyDataController controller;
    
    public InsertPharmacyDataControllerTest() {
        controller = new InsertPharmacyDataController();
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
     * Test of createAdress method, of class InsertPharmacyDataController.
     */
    @Test
    public void testCreateAdress() {
        controller.createAdress(1, "street", "locality", "postalCode", 1.0, 1.0);
        Address expResult = new Address("street",1,"postalCode","locality", 1.0, 1.0);
        Address res = controller.getAdress();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of createParkingLot method, of class InsertPharmacyDataController.
     */
    @Test
    public void testCreateParkingLot() {
        controller.createParkingLot(1,1,"drone");
        ParkingLot expResult = new ParkingLot(1,1,"drone");
        ParkingLot res = controller.getParkingLot();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of registerPharmacy method, of class InsertPharmacyDataController.
     */
    @Test
    public void testRegisterPharmacy() {
        controller.createAdress(1, "street", "locality", "postalCode", 1.0, 1.0);
        controller.createParkingLot(1,1,"drone");
        controller.createPharmacy("designation", "email", "password");
        
        AdressDB dbMock1 = mock(AdressDB.class);
        ParkingLotDB dbMock2 = mock(ParkingLotDB.class);
        PharmacyDB dbMock3 = mock(PharmacyDB.class);
        
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock1);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock2);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock3);
        
        Address a = new Address("1postalCode","street",1, "locality", "postalCode", 1.0,1.0);
        ParkingLot p = new ParkingLot("1",1,1,"drone");
        Pharmacy ph = new Pharmacy("1","designation","email","password",a);
        
        doNothing().when(dbMock1).addAdress(a);
        doNothing().when(dbMock2).addParkingLot(p,ph.getId());
        doNothing().when(dbMock3).addPharmacy(ph);
        
        boolean expResult = true;
        boolean result = controller.registerPharmacy();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registerPharmacy method, of class InsertPharmacyDataController.
     */
    @Test
    public void testRegisterPharmacy2() {
        AplicationPOT app = new AplicationPOT();
        InsertPharmacyDataController controller1 = new InsertPharmacyDataController();
        controller1.createAdress(1, "street", "locality", "postalCode", 1.0, 1.0);
        controller1.createParkingLot(1,1,"drone");
        controller1.createPharmacy("designation", "email", "password");
        
        AdressDB dbMock1 = mock(AdressDB.class);
        ParkingLotDB dbMock2 = mock(ParkingLotDB.class);
        PharmacyDB dbMock3 = mock(PharmacyDB.class);
        
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock1);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock2);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock3);
        
        Address a = new Address("1postalCode","street",1, "locality", "postalCode", 1.0,1.0);
        ParkingLot p = new ParkingLot("1",1,1,"drone");
        Pharmacy ph = new Pharmacy("1","designation","email","password",a);
        
        doNothing().when(dbMock1).addAdress(a);
        doNothing().when(dbMock2).addParkingLot(p,ph.getId());
        doNothing().when(dbMock3).addPharmacy(ph);
        
        controller1.getPharmacyList().addPharmacy(ph);
        
        boolean expResult = false;
        boolean result = controller1.registerPharmacy();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacy method, of class InsertPharmacyDataController.
     */
    @Test
    public void testGetPharmacy() {
        AplicationPOT app = new AplicationPOT();
        InsertPharmacyDataController controller = new InsertPharmacyDataController();
        controller.createAdress(1, "street", "locality", "postalCode", 1.0, 1.0);
        controller.createPharmacy("designation", "email", "password");
        
        Pharmacy expResult = new Pharmacy("1","designation", "email", "password",controller.getAdress());
        Pharmacy res = controller.getPharmacy();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of getAdress method, of class InsertPharmacyDataController.
     */
    @Test
    public void testGetAdress() {
        controller.createAdress(1, "street", "locality", "postalCode", 1.0, 1.0);
        Address expResult = new Address("street",1,"postalCode","locality", 1.0, 1.0);
        Address res = controller.getAdress();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of getParkingLot method, of class InsertPharmacyDataController.
     */
    @Test
    public void testGetParkingLot() {
        controller.createParkingLot(1,1,"drone");
        ParkingLot expResult = new ParkingLot(1,1,"drone");
        ParkingLot res = controller.getParkingLot();
        
        assertEquals(expResult,res);
    }
    
    /**
     * Test of getPharmacyList method, of class InsertPharmacyDataController.
     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("getPharmacyList");
        AplicationPOT app = new AplicationPOT();
        InsertPharmacyDataController controller1 = new InsertPharmacyDataController();
        Address a = new Address("1postalCode","street",1, "locality", "postalCode", 1.0,1.0);
        Pharmacy ph = new Pharmacy("1","designation","email","password",a);
        PharmacyList expResult = new PharmacyList();
        expResult.addPharmacy(ph);
        app.getPlatform().getPharmacyList().addPharmacy(ph);
        PharmacyList result = controller1.getPharmacyList();
        assertEquals(expResult.getPharmacyList(), result.getPharmacyList());
    }

    
    /**
     * Test of addPharmacyToGraph method, of class InsertPharmacyDataController.
     */
    @Test
    public void testAddPharmacyToGraphTrue() {
        System.out.println("addPharmacyToGraph");
        AplicationPOT app = new AplicationPOT();
        InsertPharmacyDataController controller = new InsertPharmacyDataController();
        controller.createAdress(1, "street", "locality", "postalCode", 41.35195190070675, -8.49001219205514);
        controller.createParkingLot(1,1,"drone");
        controller.createPharmacy("designation", "email", "password");
        
        AdressDB dbMock1 = mock(AdressDB.class);
        ParkingLotDB dbMock2 = mock(ParkingLotDB.class);
        PharmacyDB dbMock3 = mock(PharmacyDB.class);
        
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock1);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock2);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock3);
        
        Address a = new Address("1postalCode","street",1, "locality", "postalCode", 41.35195190070675, -8.49001219205514);
        ParkingLot p = new ParkingLot("1",1,1,"drone");
        Pharmacy ph = new Pharmacy("1","designation","email","password",a);
        
        doNothing().when(dbMock1).addAdress(a);
        doNothing().when(dbMock2).addParkingLot(p,ph.getId());
        doNothing().when(dbMock3).addPharmacy(ph);
        
        boolean expResult = true;
        controller.registerPharmacy();
        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 41.35229504778268, -8.486986406671033));
        
        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 41.35229504778268, -8.486986406671033));
        
        boolean result = controller.addPharmacyToGraph();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addPharmacyToGraph method, of class InsertPharmacyDataController.
     */
    @Test
    public void testAddPharmacyToGraphFalse() {
        System.out.println("addPharmacyToGraph");
        
        InsertPharmacyDataController controller = new InsertPharmacyDataController();
        
        controller.createAdress(1, "street", "locality", "postalCode", 1.0, 1.0);
        controller.createParkingLot(1,1,"drone");
        controller.createPharmacy("designation", "email", "password");
        
        AdressDB dbMock1 = mock(AdressDB.class);
        ParkingLotDB dbMock2 = mock(ParkingLotDB.class);
        PharmacyDB dbMock3 = mock(PharmacyDB.class);
        
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock1);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock2);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock3);
        
        Address a = new Address("1postalCode","street",1, "locality", "postalCode", 1.0, 1.0);
        ParkingLot p = new ParkingLot("1",1,1,"drone");
        Pharmacy ph = new Pharmacy("1","designation","email","password",a);
        
        doNothing().when(dbMock1).addAdress(a);
        doNothing().when(dbMock2).addParkingLot(p,ph.getId());
        doNothing().when(dbMock3).addPharmacy(ph);
        
        boolean expResult = false;
        controller.registerPharmacy();
        
        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 2.0, 2.0));
        
        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 41.35229504778268, -8.486986406671033));
        
        boolean result = controller.addPharmacyToGraph();
        assertEquals(expResult, result);
    }
    
    
}
