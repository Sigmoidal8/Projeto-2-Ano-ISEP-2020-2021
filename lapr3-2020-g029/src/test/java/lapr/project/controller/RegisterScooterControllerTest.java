/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.File;
import lapr.project.data.ScooterDB;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.ScootersList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class RegisterScooterControllerTest {
    
    private final RegisterScooterController controller;
    
    public RegisterScooterControllerTest() {
        controller = new RegisterScooterController();
    }

    /**
     * Test of generateExclusiveNumber method, of class RegisterScooterController.
     */
    @Test
    public void testGenerateExclusiveNumber() {
        System.out.println("generateExclusiveNumber");
        AplicationPOT app = new AplicationPOT() ;
        Platform plat=controller.getPlat();
        ScootersList sctl=plat.getScootersList();
        Scooter s = new Scooter(1,50.0,60.0,0);
        sctl.addScooter(s);
        int expResult = 2;
        int result = controller.generateExclusiveNumber();
        assertEquals(expResult, result);
    }


    /**
     * Test of newScooter method, of class RegisterScooterController.
     */
    @Test
    public void testNewScooter() {
        System.out.println("newScooter");
        int excNumb = 1;
        double fullCharge = 2.0;
        double power = 3.0;
        int operational = 0;
        File qrCode = new File("");
        Scooter expResult = new Scooter(excNumb,fullCharge,power,operational);
        Scooter result = controller.newScooter(excNumb, fullCharge, power, operational, qrCode);
        File resultQrCode = result.getQrCode();
        assertEquals(qrCode,resultQrCode);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerScooter method, of class RegisterScooterController.
     */
    @Test
    public void testRegisterScooter() {
        System.out.println("registerScooter");
        Platform plat=controller.getPlat();
        ScootersList sctl=plat.getScootersList();
        int expResult=sctl.getScooterList().size();
        int excNumb = 2;
        double fullCharge = -2.0;
        double power = 3.0;
        int operational = 0;
        Scooter s = new Scooter(excNumb,fullCharge,power,operational);
        controller.registerScooter(s);
        int result=sctl.getScooterList().size();
        assertEquals(expResult,result);
    }   
    
    /**
     * Test of registerScooter method, of class RegisterScooterController.
     */
    @Test
    public void testRegisterScooter2() {
        System.out.println("registerScooter");
        int excNumb = 2;
        double fullCharge = 2.0;
        double power = 3.0;
        int operational = 0;
        Scooter s = new Scooter(excNumb,fullCharge,power,operational);
        
        ScooterDB dbMock = mock(ScooterDB.class);
        AplicationPOT.getInstance().getPlatform().setSdb(dbMock);
        doNothing().when(dbMock).addScooter(s);

        boolean result = controller.registerScooter(s);
        boolean expResult = true;
        assertEquals(expResult,result);
    } 
    

     /**
     * Test of registerScooter method, of class RegisterScooterController.
     */
    @Test
    public void testRegisterScooter4() {
        System.out.println("registerScooter");
        Platform plat=controller.getPlat();
        ScootersList sctl=plat.getScootersList();
        int excNumb = 1;
        double fullCharge = -2.0;
        double power = 3.0;
        int operational = 0;
        boolean expResult=false;
        Scooter s = new Scooter(excNumb,fullCharge,power,operational);
        boolean result=controller.registerScooter(s);
        assertEquals(expResult,result);
    }   

    /**
     * Test of getPlat method, of class RegisterScooterController.
     */
    @Test
    public void testGetPlat() {
        System.out.println("getPlat");
        RegisterScooterController instance = new RegisterScooterController();
        Platform expResult = AplicationPOT.getInstance().getPlatform();
        Platform result = instance.getPlat();
        assertEquals(expResult, result);
    }


    @Test
    public void testGenerateQRCode() throws Exception {
        System.out.println("generateQRCode");
        int excNumb = 1;
        RegisterScooterController instance = new RegisterScooterController();
        File expResult=new File("ScootersQR/Scooter" + excNumb + ".jpg");
        File result = instance.generateQRCode(excNumb);
        assertEquals(expResult, result);

    }
    
}
