/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.IOException;
import java.util.List;
import lapr.project.model.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import lapr.project.controller.AplicationPOT;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GraphAlgorithmsTest {

    final Graph<String, String> completeMap = new Graph<>(false);
    Graph<String, String> incompleteMap = new Graph<>(false);

    Graph<String, String> incompleteMap2;

    public GraphAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");
        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");
        completeMap.insertEdge("Porto", "Aveiro", "A1", 75);
        completeMap.insertEdge("Porto", "Braga", "A3", 60);
        completeMap.insertEdge("Porto", "Vila Real", "A4", 100);
        completeMap.insertEdge("Viseu", "Guarda", "A25", 75);
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23", 100);
        completeMap.insertEdge("Aveiro", "Coimbra", "A1", 60);
        completeMap.insertEdge("Coimbra", "Lisboa", "A1", 200);
        completeMap.insertEdge("Coimbra", "Leiria", "A34", 80);
        completeMap.insertEdge("Aveiro", "Leiria", "A17", 120);
        completeMap.insertEdge("Leiria", "Lisboa", "A8", 150);
        completeMap.insertEdge("Aveiro", "Viseu", "A25", 85);
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        completeMap.insertEdge("Lisboa", "Faro", "A2", 280);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge("Aveiro", "Viseu");
        incompleteMap.removeEdge("Leiria", "Castelo Branco");
        incompleteMap.removeEdge("Lisboa", "Faro");

    }

    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<String>();
        double lenpath = 0;
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "LX", shortPath);
        assertTrue("Length path should be 0 if vertex does not exist", lenpath == 0);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Faro", shortPath);
        assertTrue("Length path should be 0 if there is no path", lenpath == 0);

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Porto", shortPath);
        assertTrue("Number of nodes should be 1 if source and vertex are the same", shortPath.size() == 1);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Lisboa", shortPath);
        assertTrue("Path between Porto and Lisboa should be 335 Km", lenpath == 335);

        Iterator<String> it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Braga", "Leiria", shortPath);
        assertTrue("Path between Braga and Leiria should be 255 Km", lenpath == 255);

        it = shortPath.iterator();

        assertTrue("First in path should be Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);

        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue("Path between Porto and Castelo Branco should be 335 Km", lenpath == 335);
        assertTrue("N. cities between Porto and Castelo Branco should be 5 ", shortPath.size() == 5);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue("Path between Porto and Castelo Branco should now be 365 Km", lenpath == 365);
        assertTrue("Path between Porto and Castelo Branco should be 4 cities", shortPath.size() == 4);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

    }

    /**
     * Test of shortestPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        boolean test = GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists);

        assertEquals(paths.size(), dists.size());
        assertEquals(completeMap.numVertices(), paths.size());
        assertEquals(1, paths.get(completeMap.getKey("Porto")).size());
        assertEquals(Arrays.asList("Porto", "Aveiro", "Coimbra", "Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        assertEquals(Arrays.asList("Porto", "Aveiro", "Viseu", "Guarda", "Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));
        assertEquals(335, dists.get(completeMap.getKey("Castelo Branco")), 0.01);
        assertEquals(test, true);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco        
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        GraphAlgorithms.shortestPaths(completeMap, "Porto", paths, dists);
        assertEquals(365, dists.get(completeMap.getKey("Castelo Branco")), 0.01);
        assertEquals(Arrays.asList("Porto", "Aveiro", "Leiria", "Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));

        GraphAlgorithms.shortestPaths(incompleteMap, "Porto", paths, dists);
        assertEquals(Double.MAX_VALUE, dists.get(completeMap.getKey("Faro")), 0.01);
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")), 0.01);
        assertEquals(Arrays.asList("Porto", "Aveiro", "Coimbra", "Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")), 0.01);

        GraphAlgorithms.shortestPaths(incompleteMap, "Braga", paths, dists);
        assertEquals(255, dists.get(completeMap.getKey("Leiria")), 0.01);
        assertEquals(Arrays.asList("Braga", "Porto", "Aveiro", "Leiria"), paths.get(completeMap.getKey("Leiria")));
    }

    /**
     * Test of shortestPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPaths2() {
        System.out.println("Test of shortest path");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        boolean test = GraphAlgorithms.shortestPaths(completeMap, null, paths, dists);
        assertEquals(test, false);

    }

    /**
     * Test of DistanceCalculator method, of class GraphAlgorithms.
     */
    @Test
    public void testDistanceCalculator() throws Exception {
        System.out.println("DistanceCalculator");
        String x1 = "41";
        String y1 = "-8.466667";
        String x2 = "41.05748886327707";
        String y2 = "-8.523697963520915";
        GraphAlgorithms instance = new GraphAlgorithms();
        String expResult = "9429";
        String result = instance.DistanceCalculator(x1, y1, x2, y2);
        assertEquals(expResult, result);
    }

    /**
     * Test of DistanceCalculator method, of class GraphAlgorithms.
     */
    @Test
    public void testDistanceCalculator2() throws Exception {
        System.out.println("DistanceCalculator2");
        String x1 = "41";
        String y1 = "-8";
        String x2 = "41";
        String y2 = "-8";
        GraphAlgorithms instance = new GraphAlgorithms();
        String expResult = "0";
        String result = instance.DistanceCalculator(x1, y1, x2, y2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of graphAllCombinations method, of class GraphAlgorithms.
     */
    @Test
    public void testGraphAllCombinations() throws Exception {
        System.out.println("graphAllCombinations");
        Address a1 = new Address("street", 23, "postalCode", "locality", 41, -8.466667);
        Address a2 = new Address("street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        List<Address> listAdressDeliverys = new ArrayList<>();
        listAdressDeliverys.add(a1);
        listAdressDeliverys.add(a2);
        GraphAlgorithms instance = new GraphAlgorithms();
        Graph<Address, Double> expResult = new Graph<Address, Double>(true);
        expResult.insertVertex(a1);
        expResult.insertVertex(a2);
        expResult.insertEdge(a1, a2, null, 9429.0);
        expResult.insertEdge(a2, a1, null, 11494.0);
        Graph<Address, Double> result = instance.graphAllCombinations(listAdressDeliverys);
        assertEquals(expResult, result);
    }

    /**
     * Test of calcularDistancia method, of class GraphAlgorithms.
     */
    @Test
    public void testCalcularDistancia() {
        System.out.println("calcularDistancia");
        double lat1 = 40.76;
        double lon1 = -73.984;
        double lat2 = 38.89;
        double lon2 = -77.032;
        GraphAlgorithms instance = new GraphAlgorithms();
        double expResult = 333113.0;
        double result = instance.calcularDistancia(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of graphDroneDelivery method, of class GraphAlgorithms.
     */
    @Test
    public void testGraphDroneDelivery() throws Exception {
        System.out.println("graphDroneDelivery");

        Address a1 = new Address("street", 23, "postalCode", "locality", 41, -8.466667);
        Address a2 = new Address("street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        List<Address> listAdressDeliverys = new ArrayList<>();
        listAdressDeliverys.add(a1);
        listAdressDeliverys.add(a2);

        GraphAlgorithms instance = new GraphAlgorithms();
        Graph<Address, Double> expResult = new Graph<Address, Double>(false);
        expResult.insertVertex(a1);
        expResult.insertVertex(a2);
        expResult.insertEdge(a1, a2, null, 333.2071);

        Graph<Address, Double> result = instance.graphDroneDelivery(listAdressDeliverys);
        assertEquals(expResult, result);
    }

    /**
     * Test of suggestionMostEconomicDronePath method, of class GraphAlgorithms.
     */
    @Test
    public void testSuggestionMostEconomicDronePath() {
        System.out.println("SuggestionMostEconomicDronePath");
        Address a1 = new Address("street", 21, "postalCode", "locality", 41, -8.466667);
        Address a2 = new Address("street", 23, "postalCode", "locality", 41.05748886327707, -8.523697963520915);
        
        List<Address> listAdressDeliverys = new ArrayList<>();
        listAdressDeliverys.add(a1);
        listAdressDeliverys.add(a2);
        
        Graph<Address, Double> combinationGraph = new Graph<>(true);
        Graph grafo = AplicationPOT.getInstance().getPlatform().getDroneMap();
        
        grafo.insertVertex(a1);
        grafo.insertVertex(a2);
        grafo.insertEdge(a1, a2, grafo, 2000);
        grafo.insertEdge(a2, a1, grafo, 1700);
        
        List<Address> expResult = new ArrayList<>();
        expResult.add(a1);
        expResult.add(a2);
        
        GraphAlgorithms instance = new GraphAlgorithms();
        
        List<Address> result = instance.suggestionMostEconomicDronePath(listAdressDeliverys);
        assertEquals(expResult, result);
    }
}
