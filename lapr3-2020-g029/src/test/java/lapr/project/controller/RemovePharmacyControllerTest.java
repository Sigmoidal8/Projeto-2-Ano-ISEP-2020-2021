/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.AdressDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.utils.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class RemovePharmacyControllerTest {

    private final RemovePharmacyController controller;

    private final Platform plat;

    public RemovePharmacyControllerTest() {
        controller = new RemovePharmacyController();
        plat = AplicationPOT.getInstance().getPlatform();
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
     * Test of getPharmacyList method, of class RemovePharmacyController.
     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("getPharmacyList");
        AplicationPOT app = new AplicationPOT();
        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);

        RemovePharmacyController instance = new RemovePharmacyController();
        List<Pharmacy> expResult = new ArrayList<>();
        expResult.add(pharmacy);

        List<Pharmacy> result = instance.getPharmacyList();
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacy method, of class RemovePharmacyController.
     */
    @Test
    public void testRemovePharmacy() {
        System.out.println("removePharmacy");
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress);
        ParkingLot pl = new ParkingLot("1", 20, 2, "scooter");

        ParkingLotDB dbMock3 = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock3);
        doNothing().when(dbMock3).removeParkingLot("1");
        doNothing().when(dbMock3).addParkingLot(pl, pharmacy.getId());

        pharmacy.addParkingLot(new ParkingLot("1", 20, 2, "scooter"));
        RemovePharmacyController instance = new RemovePharmacyController();

        app.getPlatform().getPharmacyList().addPharmacy(pharmacy);

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        doNothing().when(dbMock).removePharmacy("1");

        AdressDB dbMock2 = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock2).removeAdress("1");

        boolean expResult = true;
        boolean result = instance.removePharmacy(pharmacy.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacy method, of class RemovePharmacyController.
     */
    @Test
    public void testRemovePharmacy2() {
        System.out.println("removePharmacy2");
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress);
        RemovePharmacyController instance = new RemovePharmacyController();

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        doNothing().when(dbMock).removePharmacy("1");

        boolean expResult = false;
        boolean result = instance.removePharmacy(pharmacy.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacyFromGraph method, of class
     * RemovePharmacyController.
     */
    @Test
    public void testRemovePharmacyFromGraphTrue() {
        System.out.println("removePharmacyFromGraph");
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11.11, 0.4);
        Address adress2 = new Address("2", "street 2", 2, "postalCode", "locality", 11.134, 0.477);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress);

        String pharmacyID = "1";
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(adress, adress2, grafo, 2);
        grafo.insertEdge(adress2, adress, grafo, 1.5);

        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo2.insertVertex(adress);
        grafo2.insertVertex(adress2);

        grafo2.insertEdge(adress, adress2, grafo, 2);
        grafo2.insertEdge(adress2, adress, grafo, 1.5);

        RemovePharmacyController instance = new RemovePharmacyController();
        boolean expResult = true;
        boolean result = instance.removePharmacyFromGraph(pharmacyID);
        assertEquals(expResult, result);
    }

}
