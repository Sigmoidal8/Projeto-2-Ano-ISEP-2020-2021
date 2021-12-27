/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.CourierDB;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class CourierListTest {

    @Mock
    private final CourierList courierListDB;

    public CourierListTest() {
        courierListDB = new CourierList();
    }

    /**
     * Test of addCourier method, of class CourierList.
     */
    @Test
    public void testAddCourier() {
        System.out.println("addCourier");
        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");
        CourierList instance = new CourierList();
        boolean expResult = true;
        boolean result = instance.addCourier(c);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addCourier method, of class CourierList.
     */
    @Test
    public void testAddCourier2() {
        System.out.println("addCourier2");
        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");
        CourierList instance = new CourierList();
        instance.addCourier(c);
        boolean expResult = false;
        boolean result = instance.addCourier(c);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCourierList method, of class CourierList.
     */
    @Test
    public void testGetCourierList() {
        System.out.println("getCourierList");
        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");
        CourierList instance = new CourierList();
        instance.addCourier(c);
        List<Courier> expResult = new ArrayList<>();
        expResult.add(c);
        List<Courier> result = instance.getListCourier();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourier method, of class CourierList.
     */
    @Test
    public void testGetCourier() {
        System.out.println("getCourier");
        int NIF = 12345;
        CourierList instance = new CourierList();
        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.addCourier(c);
        Courier expResult = c;
        Courier result = instance.getCourier(NIF);
        assertEquals(expResult, result);
    }

    /**
     * Test of newCourier method, of class CourierList.
     */
    @Test
    public void testNewCourier() {
        System.out.println("newCourier");
        String name = "Name";
        String NIF = "12345";
        String SocialSecurityNumber = "54321";
        String email = "em@il";
        String password = "pass";
        CourierList instance = new CourierList();
        Courier expResult = new Courier("Name", "12345", "54321", "em@il", "pass");
        Courier result = instance.newCourier(name, NIF, SocialSecurityNumber, email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerCourier method, of class CourierList.
     */
    @Test
    public void testRegisterCourier() {
        System.out.println("registerCourier");

        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        doNothing().when(dbMock).addCourier(c);

        Platform plat = AplicationPOT.getInstance().getPlatform();
        Courier cour = new Courier("Name", "12345", "54321", "em@il", "pass");
        boolean expResult = true;
        boolean result = courierListDB.registerCourier(cour, plat);

        assertEquals(expResult, result);
    }

    /**
     * Test of registerCourier method, of class CourierList.
     */
    @Test
    public void testRegisterCourier2() {
        System.out.println("registerCourier2");

        CourierList cl = new CourierList();
        Platform plat = AplicationPOT.getInstance().getPlatform();

        Courier cour = new Courier("Name", "12345", "54321", "em@il", "pass");
        cl.getListCourier().add(cour);
        boolean expResult = false;
        boolean result = cl.registerCourier(cour, plat);

        assertEquals(expResult, result);
    }

    /**
     * Test of validateCourier method, of class CourierList.
     */
    @Test
    public void testValidateCourier() {
        System.out.println("validateCourier");
        Courier cour = new Courier("Name", "12345", "54321", "em@il", "abc");
        CourierList instance = new CourierList();
        instance.addCourier(cour);
        boolean expResult = false;
        boolean result = instance.validateCourier(cour);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateCourier method, of class CourierList.
     */
    @Test
    public void testValidateCourier4() {
        System.out.println("validateCourier4");
        Courier cour = new Courier("Name", "12345", "54321", "em@il", "abc");
        CourierList instance = new CourierList();
        boolean expResult = true;
        boolean result = instance.validateCourier(cour);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCourier method, of class CourierList.
     */
    @Test
    public void testGetCourier_int() {
        System.out.println("getCourier");
        int NIF = 0;
        CourierList instance = new CourierList();
        Courier expResult = null;
        Courier result = instance.getCourier(NIF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetCourier_int2() {
        System.out.println("getCourier");
        int NIF = 0;
        CourierList instance = new CourierList();
        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.addCourier(c);
        Courier expResult = null;
        Courier result = instance.getCourier(NIF);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourier method, of class CourierList.
     */
    @Test
    public void testGetCourier_String() {
        System.out.println("getCourier");
        String email = "";
        CourierList instance = new CourierList();
        Courier expResult = null;
        Courier result = instance.getCourier(email);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetCourier_String2() {
        System.out.println("getCourier");
        String email = "";
        CourierList instance = new CourierList();
        Courier c = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.addCourier(c);
        Courier expResult = null;
        Courier result = instance.getCourier(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerCourierWithRole method, of class CourierList.
     */
    @Test
    public void testRegisterCourierWithRole() {
        System.out.println("registerCourierWithRole");
        Courier cour = new Courier("Name", "12345", "-1", "em@il", "abc");
        Platform plat = AplicationPOT.getInstance().getPlatform();
        CourierList instance = new CourierList();
        instance.registerCourierWithRole(cour, plat);

        assertTrue(plat.getAutorizationFacade().hasUser("em@il"));
    }
    
    
}
