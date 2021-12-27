/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.AdressDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.StockDB;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PharmacyListTest {

    @Mock
    private final PharmacyList pl;
    @Mock
    private Address adress1;
    @Mock
    private ParkingLot parkingLot1;
    @Mock
    private Pharmacy expResult1;

    public PharmacyListTest() {
        this.pl = new PharmacyList();
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
     * Test of createPharmacy method, of class PharmacyList.
     */
    @Test
    public void testCreatePharmacy() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy expResult = new Pharmacy("1", "designation", "email", "password", adress);
        Pharmacy result = pl.createPharmacy("designation", "email", "password", adress);

        assertEquals(expResult, result);
    }

    /**
     * Test of validatePharmacy method, of class PharmacyList.
     */
    @Test
    public void testValidatePharmacy() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress);
        Pharmacy nullPharmacy = null;

        boolean notValid1 = pl.validatePharmacy(nullPharmacy);
        boolean valid = pl.validatePharmacy(pharmacy);

        assertEquals(notValid1, false);
        assertEquals(valid, true);

        pl.addPharmacy(pharmacy);

        boolean notValid2 = pl.validatePharmacy(pharmacy);

        assertEquals(notValid2, false);
    }

    /**
     * Test of getPharmacyList method, of class PharmacyList.
     */
    @Test
    public void testGetPharmacyList() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress);

        pl.addPharmacy(pharmacy);

        ArrayList<Pharmacy> expList = new ArrayList<>();

        expList.add(pharmacy);

        List<Pharmacy> list = pl.getPharmacyList();

        assertEquals(expList, list);
    }

    /**
     * Test of getPharmacy method, of class PharmacyList.
     */
    @Test
    public void testGetPharmacy() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy expResult = new Pharmacy("1", "designation", "email", "password", adress);

        pl.addPharmacy(expResult);

        Pharmacy result = pl.getPharmacy("1");

        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacy method, of class PharmacyList.
     */
    @Test
    public void testGetPharmacy2() {
        Pharmacy expResult = null;
        Pharmacy result = pl.getPharmacy("1");

        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacy method, of class PharmacyList.
     */
    @Test
    public void testGetPharmacy3() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy p = new Pharmacy("1", "designation", "email", "password", adress);

        pl.addPharmacy(p);

        Pharmacy expResult = null;
        Pharmacy result = pl.getPharmacy("2");

        assertEquals(expResult, result);
    }

    /**
     * Test of registerPharmacy method, of class PharmacyList.
     */
    @Test
    public void testRegisterPharmacy() {
        System.out.println("registerPharmacy");

        adress1 = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        parkingLot1 = new ParkingLot(1, 1, "drone");
        expResult1 = new Pharmacy("1", "designation", "email", "password", adress1);

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AdressDB dbMock1 = mock(AdressDB.class);
        ParkingLotDB dbMock2 = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock1);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock2);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        doNothing().when(dbMock).addPharmacy(expResult1);
        doNothing().when(dbMock1).addAdress(adress1);
        doNothing().when(dbMock2).addParkingLot(parkingLot1, expResult1.getId());
        when(dbMock.getPharmacy("1")).thenReturn(expResult1);

        pl.registerPharmacy(expResult1);
        Pharmacy p = expResult1.getPharmacy("1");

        assertEquals(p, expResult1);
    }

    /**
     * Test of registerPharmacy method, of class PharmacyList.
     */
    @Test
    public void testRegisterPharmacy2() {
        System.out.println("registerPharmacy");

        adress1 = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        parkingLot1 = new ParkingLot(1, 1, "drone");
        expResult1 = new Pharmacy("1", "designation", "email", "password", adress1);

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AdressDB dbMock1 = mock(AdressDB.class);
        ParkingLotDB dbMock2 = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock1);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock2);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        doNothing().when(dbMock).addPharmacy(expResult1);
        doNothing().when(dbMock1).addAdress(adress1);
        doNothing().when(dbMock2).addParkingLot(parkingLot1, expResult1.getId());
        when(dbMock.getPharmacy("1")).thenReturn(expResult1);

        pl.registerPharmacy(expResult1);
        Pharmacy p = expResult1.getPharmacy("1");
        assertEquals(p, expResult1);

        boolean expResult = false;
        boolean result = pl.registerPharmacy(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of addPharmacy method, of class PharmacyList.
     */
    @Test
    public void testAddPharmacy() {
        System.out.println("addPharmacy");
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress1);
        PharmacyList instance = new PharmacyList();
        boolean expResult = true;
        boolean result = instance.addPharmacy(pharmacy);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddPharmacyFalse() {
        System.out.println("addPharmacy");
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress1);
        PharmacyList instance = new PharmacyList();
        instance.addPharmacy(pharmacy);
        boolean expResult = false;
        boolean result = instance.addPharmacy(pharmacy);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacy method, of class PharmacyList.
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
        PharmacyList instance = app.getPlatform().getPharmacyList();

        instance.addPharmacy(pharmacy);

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        doNothing().when(dbMock).removePharmacy("1");

        AdressDB dbMock2 = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock2).removeAdress("1");

        boolean expResult = true;
        boolean result = instance.removePharmacy(pharmacy);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacy method, of class PharmacyList.
     */
    @Test
    public void testRemovePharmacy2() {
        System.out.println("removePharmacy");
        Pharmacy pharmacy = new Pharmacy("1", "designation", "email", "password", adress1);
        PharmacyList instance = new PharmacyList();

        boolean expResult = false;
        boolean result = instance.removePharmacy(pharmacy);
        assertEquals(expResult, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock() throws IOException {

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);
        Scooter s = new Scooter(1, 1000.0, 1000.0, 60.0, 1);
        startingPharmacy1.getAsl().addScooter(s);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock2() throws IOException {

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);
        Scooter s = new Scooter(1, 1000.0, 200.0, 60.0, 1);
        startingPharmacy1.getAsl().addScooter(s);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(null, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock3() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s1 = new Scooter(1, 0, 0, 60.0, 1);
        Scooter s2 = new Scooter(2, 228.77635727443578, 228.77635727443578, 60.0, 1);
        Scooter s3 = new Scooter(3, 228.77635727443578, 228.77635727443578, 60.0, 1);
        Scooter s4 = new Scooter(4, 100, 100, 60.0, 1);

        startingPharmacy1.getAsl().addScooter(s1);
        startingPharmacy1.getAsl().addScooter(s2);
        startingPharmacy1.getAsl().addScooter(s3);
        startingPharmacy1.getAsl().addScooter(s4);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        double minDistance = Double.MAX_VALUE;
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock4() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(5);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s1 = new Scooter(1, 1000.0, 0, 60.0, 1);
        Scooter s2 = new Scooter(2, 232.26053561089185, 232.26053561089185, 60.0, 1);
        Scooter s3 = new Scooter(3, 232.26053561089185, 232.26053561089185, 60.0, 1);
        Scooter s4 = new Scooter(4, 100, 100, 60.0, 1);

        startingPharmacy1.getAsl().addScooter(s1);
        startingPharmacy1.getAsl().addScooter(s2);
        startingPharmacy1.getAsl().addScooter(s3);
        startingPharmacy1.getAsl().addScooter(s4);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        double minDistance = Double.MAX_VALUE;
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock5() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s1 = new Scooter(1, -1, -1, 60.0, 1);
        Scooter s2 = new Scooter(2, 228.77635727443578, 228.77635727443578, 60.0, 1);
        Scooter s3 = new Scooter(3, 228.77635727443578, 228.77635727443578, 60.0, 1);
        Scooter s4 = new Scooter(4, 100, 100, 60.0, 1);

        startingPharmacy1.getAsl().addScooter(s1);
        startingPharmacy1.getAsl().addScooter(s2);
        startingPharmacy1.getAsl().addScooter(s3);
        startingPharmacy1.getAsl().addScooter(s4);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock6() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s1 = new Scooter(1, 0.0, 0.0, 60.0, 1);
        Scooter s2 = new Scooter(2, -1, -1, 60.0, 1);
        Scooter s3 = new Scooter(3, 228.77635727443578, 228.77635727443578, 60.0, 1);

        startingPharmacy1.getAsl().addScooter(s1);
        startingPharmacy1.getAsl().addScooter(s2);
        startingPharmacy1.getAsl().addScooter(s3);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock7() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s2 = new Scooter(2, 228.77635727443578, 228.77635727443578, 60.0, 1);
        Scooter s3 = new Scooter(3, 228.77635727443578, 228.77635727443578, 60.0, 1);

        startingPharmacy1.getAsl().addScooter(s2);
        startingPharmacy1.getAsl().addScooter(s3);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock8() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s3 = new Scooter(3, 228.77635727443578, 228.77635727443578, 60.0, 1);
        Scooter s4 = new Scooter(4, 100, 100, 60.0, 1);

        startingPharmacy1.getAsl().addScooter(s3);
        startingPharmacy1.getAsl().addScooter(s4);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of closestPharmacyWithStock method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void closestPharmacyWithStock9() throws IOException {
//        Adress adressStartingPharmacy1 = new Adress("1", "street", 23, "postalCode", "locality", 82.466667, -62.5);
//        Adress adressPharmacy2 = new Adress("2", "street", 23, "postalCode", "locality", -89.9975, 139.272778);

        PharmacyList instance = new PharmacyList();
        Address adressStartingPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy startingPharmacy1 = new Pharmacy("1", "designation", "email", "password", adressStartingPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(startingPharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Product prod = new Product("1", "ilvicon", 2, 10);

        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock("1", "1")).thenReturn(0);
        when(dbMock.getStock("2", "1")).thenReturn(5);
        when(dbMock.getStock("3", "1")).thenReturn(5);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        plat.getPharmacyList().addPharmacy(startingPharmacy1);

        Scooter s3 = new Scooter(3, 228.77635727443578, 228.77635727443578, 330.0, 1);

        startingPharmacy1.getAsl().addScooter(s3);

        Pharmacy result = instance.closestPharmacyWithStock(pharmacyList, startingPharmacy1, prod, 5);
        assertEquals(pharmacy2, result);
    }

    /**
     * Test of FowardDeliveryToClosestPharmacy method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void fowardDeliveryToClosestPharmacy() throws IOException {
        PharmacyList instance = new PharmacyList();
        Address clientAdress = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);

        Address adressPharmacy1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy1 = new Pharmacy("1", "designation", "email", "password", adressPharmacy1);

        Address adressPharmacy2 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(pharmacy1);
        pharmacyList.add(pharmacy2);

        Pharmacy result = instance.fowardDeliveryToClosestPharmacy(pharmacyList, clientAdress);
        assertEquals(pharmacy1, result);
    }

    /**
     * Test of FowardDeliveryToClosestPharmacy method, of class PharmacyList.
     *
     * @throws java.io.IOException
     */
    @Test
    public void fowardDeliveryToClosestPharmacy2() throws IOException {
        PharmacyList instance = new PharmacyList();
        Address clientAdress = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);

        Address adressPharmacy1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy1 = new Pharmacy("1", "designation", "email", "password", adressPharmacy1);

        Address adressPharmacy2 = new Address("3", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("4", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        List<Pharmacy> pharmacyList = new ArrayList<>();
        pharmacyList.add(pharmacy1);
        pharmacyList.add(pharmacy2);
        pharmacyList.add(pharmacy3);

        Pharmacy result = instance.fowardDeliveryToClosestPharmacy(pharmacyList, clientAdress);
        assertEquals(pharmacy1, result);
    }

    /**
     * Test of addPharmacyAdressToGraph method, of class PharmacyList.
     */
    @Test
    public void testAddPharmacyAdressToGraph() {
        System.out.println("addPharmacyAdressToGraph");
        AplicationPOT app = new AplicationPOT();
        
        Address adressPharmacy1 = new Address("1", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Pharmacy pharmacy1 = new Pharmacy("1", "designation", "email", "password", adressPharmacy1);

        Address adressPharmacy2 = new Address("2", "street", 23, "postalCode", "locality", 41.11338378529527, -8.621888264723509);
        Pharmacy pharmacy2 = new Pharmacy("2", "designation", "email", "password", adressPharmacy2);

        Address adressPharmacy3 = new Address("3", "street", 23, "postalCode", "locality", 41.12341, -8.466667);
        Pharmacy pharmacy3 = new Pharmacy("3", "designation", "email", "password", adressPharmacy3);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(adressPharmacy2);
        grafo.insertVertex(adressPharmacy3);

        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(adressPharmacy2);
        grafo2.insertVertex(adressPharmacy3);
        
        ArrayList<String> list = new ArrayList<>();
        list.add(adressPharmacy2.getId());

        ArrayList<String> list2 = new ArrayList<>();
        list2.add(adressPharmacy2.getId());
        
        ArrayList<String> list3 = new ArrayList<>();
        list3.add(adressPharmacy2.getId());
        
        app.getPlatform().getListRestrictionsScooter().put("1", list);
        app.getPlatform().getListRestrictionsScooter().put("2", list2);
        app.getPlatform().getListRestrictionsScooter().put("3", list3);
        
        app.getPlatform().getListRestrictionsDrone().put("1", list);
        app.getPlatform().getListRestrictionsDrone().put("2", list2);
        app.getPlatform().getListRestrictionsDrone().put("3", list3);

        PharmacyList instance = new PharmacyList();
        boolean expResult = true;
        boolean result = instance.addPharmacyAdressToGraph(pharmacy1);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacyAdressToGraph method, of class PharmacyList.
     */
    @Test
    public void testRemovePharmacyAdressToGraph() {
        System.out.println("removePharmacyAdressToGraph");

        Address adress1 = new Address("1", "street", 1, "postalCode", "locality", 41.16824114028604, -8.689274313801066);
        Pharmacy p1 = new Pharmacy("1", "designation", "email", "password", adress1);

        Address adress2 = new Address("2", "street2", 1, "postalCode2", "locality2", 41.0818631673614, -8.600353715926975);
        Pharmacy c1 = new Pharmacy("2", "designation", "email", "password", adress2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(c1.getAdress());
        grafo.insertVertex(p1.getAdress());
        grafo.insertEdge(adress1, adress2, "", GraphAlgorithms.calcularDistancia(adress1.getDecimalDegree1(), adress1.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress1, "", GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress1.getDecimalDegree1(), adress1.getDecimalDegree2()));

        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(c1.getAdress());
        grafo2.insertVertex(p1.getAdress());
        grafo2.insertEdge(adress1, adress2, "", GraphAlgorithms.calcularDistancia(adress1.getDecimalDegree1(), adress1.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo2.insertEdge(adress2, adress1, "", GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress1.getDecimalDegree1(), adress1.getDecimalDegree2()));

        PharmacyList instance = new PharmacyList();
        instance.addPharmacy(c1);
        instance.addPharmacy(p1);

        boolean expResult = true;
        boolean result = instance.removePharmacyAdressToGraph(p1);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePharmacyAdressToGraph method, of class PharmacyList.
     */
    @Test
    public void testRemovePharmacyAdressToGraph2() {
        System.out.println("removePharmacyAdressToGraph");
        
        Address adress1 = new Address("1", "street", 1, "postalCode", "locality", 41.16824114028604, -8.689274313801066);
        Pharmacy p1 = new Pharmacy("1", "designation", "email", "password", adress1);

        Address adress2 = new Address("2", "street2", 1, "postalCode2", "locality2", 41.0818631673614, -8.600353715926975);
        Pharmacy c1 = new Pharmacy("2", "designation", "email", "password", adress2);

        PharmacyList instance = new PharmacyList();
        instance.addPharmacy(c1);
        instance.addPharmacy(p1);

        boolean expResult = false;
        boolean result = instance.removePharmacyAdressToGraph(p1);
        assertEquals(expResult, result);
    }
    
}
