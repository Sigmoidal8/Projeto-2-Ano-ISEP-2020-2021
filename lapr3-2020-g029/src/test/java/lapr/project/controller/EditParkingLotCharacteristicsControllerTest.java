/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ParkingLotDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class EditParkingLotCharacteristicsControllerTest {
    
    private final EditParkingLotCharacteristicsController controller;
    
    @Mock
    private final EditParkingLotCharacteristicsController controllerDB;
    
    private final Platform plat;
    
    public EditParkingLotCharacteristicsControllerTest() {
        controller = new EditParkingLotCharacteristicsController();
        plat = AplicationPOT.getInstance().getPlatform();
        controllerDB = new EditParkingLotCharacteristicsController();
    }


    /**
     * Test of getPharmacyList method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testGetPharmacyList() {
        PharmacyList expResult = plat.getPharmacyList();
        PharmacyList res = controller.getPharmacyList();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of getPharmacy method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testGetPharmacy() {
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy expResult = new Pharmacy("id", "designation", "email", "password",adress);
        
        controller.getPharmacyList().addPharmacy(expResult);
        Pharmacy result = controller.getPharmacy("id");
        
        assertEquals(expResult,result);
    }

    /**
     * Test of getParkingLotList method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testGetParkingLotList() {
        System.out.println("getParkingLotList");
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        
        controller.getPharmacyList().addPharmacy(p);
        Pharmacy controllerPharmacy = controller.getPharmacy("id");
        
        List<ParkingLot> expResult = controllerPharmacy.getParkingLotList();
        
       List<ParkingLot> result = controller.getParkingLotList();
        
        
        assertEquals(expResult, result);

    }
    
        /**
     * Test of getParkingLot method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testGetParkingLot() {
        System.out.println("getParkingLot");
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        controller.getPharmacyList().addPharmacy(p);
        
        ParkingLot expResult = new ParkingLot("id",1,1,"Scooter");
        
        controller.getPharmacy("id");
        controller.getParkingLotList().add(expResult);
        
        ParkingLot result = controller.getParkingLot("id");
        
        
        assertEquals(expResult, result);

    }
    
        /**
     * Test of getParkingLot method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testGetParkingLot2() {
        System.out.println("getParkingLot");
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        controller.getPharmacyList().addPharmacy(p);
        
        ParkingLot expResult = null;
        
        controller.getPharmacy("id");
        List<ParkingLot> parkingLotList = controller.getParkingLotList();
          ParkingLot result = controller.getParkingLot("id7");
          assertEquals(expResult,result);
    }

    /**
     * Test of setTotalSpots method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testSetTotalSpots() {
        System.out.println("setTotalSpots");
        int totalSpots = 0;
        
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        
        EditParkingLotCharacteristicsController controllerDB = new EditParkingLotCharacteristicsController();
        
        controllerDB.getPharmacyList().addPharmacy(p);
        
        ParkingLot prk = new ParkingLot("id",1,1,"Scooter");
        
        Pharmacy controllerPharmacy = controllerDB.getPharmacy("id");
        
        controllerDB.getParkingLotList().add(prk);
        
        controllerDB.getParkingLot("id");
        
        ParkingLot expResult = controllerPharmacy.getParkingLotList().get(0);
        
        
        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).updateParkingLot(expResult,controllerPharmacy.getId());
        
        ParkingLot result = controllerDB.setTotalSpots(totalSpots);
        
        int resultTotalSpots = result.getTotalSpots();
        
        assertEquals(totalSpots,resultTotalSpots);
        
        assertEquals(expResult,result);

    }
    
    /**
     * Test of setChargeSpots method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testSetChargeSpots() {
        System.out.println("setChargeSpots");
        int chargeSpots = 1;
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        
        EditParkingLotCharacteristicsController controllerDB = new EditParkingLotCharacteristicsController();
        
        controllerDB.getPharmacyList().addPharmacy(p);
        
        ParkingLot prk = new ParkingLot("id",1,1,"Scooter");
        
        Pharmacy controllerPharmacy = controllerDB.getPharmacy("id");
        
        controllerDB.getParkingLotList().add(prk);
        
        controllerDB.getParkingLot("id");
        
        ParkingLot expResult = controllerPharmacy.getParkingLotList().get(0);
        
        
        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).updateParkingLot(expResult,controllerPharmacy.getId());
        
        ParkingLot result = controllerDB.setChargeSpots(chargeSpots);
        
        assertEquals(expResult,result);
    }
    
    /**
     * Test of setChargeSpots method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testSetChargeSpots2() {
        System.out.println("setChargeSpots");
        int chargeSpots = 3;
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        
        EditParkingLotCharacteristicsController controllerDB = new EditParkingLotCharacteristicsController();
        
        controller.getPharmacyList().addPharmacy(p);
        
        ParkingLot prk = new ParkingLot("id",1,1,"Scooter");
        
        Pharmacy controllerPharmacy = controller.getPharmacy("id");
        
        controller.getParkingLotList().add(prk);
        
        controller.getParkingLot("id");
        
        ParkingLot expResult = controllerPharmacy.getParkingLotList().get(0);
        
        
        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).updateParkingLot(expResult,controllerPharmacy.getId());
        
        ParkingLot result = controller.setChargeSpots(chargeSpots);
        
        int resultChargeSpots = controller.getPrkl().getChargeSpots();
        int resultChargeSpots2 = result.getChargeSpots();
        
        assertEquals(chargeSpots,resultChargeSpots);
        assertEquals(resultChargeSpots,resultChargeSpots2);
    }

    /**
     * Test of getPrkl method, of class EditParkingLotCharacteristicsController.
     */
    @Test
    public void testGetPrkl() {
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy p = new Pharmacy("id", "designation", "email", "password",adress);
        
        EditParkingLotCharacteristicsController controllerDB = new EditParkingLotCharacteristicsController();
        
        controllerDB.getPharmacyList().addPharmacy(p);
        
        ParkingLot expResult = new ParkingLot("id",1,1,"Scooter");
        
        Pharmacy controllerPharmacy = controllerDB.getPharmacy("id");
        
        controllerDB.getParkingLotList().add(expResult);
        
        controllerDB.getParkingLot("id");
        
        ParkingLot result = controllerDB.getPrkl();
        
        assertEquals(expResult,result);
    }
    
    
    
}
