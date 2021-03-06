/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Iterator;
import lapr.project.model.ParkingLot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VertexTest {

    final Vertex<String, Integer> instance = new Vertex<>();

    public VertexTest() {
    }

    /**
     * Test of getKey method, of class Vertex.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");

        int expResult = -1;
        assertEquals(expResult, instance.getKey());

        Vertex<String, Integer> instance1 = new Vertex<>(1, "Vertex1");
        expResult = 1;
        assertEquals(expResult, instance1.getKey());
    }

    /**
     * Test of setKey method, of class Vertex.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        int k = 2;
        instance.setKey(k);
        int expResult = 2;
        assertEquals(expResult, instance.getKey());
    }

    /**
     * Test of getElement method, of class Vertex.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");

        String expResult = null;
        assertEquals(expResult, instance.getElement());

        Vertex<String, Integer> instance1 = new Vertex<>(1, "Vertex1");
        expResult = "Vertex1";
        assertEquals(expResult, instance1.getElement());

    }

    /**
     * Test of setElement method, of class Vertex.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");
        String vInf = "Vertex1";
        instance.setElement(vInf);
        assertEquals(vInf, instance.getElement());
    }

    /**
     * Test of addAdjVert method, of class Vertex.
     */
    @Test
    public void testAddAdjVert() {
        System.out.println("addAdjVert");

        assertEquals((instance.numAdjVerts() == 0), true);

        String vAdj1 = "VAdj1";
        Edge<String, Integer> edge = new Edge<>();

        instance.addAdjVert(vAdj1, edge);
        assertEquals((instance.numAdjVerts() == 1), true);

        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge);
        assertEquals((instance.numAdjVerts() == 2), true);
    }

    /**
     * Test of getAdjVert method, of class Vertex.
     */
    @Test
    public void testGetAdjVert() {
        System.out.println("getAdjVert");

        Edge<String, Integer> edge = new Edge<>();
        Object expResult = null;
        assertEquals(expResult, instance.getAdjVert(edge));

        String vAdj = "VertexAdj";
        instance.addAdjVert(vAdj, edge);
        assertEquals(vAdj, instance.getAdjVert(edge));
    }

    /**
     * Test of remAdjVert method, of class Vertex.
     */
    @Test
    public void testRemAdjVert() {
        System.out.println("remAdjVert");

        Edge<String, Integer> edge1 = new Edge<>();
        String vAdj = "VAdj1";
        instance.addAdjVert(vAdj, edge1);

        Edge<String, Integer> edge2 = new Edge<>();
        vAdj = "VAdj2";
        instance.addAdjVert(vAdj, edge2);

        instance.remAdjVert(vAdj);
        assertEquals((instance.numAdjVerts() == 1), true);

        vAdj = "VAdj1";
        instance.remAdjVert(vAdj);
        assertEquals((instance.numAdjVerts() == 0), true);
    }

    /**
     * Test of getEdge method, of class Vertex.
     */
    @Test
    public void testGetEdge() {
        System.out.println("getEdge");

        Edge<String, Integer> edge1 = new Edge<>();
        String vAdj1 = "VAdj1";
        instance.addAdjVert(vAdj1, edge1);

        assertEquals(edge1, instance.getEdge(vAdj1));

        Edge<String, Integer> edge2 = new Edge<>();
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge2);

        assertEquals(edge2, instance.getEdge(vAdj2));
    }

    /**
     * Test of numAdjVerts method, of class Vertex.
     */
    @Test
    public void testNumAdjVerts() {
        System.out.println("numAdjVerts");

        Edge<String, Integer> edge1 = new Edge<>();
        String vAdj1 = "VAdj1";
        instance.addAdjVert(vAdj1, edge1);

        assertEquals((instance.numAdjVerts() == 1), true);

        Edge<String, Integer> edge2 = new Edge<>();
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge2);

        assertEquals((instance.numAdjVerts() == 2), true);

        instance.remAdjVert(vAdj1);

        assertEquals((instance.numAdjVerts() == 1), true);

        instance.remAdjVert(vAdj2);
        assertEquals((instance.numAdjVerts() == 0), true);

    }

    /**
     * Test of getAllAdjVerts method, of class Vertex.
     */
    @Test
    public void testGetAllAdjVerts() {
        System.out.println("getAllAdjVerts");

        Iterator<String> itVerts = instance.getAllAdjVerts().iterator();

        assertEquals(itVerts.hasNext() == false, true);

        Edge<String, Integer> edge1 = new Edge<>();
        String vAdj1 = "VAdj1";
        instance.addAdjVert(vAdj1, edge1);

        Edge<String, Integer> edge2 = new Edge<>();
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge2);

        itVerts = instance.getAllAdjVerts().iterator();

        assertEquals((itVerts.next().compareTo("VAdj1") == 0), true);
        assertEquals((itVerts.next().compareTo("VAdj2") == 0), true);

        instance.remAdjVert(vAdj1);

        itVerts = instance.getAllAdjVerts().iterator();
        assertEquals((itVerts.next().compareTo("VAdj2")) == 0, true);

        instance.remAdjVert(vAdj2);

        itVerts = instance.getAllAdjVerts().iterator();
        assertEquals(itVerts.hasNext() == false, true);
    }

    /**
     * Test of getAllOutEdges method, of class Vertex.
     */
    @Test
    public void testGetAllOutEdges() {
        System.out.println("getAllOutEdges");

        Iterator<Edge<String, Integer>> itEdges = instance.getAllOutEdges().iterator();

        assertEquals(itEdges.hasNext() == false, true);

        Edge<String, Integer> edge1 = new Edge<>();
        String vAdj1 = "VAdj1";
        instance.addAdjVert(vAdj1, edge1);

        Edge<String, Integer> edge2 = new Edge<>();
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge2);

        itEdges = instance.getAllOutEdges().iterator();

        assertEquals((itEdges.next().compareTo(edge1) == 0), true);
        assertEquals((itEdges.next().compareTo(edge2) == 0), true);

        instance.remAdjVert(vAdj1);

        itEdges = instance.getAllOutEdges().iterator();
        assertEquals((itEdges.next().compareTo(edge2)) == 0, true);

        instance.remAdjVert(vAdj2);

        itEdges = instance.getAllOutEdges().iterator();
        assertEquals(itEdges.hasNext() == false, true);
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        instance.setKey(1);
        instance.setElement("Vertex1");

        Vertex<String, Integer> instance2 = new Vertex<>(2, "Vertex2");

        Edge<String, Integer> edge1 = new Edge<>(null, 2, instance, instance2);
        instance.addAdjVert("vAdj2", edge1);

        assertEquals(instance.equals(null), false);

        assertEquals(instance.equals(instance), true);

        assertEquals(instance.equals(instance.clone()), true);

        assertEquals(instance.equals(new ParkingLot(2, 2, "drone")), false);

        assertEquals(instance.equals(instance2), false);

        Vertex<String, Integer> other = instance.clone();
        other.remAdjVert("vAdj2");
        assertEquals(instance.equals(other), false);

        other.addAdjVert("vAdj2", edge1);
        assertEquals(instance.equals(other), true);

        Vertex<String, Integer> instance3 = new Vertex<>(3, "Vertex3");
        Edge<String, Integer> edge2 = new Edge<>(null, 3, instance, instance3);
        instance.addAdjVert("vAdj3", edge2);
        assertEquals(instance.equals(other), false);

        Vertex<String, Integer> other2 = new Vertex();
        Vertex<String, Integer> other3 = new Vertex(2, "Vertex45");
        Vertex<String, Integer> other4 = new Vertex(4, "Vertex98");
        Edge<String, Integer> edge3 = new Edge<>(null, 3, other3, other4);
        other2.addAdjVert("as", edge3);

        assertEquals(instance.equals(other2), false);
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");

        instance.setKey(1);
        instance.setElement("Vertex1");

        Vertex<String, Integer> other2 = new Vertex();
        Vertex<String, Integer> other3 = new Vertex(2, "Vertex45");
        Vertex<String, Integer> other4 = new Vertex(4, "Vertex98");

        assertEquals(instance.equals(other2), false);
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");

        instance.setKey(1);
        instance.setElement("Vertex1");

        Vertex<String, Integer> other2 = new Vertex();
        Vertex<String, Integer> other3 = new Vertex(1, "Vertex1");
        Vertex<String, Integer> other4 = new Vertex(4, "Vertex98");

        assertEquals(instance.equals(other3), true);
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals");

        instance.setKey(1);
        instance.setElement("Vertex1");

        Vertex<String, Integer> other2 = new Vertex();
        Vertex<String, Integer> other3 = new Vertex(1, "Vertex29");
        Vertex<String, Integer> other4 = new Vertex(4, "Vertex98");

        Edge<String, Integer> edge1 = new Edge<>(null, 2, instance, other3);
        instance.addAdjVert("vAdj2", edge1);

        Edge<String, Integer> edge2 = new Edge<>(null, 2, other3, other3);
        other3.addAdjVert("vAdj3", edge2);

        assertEquals(instance.equals(other3), false);
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals5() {
        System.out.println("equals5");

        instance.setKey(1);
        instance.setElement("Vertex29");

        Vertex<String, Integer> other2 = new Vertex();
        Vertex<String, Integer> other3 = new Vertex(1, "Vertex29");
        Vertex<String, Integer> other4 = new Vertex(4, "Vertex98");

        Edge<String, Integer> edge1 = new Edge<>(null, 2,null, other3);
        instance.addAdjVert("vAdj2", edge1);

        Edge<String, Integer> edge2 = new Edge<>(null, 2, other3, other3);
        other3.addAdjVert("vAdj3", edge2);

        assertEquals(instance.equals(other3), false);
    }

    /**
     * Test of clone method, of class Vertex.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Edge<String, Integer> edge1 = new Edge<>();
        String vAdj1 = "VAdj1";
        instance.addAdjVert(vAdj1, edge1);

        Edge<String, Integer> edge2 = new Edge<>();
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge2);

        Vertex<String, Integer> instClone = instance.clone();

        assertEquals(instance.numAdjVerts() == instClone.numAdjVerts(), true);

        //adjacency vertices should be equal
        Iterator<String> itvertClone = instClone.getAllAdjVerts().iterator();
        Iterator<String> itvertSource = instance.getAllAdjVerts().iterator();
        while (itvertSource.hasNext()) {
            assertEquals((itvertSource.next().equals(itvertClone.next()) == true), true);
        }

        //and edges also
        Iterator<Edge<String, Integer>> itedgeSource = instance.getAllOutEdges().iterator();
        while (itedgeSource.hasNext()) {
            Iterator<Edge<String, Integer>> itedgeClone = instClone.getAllOutEdges().iterator();
            boolean exists = false;
            while (itedgeClone.hasNext()) {
                if (itedgeSource.next().equals(itedgeClone.next())) {
                    exists = true;
                }
            }
            assertEquals((exists == true), true);
        }
    }

    /**
     * Test of toString method, of class Vertex.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        instance.setKey(1);
        instance.setElement("Vertex1");

        Vertex<String, Integer> instance2 = new Vertex<>(2, "Vertex2");

        Edge<String, Integer> edge1 = new Edge<>(null, 2, instance, instance2);
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge1);

        Vertex<String, Integer> instance3 = new Vertex<>(3, "Vertex3");
        Edge<String, Integer> edge2 = new Edge<>(null, 3, instance, instance3);
        String vAdj3 = "VAdj3";
        instance.addAdjVert(vAdj3, edge2);

    }

    /**
     * Test of toString method, of class Vertex.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");

        instance.setKey(1);
        instance.setElement(null);

        Vertex<String, Integer> instance2 = new Vertex<>(2, "Vertex2");

        Edge<String, Integer> edge1 = new Edge<>(null, 2, instance, instance2);
        String vAdj2 = "VAdj2";
        instance.addAdjVert(vAdj2, edge1);

        Vertex<String, Integer> instance3 = new Vertex<>(3, "Vertex3");
        Edge<String, Integer> edge2 = new Edge<>(null, 3, instance, instance3);
        String vAdj3 = "VAdj3";
        instance.addAdjVert(vAdj3, edge2);

    }

    /**
     * Test of toString method, of class Vertex.
     */
    @Test
    public void testToString3() {
        System.out.println("toString3");

        instance.setKey(1);
        instance.setElement(null);
    }

    /**
     * Test of getElement method, of class Vertex.
     */
    @Test
    public void testConstructorVertex() {
        System.out.println("Vertex");

        @SuppressWarnings("unchecked")
        Vertex v = new Vertex(instance);
        int key = v.getKey();
        Object element = v.getElement();
        Iterable outVerts;
        outVerts = v.getAllAdjVerts();

        assertEquals(key, instance.getKey());
        assertEquals(element, instance.getElement());
        assertEquals(outVerts, instance.getAllAdjVerts());

    }

}
