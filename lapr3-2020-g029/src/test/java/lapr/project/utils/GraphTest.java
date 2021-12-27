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

public class GraphTest {

    final Graph<String, String> instance = new Graph<>(true);

    public GraphTest() {
    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");

        assertEquals((instance.numVertices() == 0), true);

        instance.insertVertex("A");
        assertEquals((instance.numVertices() == 1), true);

        instance.insertVertex("B");
        assertEquals((instance.numVertices() == 2), true);

        instance.removeVertex("A");
        assertEquals((instance.numVertices() == 1), true);

        instance.removeVertex("B");
        assertEquals((instance.numVertices() == 0), true);
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<String> itVerts = instance.vertices().iterator();

        assertEquals(itVerts.hasNext() == false, true);

        instance.insertVertex("A");
        instance.insertVertex("B");

        itVerts = instance.vertices().iterator();

        assertEquals((itVerts.next().compareTo("A") == 0), true);
        assertEquals((itVerts.next().compareTo("B") == 0), true);

        instance.removeVertex("A");

        itVerts = instance.vertices().iterator();
        assertEquals((itVerts.next().compareTo("B")) == 0, true);

        instance.removeVertex("B");

        itVerts = instance.vertices().iterator();
        assertEquals(itVerts.hasNext() == false, true);
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertEquals((instance.numEdges() == 0), true);

        instance.insertEdge("A", "B", "Edge1", 6);
        assertEquals((instance.numEdges() == 1), true);

        instance.insertEdge("A", "C", "Edge2", 1);
        assertEquals((instance.numEdges() == 2), true);

        instance.removeEdge("A", "B");
        assertEquals((instance.numEdges() == 1), true);

        instance.removeEdge("A", "C");
        assertEquals((instance.numEdges() == 0), true);
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge<String, String>> itEdge = instance.edges().iterator();

        assertEquals((itEdge.hasNext() == false), true);

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        itEdge = instance.edges().iterator();

        itEdge.next();
        itEdge.next();
        assertEquals(itEdge.next().getElement().equals("Edge3") == true, true);

        itEdge.next();
        itEdge.next();
        assertEquals(itEdge.next().getElement().equals("Edge6") == true, true);

        instance.removeEdge("A", "B");

        itEdge = instance.edges().iterator();
        assertEquals(itEdge.next().getElement().equals("Edge2") == true, true);

        instance.removeEdge("A", "C");
        instance.removeEdge("B", "D");
        instance.removeEdge("C", "D");
        instance.removeEdge("C", "E");
        instance.removeEdge("D", "A");
        instance.removeEdge("E", "D");
        instance.removeEdge("E", "E");
        itEdge = instance.edges().iterator();
        assertEquals((itEdge.hasNext() == false), true);
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        assertEquals(instance.getEdge("A", "E") == null, true);

        assertEquals(instance.getEdge("B", "D").getElement().equals("Edge3") == true, true);
        assertEquals(instance.getEdge("D", "B") == null, true);

        instance.removeEdge("D", "A");
        assertEquals(instance.getEdge("D", "A") == null, true);

        assertEquals(instance.getEdge("E", "E").getElement().equals("Edge8") == true, true);
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testAdjVertices() {
        System.out.println("Test adjVertices");

        assertEquals(instance.adjVertices(null), null);
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);
        instance.insertEdge(null, null, "Edge9", 1);
        instance.insertEdge("A", "B", "Edge10", 1);

        Edge<String, String> edge0 = new Edge<>();

        String[] vertices = new String[2];

        //assertTrue("endVertices should be null", instance.endVertices(edge0)==null);
        Edge<String, String> edge1 = instance.getEdge("A", "B");
        Edge<String, String> edge2 = instance.getEdge(null, "B");
        Edge<String, String> edge3 = instance.getEdge("A", null);
        Edge<String, String> edge4 = instance.getEdge("A", "A");
        Edge<String, String> edge5 = instance.getEdge(null, null);
        Edge<String, String> edge6 = instance.getEdge("Z", "B");
        Edge<String, String> edge7 = new Edge<>("element", 3.4, null, null);

        //vertices = instance.endVertices(edge1);
        assertEquals(instance.endVertices(edge1)[0].equals("A"), true);
        assertEquals(instance.endVertices(edge1)[1].equals("B"), true);
        assertEquals(instance.endVertices(null), null);
        assertEquals(instance.endVertices(edge2), null);
        assertEquals(instance.endVertices(edge3), null);
        assertEquals(instance.endVertices(edge4), null);
        assertEquals(instance.endVertices(edge5), null);
        assertEquals(instance.endVertices(edge6), null);
        assertEquals(instance.endVertices(edge7), null);

    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Edge<String, String> edge5 = instance.getEdge("C", "E");
        String vert = instance.opposite("A", edge5);
        assertEquals(vert == null, true);

        Edge<String, String> edge1 = instance.getEdge("A", "B");
        vert = instance.opposite("A", edge1);
        assertEquals(vert.equals("B") == true, true);

        Edge<String, String> edge8 = instance.getEdge("E", "E");
        vert = instance.opposite("E", edge8);
        assertEquals(vert.equals("E") == true, true);

        vert = instance.opposite(null, edge8);
        assertEquals(vert, null);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        int outdeg = instance.outDegree("G");
        assertEquals(outdeg == -1, true);

        outdeg = instance.outDegree("A");
        assertEquals(outdeg == 2, true);

        outdeg = instance.outDegree("B");
        assertEquals(outdeg == 1, true);

        outdeg = instance.outDegree("E");
        assertEquals(outdeg == 2, true);
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        int indeg = instance.inDegree("G");
        assertEquals(indeg == -1, true);

        indeg = instance.inDegree("A");
        assertEquals(indeg == 1, true);

        indeg = instance.inDegree("D");
        assertEquals(indeg == 3, true);

        indeg = instance.inDegree("E");
        assertEquals(indeg == 2, true);
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");

        assertEquals(instance.outgoingEdges(null), null);

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Iterator<Edge<String, String>> itEdge = instance.outgoingEdges("C").iterator();
        Edge<String, String> first = itEdge.next();
        Edge<String, String> second = itEdge.next();
        assertEquals(
                ((first.getElement().equals("Edge4") == true && second.getElement().equals("Edge5") == true)
                || (first.getElement().equals("Edge5") == true && second.getElement().equals("Edge4") == true)), true);

        instance.removeEdge("E", "E");

        itEdge = instance.outgoingEdges("E").iterator();
        assertEquals((itEdge.next().getElement().equals("Edge7") == true), true);

        instance.removeEdge("E", "D");

        itEdge = instance.outgoingEdges("E").iterator();
        assertEquals((itEdge.hasNext() == false), true);
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        instance.insertVertex(null);

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Iterator<Edge<String, String>> itEdge = instance.incomingEdges("D").iterator();

        assertEquals((itEdge.next().getElement().equals("Edge3") == true), true);
        assertEquals((itEdge.next().getElement().equals("Edge4") == true), true);
        assertEquals((itEdge.next().getElement().equals("Edge7") == true), true);

        itEdge = instance.incomingEdges("E").iterator();

        assertEquals((itEdge.next().getElement().equals("Edge5") == true), true);
        assertEquals((itEdge.next().getElement().equals("Edge8") == true), true);

        instance.removeEdge("E", "E");

        itEdge = instance.incomingEdges("E").iterator();

        assertEquals((itEdge.next().getElement().equals("Edge5") == true), true);

        instance.removeEdge("C", "E");

        itEdge = instance.incomingEdges("E").iterator();
        assertEquals((itEdge.hasNext() == false), true);

        itEdge = instance.incomingEdges(null).iterator();
        assertEquals((itEdge.hasNext() == false), true);

        Iterable<Edge<String, String>> itEdge2 = instance.incomingEdges("edge12");
        assertEquals(itEdge2, null);
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");

        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        Iterator<String> itVert = instance.vertices().iterator();

        assertEquals((itVert.next().equals("A") == true), true);
        assertEquals((itVert.next().equals("B") == true), true);
        assertEquals((itVert.next().equals("C") == true), true);
        assertEquals((itVert.next().equals("D") == true), true);
        assertEquals((itVert.next().equals("E") == true), true);

        assertEquals(instance.insertVertex("F"), true);
        instance.insertVertex("Z");
        assertEquals(instance.insertVertex("Z"), false);
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");

        assertEquals((instance.numEdges() == 0), true);

        instance.insertEdge("A", "B", "Edge1", 6);
        assertEquals((instance.numEdges() == 1), true);

        instance.insertEdge("A", "C", "Edge2", 1);
        assertEquals((instance.numEdges() == 2), true);

        instance.insertEdge("B", "D", "Edge3", 3);
        assertEquals((instance.numEdges() == 3), true);

        instance.insertEdge("C", "D", "Edge4", 4);
        assertEquals((instance.numEdges() == 4), true);

        instance.insertEdge("C", "E", "Edge5", 1);
        assertEquals((instance.numEdges() == 5), true);

        instance.insertEdge("D", "A", "Edge6", 2);
        assertEquals((instance.numEdges() == 6), true);

        instance.insertEdge("E", "D", "Edge7", 1);
        assertEquals((instance.numEdges() == 7), true);

        instance.insertEdge("E", "E", "Edge8", 1);
        assertEquals((instance.numEdges() == 8), true);

        Iterator<Edge<String, String>> itEd = instance.edges().iterator();

        itEd.next();
        itEd.next();
        assertEquals((itEd.next().getElement().equals("Edge3") == true), true);
        itEd.next();
        itEd.next();
        assertEquals((itEd.next().getElement().equals("Edge6") == true), true);

        assertEquals(instance.insertEdge("A", "E", "Edge9", 1), true);
        instance.insertEdge(null, null, null, 0);
        assertEquals(instance.insertEdge(null, null, null, 0), false);
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("Test removeVertex");

        assertEquals(instance.removeVertex("A"), false);
        assertEquals(instance.removeVertex(null), false);
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");

        instance.removeVertex("C");
        assertEquals((instance.numVertices() == 4), true);

        Iterator<String> itVert = instance.vertices().iterator();
        assertEquals((itVert.next().equals("A") == true), true);
        assertEquals((itVert.next().equals("B") == true), true);
        assertEquals((itVert.next().equals("D") == true), true);
        assertEquals((itVert.next().equals("E") == true), true);

        instance.removeVertex("A");
        assertEquals((instance.numVertices() == 3), true);

        itVert = instance.vertices().iterator();
        assertEquals((itVert.next().equals("B") == true), true);
        assertEquals((itVert.next().equals("D") == true), true);
        assertEquals((itVert.next().equals("E") == true), true);

        instance.removeVertex("E");
        assertEquals((instance.numVertices() == 2), true);

        itVert = instance.vertices().iterator();

        assertEquals(itVert.next().equals("B") == true, true);
        assertEquals(itVert.next().equals("D") == true, true);

        instance.removeVertex("B");
        instance.removeVertex("D");
        assertEquals((instance.numVertices() == 0), true);
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("Test removeEdge");

        assertEquals(instance.removeEdge(null, null), false);

        assertEquals((instance.numEdges() == 0), true);

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        assertEquals((instance.numEdges() == 8), true);

        instance.removeEdge("E", "E");
        assertEquals((instance.numEdges() == 7), true);

        Iterator<Edge<String, String>> itEd = instance.edges().iterator();

        itEd.next();
        itEd.next();
        assertEquals((itEd.next().getElement().equals("Edge3") == true), true);
        itEd.next();
        itEd.next();
        assertEquals((itEd.next().getElement().equals("Edge6") == true), true);

        assertEquals(instance.removeEdge("C", "D"), true);
        assertEquals((instance.numEdges() == 6), true);

        itEd = instance.edges().iterator();
        itEd.next();
        itEd.next();
        assertEquals((itEd.next().getElement().equals("Edge3") == true), true);
        assertEquals((itEd.next().getElement().equals("Edge5") == true), true);
        assertEquals((itEd.next().getElement().equals("Edge6") == true), true);
        assertEquals((itEd.next().getElement().equals("Edge7") == true), true);
        assertEquals(instance.removeEdge("B", "D"), true);

    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("Test Clone");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        Graph<String, String> instClone = instance.clone();

        assertEquals(instance.numVertices() == instClone.numVertices(), true);
        assertEquals(instance.numEdges() == instClone.numEdges(), true);

        //vertices should be equal
        Iterator<String> itvertClone = instClone.vertices().iterator();
        Iterator<String> itvertSource = instance.vertices().iterator();
        while (itvertSource.hasNext()) {
            assertEquals((itvertSource.next().equals(itvertClone.next()) == true), true);
        }
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");

        instance.insertEdge("A", "B", "Edge1", 6);
        instance.insertEdge("A", "C", "Edge2", 1);
        instance.insertEdge("B", "D", "Edge3", 3);
        instance.insertEdge("C", "D", "Edge4", 4);
        instance.insertEdge("C", "E", "Edge5", 1);
        instance.insertEdge("D", "A", "Edge6", 2);
        instance.insertEdge("E", "D", "Edge7", 1);
        instance.insertEdge("E", "E", "Edge8", 1);

        assertEquals(instance.equals(null), false);

        assertEquals(instance.equals(instance), true);

        assertEquals(instance.equals(instance.clone()), true);

        assertEquals(instance.equals(new ParkingLot(2, 2, "drone")), false);

        Graph<String, String> other = instance.clone();

        other.removeEdge("E", "E");
        assertEquals(instance.equals(other), false);

        other.insertEdge("E", "E", "Edge8", 1);
        assertEquals(instance.equals(other), true);

        other.removeEdge("E", "E");
        assertEquals(instance.equals(other), false);

        other.insertEdge("E", "Z", "Edge8", 1);
        assertEquals(instance.equals(other), false);

        other.removeVertex("D");
        assertEquals(instance.equals(other), false);

    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {

        System.out.println(instance);
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString2() {

        System.out.println("toString");

        instance.insertEdge("A", "B", "Edge1", 2);
        instance.insertVertex("Vertex1");
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");

        String expResult = "Graph: 3 vertices, 1 edges\nA (0): \n      (Edge1) - 2.0 - B\n\nB (1): \n\nVertex1 (2):";
        String result = instance.toString().trim();
        assertEquals(expResult, result);

    }

}
