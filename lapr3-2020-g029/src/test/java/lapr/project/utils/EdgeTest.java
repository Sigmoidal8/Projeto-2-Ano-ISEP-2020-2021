/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import lapr.project.model.ParkingLot;
import static oracle.security.pki.resources.OraclePKICmd.V;
import static oracle.security.pki.resources.OraclePKIMsgID.V;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {

    final Edge<String, String> instance = new Edge<>();

    public EdgeTest() {
    }

    /**
     * Test of getElement method, of class Edge.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");

        String expResult = null;
        assertEquals(expResult, instance.getElement());

        Edge<String, String> instance1 = new Edge<>("edge1", 1.0, null, null);
        expResult = "edge1";
        assertEquals(expResult, instance1.getElement());
    }

    /**
     * Test of setElement method, of class Edge.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");

        String eInf = "edge1";
        instance.setElement(eInf);

        assertEquals("edge1", instance.getElement());
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");

        double expResult = 0.0;
        assertEquals(expResult, instance.getWeight(), 0.0);
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double ew = 2.0;
        instance.setWeight(ew);

        double expResult = 2.0;
        assertEquals(expResult, instance.getWeight(), 2.0);
    }

    /**
     * Test of getVOrig method, of class Edge.
     */
    @Test
    public void testGetVOrig() {
        System.out.println("getVOrig");

        Object expResult = null;
        assertEquals(expResult, instance.getVOrig());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVOrig());
    }

    /**
     * Test of setVOrig method, of class Edge.
     */
    @Test
    public void testSetVOrig() {
        System.out.println("setVOrig");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        assertEquals(vertex1.getElement(), instance.getVOrig());
    }

    /**
     * Test of getVDest method, of class Edge.
     */
    @Test
    public void testGetVDest() {
        System.out.println("getVDest");

        Object expResult = null;
        assertEquals(expResult, instance.getVDest());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVDest());
    }

    /**
     * Test of setVDest method, of class Edge.
     */
    @Test
    public void testSetVDest() {
        System.out.println("setVDest");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVDest(vertex1);
        assertEquals(vertex1.getElement(), instance.getVDest());
    }

    /**
     * Test of getEndpoints method, of class Edge.
     */
    @Test
    public void testGetEndpoints() {
        System.out.println("getEndpoints");

        String[] expResult = null;
        String[] result = instance.getEndpoints();
        assertArrayEquals(expResult, result);

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String[] expResult1 = {"Vertex1", "Vertex1"};
        assertArrayEquals(expResult1, instance.getEndpoints());

        instance.setVOrig(null);
        instance.setVDest(null);

        String[] expResult2 = null;
        assertArrayEquals(expResult2, instance.getEndpoints());
        
        Vertex<String, String> vertex2 = new Vertex<>(3, null);
        instance.setVOrig(vertex2);
        instance.setVDest(vertex2);
        assertArrayEquals(expResult2, instance.getEndpoints());


        Vertex<String, String> vertex3 = new Vertex();
        instance.setVOrig(vertex3);
        instance.setVDest(vertex3);
        assertArrayEquals(expResult2, instance.getEndpoints());
        
        instance.setVOrig(vertex1);
        instance.setVDest(null);

        String[] expResult3 = {instance.getVOrig(),instance.getVDest()};
        assertArrayEquals(expResult3, instance.getEndpoints());
        
        instance.setVOrig(null);
        instance.setVDest(vertex1);

        String[] expResult4 = {instance.getVOrig(),instance.getVDest()};
        assertArrayEquals(expResult4, instance.getEndpoints());
        
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        assertEquals(instance.equals(null), false);

        assertEquals(instance.equals(instance), true);

        assertEquals(instance.equals(instance.clone()), true);

        assertEquals(instance.equals(new ParkingLot(2, 2, "drone")), false);

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        assertEquals(instance.equals(otherEdge), false);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEqualsWeight() {
        System.out.println("equalsWeight");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>(null, 0.0, null, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 1.0, null, null);

        assertEquals(instance.equals(otherEdge), true);
        assertEquals(instance.equals(otherEdge2), false);

    }
    
    @Test
    public void testEqualsOtherOrigNull() {

        Assertions.assertFalse(instance.equals(null), "should not be equal to null");

        Assertions.assertTrue(instance.equals(instance), "should be equal to itself");

        Assertions.assertTrue(instance.equals(instance.clone()), "should be equal to a clone");

        Vertex<String, String> vertex1 = new Vertex<>(1,"Vertex1");

        Vertex vertex2 = new Vertex(1, vertex1);

        instance.setElement(null);
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);
        instance.setWeight(1.0);

        Edge<String, String> otherEdge = new Edge<>("edge1",1.0,null,vertex1);
        Assertions.assertFalse(instance.equals(otherEdge), "should not be equal to otherEdge");

        Edge<String, String> otherEdge2 = new Edge<>("edge1",1.0,vertex1,null);
        instance.setVOrig(null);
        Assertions.assertFalse(instance.equals(otherEdge2), "should not be equal to otherEdge");

        instance.setVOrig(vertex2);
        Edge<String, String> otherEdge3 = new Edge<>("edge1",1.0,vertex1,vertex2);
        Assertions.assertFalse(instance.equals(otherEdge3), "should not be equal to otherEdge");

        instance.setVOrig(vertex1);

    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals1");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>(null, 0.0, vertex1, vertex1);

        assertEquals(instance.equals(otherEdge), false);
        assertEquals(otherEdge.equals(instance), false);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals2");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>(null, 0.0, vertex1, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, vertex1, vertex1);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals6() {
        System.out.println("equals6");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Vertex<String, String> vertex2 = new Vertex<>(1, "Vertex2");
        Edge<String, String> otherEdge = new Edge<>(null, 0.0, vertex1, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, vertex2, vertex1);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals3");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Vertex<String, String> vertex2 = new Vertex<>(1, "Vertex2");
        Edge<String, String> otherEdge = new Edge<>(null, 0.0, null, vertex1);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, null, vertex2);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals7() {
        System.out.println("equals7");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>(null, 3.0, null, vertex1);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, null, vertex1);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals4");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Vertex<String, String> vertex2 = new Vertex<>(1, "Vertex2");

        Edge<String, String> otherEdge = new Edge<>(null, 0.0, vertex1, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, vertex1, vertex2);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals5() {
        System.out.println("equals5");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("Element", 8.0, vertex1, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, vertex1, null);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals8() {
        System.out.println("equals5");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>(null, 8.0, vertex1, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, vertex1, null);

        assertEquals(otherEdge2.equals(otherEdge), false);
        assertEquals(otherEdge.equals(otherEdge2), false);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEqualsElement() {
        System.out.println("equalsElement");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");

        Edge<String, String> otherEdge = new Edge<>("Element", 0.0, null, null);
        Edge<String, String> otherEdge2 = new Edge<>("Element2", 0.0, null, null);

        assertEquals(otherEdge2.equals(otherEdge), false);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEqualsElement2() {
        System.out.println("equalsElement2");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");

        Edge<String, String> otherEdge = new Edge<>(null, 0.0, null, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, null, null);

        assertEquals(otherEdge2.equals(otherEdge), true);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEqualsElement3() {
        System.out.println("equalsElement3");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");

        Edge<String, String> otherEdge = new Edge<>("Element", 0.0, null, null);
        Edge<String, String> otherEdge2 = new Edge<>(null, 0.0, null, null);

        assertEquals(otherEdge2.equals(otherEdge), true);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEqualsElement5() {
        System.out.println("equalsElement5");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");

        Edge<String, String> otherEdge = new Edge<>(null, 0.0, null, null);
        Edge<String, String> otherEdge2 = new Edge<>("Element", 0.0, null, null);

        assertEquals(otherEdge2.equals(otherEdge), true);
    }
    
    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEqualsElement4() {
        System.out.println("equalsElement4");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");

        Edge<String, String> otherEdge = new Edge<>("Element", 0.0, null, null);
        Edge<String, String> otherEdge2 = new Edge<>("Element", 0.0, null, null);

        assertEquals(otherEdge2.equals(otherEdge), true);
    }

    /**
     * Test of compareTo method, of class Edge.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        int expResult = -1;
        int result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        otherEdge.setWeight(0.0);
        expResult = 0;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        instance.setWeight(2.0);
        expResult = 1;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class Edge.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        Edge instClone = otherEdge.clone();

        assertEquals(otherEdge.getElement() == instClone.getElement(), true);
        assertEquals(otherEdge.getWeight() == instClone.getWeight(), true);

        String[] expResult = otherEdge.getEndpoints();
        assertArrayEquals(expResult, instClone.getEndpoints());
    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        instance.setElement("edge1");
        instance.setWeight(1.0);
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String expResult = "(edge1) - 1.0 - Vertex1";
        String result = instance.toString().trim();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");

        instance.setElement("edge1");
        instance.setWeight(0.0);
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String expResult = "(edge1) - Vertex1";
        String result = instance.toString().trim();
        assertEquals(expResult, result);

    }

}
