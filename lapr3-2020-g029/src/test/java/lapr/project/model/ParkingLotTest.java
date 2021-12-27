/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.controller.AplicationPOT;
import lapr.project.data.ParkingLotDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ParkingLotTest {

    public ParkingLotTest() {
    }

    /**
     * Test of getId method, of class ParkingLot.
     */
    @Test
    public void testGetId() {
        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");
        String expResult = "1";
        String res = parkingLot.getId();

        assertEquals(expResult, res);
    }

    /**
     * Test of getTotalSpots method, of class ParkingLot.
     */
    @Test
    public void testGetTotalSpots() {
        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");
        int expResult = 1;
        int res = parkingLot.getTotalSpots();

        assertEquals(expResult, res);
    }

    /**
     * Test of getChargeSpots method, of class ParkingLot.
     */
    @Test
    public void testGetChargeSpots() {
        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");
        int expResult = 1;
        int res = parkingLot.getChargeSpots();

        assertEquals(expResult, res);
    }
    
    /**
     * Test of setTotalSpots method, of class ParkingLot.
     */
    @Test
    public void testSetTotalSpots() {
        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");
        int expResult = 15;
        parkingLot.setTotalSpots(expResult);
        
        int res = parkingLot.getTotalSpots();

        assertEquals(expResult, res);
    }
    
     /**
     * Test of setChargeSpots method, of class ParkingLot.
     */
    @Test
    public void testSetChargeSpots() {
        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");
        int expResult = 15;
        parkingLot.setChargeSpots(expResult);
        
        int res = parkingLot.getChargeSpots();

        assertEquals(expResult, res);
    }
    
    /**
     * Test of getChargeSpots method, of class ParkingLot.
     */
    @Test
    public void testGetType() {
        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");
        String expResult = "drone";
        String res = parkingLot.getType();

        assertEquals(expResult, res);
    }
    
    
    

    /**
     * Test of equals method, of class ParkingLot.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new ParkingLot(2, 2,"drone");
        ParkingLot instance = new ParkingLot(2, 2,"drone");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ParkingLot.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object obj = null;
        ParkingLot instance = new ParkingLot(2, 2,"drone");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ParkingLot.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");
        Object obj = new Scooter(2, 4, 1, 2);
        ParkingLot instance = new ParkingLot(2, 2,"drone");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkingLot method, of class ParkingLot.
     */
    @Test
    public void testGetParkingLot() {
        System.out.println("getParkingLot");

        ParkingLot parkingLot = new ParkingLot("1", 1, 1,"drone");

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        when(dbMock.getParkingLot("1")).thenReturn(parkingLot);

        String id = "1";
        ParkingLot result = parkingLot.getParkingLot(id);
        assertEquals(parkingLot, result);
    }
    
     /**
     * Test of generateID method, of class ParkingLot.
     */
    @Test
    public void testGenerateID() {
       System.out.println("generateID");
       ParkingLot instance = new ParkingLot(2, 2,"drone");
       instance.generateID(0,"1");
       String expResult = "10drone";
       String result = instance.getId();
       
       assertEquals(expResult,result);
    }
    
    /**
     * Test of toString method, of class ParkingLot.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ParkingLot instance = new ParkingLot("23",2, 2,"drone");
        String expResult = "ParkingLot 23";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
