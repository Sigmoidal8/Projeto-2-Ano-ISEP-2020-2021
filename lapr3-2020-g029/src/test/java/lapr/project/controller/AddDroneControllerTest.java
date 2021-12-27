/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.data.DroneDB;
import lapr.project.model.Address;
import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class AddDroneControllerTest {
    
    public AddDroneControllerTest() {
    }

    

    /**
     * Test of getDronesWithouthPharmacy method, of class AddDroneController.
     */
    @Test
    public void testGetDronesWithouthPharmacy() {
        System.out.println("getDronesWithouthPharmacy");
        AddDroneController instance = new AddDroneController();
        Drone d = new Drone(1, 50.0, 60.0);
        instance.getDronesList().addDrone(d);
        List<Drone> expResult=instance.getDronesList().getPharmacyLessDrones();
        List<Drone> result = instance.getDronesWithouthPharmacy();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDrone method, of class AddDroneController.
     */
    @Test
    public void testGetDrone() {
        System.out.println("getDrone");
        int id = 1;
        Drone d = new Drone(1, 50.0, 60.0);
        AddDroneController instance = new AddDroneController();
        instance.getDronesList().addDrone(d);
        Drone expResult = d;
        Drone result = instance.getDrone(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of getPharmacyList method, of class AddDroneController.
     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("getPharmacyList");
        AddDroneController instance = new AddDroneController();
        PharmacyList expResult = AplicationPOT.getInstance().getPlatform().getPharmacyList();
        PharmacyList result = instance.getPharmacyList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDronePharmacy method, of class AddDroneController.
     */
    @Test
    public void testSetDronePharmacy() {
        System.out.println("setDronePharmacy");
        String pharmacyID = "id";
        Drone d = new Drone(1, 50.0, 60.0);
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        AddDroneController instance = new AddDroneController();
        
        DroneDB dbMock=mock(DroneDB.class);
        AplicationPOT.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).updateDrone(d);
        
        instance.getPharmacyList().addPharmacy(p);
        instance.setDronePharmacy(pharmacyID, d);
    }

    /**
     * Test of getDronesList method, of class AddDroneController.
     */
    @Test
    public void testGetDronesList() {
        System.out.println("getDronesList");
        AddDroneController instance = new AddDroneController();
        DronesList expResult = AplicationPOT.getInstance().getPlatform().getDronesList();
        DronesList result = instance.getDronesList();
        assertEquals(expResult, result);
    }
    
}
