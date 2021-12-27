/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Address;
import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class RemoveDroneControllerTest {
    
    public RemoveDroneControllerTest() {
    }
    
 

    /**
     * Test of getPharmacyList method, of class RemoveDroneController.
     */
    @Test
    public void testGetPharmacyList() {
        RemoveDroneController instance = new RemoveDroneController();
        PharmacyList expResult = AplicationPOT.getInstance().getPlatform().getPharmacyList();
        PharmacyList result = instance.getPharmacyList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDronesPharmacy method, of class RemoveDroneController.
     */
    @Test
    public void testGetDronesPharmacy() {
        System.out.println("getDronesPharmacy");
        String pharmacyID = "id";
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        Drone d = new Drone(1, 50.0, 60.0);
        p.getAvailableDroneList().addDrone(d);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(p);
        RemoveDroneController instance = new RemoveDroneController();
        DronesList expResult = AplicationPOT.getInstance().getPlatform().getPharmacyList().getPharmacy(pharmacyID).getAvailableDroneList();
        DronesList result = instance.getDronesPharmacy(pharmacyID);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeDronePharmacy method, of class RemoveDroneController.
     */
    @Test
    public void testRemoveDronePharmacy() {
        System.out.println("removeDronePharmacy");
        int id = 1;
        boolean result;
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        Drone d = new Drone(1, 50.0, 60.0);
        p.getAvailableDroneList().addDrone(d);
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(p);
        
        DroneDB dbMock=mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(d);
        
        DronesList drlPharmacy = p.getAvailableDroneList();
        RemoveDroneController instance = new RemoveDroneController();
        instance.removeDronePharmacy(id, drlPharmacy);
        result= p.getAvailableDroneList().getDroneList().contains(d);
        assertEquals(false,result);
    }
    
}
