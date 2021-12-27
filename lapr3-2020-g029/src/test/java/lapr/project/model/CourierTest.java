/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.controller.AplicationPOT;
import lapr.project.data.CourierDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourierTest {

    @Mock
    private final Courier courierDB;

    public CourierTest() {
        courierDB = new Courier("Name", "12345", "54321", "em@il", "abc");
    }

    /**
     * Test of getName method, of class Courier.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        String expResult = "Name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Courier.
     */
    @Test
    public void testGetName2() {
        System.out.println("getName");
        Courier instance = new Courier(null, "12345", "54321", "em@il", "abc");
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNIF method, of class Courier.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        int expResult = 12345;
        int result = instance.getNIF();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSocialSecurityNumber method, of class Courier.
     */
    @Test
    public void testGetSocialSecurityNumber() {
        System.out.println("getSocialSecurityNumber");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        int expResult = 54321;
        int result = instance.getSocialSecurityNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Courier.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        String expResult = "em@il";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Courier.
     */
    @Test
    public void testGetEmail2() {
        System.out.println("getEmail");
        Courier instance = new Courier("Name", "12345", "54321", null, "abc");
        String expResult = null;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Courier.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        String expResult = "abc";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Courier.
     */
    @Test
    public void testGetPassword2() {
        System.out.println("getPassword");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", null);
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBackpackCapacity method, of class Courier.
     */
    @Test
    public void testGetBackpackCapacity() {
        System.out.println("getBackpackCapacity");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        double expResult = 15.0;
        double result = instance.getBackpackCapacity();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of setName method, of class Courier.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of setNIF method, of class Courier.
     */
    @Test
    public void testSetNIF() {
        System.out.println("setNIF");
        int NIF = 1111;
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.setNIF(NIF);
        assertEquals(NIF, instance.getNIF(), 0.1);
    }

    /**
     * Test of setSocialSecurityNumber method, of class Courier.
     */
    @Test
    public void testSetSocialSecurityNumber() {
        System.out.println("setSocialSecurityNumber");
        int SocialSecurityNumber = 1111;
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.setSocialSecurityNumber(SocialSecurityNumber);
        assertEquals(SocialSecurityNumber, instance.getSocialSecurityNumber(), 0.1);
    }

    /**
     * Test of setEmail method, of class Courier.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "abc";
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of setPassword method, of class Courier.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "aaa";
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of setBackpackCapacity method, of class Courier.
     */
    @Test
    public void testSetBackpackCapacity() {
        System.out.println("setBackpackCapacity");
        double aBackpackCapacity = 10.0;
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        instance.setBackpackCapacity(aBackpackCapacity);
        assertEquals(aBackpackCapacity, instance.getBackpackCapacity(), 0.1);
    }

    /**
     * Test of getCourier method, of class Courier.
     */
    @Test
    public void testGetCourier() {
        System.out.println("getCourier");

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        when(dbMock.getCourier("12345")).thenReturn(courierDB);

        String nif = "12345";
        Courier d = courierDB.getCourier(nif);

        assertEquals(d, courierDB);
    }

    /**
     * Test of save method, of class Courier.
     */
    @Test
    public void testSave() {
        System.out.println("save");

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        doNothing().when(dbMock).addCourier(courierDB);
        when(dbMock.getCourier("12345")).thenReturn(courierDB);
        courierDB.save();

        Courier result = courierDB.getCourier("12345");
        assertEquals(result, courierDB);
    }
    
    /**
     * Test of save method, of class Courier.
     */
    @Test
    public void testSave2() {
        System.out.println("save");

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        doNothing().when(dbMock).addCourier(courierDB);
        when(dbMock.getCourier("12345")).thenThrow(new IllegalArgumentException());
        courierDB.save();
        
        assertThrows(IllegalArgumentException.class, ()->
                {courierDB.getCourier("12345");});
    }
    
    /**
     * Test of update method, of class Courier.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");

        CourierDB dbMock = mock(CourierDB.class);
        AplicationPOT.getInstance().getPlatform().setCoudb(dbMock);
        doNothing().when(dbMock).updateCourier(courierDB);
        when(dbMock.getCourier("12345")).thenReturn(courierDB);
        courierDB.update();

        Courier result = courierDB.getCourier("12345");

        assertEquals(result, courierDB);
    }

    /**
     * Test of toString method, of class Courier.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        String expResult = "Name, 12345, 54321, em@il, abc";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abc");
        boolean expResult = true;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals2() {
        System.out.println("not equals2");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("NameName", "145", "521", "email", "abc");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals3() {
        System.out.println("not equals3");
        Courier instance = new Courier("NameName", "145", "521", "email", "abc");
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals4() {
        System.out.println("not equals4");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("NameName", "145", "521", "email", "abc");
        boolean expResult = false;
        boolean result = instance.equals(new Client("id", "name", "password", "email", "12", s1));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals5() {
        System.out.println("not equals5");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Drone instance = new Drone(1, 50.0, 60.0);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals6() {
        System.out.println("not equals6");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("NameName", "145", "521", "em@il", "abcd");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals7() {
        System.out.println("not equals7");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("NameName", "145", "54321", "email", "abcd");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals8() {
        System.out.println("not equals8");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("NameName", "12345", "543", "email", "abcd");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals9() {
        System.out.println("not equals9");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "123", "543", "email", "abcd");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals10() {
        System.out.println("not equals10");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "12345", "54321", "email", "abcd");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals11() {
        System.out.println("equals11");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "12345", "54321", "em@il", "abcde");
        boolean expResult = true;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals12() {
        System.out.println("not equals12");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "12345", "54321", "email", "abc");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals13() {
        System.out.println("not equals13");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "12345", "543", "em@il", "abc");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals14() {
        System.out.println("not equals14");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("Name", "12", "54321", "em@il", "abc");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals15() {
        System.out.println("not equals15");
        Object otherObjeto = new Courier("Name", "12345", "54321", "em@il", "abc");
        Courier instance = new Courier("N", "12345", "54321", "em@il", "abc");
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
}
