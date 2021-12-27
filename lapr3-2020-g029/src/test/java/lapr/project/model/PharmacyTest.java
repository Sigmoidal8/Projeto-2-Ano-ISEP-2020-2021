/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PharmacyTest {

    @Mock
    private final Pharmacy pharmacyDB;

    public PharmacyTest() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        ParkingLot parkingLot = new ParkingLot(1, 1, "drone");
        pharmacyDB = new Pharmacy("id", "designation", "email", "password", adress);
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
     * Test of getId method, of class Pharmacy.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "password", adress);
        String expResult = "id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDesignation method, of class Pharmacy.
     */
    @Test
    public void testGetDesignation() {
        System.out.println("getDesignation");
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "password", adress);
        String expResult = "designation";
        String result = instance.getDesignation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Pharmacy.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "password", adress);
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdress method, of class Pharmacy.
     */
    @Test
    public void testGetAdress() {
        System.out.println("getAdress");
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "pawssword", adress);
        Address expResult = adress;
        Address result = instance.getAdress();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "pawssword", adress);
        String expResult = "pawssword";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacy method, of class Pharmacy.
     */
    @Test
    public void testGetPharmacy() {
        System.out.println("getPharmacy");

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        when(dbMock.getPharmacy("1")).thenReturn(pharmacyDB);

        String id = "1";
        Pharmacy p = pharmacyDB.getPharmacy(id);

        assertEquals(p, pharmacyDB);
    }

    /**
     * Test of save method, of class Pharmacy.
     */
    @Test
    public void testSave() {
        System.out.println("save");

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        when(dbMock.getPharmacy("1")).thenReturn(pharmacyDB);
        doNothing().when(dbMock).addPharmacy(pharmacyDB);

        pharmacyDB.save();
        Pharmacy p = pharmacyDB.getPharmacy("1");

        assertEquals(p, pharmacyDB); 
    }

    /**
     * Test of save method, of class Pharmacy.
     */
    @Test
    public void testSave2() {
        System.out.println("save2");

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        doNothing().when(dbMock).addPharmacy(pharmacyDB);
        when(dbMock.getPharmacy("id")).thenThrow(new IllegalArgumentException());

        pharmacyDB.save();
        assertThrows(IllegalArgumentException.class, ()
                -> {
            pharmacyDB.getPharmacy("id");
        });

    }

    /**
     * Test of update method, of class Pharmacy.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");

        PharmacyDB dbMock = mock(PharmacyDB.class);
        AplicationPOT.getInstance().getPlatform().setPdb(dbMock);
        when(dbMock.getPharmacy("1")).thenReturn(pharmacyDB);
        doNothing().when(dbMock).updatePharmacy(pharmacyDB);

        pharmacyDB.update();
        Pharmacy p = pharmacyDB.getPharmacy("1");

        assertEquals(p, pharmacyDB);
    }

    /**
     * Test of setId method, of class Pharmacy.
     */
    @Test
    public void testSetId() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "password", adress);
        instance.setId("2");
        String result = "2";
        String expResult = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkingLot method, of class Pharmacy.
     */
    @Test
    public void testGetParkingLot() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "password", adress);
        List<ParkingLot> result = new ArrayList<>();
        List<ParkingLot> expResult = instance.getParkingLotList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductList method, of class Pharmacy.
     */
    @Test
    public void testGetProductList() {
        Address adress = new Address("1", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("id", "designation", "email", "password", adress);
        boolean result = true;
        boolean expResult = instance.getProductList().getProductList().isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsl method, of class Pharmacy.
     */
    @Test
    public void testGetAsl() {
        System.out.println("getAvailableScooterList");
        Platform plat = AplicationPOT.getInstance().getPlatform();
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy p = new Pharmacy("15", "designation", "email", "password", adress);
        plat.getPharmacyList().addPharmacy(p);
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        p.getAsl().addScooter(s);
        Scooter result = p.getAsl().getAvailableScooterList().get(0);
        assertEquals(s, result);
    }

    /**
     * Test of setAsl method, of class Pharmacy.
     */
    @Test
    public void testSetAsl() {
        System.out.println("setAsl");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        AvailableScooterList asl = new AvailableScooterList();
        instance.setAsl(asl);
        AvailableScooterList result = instance.getAsl();
        assertEquals(asl, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEqualsSameObject() {
        System.out.println("equals");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsDiffObj() {
        System.out.println("equals");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        boolean expResult = false;
        boolean result = instance.equals(new Courier("Name", "12345", "54321", "em@il", "abc"));
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsDiffScooters() {
        System.out.println("equals");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        Address adress2 = new Address("16", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance2 = new Pharmacy("16", "designation", "email", "password", adress2);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsTrue() {
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        Pharmacy instance2 = new Pharmacy("15", "designation", "email", "password", adress);
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Pharmacy.
     */
    @Test
    public void testToString() {
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        String result = instance.toString();

        String expResult = "Pharmacy{id=15, designation=designation, email=email, password=password}";
        assertEquals(expResult, result);
    }

    /**
     * Test of setProductList method, of class Pharmacy.
     */
    @Test
    public void testSetProductList() {
        System.out.println("setProductList");
        ProductList pl = new ProductList();
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        instance.setProductList(pl);
        assertEquals(pl, instance.getProductList());
    }

    /**
     * Test of getAvailableDroneList method, of class Pharmacy.
     */
    @Test
    public void testGetAvailableDroneList() {
        System.out.println("getAvailableDroneList");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        DronesList expResult = new DronesList();

        Drone d = new Drone(1, 20, 20, 1);
        expResult.addDrone(d);
        instance.getAvailableDroneList().addDrone(d);
        DronesList result = instance.getAvailableDroneList();
        assertEquals(expResult.getDroneList(), result.getDroneList());
    }

    /**
     * Test of getParkingLotList method, of class Pharmacy.
     */
    @Test
    public void testGetParkingLotList() {
        System.out.println("getParkingLotList");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        ParkingLot parkingLot = new ParkingLot("1", 1, 1, "Drone");

        List<ParkingLot> expResult = new ArrayList<>();
        expResult.add(parkingLot);

        instance.addParkingLot(parkingLot);
        List<ParkingLot> result = instance.getParkingLotList();

        assertTrue(expResult.equals(result));
    }

    /**
     * Test of addParkingLot method, of class Pharmacy.
     */
    @Test
    public void testAddParkingLotTrue() {
        System.out.println("AddParkingLot");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);

        ParkingLot parkingLot = new ParkingLot("1", 1, 1, "Drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(parkingLot, instance.getId());

        boolean added = instance.addParkingLot(parkingLot);
        assertTrue(added);

        boolean added2 = instance.getParkingLotList().contains(parkingLot);
        assertTrue(added2);

        int expResult = 1;
        int result = instance.getNumberOfParkingLots();

        assertEquals(expResult, result);
    }
    /**
     * Test of addParkingLot method, of class Pharmacy.
     */
    @Test
    public void testAddParkingLotFalse() {
        System.out.println("AddParkingLotFalse");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);

        ParkingLot parkingLot = new ParkingLot("1", 1, 1, "Drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(parkingLot, instance.getId());

        boolean added = instance.addParkingLot(parkingLot);
        assertTrue(added);

        boolean verify = instance.getParkingLotList().contains(parkingLot);
        assertTrue(verify);
        
        boolean added2 = instance.addParkingLot(parkingLot);
        assertFalse(added2);

        boolean expResult = false;
        boolean result = instance.addParkingLot(parkingLot);

        assertEquals(expResult, result);
    }
    /**
     * Test of registerParkingLot method, of class Pharmacy.
     */
    @Test
    public void testRegisterParkingLotTrue() {
        System.out.println("RegisterParkingLotTrue");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        ParkingLot parkingLot = new ParkingLot("1", 1, 1, "Drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(parkingLot, instance.getId());

        boolean registered = instance.registerParkingLot(parkingLot);

        assertTrue(registered);
    }

    /**
     * Test of registerParkingLot method, of class Pharmacy.
     */
    @Test
    public void testRegisterParkingLotFalse() {
        System.out.println("RegisterParkingLotFalse");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        ParkingLot parkingLot = new ParkingLot("1", 1, 1, "Drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(parkingLot, instance.getId());

        instance.addParkingLot(parkingLot);

        boolean registered = instance.registerParkingLot(parkingLot);

        assertFalse(registered);
    }

    /**
     * Test of getNumberOfParkingLots method, of class Pharmacy.
     */
    @Test
    public void testGetNumberOfParkingLots() {
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);

        int expResult = 0;

        int result = instance.getNumberOfParkingLots();

        assertEquals(expResult, result);
    }

    /**
     * Test of hasDroneParkingLot method, of class Pharmacy.
     */
    @Test
    public void testHasScooterParkingLotFalse() {
        System.out.println("hasScooterParkingLot");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);

        instance.registerParkingLot(new ParkingLot(20, 2, "drone"));

        boolean expResult = false;
        boolean result = instance.hasScooterParkingLot();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasDroneParkingLot method, of class Pharmacy.
     */
    @Test
    public void testHasDroneParkingLotTrue() {
        System.out.println("hasDroneParkingLot");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);

        instance.registerParkingLot(new ParkingLot(20, 2, "drone"));

        boolean expResult = true;
        boolean result = instance.hasDroneParkingLot();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasDroneParkingLot method, of class Pharmacy.
     */
    @Test
    public void testHasDroneParkingLotFalse() {
        System.out.println("hasDroneParkingLot");

        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);

        instance.registerParkingLot(new ParkingLot(20, 2, "scooter"));

        boolean expResult = false;
        boolean result = instance.hasDroneParkingLot();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkingLotFromID method, of class Pharmacy.
     */
    @Test
    public void testGetParkingLotFromID() {
        System.out.println("GetParkingLotFromID");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        ParkingLot expResult = new ParkingLot("1", 1, 1, "Drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(expResult, instance.getId());

        instance.addParkingLot(expResult);

        ParkingLot result = instance.getParkingLotFromID("1");

        assertEquals(expResult, result);
    }

    /**
     * Test of getParkingLotFromID method, of class Pharmacy.
     */
    @Test
    public void testGetParkingLotFromIDNull() {
        System.out.println("GetParkingLotFromIDNull");
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        Pharmacy instance = new Pharmacy("15", "designation", "email", "password", adress);
        ParkingLot expResult = new ParkingLot("1", 1, 1, "Drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(expResult, instance.getId());

        instance.addParkingLot(expResult);

        ParkingLot result = instance.getParkingLotFromID("75");

        assertEquals(result, null);
    }
}
