/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;
import lapr.project.model.Platform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class RegisterDroneControllerTest {
    
    public RegisterDroneControllerTest() {
    }
    
    /**
     * Test of generateID method, of class RegisterDroneController.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        AplicationPOT app = new AplicationPOT();
        RegisterDroneController instance = new RegisterDroneController();
        Platform plat=app.getInstance().getPlatform();
        int expResult = 1;
        int result = instance.generateID();
        assertEquals(expResult, result);
    }

    /**
     * Test of newDrone method, of class RegisterDroneController.
     */
    @Test
    public void testNewDrone() {
        System.out.println("newDrone");
        int id = 1;
        double fullCharge = 30.0;
        double power = 40.0;
        RegisterDroneController instance = new RegisterDroneController();
        Drone expResult = new Drone(id,fullCharge,power);
        Drone result = instance.newDrone(id, fullCharge, power);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerDrone method, of class RegisterDroneController.
     */
    @Test
    public void testRegisterDrone() {
        System.out.println("registerDrone");
        Drone d = new Drone(1,-40.0,30.0);        
        RegisterDroneController instance = new RegisterDroneController();
        boolean expResult = false;
        boolean result = instance.registerDrone(d);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registerDrone method, of class RegisterDroneController.
     */
    @Test
    public void testRegisterDrone2() {
        System.out.println("registerDrone");
        AplicationPOT app = new AplicationPOT();
        Drone d = new Drone(1,40.0,30.0);
        RegisterDroneController instance = new RegisterDroneController();
        boolean expResult = true;
        DroneDB dbMock = mock(DroneDB.class);
        app.getInstance().getPlatform().setDrdb(dbMock);
        doNothing().when(dbMock).addDrone(d);
        boolean result = instance.registerDrone(d);
        assertEquals(expResult, result);
    }
    
}
