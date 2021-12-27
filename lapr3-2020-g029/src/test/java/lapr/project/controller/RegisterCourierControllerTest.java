/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class RegisterCourierControllerTest {

    private final RegisterCourierController controller;

    public RegisterCourierControllerTest() {
        controller = new RegisterCourierController();
    }

    /**
     * Test of newCourier method, of class RegisterCourierController.
     */
    @Test
    public void testNewCourier() {
        String name = "Name";
        String NIF = "123";
        String SocialSecurityNumber = "321";
        String email = "email";
        String password = "555";

        Courier result = controller.newCourier(name, NIF, SocialSecurityNumber, email, password);
        Courier expResult = new Courier(name, NIF, SocialSecurityNumber, email, password);

        assertEquals(result, expResult);
    }

    /**
     * Test of registerCourier method, of class RegisterCourierController.
     */
    @Test
    public void testRegisterCourier() {
        System.out.println("registerCourier");
        String name = "Name";
        String NIF = "123";
        String SocialSecurityNumber = "321";
        String email = "email";
        String password = "555";

        Courier c = new Courier(name, NIF, SocialSecurityNumber, email, password);

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        doNothing().when(dbMock).addCourier(c);

        
        Courier courier = controller.newCourier(name, NIF, SocialSecurityNumber, email, password);

        boolean expResult = true;
        boolean result = controller.registerCourier();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registerCourier method, of class RegisterCourierController.
     */
    @Test
    public void testRegisterCourier2() {
        System.out.println("registerCourier");
        String name = "Name";
        String NIF = "12345";
        String SocialSecurityNumber = "321";
        String email = "email";
        String password = "555";

        Courier c = new Courier(name, NIF, SocialSecurityNumber, email, password);

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        doNothing().when(dbMock).addCourier(c);

        
        Courier courier = controller.newCourier(name, NIF, SocialSecurityNumber, email, password);
        AplicationPOT.getInstance().getPlatform().getCourierList().getListCourier().add(courier);
        boolean expResult = false;
        boolean result = controller.registerCourier();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourier method, of class RegisterCourierController.
     */
    @Test
    public void testGetCourier() {
        System.out.println("getCourier");
        System.out.println("registerCourier");
        String name = "Name";
        String NIF = "123";
        String SocialSecurityNumber = "321";
        String email = "email";
        String password = "555";

        RegisterCourierController instance = new RegisterCourierController();
        Courier expResult = new Courier(name, NIF, SocialSecurityNumber, email, password);

        instance.newCourier(name, NIF, SocialSecurityNumber, email, password);
        Courier result = instance.getCourier();
        assertEquals(expResult, result);
    }

    /**
     * Test of existsEmail method, of class RegisterCourierController.
     */
    @Test
    public void testExistsEmail() {
        System.out.println("existsEmail");
        String email = "";

        boolean expResult = false;
        boolean result = controller.existsEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of existsEmail method, of class RegisterClientController.
     */
    @Test
    public void testExistsEmail2() {
        System.out.println("existsEmail");
        String email = "email";

        RegisterCourierController instance = new RegisterCourierController();
        AplicationPOT.getInstance().getPlatform().getAutorizationFacade().registerUserWithRole("name", "email", "pass", "role");

        boolean expResult = true;
        boolean result = instance.existsEmail(email);
        assertEquals(expResult, result);
    }

}
