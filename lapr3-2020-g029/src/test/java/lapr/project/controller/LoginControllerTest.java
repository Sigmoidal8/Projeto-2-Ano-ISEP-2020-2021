/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginControllerTest {
    
    public LoginControllerTest() {
    }

    /**
     * Test of getUserRole method, of class LoginController.
     */
    @Test
    public void testGetUserRole() {
        System.out.println("getUserRole");
        AplicationPOT app = new AplicationPOT();
        LoginController instance = new LoginController();
        app.doLogin("lapr3g029@gmail.com", "Lapr32021");
        String expResult = "Administrator";
        String result = instance.getUserRole();
        assertEquals(expResult,result);
    }
    
    /**
     * Test of getUserRole method, of class LoginController.
     */
    @Test
    public void testGetUserRole2() {
        System.out.println("getUserRole");
        AplicationPOT app = new AplicationPOT();
        LoginController instance = new LoginController();
        String expResult = null;
        String result = instance.getUserRole();
        assertTrue(expResult == result);
    }
    
      /**
     * Test of getUserRole method, of class LoginController.
     */
    @Test
    public void testGetUserRol3() {
        System.out.println("getUserRole");
        AplicationPOT app = new AplicationPOT();
        LoginController instance = new LoginController();
        app.doLogin("lapr3g029@gmail.com", "Lapr32021");
        String expResult = "Administrator";
        String result = instance.getUserRole();
        assertEquals(expResult,result);
        
        app.doLogout();
        expResult = null;
        result = instance.getUserRole();
        assertEquals(expResult,result);
    }

    /**
     * Test of doLogin method, of class LoginController.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        String strId = "lapr3g029@gmail.com";
        String strPwd = "Lapr32021";
        AplicationPOT app = new AplicationPOT();
        LoginController instance = new LoginController();
        boolean expResult = true;
        boolean result = instance.doLogin(strId, strPwd);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of doLogin method, of class LoginController.
     */
    @Test
    public void testDoLogin2() {
        System.out.println("doLogin");
        String strId = "lapr3g029@gmail.com";
        String strPwd = "a";
        AplicationPOT app = new AplicationPOT();
        LoginController instance = new LoginController();
        boolean expResult = false;
        boolean result = instance.doLogin(strId, strPwd);
        assertEquals(expResult, result);
    }

    /**
     * Test of doLogout method, of class LoginController.
     */
    @Test
    public void testDoLogout() {
        System.out.println("doLogout");
        String strId = "lapr3g029@gmail.com";
        String strPwd = "Lapr32021";
        AplicationPOT app = new AplicationPOT();
        app.doLogin("lapr3g029@gmail.com", "Lapr32021");
        LoginController instance = new LoginController();
        instance.doLogout();
        
        assertEquals(null,app.getAtualSession());
    }
    
}
