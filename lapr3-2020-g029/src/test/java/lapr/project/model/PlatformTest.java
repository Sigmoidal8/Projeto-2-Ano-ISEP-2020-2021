/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.data.AdressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.CourierDB;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DroneDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyProductDB;
import lapr.project.data.ProductDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.StockDB;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatformTest {

    public PlatformTest() {
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
     * Test of getAutorizationFacade method, of class Platform.
     */
    @Test
    public void testGetAutorizationFacade() {
        System.out.println("getAutorizationFacade");
        Platform instance = new Platform();
        AutorizationFacade expResult = instance.getAutorizationFacade();
        AutorizationFacade result = instance.getAutorizationFacade();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientRegister method, of class Platform.
     */
    @Test
    public void testGetClientRegister() {
        System.out.println("getClientRegister");
        Platform instance = new Platform();
        ClientRegister expResult = instance.getClientRegister();
        ClientRegister result = instance.getClientRegister();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryList method, of class Platform.
     */
    @Test
    public void testGetDeliveryList() {
        System.out.println("getDeliveryList");
        Platform instance = new Platform();
        DeliveryList expResult = instance.getDeliveryList();
        DeliveryList result = instance.getDeliveryList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScootersList method, of class Platform.
     */
    @Test
    public void testGetScootersList() {
        System.out.println("getScootersList");
        Platform instance = new Platform();
        ScootersList expResult = instance.getScootersList();
        ScootersList result = instance.getScootersList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourierList method, of class Platform.
     */
    @Test
    public void testGetCourierList() {
        System.out.println("getCourierList");
        Platform instance = new Platform();
        CourierList expResult = instance.getCourierList();
        CourierList result = instance.getCourierList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAvailableScooterList method, of class Platform.
     */
    @Test
    public void testGetAvailableScooterList() {
        System.out.println("getAvailableScooterList");
        Platform instance = new Platform();
        AvailableScooterList expResult = instance.getAvailableScooterList();
        AvailableScooterList result = instance.getAvailableScooterList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailableScooterList method, of class Platform.
     */
    @Test
    public void testGetAvailableScooterList2() {
        System.out.println("getAvailableScooterList");
        Platform instance = new Platform();
        List<Scooter> expResult = instance.getAvailableScooterList().getAvailableScooterList();
        List<Scooter> result = instance.getAvailableScooterList().getAvailableScooterList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyList method, of class Platform.
     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("getPharmacyList");
        Platform instance = new Platform();
        PharmacyList expResult = instance.getPharmacyList();
        PharmacyList result = instance.getPharmacyList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getProductList method, of class Platform.
     */
    @Test
    public void testGetProductList() {
        System.out.println("getProductList");
        Platform instance = new Platform();
        ProductList expResult = instance.getProductList();
        ProductList result = instance.getProductList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductList method, of class Platform.
     */
    @Test
    public void testGetProductList2() {
        System.out.println("getProductList");
        Platform instance = new Platform();
        List<Product> expResult = instance.getProductList().getProductList();
        List<Product> result = instance.getProductList().getProductList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdb method, of class Platform.
     */
    @Test
    public void testGetAdb() {
        System.out.println("getAdb");
        Platform instance = new Platform();
        AdressDB expResult = instance.getAdb();
        AdressDB result = instance.getAdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCdb method, of class Platform.
     */
    @Test
    public void testGetCdb() {
        System.out.println("getCdb");
        Platform instance = new Platform();
        ClientDB expResult = instance.getCdb();
        ClientDB result = instance.getCdb();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCoudb method, of class Platform.
     */
    @Test
    public void testGetCoudb() {
        System.out.println("getCoudb");
        Platform instance = new Platform();
        CourierDB expResult = instance.getCoudb();
        CourierDB result = instance.getCoudb();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDdb method, of class Platform.
     */
    @Test
    public void testGetDdb() {
        System.out.println("getDdb");
        Platform instance = new Platform();
        DeliveryDB expResult = instance.getDdb();
        DeliveryDB result = instance.getDdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdb method, of class Platform.
     */
    @Test
    public void testGetIdb() {
        System.out.println("getIdb");
        Platform instance = new Platform();
        InvoiceDB expResult = instance.getIdb();
        InvoiceDB result = instance.getIdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPldb method, of class Platform.
     */
    @Test
    public void testGetPldb() {
        System.out.println("getPldb");
        Platform instance = new Platform();
        ParkingLotDB expResult = instance.getPldb();
        ParkingLotDB result = instance.getPldb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPdb method, of class Platform.
     */
    @Test
    public void testGetPdb() {
        System.out.println("getPdb");
        Platform instance = new Platform();
        PharmacyDB expResult = instance.getPdb();
        PharmacyDB result = instance.getPdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPpdb method, of class Platform.
     */
    @Test
    public void testGetPpdb() {
        System.out.println("getPpdb");
        Platform instance = new Platform();
        PharmacyProductDB expResult = instance.getPpdb();
        PharmacyProductDB result = instance.getPpdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrdb method, of class Platform.
     */
    @Test
    public void testGetPrdb() {
        System.out.println("getPrdb");
        Platform instance = new Platform();
        ProductDB expResult = instance.getPrdb();
        ProductDB result = instance.getPrdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSdb method, of class Platform.
     */
    @Test
    public void testGetSdb() {
        System.out.println("getSdb");
        Platform instance = new Platform();
        ScooterDB expResult = instance.getSdb();
        ScooterDB result = instance.getSdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStdb method, of class Platform.
     */
    @Test
    public void testGetStdb() {
        System.out.println("getStdb");
        Platform instance = new Platform();
        StockDB expResult = instance.getStdb();
        StockDB result = instance.getStdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDroneMap method, of class Platform.
     */
    @Test
    public void testGetDroneMap() {
        System.out.println("getDroneMap");
        Platform instance = new Platform();
        Graph expResult = new Graph(true);
        Graph result = instance.getDroneMap();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterMap method, of class Platform.
     */
    @Test
    public void testGetScooterMap() {
        System.out.println("getScooterMap");
        Platform instance = new Platform();
        Graph expResult = new Graph(true);
        Graph result = instance.getScooterMap();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListRestrictionsDrone method, of class Platform.
     */
    @Test
    public void testGetListRestrictionsDrone() {
        System.out.println("getListRestrictionsDrone");
        Platform instance = new Platform();
        Map<String, List<String>> expResult = new HashMap<>();
        Map<String, List<String>> result = instance.getListRestrictionsDrone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListRestrictionsScooter method, of class Platform.
     */
    @Test
    public void testGetListRestrictionsScooter() {
        System.out.println("getListRestrictionsScooter");
        Platform instance = new Platform();
        Map<String, List<String>> expResult = new HashMap<>();
        Map<String, List<String>> result = instance.getListRestrictionsScooter();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdb method, of class Platform.
     */
    @Test
    public void testSetAdb() {
        System.out.println("setAdb");
        AdressDB result = new AdressDB();
        Platform instance = new Platform();
        instance.setAdb(result);
        AdressDB expResult = instance.getAdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCdb method, of class Platform.
     */
    @Test
    public void testSetCdb() {
        System.out.println("setCdb");
        ClientDB result = new ClientDB();
        Platform instance = new Platform();
        instance.setCdb(result);
        ClientDB expResult = instance.getCdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCoudb method, of class Platform.
     */
    @Test
    public void testSetCoudb() {
        System.out.println("setCoudb");
        CourierDB result = new CourierDB();
        Platform instance = new Platform();
        instance.setCoudb(result);
        CourierDB expResult = instance.getCoudb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDdb method, of class Platform.
     */
    @Test
    public void testSetDdb() {
        System.out.println("setDdb");
        DeliveryDB result = new DeliveryDB();
        Platform instance = new Platform();
        instance.setDdb(result);
        DeliveryDB expResult = instance.getDdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdb method, of class Platform.
     */
    @Test
    public void testSetIdb() {
        System.out.println("setIdb");
        InvoiceDB result = new InvoiceDB();
        Platform instance = new Platform();
        instance.setIdb(result);
        InvoiceDB expResult = instance.getIdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPldb method, of class Platform.
     */
    @Test
    public void testSetPldb() {
        System.out.println("setPldb");
        ParkingLotDB result = new ParkingLotDB();
        Platform instance = new Platform();
        instance.setPldb(result);
        ParkingLotDB expResult = instance.getPldb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPdb method, of class Platform.
     */
    @Test
    public void testSetPdb() {
        System.out.println("setPdb");
        PharmacyDB result = new PharmacyDB();
        Platform instance = new Platform();
        instance.setPdb(result);
        PharmacyDB expResult = instance.getPdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPpdb method, of class Platform.
     */
    @Test
    public void testSetPpdb() {
        System.out.println("setPpdb");
        PharmacyProductDB result = new PharmacyProductDB();
        Platform instance = new Platform();
        instance.setPpdb(result);
        PharmacyProductDB expResult = instance.getPpdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrdb method, of class Platform.
     */
    @Test
    public void testSetPrdb() {
        System.out.println("setPrdb");
        ProductDB result = new ProductDB();
        Platform instance = new Platform();
        instance.setPrdb(result);
        ProductDB expResult = instance.getPrdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSdb method, of class Platform.
     */
    @Test
    public void testSetSdb() {
        System.out.println("setSdb");
        ScooterDB result = new ScooterDB();
        Platform instance = new Platform();
        instance.setSdb(result);
        ScooterDB expResult = instance.getSdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStdb method, of class Platform.
     */
    @Test
    public void testSetStdb() {
        System.out.println("setStdb");
        StockDB result = new StockDB();
        Platform instance = new Platform();
        instance.setStdb(result);
        StockDB expResult = instance.getStdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDrdb method, of class Platform.
     */
    @Test
    public void testGetDrdb() {
        System.out.println("getDrdb");
        Platform instance = new Platform();
        DroneDB expResult = instance.getDrdb();
        DroneDB result = instance.getDrdb();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDronesList method, of class Platform.
     */
    @Test
    public void testGetDronesList() {
        System.out.println("getDronesList");
        Platform instance = new Platform();
        DronesList expResult = instance.getDronesList();
        DronesList result = instance.getDronesList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDrdb method, of class Platform.
     */
    @Test
    public void testSetDrdb() {
        System.out.println("setDrdb");
        DroneDB drdb = new DroneDB();
        Platform instance = new Platform();
        instance.setDrdb(drdb);

        assertEquals(drdb, instance.getDrdb());
    }

    /**
     * Test of setDrdb method, of class Platform.
     */
    @Test
    public void testInitializeDroneGraph() {
        System.out.println("initializeDroneGraph");
        Address adress1 = new Address("1", "street", 1, "postalCode", "locality", 12.36523, 52.36522);
        Pharmacy p1 = new Pharmacy("1", "designation", "email", "password", adress1);

        Address adress2 = new Address("2", "street2", 1, "postalCode2", "locality2", 12.46523, 50.36522);
        Client c1 = new Client("n785697566", "name", "password", "email", "785697566", adress2);

        Platform instance = new Platform();
        instance.getClientRegister().addClient(c1);
        instance.getPharmacyList().getPharmacyList().add(p1);

        Graph grafo = new Graph(true);
        grafo.insertVertex(c1.getAdress());
        grafo.insertVertex(p1.getAdress());
        grafo.insertEdge(adress1, adress2, "", GraphAlgorithms.calcularDistancia(adress1.getDecimalDegree1(), adress1.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress1, "", GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress1.getDecimalDegree1(), adress1.getDecimalDegree2()));

        instance.initializeDroneGraph();

        assertEquals(grafo, instance.getDroneMap());
    }

    /**
     * Test of setDrdb method, of class Platform.
     */
    @Test
    public void testInitializeDroneGraph2() {
        System.out.println("initializeDroneGraph2");
        Address adress1 = new Address("1", "street", 1, "postalCode", "locality", 12.36523, 52.36522);
        Pharmacy p1 = new Pharmacy("1", "designation", "email", "password", adress1);

        Address adress2 = new Address("2", "street2", 1, "postalCode2", "locality2", 12.46523, 50.36522);
        Client c1 = new Client("n785697566", "name", "password", "email", "785697566", adress2);

        Platform instance = new Platform();
        instance.getClientRegister().addClient(c1);
        instance.getPharmacyList().getPharmacyList().add(p1);
        instance.getListRestrictionsDrone().put("1", new ArrayList<>());
        instance.getListRestrictionsDrone().get("1").add("2");

        Graph grafo = new Graph(true);
        grafo.insertVertex(c1.getAdress());
        grafo.insertVertex(p1.getAdress());
        grafo.insertEdge(adress2, adress1, "", GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress1.getDecimalDegree1(), adress1.getDecimalDegree2()));

        instance.initializeDroneGraph();

        assertEquals(grafo, instance.getDroneMap());
    }

    /**
     * Test of setDrdb method, of class Platform.
     */
    @Test
    public void testInitializeScooterGraph() throws IOException {
        System.out.println("initializeScooterGraph");
        Address adress1 = new Address("1", "street", 1, "postalCode", "locality", 41.16824114028604, -8.689274313801066);
        Pharmacy p1 = new Pharmacy("1", "designation", "email", "password", adress1);

        Address adress2 = new Address("2", "street2", 1, "postalCode2", "locality2", 41.0818631673614, -8.600353715926975);
        Client c1 = new Client("n785697566", "name", "password", "email", "785697566", adress2);

        Platform instance = new Platform();
        instance.getClientRegister().addClient(c1);
        instance.getPharmacyList().getPharmacyList().add(p1);

        Graph grafo = new Graph(true);
        grafo.insertVertex(c1.getAdress());
        grafo.insertVertex(p1.getAdress());
        grafo.insertEdge(adress1, adress2, "", GraphAlgorithms.calcularDistancia(adress1.getDecimalDegree1(), adress1.getDecimalDegree2(), adress2.getDecimalDegree1(), adress2.getDecimalDegree2()));
        grafo.insertEdge(adress2, adress1, "", GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress1.getDecimalDegree1(), adress1.getDecimalDegree2()));

        instance.initializeScooterGraph();

        assertEquals(grafo, instance.getScooterMap());
    }

    /**
     * Test of setDrdb method, of class Platform.
     */
    @Test
    public void testInitializeScooterGraph2() throws IOException {
        System.out.println("testInitializeScooterGraph2");
        Address adress1 = new Address("1", "street", 1, "postalCode", "locality", 41.16824114028604, -8.689274313801066);
        Pharmacy p1 = new Pharmacy("1", "designation", "email", "password", adress1);

        Address adress2 = new Address("2", "street2", 1, "postalCode2", "locality2", 41.0818631673614, -8.600353715926975);
        Client c1 = new Client("n785697566", "name", "password", "email", "785697566", adress2);

        Platform instance = new Platform();
        instance.getClientRegister().addClient(c1);
        instance.getPharmacyList().getPharmacyList().add(p1);
        instance.getListRestrictionsScooter().put("1", new ArrayList<>());
        instance.getListRestrictionsScooter().get("1").add("2");

        Graph grafo = new Graph(true);
        grafo.insertVertex(c1.getAdress());
        grafo.insertVertex(p1.getAdress());
        grafo.insertEdge(adress2, adress1, "", GraphAlgorithms.calcularDistancia(adress2.getDecimalDegree1(), adress2.getDecimalDegree2(), adress1.getDecimalDegree1(), adress1.getDecimalDegree2()));

        instance.initializeScooterGraph();

        assertEquals(grafo, instance.getScooterMap());
    }
}
