/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.ScootersList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class RemoveScooterControllerTest {
    
    private final RemoveScooterController controller;
    
    public RemoveScooterControllerTest() {
        controller = new RemoveScooterController();
    }

    /**
     * Test of getPharmacyList method, of class RemoveScooterController.
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
     * Test of getPharmacy method, of class RemoveScooterController.
     */
   @Test
    public void testGetPharmacy() {
        System.out.println("getPharmacy");
        AplicationPOT app = new AplicationPOT();
        RemoveScooterController controller1 = new RemoveScooterController();
        Platform plat=controller1.getPlat();
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        plat.getPharmacyList().addPharmacy(p);
        Pharmacy expResult = p;
        Pharmacy result = controller1.getPharmacy("15");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailableScooterList method, of class RemoveScooterController.
     */
    @Test
    public void testGetAvailableScooterList() {
        System.out.println("getAvailableScooterList");
        Platform plat=controller.getPlat();
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        plat.getPharmacyList().addPharmacy(p);
        Scooter s = new Scooter(1,50.0,60.0,0); 
        p.getAsl().addScooter(s);
        Scooter result = controller.getAvailableScooterList(p).getAvailableScooterList().get(0);
        assertEquals(s, result);
    }

    /**
     * Test of getScooter method, of class RemoveScooterController.
     */
    @Test
    public void testGetScooter() {
        System.out.println("getScooter");
        int scooterID = 1;
        Platform plat = controller.getPlat();
        ScootersList sctl = plat.getScootersList();
        Scooter s = new Scooter(1, 50.0, 60.0, 0);
        sctl.addScooter(s);
        Scooter expResult = s;
        Address adress = new Address("15", "street", 1, "postalCode", "locality", 0, 0);
        ParkingLot parkingLot = new ParkingLot(1, 1, "drone");
        Pharmacy p = new Pharmacy("15", "designation", "email", "password", adress);
        plat.getPharmacyList().addPharmacy(p);
        p.getAsl().addScooter(s);
        Scooter result = controller.getScooter(scooterID, p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeScooterFromAvailableScooterList method, of class RemoveScooterController.
     */
    @Test
    public void testRemoveScooterFromAvailableScooterList() {
        System.out.println("removeScooterFromAvailableScooterList");
        
        Scooter sc = new Scooter(1,50.0,60.0,0);
        
        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).updateScooter(sc);
        
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        
        RemoveScooterController instance = new RemoveScooterController();
        boolean expResult = false;
        boolean result = instance.removeScooterFromAvailableScooterList(sc, p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeScooterFromAvailableScooterList method, of class RemoveScooterController.
     */
    @Test
    public void testRemoveScooterFromAvailableScooterListFalse() {
        System.out.println("removeScooterFromAvailableScooterListFalse");
        
        Scooter sc = new Scooter(1,50.0,60.0,0);
        
        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).updateScooter(sc);
        
        Address adress = new Address("15","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("15", "designation", "email","password", adress);
        
        p.getAsl().addScooter(sc);
        
        RemoveScooterController instance = new RemoveScooterController();
        boolean expResult = true;
        boolean result = instance.removeScooterFromAvailableScooterList(sc, p);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlat method, of class RemoveScooterController.
     */
    @Test
    public void testGetPlat() {
        System.out.println("getPlat");
        
        RemoveScooterController instance = new RemoveScooterController();
        Platform expResult = AplicationPOT.getInstance().getPlatform();
        Platform result = instance.getPlat();
        assertEquals(expResult, result);
    }
    
}
