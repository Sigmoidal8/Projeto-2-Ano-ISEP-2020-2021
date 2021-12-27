/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Locale;
import lapr.project.controller.AplicationPOT;

public class Client {
    
    /**
     * Id of the Client
     */
    private final String id;
    
    /**
     * Name of the Client
     */
    private String name;
    
    /**
     * Password of the client
     */
    private String password;
    
    /**
     * Email of the client
     */
    private String email;
    
    /**
     * NIF of the client
     */
    private int nif;
    
    /**
     * Amount of credits
     * of the Client
     */
    private double credits;
    
    /**
     * The Adress of the Client
     */
    private Address adress;
    
    /**
     * Creates an instance of Client receiving
     * the id, name, password, email, nif and adress
     * by parameter and initializing its credits at 0.
     * 
     * @param id
     * @param name
     * @param password
     * @param email
     * @param nif
     * @param adress 
     */
    public Client(String id, String name, String password, String email, String nif,Address adress){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.nif = Integer.parseInt(nif);
        this.credits = 0;      //sempre que se regista um novo cliente inicia os seus créditos a 0.
        this.adress = adress;
    }

    /**
     * Creates an instance of Client receiving
     * the id, name, password, email, nif, credits
     * and adress by parameter
     * 
     * @param id
     * @param name
     * @param password
     * @param email
     * @param nif
     * @param credits
     * @param adress 
     */
    public Client(String id, String name, String password, String email, String nif, double credits,Address adress){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.nif = Integer.parseInt(nif);
        this.credits = credits;      //sempre que se regista um novo cliente inicia os seus créditos a 0.
        this.adress = adress;
    }
    
    /**
     * Returns the id of the
     * Client
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the
     * Client
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the password of the
     * Client
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the email of the
     * Client
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the NIF of the
     * Client
     * 
     * @return the NIF
     */
    public int getNIF() {
        return nif;
    }

    /**
     * Returns the credits of the
     * Client
     * 
     * @return the credits
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Returns the Adress of the
     * Client
     * 
     * @return the adress
     */
    public Address getAdress() {
        return adress;
    }


    /**
     * Changes the name of the Client
     * to the one received by parameter
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the password of the Client
     * to the one received by parameter
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Changes the email of the Client
     * to the one received by parameter
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Changes the NIF of the Client
     * to the one received by parameter
     * 
     * @param nif
     */
    public void setNIF(int nif) {
        this.nif = nif;
    }

    /**
     * Changes the amount of credits of the Client
     * to the one received by parameter
     * 
     * @param credits the credits to set
     */
    public void setCredits(double credits) {
        this.credits = credits;
    }

    /**
     * Changes the Adress of the Client
     * to the one received by parameter
     * 
     * @param adress the adress to set
     */
    public void setAdress(Address adress) {
        this.adress = adress;
    }
   
    /**
     * Returns the Client saved in the database
     * with the same id of this Client
     * 
     * @param id
     * @return 
     */
    public Client getClient(String id) {
        return AplicationPOT.getInstance().getPlatform().getCdb().getClient(id);
    }

    /**
     * Validates if the Client with this 
     * id is already in the database, if it is
     * not it saves it
     */
    public void save() {
        try {
            getClient(this.getId());
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getCdb().addClient(this);
        }
    }

    /**
     * Updates the data of this Client
     * in the database
     */
    public void update() {
        AplicationPOT.getInstance().getPlatform().getCdb().updateClient(this);
    }
    
    /**
     * Returns a textual description of the
     * Client presenting the id, name, password,
     * email, nif, credits, street, latitude and
     * longitude
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format(Locale.US,"%s, %s, %s, %s, %d, %.2f, %s", id, name, password, email, nif, credits, adress);
    }
    
    /**
     * Returns if the object received by parameter
     * is the same as this Client
     *  
     * @param otherObjeto
     * @return true or false
     */
    @Override
    public boolean equals(Object otherObjeto) {
        if (this == otherObjeto) {
            return true;
        }
        if (otherObjeto == null || this.getClass() != otherObjeto.getClass()) {
            return false;
        }
        Client otherclient = (Client) otherObjeto;

        return id.equalsIgnoreCase(otherclient.id);
    }
}
