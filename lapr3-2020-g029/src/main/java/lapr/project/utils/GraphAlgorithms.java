/*
* A collection of graph algorithms.
 */
package lapr.project.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.model.Address;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    private double finalPathDistance = 0;

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph novoGrafoConexo with nonnegative edge weights This
     * implementation uses Dijkstra's algorithm
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys
     * @param dist minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {

        int vKey = g.getKey(vOrig);
        dist[vKey] = 0;
        while (vKey != -1) {
            vOrig = vertices[vKey];
            visited[vKey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                int vKeyAdj = g.getKey(vAdj);
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);
                if (!visited[vKeyAdj] && dist[vKeyAdj] > dist[vKey] + edge.getWeight()) {
                    dist[vKeyAdj] = dist[vKey] + edge.getWeight();
                    pathKeys[vKeyAdj] = vKey;
                }
            }
            double minDist = Double.MAX_VALUE;
            vKey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vKey = i;
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {

        if (!vOrig.equals(vDest)) {
            path.push(vDest);
            int vKey = g.getKey(vDest);
            int pervVKey = pathKeys[vKey];
            vDest = verts[pervVKey];

            getPath(g, vOrig, vDest, verts, pathKeys, path);
        } else {
            path.push(vOrig);
        }
    }

    //shortest-path between vOrig and vDest
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allKeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        double lengthPath = dist[g.getKey(vDest)];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;
    }

    //shortest-path between voInf and all other
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allKeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != Double.MAX_VALUE) {
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            }
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    public static String DistanceCalculator(String x1, String y1, String x2, String y2) throws IOException {
        StringBuilder link2 = new StringBuilder();
        link2.append("https://route.ls.hereapi.com/routing/7.2/calculateroute.xml?apiKey=ZS4HN-O9CLS4SwrC2lJujJufnGZ4N9tHo4aaMvEcyAo&waypoint0=geo!");
        link2.append(x1.trim());
        link2.append(",");
        link2.append(y1.trim());
        link2.append("&waypoint1=geo!");
        link2.append(x2.trim());
        link2.append(",");
        link2.append(y2.trim());
        link2.append("&mode=balanced;car;traffic:disabled");

        URL url3 = new URL(link2.toString());
        HttpURLConnection con2 = (HttpURLConnection) url3.openConnection();
        con2.setRequestMethod("GET");

        con2.getResponseCode();
        BufferedReader inReader = new BufferedReader(new InputStreamReader(con2.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = inReader.readLine()) != null) {
            content.append(inputLine);
        }
        inReader.close();

        con2.disconnect();
        return (content.toString().split("Distance")[1].split(">")[1].split("<")[0]);
    }

    public List<Address> pathCalculator(String x1, String y1, String x2, String y2) throws IOException {
        StringBuilder link = new StringBuilder();
        link.append("https://route.ls.hereapi.com/routing/7.2/calculateroute.xml?apiKey=ZS4HN-O9CLS4SwrC2lJujJufnGZ4N9tHo4aaMvEcyAo&waypoint0=geo!");
        link.append(x1.trim());
        link.append(",");
        link.append(y1.trim());
        link.append("&waypoint1=geo!");
        link.append(x2.trim());
        link.append(",");
        link.append(y2.trim());
        link.append("&mode=balanced;car;traffic:disabled");

        URL url = new URL(link.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        StringBuffer aux = content;

        String[] aux2 = aux.toString().split("<Latitude>");
        List<String> latitudes = new ArrayList<>();
        List<String> longitudes = new ArrayList<>();
        List<String> streets = new ArrayList<>();

        for (int i = 10; i < aux2.length - 1; i++) {
            String latitude = aux2[i].split("<")[0];
            latitudes.add(latitude);
        }

        aux = content;
        aux2 = content.toString().split("<Longitude>");
        for (int i = 10; i < aux2.length - 1; i++) {
            String longitude = aux2[i].split("<")[0];
            longitudes.add(longitude);
        }

        aux = content;
        aux2 = content.toString().split("next-street&quot;&gt;");
        for (int i = 1; i < aux2.length; i++) {
            String street = aux2[i].split("&lt;")[0];
            if (!streets.contains(street)) {
                streets.add(street);
            } else {
                streets.add("");
            }
        }

        List<Address> adresses = new ArrayList<>();
        for (int i = 0; i < streets.size(); i++) {
            if (!streets.get(i).equals("")) {
                Address adress = new Address(streets.get(i), Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i)));
                adresses.add(adress);
            }
        }

        return adresses;
    }

    public static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        int raio = 6371; //Raio da terra em Km
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double latitude1 = lat1 * Math.PI / 180; // φ, λ in radians
            double latitude2 = lat2 * Math.PI / 180;
            double latitudeDiference = (lat2 - lat1) * Math.PI / 180;
            double longitudeDiference = (lon2 - lon1) * Math.PI / 180;
            double a = Math.pow(Math.sin(latitudeDiference / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(longitudeDiference / 2), 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = c * raio;
            return d * 1000;
        }
    }

    public Graph<Address, Double> graphAllCombinations(List<Address> listAdressDeliverys) throws IOException {
        finalPathDistance = 0;
        Graph<Address, Double> combinationsGraph = new Graph<Address, Double>(true);

        for (Address liAdDel : listAdressDeliverys) {
            combinationsGraph.insertVertex(liAdDel);
        }

        for (Address a1 : listAdressDeliverys) {
            for (Address a2 : listAdressDeliverys) {
                if (a1 != a2) {
                    double distance = Double.parseDouble(DistanceCalculator(a1.getDecimalDegree1().toString(), a1.getDecimalDegree2().toString(), a2.getDecimalDegree1().toString(), a2.getDecimalDegree2().toString()));
                    combinationsGraph.insertEdge(a1, a2, null, distance);
                }
            }
        }
        return combinationsGraph;
    }

    public Graph<Address, Double> graphDroneDelivery(List<Address> listAdressDeliverys) {
        finalPathDistance = 0;
        Graph<Address, Double> combinationsGraph = new Graph<Address, Double>(false);

        for (Address liAdDel : listAdressDeliverys) {
            combinationsGraph.insertVertex(liAdDel);
        }

        for (Address a1 : listAdressDeliverys) {
            for (Address a2 : listAdressDeliverys.subList(listAdressDeliverys.lastIndexOf(a1), listAdressDeliverys.size())) {
                if (a1 != a2) {
                    double distance = calcularDistancia(a1.getDecimalDegree1(), a1.getDecimalDegree2(), a2.getDecimalDegree1(), a2.getDecimalDegree2());
                    combinationsGraph.insertEdge(a1, a2, null, distance);
                }
            }
        }
        return combinationsGraph;
    }

    public List<Address> suggestionMostEconomicScooterPath(List<Address> deliveryList) {
        double totalDistance = 0;
        List<Address> finalPath = new ArrayList<>();
        for (int i = 0; i < deliveryList.size() - 1; i++) {
            LinkedList<Address> pathHypothesis = new LinkedList<>();
            double distance = GraphAlgorithms.shortestPath(AplicationPOT.getInstance().getPlatform().getScooterMap(), deliveryList.get(i), deliveryList.get(i + 1), pathHypothesis);
            totalDistance += distance;
            for (Address ad : pathHypothesis) {
                if(!pathHypothesis.getLast().equals(ad)){
                    finalPath.add(ad);
                }
            }
        }
        finalPath.add(deliveryList.get(deliveryList.size()-1));
        finalPathDistance = totalDistance;
        return finalPath;
    }

    public List<Address> suggestionMostEconomicDronePath(List<Address> deliveryList) {
        double totalDistance = 0;
        List<Address> finalPath = new ArrayList<>();
        for (int i = 0; i < deliveryList.size() - 1; i++) {
            LinkedList<Address> pathHypothesis = new LinkedList<>();
            double distance = GraphAlgorithms.shortestPath(AplicationPOT.getInstance().getPlatform().getDroneMap(), deliveryList.get(i), deliveryList.get(i + 1), pathHypothesis);
            totalDistance += distance;
            for (Address ad : pathHypothesis) {
                if(!pathHypothesis.getLast().equals(ad)){
                    finalPath.add(ad);
                }
            }
        }
        finalPath.add(deliveryList.get(deliveryList.size()-1));
        finalPathDistance = totalDistance;
        return finalPath;
    }

    /**
     * @return the finalPathDistance
    */
    public double getFinalPathDistance() {
        return finalPathDistance;
    }
}
