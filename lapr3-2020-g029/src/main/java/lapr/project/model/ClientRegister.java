/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.AdressDB;
import lapr.project.data.ClientDB;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class ClientRegister {

    /**
     * A list of Clients
     */
    private final List<Client> clientList;

    /**
     * Creates an instance of ClientRegister
     */
    public ClientRegister() {
        clientList = new ArrayList<>();
    }

    /**
     * Returns the list of Clients
     *
     * @return the list
     */
    public List<Client> getClientList() {
        return new ArrayList<>(clientList);
    }

    /**
     * Creates a Client with the name, password, email, NIF, Adress by parameter
     *
     * @param name
     * @param password
     * @param email
     * @param nif
     * @param adress
     * @return the client
     */
    public Client newClient(String name, String password, String email, String nif, Address adress) {
        return new Client(generateId(name, nif), name, password, email, nif, adress);
    }

    /**
     * Validates the client using the id
     *
     * @param email
     * @return if the clients exists
     */
    public Client getClientByEmail(String email) {
        for (Client c : clientList) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Validates the client using the id
     *
     * @param email
     * @return if the clients exists
     */
    public boolean validateClientByEmail(String email) {
        for (Client c : clientList) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registers a client and adds it to the list
     *
     * @param client
     * @return if it was added
     */
    public boolean registerClient(Client client, Platform plat) {
        if (!validateClient(client)) {
            registerClientWithRole(client, plat);
            ClientDB clientDB = AplicationPOT.getInstance().getPlatform().getCdb();
            AdressDB adressDB = AplicationPOT.getInstance().getPlatform().getAdb();
            adressDB.addAdress(client.getAdress());
            clientDB.addClient(client);
            return addClient(client);

        }
        return false;
    }

    /**
     * Registers a collaborator as a user
     *
     * @param client
     * @param plat
     */
    public void registerClientWithRole(Client client, Platform plat) {
        String name = client.getName();
        String email = client.getEmail();
        String pwd = client.getPassword();
        AutorizationFacade aut = plat.getAutorizationFacade();
        aut.registerUserWithRole(name, email, pwd, "Client");
    }

    /**
     * Validates if the Client is in the list
     *
     * @param client
     * @return if the client is in the list
     */
    public boolean validateClient(Client client) {
        return getClientList().contains(client);
    }

    /**
     * Adds a client to the clients list
     *
     * @param client
     * @return if it was added
     */
    public boolean addClient(Client client) {
        return clientList.add(client);
    }

    /**
     * Generates a id to the client
     *
     * @param name
     * @param nif
     * @return the id
     */
    public String generateId(String name, String nif) {
        String[] names = name.split(" ");
        String space = "";
        space += names[0].charAt(0);
        space += nif;

        return space;
    }

    /**
     * Adds the Client Adress to the graph of the Platform
     *
     * @param client
     * @throws IOException
     */
    public boolean addClientAdressToGraph(Client client){
        try {
            Graph scooterMap = AplicationPOT.getInstance().getPlatform().getScooterMap();
            Map<String, List<String>> restrictionsScooter = AplicationPOT.getInstance().getPlatform().getListRestrictionsScooter();
            scooterMap.insertVertex(client.getAdress());
            Iterable<Address> it = scooterMap.vertices();
            for (Address ad : it) {
                if (!client.getAdress().equals(ad)) {
                    if (restrictionsScooter.get(ad.getId()) != null) {
                        if (!restrictionsScooter.get(ad.getId()).contains(client.getAdress().getId())) {
                            scooterMap.insertEdge(ad, client.getAdress(), "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString(), client.getAdress().getDecimalDegree1().toString(), client.getAdress().getDecimalDegree2().toString())));
                        }
                    } else {
                        scooterMap.insertEdge(ad, client.getAdress(), "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString(), client.getAdress().getDecimalDegree1().toString(), client.getAdress().getDecimalDegree2().toString())));
                    }
                    if (restrictionsScooter.get(client.getAdress().getId()) != null) {
                        if (!restrictionsScooter.get(client.getAdress().getId()).contains(ad.getId())) {
                            scooterMap.insertEdge(client.getAdress(), ad, "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(client.getAdress().getDecimalDegree1().toString(), client.getAdress().getDecimalDegree2().toString(), ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString())));
                        }
                    } else {
                        scooterMap.insertEdge(client.getAdress(), ad, "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(client.getAdress().getDecimalDegree1().toString(), client.getAdress().getDecimalDegree2().toString(), ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString())));
                    }
                }
            }

            Graph droneMap = AplicationPOT.getInstance().getPlatform().getDroneMap();
            Map<String, List<String>> restrictionsDrone = AplicationPOT.getInstance().getPlatform().getListRestrictionsDrone();
            droneMap.insertVertex(client.getAdress());
            Iterable<Address> it2 = droneMap.vertices();
            for (Address ad : it2) {
                if (!client.getAdress().equals(ad)) {
                    if (restrictionsDrone.get(ad.getId()) != null) {
                        if (!restrictionsDrone.get(ad.getId()).contains(client.getAdress().getId())) {
                            droneMap.insertEdge(ad, client.getAdress(), "", GraphAlgorithms.calcularDistancia(ad.getDecimalDegree1(), ad.getDecimalDegree2(), client.getAdress().getDecimalDegree1(), client.getAdress().getDecimalDegree2()));
                        }
                    } else {
                        droneMap.insertEdge(ad, client.getAdress(), "", GraphAlgorithms.calcularDistancia(ad.getDecimalDegree1(), ad.getDecimalDegree2(), client.getAdress().getDecimalDegree1(), client.getAdress().getDecimalDegree2()));
                    }
                    if (restrictionsDrone.get(client.getAdress().getId()) != null) {
                        if (!restrictionsDrone.get(client.getAdress().getId()).contains(ad.getId())) {
                            droneMap.insertEdge(client.getAdress(), ad, "", GraphAlgorithms.calcularDistancia(client.getAdress().getDecimalDegree1(), client.getAdress().getDecimalDegree2(), ad.getDecimalDegree1(), ad.getDecimalDegree2()));
                        }
                    } else {
                        droneMap.insertEdge(client.getAdress(), ad, "", GraphAlgorithms.calcularDistancia(client.getAdress().getDecimalDegree1(), client.getAdress().getDecimalDegree2(), ad.getDecimalDegree1(), ad.getDecimalDegree2()));
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
