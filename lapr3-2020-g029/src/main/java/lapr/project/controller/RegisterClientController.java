/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.ClientRegister;
import lapr.project.model.Platform;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;


public class RegisterClientController {

    /**
     * The Platform
     */
    private final Platform plat;

    /**
     * The registry of client
     */
    private ClientRegister rclient;

    /**
     * A Client
     */
    private Client client;

    /**
     * The Autorization
     */
    private final AutorizationFacade autorization;

    /**
     * Creates an instance of RegisterClientController and gets the
     * aplicationPot and the Client registry
     */
    public RegisterClientController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        rclient = plat.getClientRegister();
        this.autorization = plat.getAutorizationFacade();

    }

    /**
     * Creates a Client with the name, password, email, NIF, IBAN, adress by
     * parameter
     *
     * @param name
     * @param password
     * @param email
     * @param NIF
     * @param street
     * @param doorNumber
     * @param postalCode
     * @param locality
     * @param decimalDegree1
     * @param decimalDegree2
     * @return the client
     */
    public boolean newClient(String name, String password, String email, String nif, String street, int doorNumber, String postalCode, String locality, double decimalDegree1, double decimalDegree2) {
        rclient = plat.getClientRegister();
        if (!rclient.validateClientByEmail(email)) {
            Address adress = new Address(street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
            client = rclient.newClient(name, password, email, nif, adress);
            return true;
        }
        return false;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Searchs if the email already exists in the platform
     *
     * @param email
     * @return if the email exists
     */
    public boolean existsEmail(String email) {
        return autorization.hasUser(email);
    }

    /**
     * Registers the client and adds it to the client list
     *
     * @return if the client was added
     */
    public boolean registerClient() {
        return rclient.registerClient(client,plat);
    }
    
    /**
     * Adds the Client Address of the Client
     * to the graph
     * 
     * @return 
     */
    public boolean addClientToGraph(){
        return rclient.addClientAdressToGraph(client);
    }

}
