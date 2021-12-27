/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DroneDB;
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

public class DeliveryListTest {

    public DeliveryListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDeliveryList method, of class DeliveryList.
     */
    @Test
    public void testGetDeliveryList_0args() {
        System.out.println("getDeliveryList");
        DeliveryList instance = new DeliveryList();
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        instance.addDelivery(delivery);
        List<Delivery> expResult = new ArrayList<>();
        expResult.add(delivery);

        List<Delivery> result = instance.getDeliveryList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryList method, of class DeliveryList.
     */
    @Test
    public void testGetDeliveryList_Pharmacy() {
        System.out.println("getDeliveryList");
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        DeliveryList instance = new DeliveryList();
        List<Delivery> expResult = instance.getDeliveryList();
        expResult.add(delivery);

        List<Delivery> result = instance.getDeliveryList(pharmacy);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDeliveryList_Pharmacy2() {
        System.out.println("getDeliveryList");

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        DeliveryList instance = new DeliveryList();
        List<Delivery> expResult = new ArrayList<>();

        List<Delivery> result = instance.getDeliveryList(pharmacy);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDeliveryList_Pharmacy3() {
        System.out.println("getDeliveryList");

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressPharmacy2 = new Address("12", "street2", 232, "postalCode2", "locality2", 41.1244, -8.465567);
        Pharmacy pharmacy2 = new Pharmacy("id2", "designation", "email", "password", adressPharmacy2);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        DeliveryList instance = new DeliveryList();
        List<Delivery> dlPh1 = instance.getDeliveryList(pharmacy2);
        List<Delivery> expResult = instance.getDeliveryList(pharmacy2);
        dlPh1.add(delivery);

        List<Delivery> result = instance.getDeliveryList(pharmacy2);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDeliveryList_Pharmacy4() {
        System.out.println("getDeliveryList");

        DeliveryList instance = new DeliveryList();
        List<Delivery> expResult = instance.getDeliveryList(null);
        List<Delivery> result = instance.getDeliveryList(null);
        assertEquals(expResult, result);
    }

    /**
     * Test of addDelivery method, of class DeliveryList.
     */
    @Test
    public void testAddDelivery() {
        System.out.println("addDelivery");
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        DeliveryList instance = new DeliveryList();
        boolean expResult = true;
        boolean result = instance.addDelivery(delivery);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of addDelivery method, of class DeliveryList.
     */
    @Test
    public void testAddDelivery2() {
        System.out.println("addDelivery2");
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        DeliveryList instance = new DeliveryList();
        
        instance.addDelivery(delivery);
        boolean expResult = false;
        boolean result = instance.addDelivery(delivery);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifyDelivery method, of class DeliveryList.
     */
    @Test
    public void testVerifyDelivery() {
        System.out.println("verifyDelivery");
        DeliveryList instance = new DeliveryList();

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);
        instance.addDelivery(delivery);

        String id = "123";
        Delivery expResult = delivery;
        Delivery result = instance.verifyDelivery(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifyDelovery method, of class DeliveryList.
     */
    @Test
    public void testVerifyDelivery2() {
        System.out.println("verifyDelivery");

        String id = "12799998989898";
        DeliveryList instance = new DeliveryList();
        Delivery expResult = null;
        Delivery result = instance.verifyDelivery(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerifyDelivery3() {
        System.out.println("verifyDelivery");
        DeliveryList instance = new DeliveryList();

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);
        instance.addDelivery(delivery);

        String id = "123456";
        Delivery expResult = null;
        Delivery result = instance.verifyDelivery(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of generateId method, of class BuyAProductController.
     */
    @Test
    public void testGenerateId() {
        System.out.println("generateId");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String expResult = "DEL/" + formatter2.format(date) + "/" + formatter.format(date);
        DeliveryList instance = new DeliveryList();
        String result = instance.generateId(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerDelivery method, of class DeliveryList.
     */
    @Test
    public void testRegisterDelivery() {
        System.out.println("registerDelivery");
        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);
        Delivery delivery = new Delivery("123", new Date(), 2.0f, invoice, pharmacy);

        DeliveryDB dbMock = mock(DeliveryDB.class);
        AplicationPOT.getInstance().getPlatform().setDdb(dbMock);
        doNothing().when(dbMock).addDelivery(delivery);

        DeliveryList instance = new DeliveryList();
        instance.registerDelivery(delivery);
    }

    /**
     * Test of calcWeight method, of class DeliveryList.
     */
    @Test
    public void testCalcWeight() {
        System.out.println("calcWeight");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c = new Client("id", "name", "password", "email", "12", s1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String str = "INV/" + formatter2.format(date) + "/" + formatter.format(date);

        Invoice inv = new Invoice(str, date, 2.0f, c);

        HashMap<Product, Integer> mp = new HashMap<>();
        Product pr = new Product("id", "name", 2.0f, 0.5f);
        mp.put(pr, 3);

        inv.setMp(mp);

        DeliveryList instance = new DeliveryList();

        double expResult = 1.5;
        double result = instance.calcWeight(inv);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of removedDeliveryFromList method, of class DeliveryList.
     */
    @Test
    public void testRemovedDeliveryFromList() {
        System.out.println("removedDeliveryFromList");

        DeliveryDB dbMock = mock(DeliveryDB.class);
        doNothing().when(dbMock).removeDelivery("123");
        AplicationPOT.getInstance().getPlatform().setDdb(dbMock);

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);
        ParkingLot parkingLot = new ParkingLot(1, 1, "drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        Address adressClient1 = new Address("2", "street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        Client client = new Client("id", "name", "password", "email", "12", adressClient1);
        Invoice invoice = new Invoice("1", new Date(), 20, client);

        Delivery delivery = new Delivery("123", new Date(), 0.0f, invoice, pharmacy);

        DeliveryList instance = new DeliveryList();

        instance.addDelivery(delivery);
        assertEquals(1, instance.getDeliveryList().size());

        instance.removedDeliveryFromList(delivery);

        assertEquals(0, instance.getDeliveryList().size());
    }

    /**
     * Test of getEnergySpentInDeliver method, of class DeliveryList.
     */
    @Test
    public void testGetEnergySpentInDeliver_double_Drone() {
        System.out.println("getEnergySpentInDeliver");

        Drone drone = new Drone(1, 300, 250);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        double distance = 13000.0;
        DeliveryList instance = new DeliveryList();
        ArrayList expResult = new ArrayList();
        expResult.add(35.71375513576572);
        expResult.add(986.0);
        ArrayList result = instance.getEnergySpentInDeliver(distance, drone, 1);
        assertEquals(expResult, result);
    }

      /**
     * Test of getEnergySpentInDeliver method, of class DeliveryList.
     */
    @Test
    public void testGetEnergySpentInDeliver2() {
        System.out.println("getEnergySpentInDeliver");

        Drone drone = new Drone(1, 300, 250);

        DroneDB dbMock = mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(drone);

        double distance = 13000.0;
        DeliveryList instance = new DeliveryList();
        instance.setWindSpeed(2.0);
        instance.setWindAngle(1.0);
        ArrayList expResult = new ArrayList();
        expResult.add(325.9123963426483);
        expResult.add(986.0);
        ArrayList result = instance.getEnergySpentInDeliver(distance, drone, 1);
        assertEquals(expResult, result);
    }
    /**
     * Test of isPharmacyAChargeAway method, of class DeliveryList.
     */
    @Test
    public void testIsPharmacyAChargeAway() {
        System.out.println("isPharmacyAChargeAway");
        Address lastClient = new Address("1", "street", 23, "postalCode", "locality", 41, -8.466667);

        Address adressPharmacy = new Address("1", "street", 23, "postalCode", "locality", 41.21, -8.466667);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password", adressPharmacy);

        DeliveryList instance = new DeliveryList();
        double expResult = 23350.93;
        double result = instance.isPharmacyAChargeAway(lastClient, pharmacy);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of getCdScooter method, of class DeliveryList.
     */
    @Test
    public void testGetCdScooter() {
        System.out.println("getCdScooter");
        DeliveryList instance = new DeliveryList();
        double expResult = 0.7;
        double result = instance.getCdScooter();
        assertEquals(expResult, result, 0.0);
    }


    /**
     * Test of getAreaScooter method, of class DeliveryList.
     */
    @Test
    public void testGetAreaScooter() {
        System.out.println("getAreaScooter");
        DeliveryList instance = new DeliveryList();
        double expResult = 1;
        double result = instance.getAreaScooter();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAreaDrone method, of class DeliveryList.
     */
    @Test
    public void testGetAreaDrone() {
        System.out.println("getAreaDrone");
        DeliveryList instance = new DeliveryList();
        double expResult = 0.3;
        double result = instance.getAreaDrone();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDensity method, of class DeliveryList.
     */
    @Test
    public void testGetDensity() {
        System.out.println("getDensity");
        DeliveryList instance = new DeliveryList();
        double expResult = 1.225;
        double result = instance.getDensity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRolingCoeficient method, of class DeliveryList.
     */
    @Test
    public void testGetRolingCoeficient() {
        System.out.println("getRolingCoeficient");
        DeliveryList instance = new DeliveryList();
        double expResult = 0.008;
        double result = instance.getRolingCoeficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getGravityForce method, of class DeliveryList.
     */
    @Test
    public void testGetGravityForce() {
        System.out.println("getGravityForce");
        DeliveryList instance = new DeliveryList();
        double expResult = 9.8;
        double result = instance.getGravityForce();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTotalMassScooter method, of class DeliveryList.
     */
    @Test
    public void testGetTotalMassScooter() {
        System.out.println("getTotalMassScooter");
        DeliveryList instance = new DeliveryList();
        double expResult = 120;
        double result = instance.getTotalMassScooter();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTotalMassDrone method, of class DeliveryList.
     */
    @Test
    public void testGetTotalMassDrone() {
        System.out.println("getTotalMassDrone");
        DeliveryList instance = new DeliveryList();
        double expResult = 5;
        double result = instance.getTotalMassDrone();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWindSpeed method, of class DeliveryList.
     */
    @Test
    public void testGetWindSpeed() {
        System.out.println("getWindSpeed");
        DeliveryList instance = new DeliveryList();
        double expResult = 0;
        double result = instance.getWindSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWindAngle method, of class DeliveryList.
     */
    @Test
    public void testGetWindAngle() {
        System.out.println("getWindAngle");
        DeliveryList instance = new DeliveryList();
        double expResult = 0;
        double result = instance.getWindAngle();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLiftSpeed method, of class DeliveryList.
     */
    @Test
    public void testGetLiftSpeed() {
        System.out.println("getLiftSpeed");
        DeliveryList instance = new DeliveryList();
        double expResult = 6;
        double result = instance.getLiftSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLiftDistance method, of class DeliveryList.
     */
    @Test
    public void testGetLiftDistance() {
        System.out.println("getLiftDistance");
        DeliveryList instance = new DeliveryList();
        double expResult = 150;
        double result = instance.getLiftDistance();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setCdScooter method, of class DeliveryList.
     */
    @Test
    public void testSetCdScooter() {
        System.out.println("setCdScooter");
        double cdScooter = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setCdScooter(cdScooter);
        assertEquals(cdScooter, instance.getCdScooter());
    }


    /**
     * Test of setAreaScooter method, of class DeliveryList.
     */
    @Test
    public void testSetAreaScooter() {
        System.out.println("setAreaScooter");
        double areaScooter = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setAreaScooter(areaScooter);
        assertEquals(areaScooter, instance.getAreaScooter());
    }

    /**
     * Test of setAreaDrone method, of class DeliveryList.
     */
    @Test
    public void testSetAreaDrone() {
        System.out.println("setAreaDrone");
        double areaDrone = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setAreaDrone(areaDrone);
        assertEquals(areaDrone, instance.getAreaDrone());
    }

    /**
     * Test of setDensity method, of class DeliveryList.
     */
    @Test
    public void testSetDensity() {
        System.out.println("setDensity");
        double density = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setDensity(density);
        assertEquals(density, instance.getDensity());
    }

    /**
     * Test of setRolingCoeficient method, of class DeliveryList.
     */
    @Test
    public void testSetRolingCoeficient() {
        System.out.println("setRolingCoeficient");
        double rolingCoeficient = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setRolingCoeficient(rolingCoeficient);
        assertEquals(rolingCoeficient, instance.getRolingCoeficient());
    }

    /**
     * Test of setGravityForce method, of class DeliveryList.
     */
    @Test
    public void testSetGravityForce() {
        System.out.println("setGravityForce");
        double gravityForce = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setGravityForce(gravityForce);
        assertEquals(gravityForce, instance.getGravityForce());
    }

    /**
     * Test of setTotalMassScooter method, of class DeliveryList.
     */
    @Test
    public void testSetTotalMassScooter() {
        System.out.println("setTotalMassScooter");
        double totalMassScooter = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setTotalMassScooter(totalMassScooter);
        assertEquals(totalMassScooter, instance.getTotalMassScooter());
    }

    /**
     * Test of setTotalMassDrone method, of class DeliveryList.
     */
    @Test
    public void testSetTotalMassDrone() {
        System.out.println("setTotalMassDrone");
        double totalMassDrone = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setTotalMassDrone(totalMassDrone);
        assertEquals(totalMassDrone, instance.getTotalMassDrone());
    }

    /**
     * Test of setWindSpeed method, of class DeliveryList.
     */
    @Test
    public void testSetWindSpeed() {
        System.out.println("setWindSpeed");
        double windSpeed = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setWindSpeed(windSpeed);
        assertEquals(windSpeed, instance.getWindSpeed());
    }

    /**
     * Test of setWindAngle method, of class DeliveryList.
     */
    @Test
    public void testSetWindAngle() {
        System.out.println("setWindAngle");
        double windAngle = 2.0;
        DeliveryList instance = new DeliveryList();
        instance.setWindAngle(windAngle);
        assertEquals(windAngle, instance.getWindAngle());
    }

    /**
     * Test of setLiftSpeed method, of class DeliveryList.
     */
    @Test
    public void testSetLiftSpeed() {
        System.out.println("setLiftSpeed");
        double liftSpeed = 3;
        DeliveryList instance = new DeliveryList();
        instance.setLiftSpeed(liftSpeed);
        assertEquals(liftSpeed, instance.getLiftSpeed());
    }

    /**
     * Test of setLiftDistance method, of class DeliveryList.
     */
    @Test
    public void testSetLiftDistance() {
        System.out.println("setLiftDistance");
        double liftDistance = 50;
        DeliveryList instance = new DeliveryList();
        instance.setLiftDistance(liftDistance);
        assertEquals(liftDistance, instance.getLiftDistance());
    }



    /**
     * Test of getnPower method, of class DeliveryList.
     */
    @Test
    public void testGetnPower() {
        System.out.println("getnPower");
        DeliveryList instance = new DeliveryList();
        double expResult = 0.5;
        double result = instance.getnPower();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getrDrone method, of class DeliveryList.
     */
    @Test
    public void testGetrDrone() {
        System.out.println("getrDrone");
        DeliveryList instance = new DeliveryList();
        double expResult = 3.5;
        double result = instance.getrDrone();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPavio method, of class DeliveryList.
     */
    @Test
    public void testGetPavio() {
        System.out.println("getPavio");
        DeliveryList instance = new DeliveryList();
        double expResult = 0.1;
        double result = instance.getPavio();
        assertEquals(expResult, result, 0.0);
    }

}
