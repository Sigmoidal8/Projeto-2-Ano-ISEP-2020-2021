/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ParkingLotDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class InsertParkingLotControllerTest {
    
    private final InsertParkingLotController controller;

    private final Platform plat;

    public InsertParkingLotControllerTest() {

        controller = new InsertParkingLotController();

        plat = AplicationPOT.getInstance().getPlatform();
    }
    /**

     * Test of getPharmacyFromID method, of class InsertParkingLotController.

     */

    @Test
    public void testGetPharmacyFromID() {

        System.out.println("GetPharmacyFromID");

        Address adress = new Address("1","street",1,"postalCode","locality",0,0);

        Pharmacy expResult = new Pharmacy("id", "designation", "email", "password",adress);
        controller.getPharmacyList().addPharmacy(expResult);

        controller.getPharmacyFromID("id");

        Pharmacy result = controller.getPharmacy();
        assertEquals(expResult,result);
    }
    
    /**

     * Test of createParkingLot method, of class InsertParkingLotController.

     */

    @Test
    public void testCreateParkingLot() {
        System.out.println("CreateParkingLot");
        controller.createParkingLot(1, 1, "drone");

        ParkingLot expResult = new ParkingLot(1, 1, "drone");

        ParkingLot res = controller.getParkingLot();
        assertEquals(expResult, res);
    }

    /**

     * Test of registerParkingLot method, of class InsertParkingLotController.

     */

    @Test
    public void testRegisterParkingLotTrue() {
        System.out.println("RegisterParkingLotTrue");

        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);

        controller.getPharmacyList().addPharmacy(pharmacy);
        controller.getPharmacyFromID("id");
        controller.createParkingLot(1, 1, "drone");

        ParkingLot parkingLot = controller.getParkingLot();

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(parkingLot,controller.getPharmacy().getId());
        
        boolean registered = controller.registerParkingLot();

        assertTrue(registered);
    }
    
    /**

     * Test of registerParkingLot method, of class InsertParkingLotController.

     */

    @Test
    public void testRegisterParkingLotFalse() {
        System.out.println("RegisterParkingLotFalse");

        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);

        controller.getPharmacyList().addPharmacy(pharmacy);
        controller.getPharmacyFromID("id");
        controller.createParkingLot(1, 1, "drone");

        ParkingLot parkingLot = controller.getParkingLot();

        ParkingLotDB dbMock = mock(ParkingLotDB.class);
        AplicationPOT.getInstance().getPlatform().setPldb(dbMock);
        doNothing().when(dbMock).addParkingLot(parkingLot,controller.getPharmacy().getId());
        
        controller.getPharmacy().addParkingLot(parkingLot);

        boolean registered = controller.registerParkingLot();

        assertFalse(registered);
    }
    /**

     * Test of getPharmacy method, of class InsertParkingLotController.

     */

    @Test
    public void testGetPharmacy() {
        System.out.println("GetPharmacy");

        Address adress = new Address("1","street",1,"postalCode","locality",0,0);

        Pharmacy expResult = new Pharmacy("id", "designation", "email", "password",adress);

        controller.getPharmacyList().addPharmacy(expResult);

        controller.getPharmacyFromID("id");
        Pharmacy result = controller.getPharmacy();
        
        assertEquals(expResult,result);
    }
    /**

     * Test of getParkingLot method, of class InsertParkingLotController.

     */
    @Test
    public void testGetParkingLot() {
        System.out.println("GetParkingLot");

        ParkingLot expResult = new ParkingLot(1,1,"drone");

        controller.createParkingLot(1, 1, "drone");
        ParkingLot result = controller.getParkingLot();

        assertEquals(expResult,result);
    }
    
    /**

     * Test of getPharmacyList method, of class InsertParkingLotController.

     */
    @Test
    public void testGetPharmacyList() {
        System.out.println("GetPharmacyList");

        PharmacyList expResult = plat.getPharmacyList();

        PharmacyList result = controller.getPharmacyList();

        assertEquals(expResult,result);
    }
}
