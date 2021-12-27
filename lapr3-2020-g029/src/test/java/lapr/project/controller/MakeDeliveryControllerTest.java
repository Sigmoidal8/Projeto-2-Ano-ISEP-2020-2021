/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DroneDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Address;
import lapr.project.model.AvailableScooterList;
import lapr.project.model.Client;
import lapr.project.model.Courier;
import lapr.project.model.Delivery;
import lapr.project.model.DeliveryList;
import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.Invoice;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class MakeDeliveryControllerTest {

    private final MakeDeliveryController controller;

    private final Platform plat;

    public MakeDeliveryControllerTest() {
        controller = new MakeDeliveryController();
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
     * Test of getChoosenDelivery method, of class MakeDeliveryController.
     */
    @Test
    public void testGetChoosenDelivery() {
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery expResult = new Delivery("id", date, 1.0, invoice, pharmacy);

        controller.getDeliveryList().addDelivery(expResult);

        Delivery result = controller.getChoosenDelivery("id");

        assertEquals(expResult, result);
    }

    /**
     * Test of addDeliverySelected method, of class MakeDeliveryController.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testAddDeliverySelectedTrue() throws IOException {
        //Delivery weighs less than the courier backpack capacity in kg

        MakeDeliveryController controller2 = new MakeDeliveryController();
        Date date = new Date();

        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");

        Scooter scooter = new Scooter(123, 1500, 50, 1);

        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).updateScooter(scooter);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.23);
        ParkingLot parkingLot = new ParkingLot(1, 1, "drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("id", date, 1.0, invoice, pharmacy);

        controller2.addDeliverySelected(delivery, c);

        List<Delivery> listTotalOrdersToDeliver = controller2.getDeliverySelectedScooter();

        assertEquals(true, listTotalOrdersToDeliver.contains(delivery));
    }

    /**
     * Test of addDeliverySelected method, of class MakeDeliveryController.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testAddDeliverySelectedTrue3() throws IOException {
        //Delivery weighs less than the courier backpack capacity in kg

        MakeDeliveryController controller2 = new MakeDeliveryController();
        Date date = new Date();

        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");

        Scooter scooter = new Scooter(123, 1500, 50, 1);

        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).updateScooter(scooter);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.23);
        ParkingLot parkingLot = new ParkingLot(1, 1, "drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("id", date, 15.0, invoice, pharmacy);

        controller2.addDeliverySelected(delivery, c);

        List<Delivery> listTotalOrdersToDeliver = controller2.getDeliverySelectedScooter();

        assertEquals(true, listTotalOrdersToDeliver.contains(delivery));
    }

    /**
     * Test of addDeliverySelected method, of class MakeDeliveryController.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testAddDeliverySelectedTrue2() throws IOException {
        //Delivery weighs less than the courier backpack capacity in kg

        MakeDeliveryController controller2 = new MakeDeliveryController();
        Date date = new Date();

        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");

        Scooter scooter = new Scooter(123, 3000, 50, 1);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("id", date, 1.0, invoice, pharmacy);

        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).updateScooter(scooter);

        Address adress2 = new Address("3", "street2", 1, "postalCode2", "locality2", 11.42, 0.5);
        Client client2 = new Client("id2", "name2", "password2", "email2", "1232", adress2);
        Invoice invoice2 = new Invoice("id2", date, 1.0, client2);

        Delivery delivery2 = new Delivery("id2", date, 3.0, invoice2, pharmacy);

        controller2.addDeliverySelected(delivery, c);

        controller2.addDeliverySelected(delivery2, c);

        List<Delivery> listTotalOrdersToDeliver = controller2.getDeliverySelectedScooter();

        assertEquals(true, listTotalOrdersToDeliver.contains(delivery2));
    }

    @Test
    public void testAddDeliverySelectedFalse() throws IOException {
        //Delivery weighs more than the courier backpack capacity in kg
        Date date = new Date();

        MakeDeliveryController controller2 = new MakeDeliveryController();
        Courier courier = new Courier("name", "123", "321", "email", "password");

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("id", date, 16.0, invoice, pharmacy);

        controller2.addDeliverySelected(delivery, courier);

        List<Delivery> listTotalOrdersToDeliver = controller2.getDeliverySelectedScooter();

        assertEquals(listTotalOrdersToDeliver.contains(delivery), false);
    }

    @Test
    public void testAddDeliverySelectedFalse2() throws IOException {
        //Scooter don't have energy 
        Date date = new Date();

        MakeDeliveryController controller2 = new MakeDeliveryController();
        Courier courier = new Courier("name", "123", "321", "email", "password");

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.16824114028604, -8.689274313801066);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("1", "street", 1, "postalCode", "locality", 41.0818631673614, -8.600353715926975);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("id", date, 16.0, invoice, pharmacy);

        controller2.addDeliverySelected(delivery, courier);

        List<Delivery> listTotalOrdersToDeliver = controller2.getDeliverySelectedScooter();

        assertEquals(listTotalOrdersToDeliver.contains(delivery), false);
    }

    @Test
    public void testAddDeliverySelectedFalse3() throws IOException {
        //Scooter don't have energy 
        Date date = new Date();

        MakeDeliveryController controller2 = new MakeDeliveryController();
        Courier courier = new Courier("name", "123", "321", "email", "password");

        Scooter scooter = new Scooter(123, 0, 34, 1);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.16824114028604, -8.689274313801066);
        Client client = new Client("id", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("id", date, 1.0, client);

        Address a2 = new Address("1", "street", 1, "postalCode", "locality", 41.0818631673614, -8.600353715926975);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("id", date, 16.0, invoice, pharmacy);

        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).updateScooter(scooter);

        MakeDeliveryController instance = new MakeDeliveryController();
        Boolean expResult = false;
        Boolean result = instance.addDeliverySelected(delivery, courier);

        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryList method, of class MakeDeliveryController.
     */
    @Test
    public void testGetDeliveryList() {
        DeliveryList expResult = plat.getDeliveryList();
        DeliveryList result = controller.getDeliveryList();

        assertEquals(expResult, result);
    }

    /**
     * Test of addDeliverySelected method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddDeliverySelected_3args() throws Exception {
        System.out.println("addDeliverySelected");

        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 14, 2.23);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 14.11, 2.23);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 8.0, invoice, pharmacy);

        Drone drone = new Drone(1, 3000, 3000, 300);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        MakeDeliveryController instance = new MakeDeliveryController();
        Boolean expResult = true;
        Boolean result = instance.addDeliverySelected(delivery, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of addDeliverySelected method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddDeliverySelected_3args3() throws Exception {
        System.out.println("addDeliverySelected3");

        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 14, 2.23);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 14.11, 2.23);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 15.0, invoice, pharmacy);

        Drone drone = new Drone(1, 3000, 3000, 300);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        MakeDeliveryController instance = new MakeDeliveryController();
        Boolean expResult = true;
        Boolean result = instance.addDeliverySelected(delivery, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of addDeliverySelected method, of class MakeDeliveryController.
     */
    @Test
    public void testAddDeliverySelected_3args2() throws Exception {
        System.out.println("addDeliverySelected");

        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);

        Drone drone = new Drone(1, 3000, 250);

        Address adress2 = new Address("3", "street2", 1, "postalCode2", "locality2", 11.42, 0.5);
        Client client2 = new Client("id2", "name2", "password2", "email2", "1232", adress2);
        Invoice invoice2 = new Invoice("id2", date, 1.0, client2);

        Delivery delivery2 = new Delivery("id2", date, 3.0, invoice2, pharmacy);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        MakeDeliveryController instance = new MakeDeliveryController();
        instance.addDeliverySelected(delivery, drone);
        instance.addDeliverySelected(delivery2, drone);

        assertEquals(true, instance.getDeliverySelectedDrone().contains(delivery2));
    }

    @Test
    public void testAddDeliverySelected_3args_False() throws Exception {
        ////Delivery weighs more than the capacity in kg
        System.out.println("addDeliverySelected");

        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 14, 2.23);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 14.11, 2.23);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 30.0, invoice, pharmacy);

        Drone drone = new Drone(1, 3000, 3000, 300);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        MakeDeliveryController instance = new MakeDeliveryController();
        Boolean expResult = false;
        Boolean result = instance.addDeliverySelected(delivery, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of getShortestPathDroneDelivery method, of class
     * MakeDeliveryController.
     */
    @Test
    public void testGetShortestPathDroneDelivery() throws Exception {
        System.out.println("getShortestPathDroneDelivery");
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 0, 0);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 8.0, invoice, pharmacy);

        Drone drone = new Drone(1, 300, 250, 1);

        MakeDeliveryController instance = new MakeDeliveryController();

        instance.addDeliverySelected(delivery, drone);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertEdge(a2, adress, grafo, 300);
        grafo.insertEdge(adress, a2, grafo, 250);

        List<Address> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(adress);

        List<Address> result = instance.getShortestPathDroneDelivery();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergySpentInDeliver method, of class MakeDeliveryController.
     */
    @Test
    public void testGetEnergySpentInDeliver_Drone() throws IOException {
        System.out.println("getEnergySpentInDeliver");

        Drone drone = new Drone(1, 300, 250);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 8.0, invoice, pharmacy);

        MakeDeliveryController instance = new MakeDeliveryController();

        instance.addDeliverySelected(delivery, drone);

        Graph<Address, Double> grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertEdge(a2, adress, 200.0, 200);

        List<Address> path = instance.getShortestPathDroneDelivery();

        ArrayList<Double> expResult = new ArrayList<>();
        expResult.add(23.77612748814178);
        expResult.add(64.4);
        ArrayList result = instance.getEnergySpentInDeliver(drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeDeliveryFromList method, of class MakeDeliveryController.
     */
    @Test
    public void testRemoveDeliveryFromList() throws IOException {
        System.out.println("removeDeliveryFromList");
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 8.0, invoice, pharmacy);

        MakeDeliveryController instance = new MakeDeliveryController();

        Drone drone = new Drone(1, 1500, 1500);

        DeliveryDB dbMock = mock(DeliveryDB.class);
        AplicationPOT.getInstance().getPlatform().setDdb(dbMock);
        doNothing().when(dbMock).removeDelivery("iddelivery");

        instance.addDeliverySelected(delivery, drone);

        assertEquals(true, instance.getDeliverySelectedDrone().contains(delivery));

        instance.removeDeliveryFromList(delivery);

        assertEquals(false, instance.getDeliveryList().getDeliveryList().contains(delivery));
    }

    /**
     * Test of getPharmacyToPark method, of class MakeDeliveryController.
     */
    @Test
    public void testGetPharmacyToPark() {
        System.out.println("getPharmacyToPark");

        Drone drone = new Drone(1, 1000, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(pharmacy.getAdress());

        assertEquals(pharmacy, result);
    }

    /**
     * Test of getPharmacyToPark method, of class MakeDeliveryController.
     */
    @Test
    public void testGetPharmacyToPark2() {
        System.out.println("getPharmacyToPark");

        Drone drone = new Drone(1, 300, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(pharmacy.getAdress());

        assertEquals(pharmacy, result);
    }

    /**
     * Test of getPharmacyToPark method, of class MakeDeliveryController.
     */
    @Test
    public void testGetPharmacyToPark3() {
        System.out.println("getPharmacyToPark");

        AplicationPOT app = new AplicationPOT();

        Drone drone = new Drone(1, 300, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation2", "email2", "pawssword2", a3);

        ParkingLot pl = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(pl, pharmacy2.getId());

        pharmacy2.registerParkingLot(pl);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(pharmacy.getAdress());

        assertEquals(pharmacy, result);
    }

    /**
     * Test of getPharmacyToPark method, of class MakeDeliveryController.
     */
    @Test
    public void testGetPharmacyToPark7() {
        System.out.println("getPharmacyToPark");

        AplicationPOT app = new AplicationPOT();

        Drone drone = new Drone(1, 278.2675886395733, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation2", "email2", "pawssword2", a3);

        ParkingLot pl = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(pl, pharmacy2.getId());

        pharmacy2.registerParkingLot(pl);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(pharmacy2.getAdress());

        assertEquals(pharmacy2, result);
    }

    @Test
    public void testGetPharmacyToParkNull() {
        System.out.println("getPharmacyToParkNull");

        AplicationPOT app = new AplicationPOT();

        Drone drone = new Drone(1, 300, 260, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation2", "email2", "pawssword2", a3);

        ParkingLot pl = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(pl, pharmacy2.getId());

        pharmacy.registerParkingLot(pl);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(lastClient);

        assertEquals(null, result);
    }

    @Test
    public void testGetPharmacyToPark4() {
        System.out.println("getPharmacyToPark4");

        AplicationPOT app = new AplicationPOT();

        Drone drone = new Drone(1, 300, 260, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation2", "email2", "pawssword2", a3);
        ParkingLot pl = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(pl, pharmacy2.getId());

        pharmacy2.registerParkingLot(pl);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(pharmacy2.getAdress());

        assertEquals(pharmacy2, result);
    }

    @Test
    public void testGetPharmacyToPark5() {
        System.out.println("getPharmacyToPark5");

        AplicationPOT app = new AplicationPOT();

        Drone drone = new Drone(1, 300, 45.81215331919859, 200);
        Address lastClient = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.20, 0.4);
        Address a3 = new Address("3", "street2", 1, "postalCode2", "locality2", 11, 0.3335);

        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation2", "email2", "pawssword2", a3);
        ParkingLot pl = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(pl, pharmacy2.getId());

        pharmacy2.registerParkingLot(pl);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Pharmacy result = instance.getPharmacyToPark(lastClient);

        assertEquals(null, result);
    }

    /**
     * Test of getScootersList method, of class MakeDeliveryController.
     */
    @Test
    public void testGetScootersList() {
        System.out.println("getScootersList");

        AplicationPOT app = new AplicationPOT();
        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        MakeDeliveryController instance = new MakeDeliveryController();
        Scooter scooter = new Scooter(1, 1.0, 1.0, 1.0, 1, pharmacy);
        Scooter scooter2 = new Scooter(2, 1.0, 1.0, 1.0, 1, pharmacy);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);

        AvailableScooterList expResult = AplicationPOT.getInstance().getPlatform().getPharmacyList().getPharmacy("idpharmacy").getAsl();
        expResult.addScooter(scooter);
        expResult.addScooter(scooter2);

        AvailableScooterList result = instance.getScootersList(pharmacy);

        assertEquals(expResult.getAvailableScooterList(), result.getAvailableScooterList());
    }

    /**
     * Test of getDroneList method, of class MakeDeliveryController.
     */
    @Test
    public void testGetDroneList() {
        System.out.println("getDroneList");
        AplicationPOT app = new AplicationPOT();

        Address a2 = new Address("2", "street2", 1, "postalCode2", "locality2", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        MakeDeliveryController instance = new MakeDeliveryController();

        Drone drone1 = new Drone(1, 100, 100, 100);
        Drone drone2 = new Drone(2, 100, 100, 100);

        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);

        DronesList expResult = AplicationPOT.getInstance().getPlatform().getPharmacyList().getPharmacy("idpharmacy").getAvailableDroneList();
        expResult.addDrone(drone1);
        expResult.addDrone(drone2);

        DronesList result = instance.getDroneList(pharmacy);
        assertEquals(expResult.getDroneList(), result.getDroneList());
    }

    /**
     * Test of getDeliverySelected method, of class MakeDeliveryController.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testGetDeliverySelected() throws IOException {
        System.out.println("getDeliverySelected");
        Date date = new Date();

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 11, 0.3);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);

        MakeDeliveryController instance = new MakeDeliveryController();

        List<Delivery> expResult = new ArrayList<>();

        Drone drone = new Drone(1, 1500, 200);

        instance.addDeliverySelected(delivery, drone);

        expResult.add(delivery);

        List<Delivery> result = instance.getDeliverySelectedDrone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourier method, of class MakeDeliveryController.
     */
    @Test
    public void testGetCourier() {
        System.out.println("getCourier");
        String email = "em@il";
        Courier courier = new Courier("Name", "12345", "54321", "em@il", "abc");
        AplicationPOT.getInstance().getPlatform().getCourierList().addCourier(courier);
        MakeDeliveryController instance = new MakeDeliveryController();

        Courier expResult = courier;
        Courier result = instance.getCourier(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyList method, of class MakeDeliveryController.
     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("getPharmacyList");

        AplicationPOT app = new AplicationPOT();
        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 11.11, 0.4);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Pharmacy> expResult = new ArrayList<>();
        expResult.add(pharmacy);

        List<Pharmacy> result = instance.getPharmacyList();
        assertEquals(expResult, result);
    }

    /**
     * Test of sendEmail method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSendEmail() throws Exception {
        System.out.println("sendEmail");
        MakeDeliveryController controller2 = new MakeDeliveryController();

        AplicationPOT app = new AplicationPOT();
        //cria invoice+client
        Address s1 = new Address("Street", 5, "2000-130", "Locality", 0.0, 0.0);
        Client c = new Client("id", "name", "password", "lapr3g029@gmail.com", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);
        HashMap<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);
        Invoice inv = new Invoice(str, date, 2.0f, c);
        inv.setMp(mp);

        //criar pharmacy
        Pharmacy p = new Pharmacy("Pharmacy", "lapr3g029@gmail.com", "Lapr32021", s1);
        controller2.getPharmacyList().add(p);

        MakeDeliveryController instance = new MakeDeliveryController();

        instance.sendEmail(c, inv, p);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter() throws Exception {
        System.out.println("calculateFullPathScooter");
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.34488, -8.47907);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34357, -8.47826);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.34556, -8.47964);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 1000, 1000, 200, 1, pharmacy);
        Address adress3 = new Address("0", "street4", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(adress3);
        expResult.add(adress4);
        expResult.add(adress);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(adress2);
        expResult.add(adress5);
        expResult.add(a2);

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter2() throws Exception {
        System.out.println("calculateFullPathScooter");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.34488, -8.47907);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34357, -8.47826);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.34556, -8.47964);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 1000, 2, 200, 1, pharmacy);
        Address adress3 = new Address("0", "street4", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter3() throws Exception {
        System.out.println("calculateFullPathScooter");
        Date date = new Date();

        Address adress = new Address("1", "street1", 1, "postalCode", "locality", -19.252968, -40.635457);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", -19.320845, -40.690998);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 1000, 1000, 200, 1, pharmacy);
        Address adress3 = new Address("0", "street4", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(adress);
        expResult.add(a2);

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter4() throws Exception {
        System.out.println("calculateFullPathScooter4");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.34488, -8.47907);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34357, -8.49826);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.34556, -8.47964);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);

        pharmacy.addParkingLot(park);

        Scooter scooter = new Scooter(1, 20, 20, 200, 1, pharmacy);
        Address adress3 = new Address("0", "street4", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter5() throws Exception {
        System.out.println("calculateFullPathScooter5");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.34488, -8.47907);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34357, -8.49826);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 42.34357, -8.49826);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.34556, -8.47964);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        Scooter scooter = new Scooter(1, 20, 20, 200, 1, pharmacy);
        Address adress3 = new Address("0", "street4", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter7() throws Exception {
        System.out.println("calculateFullPathScooter7");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.34488, -8.47907);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34357, -8.49826);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 42.34357, -8.49826);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.34556, -8.47964);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy2);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 20, 20, 200, 1, pharmacy);
        Address adress3 = new Address("0", "street4", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter8() throws Exception {
        System.out.println("calculateFullPathScooter8");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 41.350653243257426, -8.483354917921819);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        pharmacy.addParkingLot(park);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 15, 15, 200, 1, pharmacy);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter9() throws Exception {
        System.out.println("calculateFullPathScooter9");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 41.350653243257426, -8.483354917921819);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 24, 24, 200, 1, pharmacy);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter10() throws Exception {
        System.out.println("calculateFullPathScooter10");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 41.350653243257426, -8.483354917921819);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 24, 24, 200, 1, pharmacy);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();
        expResult1.add(a2);
        expResult1.add(adress4);
        expResult1.add(adress4);
        expResult1.add(adress);
        expResult1.add(adress4);
        expResult1.add(adress2);
        expResult1.add(adress4);
        expResult1.add(a3);
        expResult1.add(adress4);
        expResult1.add(adress4);
        expResult1.add(a2);

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter11() throws Exception {
        System.out.println("calculateFullPathScooter11");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 41.34943218082124, -8.48147476803629);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Address adress3 = new Address("5", "street5", 1, "postalCode", "locality", 41.352122187085584, -8.48643148993658);
        Client client3 = new Client("idclient3", "name3", "password", "email", "123", adress3);
        Invoice invoice3 = new Invoice("idinvoice2", date, 1.0, client3);

        Address adress4 = new Address("7", "street7", 1, "postalCode", "locality", 41.35117183828872, -8.483899484810031);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);
        Delivery delivery3 = new Delivery("iddelivery2", date, 1.0, invoice3, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery3);
        deliveries.add(delivery2);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);
        grafo.insertVertex(adress3);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress4, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress4.getDecimalDegree1().toString(), adress4.getDecimalDegree2().toString())));
        grafo.insertEdge(adress4, adress3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress4.getDecimalDegree1().toString(), adress4.getDecimalDegree2().toString(), adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress3, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress3.getDecimalDegree1().toString(), adress3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 6.5, 6.5, 200, 1, pharmacy);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter12() throws Exception {
        System.out.println("calculateFullPathScooter12");
        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 41.350653243257426, -8.483354917921819);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(a2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(adress2, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));

        Scooter scooter = new Scooter(1, 15, 15, 200, 1, pharmacy);
        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
        MakeDeliveryController instance = new MakeDeliveryController();

        List<Address> expResult1 = new ArrayList<>();

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);

        assertEquals(expResult1, result);
    }

//    /**
//     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testCalculateFullPathScooter13() throws Exception {
//        System.out.println("calculateFullPathScooter13");
//        AplicationPOT app = new AplicationPOT();
//        Date date = new Date();
//        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
//        Client client = new Client("idclient", "name", "password", "email", "123", adress);
//        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);
//
//        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
//        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);
//
//        Address a3 = new Address("6", "street6", 1, "postalCode", "locality", 41.350655608702155, -8.48337815732091);
//        Pharmacy pharmacy2 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a3);
//
//        Address a4 = new Address("7", "street7", 1, "postalCode", "locality", 41.352219657939436, -8.486927231481133);
//        Pharmacy pharmacy3 = new Pharmacy("idpharmacy6", "designation6", "email6", "pawssword6", a4);
//
//        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
//        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
//        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);
//
//        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
//        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);
//
//        List<Delivery> deliveries = new ArrayList<>();
//        deliveries.add(delivery);
//        deliveries.add(delivery2);
//
//        ParkingLot park = new ParkingLot(20, 2, "scooter");
//
//        ParkingLotDB dbMock = mock(ParkingLotDB.class);
//        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
//        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
//        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());
//        doNothing().when(dbMock).addParkingLot(park, pharmacy3.getId());
//
//        pharmacy.addParkingLot(park);
//        pharmacy2.addParkingLot(park);
//        pharmacy3.addParkingLot(park);
//
//        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
//        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);
//        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy3);
//
//        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
//        grafo.insertVertex(a2);
//        grafo.insertVertex(adress);
//        grafo.insertVertex(adress2);
//        grafo.insertVertex(a3);
//        grafo.insertVertex(a4);
//
//        grafo.insertEdge(a2, adress, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
//        grafo.insertEdge(adress, a3, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString(), a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString())));
//        grafo.insertEdge(a3, adress2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString())));
//        grafo.insertEdge(adress2, a4, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a3.getDecimalDegree1().toString(), a3.getDecimalDegree2().toString(), a4.getDecimalDegree1().toString(), a4.getDecimalDegree2().toString())));
//        grafo.insertEdge(a4, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(a4.getDecimalDegree1().toString(), a4.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
//        grafo.insertEdge(adress2, a2, grafo, Double.parseDouble(GraphAlgorithms.DistanceCalculator(adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString())));
//
//        Scooter scooter = new Scooter(1, 8.5, 8.5, 200, 1, pharmacy);
//        Address adress4 = new Address("0", "street5", 1, "postalCode", "locality", 41.34556, -8.47964);
//        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);
//        MakeDeliveryController instance = new MakeDeliveryController();
//
//        List<Address> expResult1 = new ArrayList<>();
//
//        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);
//
//        assertEquals(null, result);
//    }

    /**
     * Test of calculateFullPathScooter method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathScooter6() throws Exception {
        System.out.println("calculateFullPathScooter6");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street1", 1, "postalCode", "locality", 41.34488, -8.47907);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street2", 1, "postalCode", "locality", 41.342796, -8.478842);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street3", 1, "postalCode", "locality", 41.34556, -8.47964);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Address a3 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.343310, -8.478447);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        Address a4 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.3432123242134, -8.473895182495323);
        Pharmacy pharmacy3 = new Pharmacy("idpharmacy3", "designation", "email", "pawssword", a4);

        ParkingLot park = new ParkingLot(20, 2, "scooter");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy3.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy3);

        pharmacy2.addParkingLot(park);
        pharmacy3.addParkingLot(park);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(a3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        Scooter scooter = new Scooter(1, 400, 400, 200, 1, pharmacy);
        Address adress5 = new Address("0", "street6", 1, "postalCode", "locality", 41.34556, -8.47964);

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(adress);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(adress2);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(adress5);
        expResult.add(a2);

        List<Address> result = instance.calculateFullPathScooter(deliveries, scooter);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone() throws Exception {
        System.out.println("calculateFullPathDrone");
        Date date = new Date();
        Address adress = new Address("1", "street 2", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street 1", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street 3", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Address adress3 = new Address("4", "street 4", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);
        Client client3 = new Client("idclient3", "name", "password", "email", "123", adress3);
        Invoice invoice3 = new Invoice("idinvoice3", date, 1.0, client3);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);
        Delivery delivery3 = new Delivery("iddelivery3", date, 1.0, invoice3, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);
        deliveries.add(delivery3);

        Drone drone = new Drone(1, 2000, 2000, 200);

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(adress);
        expResult.add(adress2);
        expResult.add(adress3);
        expResult.add(a2);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(adress3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone2() throws Exception {
        System.out.println("calculateFullPathDrone2");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.4455643234, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Address a3 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.289669629918414, -8.473895182495323);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        Address a4 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.3432123242134, -8.473895182495323);
        Pharmacy pharmacy3 = new Pharmacy("idpharmacy3", "designation", "email", "pawssword", a4);

        ParkingLot park = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy3.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy3);

        pharmacy2.addParkingLot(park);
        pharmacy3.addParkingLot(park);

        Drone drone = new Drone(1, 60, 60, 200);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(a3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(adress);
        expResult.add(a3);
        expResult.add(adress2);
        expResult.add(a3);
        expResult.add(a2);
        List< Address> result = instance.calculateFullPathDrone(deliveries, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone3() throws Exception {
        System.out.println("calculateFullPathDrone");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Drone drone = new Drone(1, 2000, 2000, 200);

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     */
    @Test
    public void testCalculateFullPathDrone4() throws Exception {
        System.out.println("calculateFullPathDrone4");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Drone drone = new Drone(1, 1000, 40, 200);

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone5() throws Exception {
        System.out.println("calculateFullPathDrone5");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.4455643234, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Address a3 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.289669629918414, -8.473895182495323);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        Address a4 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.3432123242134, -8.473895182495323);
        Pharmacy pharmacy3 = new Pharmacy("idpharmacy3", "designation", "email", "pawssword", a4);

        ParkingLot park = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy3.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy3);

        pharmacy2.addParkingLot(park);
        pharmacy3.addParkingLot(park);

        Drone drone = new Drone(1, 490, 20, 200);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(a3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone6() throws Exception {
        System.out.println("calculateFullPathDrone6");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.4455643234, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Address a3 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.289669629918414, -8.473895182495323);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        Address a4 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.3432123242134, -8.473895182495323);
        Pharmacy pharmacy3 = new Pharmacy("idpharmacy3", "designation", "email", "pawssword", a4);

        Drone drone = new Drone(1, 490, 20, 200);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(a3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone7() throws Exception {
        System.out.println("calculateFullPathDrone7");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street2", 1, "postalCode", "locality", 41.3533129514202, -8.490511092212316);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Address adress3 = new Address("3", "street3", 1, "postalCode", "locality", 41.35229504778268, -8.486986406671033);
        Client client3 = new Client("idclient2", "name", "password", "email", "123", adress3);
        Invoice invoice3 = new Invoice("idinvoice3", date, 1.0, client3);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);
        Delivery delivery3 = new Delivery("iddelivery3", date, 1.0, invoice3, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery3);
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Address a3 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.350653243257426, -8.483354917921819);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        ParkingLot park = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        Drone drone = new Drone(1, 7.50, 7.50, 200);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);
        grafo.insertVertex(adress3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(a2, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(a3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress3, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateFullPathDrone method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateFullPathDrone8() throws Exception {
        System.out.println("calculateFullPathDrone8");

        AplicationPOT app = new AplicationPOT();
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.348805456466216, -8.480564371595706);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.34453739615322, -8.477187493170355);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street2", 1, "postalCode", "locality", 41.3533129514202, -8.490511092212316);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Address adress3 = new Address("3", "street3", 1, "postalCode", "locality", 41.35229504778268, -8.486986406671033);
        Client client3 = new Client("idclient2", "name", "password", "email", "123", adress3);
        Invoice invoice3 = new Invoice("idinvoice3", date, 1.0, client3);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);
        Delivery delivery3 = new Delivery("iddelivery3", date, 1.0, invoice3, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery3);
        deliveries.add(delivery2);

        Address a3 = new Address("4", "lamelas", 1, "postalCode", "locality", 41.350653243257426, -8.483354917921819);
        Pharmacy pharmacy2 = new Pharmacy("idpharmacy2", "designation", "email", "pawssword", a3);

        ParkingLot park = new ParkingLot(20, 2, "drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(park, pharmacy.getId());
        doNothing().when(dbMock).addParkingLot(park, pharmacy2.getId());

        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        app.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy2);

        pharmacy.addParkingLot(park);
        pharmacy2.addParkingLot(park);

        Drone drone = new Drone(1, 6, 6, 200);

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);
        grafo.insertVertex(a3);
        grafo.insertVertex(adress3);

        grafo.insertEdge(a2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(a2, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress2, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(a3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(a3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a3, adress3, Double.NaN, GraphAlgorithms.calcularDistancia(a3.getDecimalDegree1(), a3.getDecimalDegree2(), adress3.getDecimalDegree1(), adress3.getDecimalDegree2()));
        grafo.insertEdge(adress3, a3, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), a3.getDecimalDegree1(), a3.getDecimalDegree2()));
        grafo.insertEdge(adress3, a2, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress3, adress, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress3, adress2, Double.NaN, GraphAlgorithms.calcularDistancia(adress3.getDecimalDegree1(), adress3.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));

        MakeDeliveryController instance = new MakeDeliveryController();
        List<Address> expResult = new ArrayList<>();

        List<Address> result = instance.calculateFullPathDrone(deliveries, drone);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergySpent method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEnergySpent_3args() throws Exception {
        System.out.println("getEnergySpent");

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);

        double distance = 26000;
        Scooter scooter = new Scooter(1, 1000, 1000, 200, 1);

        List<Address> deliveries = new ArrayList<>();
        deliveries.add(a2);
        deliveries.add(adress);
        deliveries.add(adress2);

        MakeDeliveryController instance = new MakeDeliveryController();
        ArrayList expResult = new ArrayList();
        expResult.add(273.18327484502646);
        expResult.add(4917.298947210476);
        expResult.add(5.287455629426289);
        ArrayList result = instance.getEnergySpent(distance, scooter, deliveries);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergySpent method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEnergySpent_3args2() throws Exception {
        System.out.println("getEnergySpent");

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);

        double distance = 26000;
        Scooter scooter = new Scooter(1, 1000, 1000, 314.6444980351331, 1);

        List<Address> deliveries = new ArrayList<>();
        deliveries.add(a2);
        deliveries.add(adress);
        deliveries.add(adress2);

        MakeDeliveryController instance = new MakeDeliveryController();
        ArrayList expResult = new ArrayList();
        expResult.add(273.18327484502646);
        expResult.add(3125.622076926584);
        expResult.add(8.318344112019368);
        ArrayList result = instance.getEnergySpent(distance, scooter, deliveries);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of getEnergySpentInDeliver method, of class MakeDeliveryController.
//     *
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testGetEnergySpentInDeliver_3args() throws Exception {
//        int excNumb = 1;
//        double fullCharge = 2.0;
//        double power = 3.0;
//        int operational = 1;
//        Scooter scooter = new Scooter(excNumb, fullCharge, power, operational);
//        Address a1 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
//        Address a2 = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
//
//        ArrayList<Double> expResult = new ArrayList<>();
//        expResult.add(24.162420759137312);
//        expResult.add(28994.904910964775);
//
//        ArrayList<Double> result = controller.getEnergySpentInDeliver(scooter, a1, a2);
//
//        assertEquals(expResult, result);
//    }
    /**
     * Test of getEnergySpent method, of class MakeDeliveryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEnergySpent_double_Drone() throws Exception {
        System.out.println("getEnergySpent");

        double distance = 26000;
        Drone drone = new Drone(1, 900, 900, 200);
        MakeDeliveryController instance = new MakeDeliveryController();
        ArrayList expResult = new ArrayList();
        expResult.add(61.572156857868826);
        expResult.add(1922.0);
        ArrayList result = instance.getEnergySpent(distance, drone);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDistance method, of class MakeDeliveryController.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testGetDistance() throws IOException {
        System.out.println("getDistance");

        Date date = new Date();
        Address adress = new Address("1", "street 3", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street 2", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress2 = new Address("3", "street 1", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        Drone drone = new Drone(1, 2000, 2000, 200);

        MakeDeliveryController instance = new MakeDeliveryController();

        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo.insertVertex(a2);
        grafo.insertVertex(adress);
        grafo.insertVertex(adress2);

        grafo.insertEdge(a2, adress, grafo, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
        grafo.insertEdge(adress, adress2, grafo, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(a2, adress2, grafo, GraphAlgorithms.calcularDistancia(a2.getDecimalDegree1(), a2.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress, a2, grafo, GraphAlgorithms.calcularDistancia(adress.getDecimalDegree1(), adress.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));
        grafo.insertEdge(adress2, a2, grafo, GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2()));

        instance.calculateFullPathDrone(deliveries, drone);

        double expResult = 51800.27;
        double result = instance.getDistance();
        assertEquals(expResult, result, 0.5);
    }

    /**
     *
     */
    @Test
    public void testCalcWeigth() {
        System.out.println("getCalcWeigth");
        Date date = new Date();

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address adress2 = new Address("3", "street", 1, "postalCode", "locality", 41.344366334336286, -8.471569272354674);
        Client client2 = new Client("idclient2", "name", "password", "email", "123", adress2);
        Invoice invoice2 = new Invoice("idinvoice2", date, 1.0, client2);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery2", date, 1.0, invoice2, pharmacy);

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);
        deliveries.add(delivery2);

        MakeDeliveryController instance = new MakeDeliveryController();

        double expResult = 2.0;
        double result = instance.calcWeight(deliveries);

        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliverySelectedScooter method, of class
     * MakeDeliveryController.
     */
    @Test
    public void testGetDeliverySelectedScooter() {
        Date date = new Date();

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);

        List<Delivery> expResult = new ArrayList<>();
        expResult.add(delivery);
        controller.getDeliverySelectedScooter().add(delivery);
        List<Delivery> result = controller.getDeliverySelectedScooter();

        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliverySelectedDrone method, of class MakeDeliveryController.
     */
    @Test
    public void testGetDeliverySelectedDrone() {
        Date date = new Date();

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Delivery delivery = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);

        List<Delivery> expResult = new ArrayList<>();
        expResult.add(delivery);
        controller.getDeliverySelectedDrone().add(delivery);
        List<Delivery> result = controller.getDeliverySelectedDrone();

        assertEquals(expResult, result);
    }

    /**
     * Test of getPathToPharmacy method, of class MakeDeliveryController.
     */
    @Test

    public void testGetPathToPharmacy() throws Exception {
        List<Address> adressList = new ArrayList<>();
        Address a1 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Address a2 = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        adressList.add(a1);
        adressList.add(a2);

        DeliveryList dl = AplicationPOT.getInstance().getPlatform().getDeliveryList();
        List<Address> expResult = dl.getShortestPathDroneAdress(adressList);
        List<Address> result = controller.getPathToPharmacy(adressList);

        assertEquals(expResult, result);
    }

    /**
     * Test of calcWeight method, of class MakeDeliveryController.
     */
    @Test
    public void testCalcWeight() {
        Date date = new Date();
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 41.2605435372676, -8.47534582249023);
        Client client = new Client("idclient", "name", "password", "email", "123", adress);
        Invoice invoice = new Invoice("idinvoice", date, 1.0, client);

        Address a2 = new Address("2", "street", 1, "postalCode", "locality", 41.147067071705344, -8.617826480913065);
        Pharmacy pharmacy = new Pharmacy("idpharmacy", "designation", "email", "pawssword", a2);

        Delivery delivery1 = new Delivery("iddelivery", date, 1.0, invoice, pharmacy);
        Delivery delivery2 = new Delivery("iddelivery1", date, 1.0, invoice, pharmacy);

        List<Delivery> listTotalOrdersToDeliver = new ArrayList<>();
        listTotalOrdersToDeliver.add(delivery1);
        listTotalOrdersToDeliver.add(delivery2);

        double result = controller.calcWeight(listTotalOrdersToDeliver);
        double expResult = 2.0;

        assertEquals(expResult, result);
    }
}
