/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.controller.AplicationPOT;

public class Courier {

    /**
     * The name of the Courier
     */
    private String name;
    
    /**
     * The NIF of the Courier
     */
    private int nif;
    
    /**
     * The social security number of
     * the Courier
     */
    private int socialSecurityNumber;
    
    /**
     * The email of the Courier
     */
    private String email;
    
    /**
     * The password of the Courier
     */
    private String password;
    
    /**
     * The backpackCapacity of the
     * Courier
     */
    private double backpackCapacity = 15; //in kilograms 

    /**
     * Creates an instance of the Courier receiving
     * the name, nif, socialSecurityNumber, email and
     * password by parameter
     * 
     * @param name
     * @param nif
     * @param socialSecurityNumber
     * @param email
     * @param password 
     */
    public Courier(String name, String nif, String socialSecurityNumber, String email, String password) {
        this.name = name;
        this.nif = Integer.parseInt(nif);
        this.socialSecurityNumber = Integer.parseInt(socialSecurityNumber);
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the name of the
     * Courier
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the NIF of the
     * Courier
     * 
     * @return the nif
     */
    public int getNIF() {
        return nif;
    }

    /**
     * Returns the social security
     * number of the Courier
     * 
     * @return the socialSecurityNumber
     */
    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Returns the email of the
     * Courier
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password of the
     * Courier
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Returns the backpack capacity
     * of the courier
     * 
     * @return the backpackCapacity
     */
    public double getBackpackCapacity() {
        return backpackCapacity;
    }
    
    /**
     * Changes the backpackCapacity of the Courier
     * to the one received by parameter
     * 
     * @param aBackpackCapacity the backpackCapacity to set
     */
    public void setBackpackCapacity(double aBackpackCapacity) {
        backpackCapacity = aBackpackCapacity;
    }

    /**
     * Changes the name of the Courier
     * to the one received by parameter
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the NIF of the Courier
     * to the one received by parameter
     * 
     * @param nif the nif to set
     */
    public void setNIF(int nif) {
        this.nif = nif;
    }

    /**
     * Changes the socialSecurityNumber of the 
     * Courier to the one received by parameter
     * 
     * @param socialSecurityNumber the socialSecurityNumber to set
     */
    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Changes the email of the Courier
     * to the one received by parameter
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Changes the password of the Courier
     * to the one received by parameter
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns the Courier saved in the database
     * with the same NIF of this Courier
     * 
     * @param nif
     * @return 
     */
    public Courier getCourier(String nif) {
        return AplicationPOT.getInstance().getPlatform().getCoudb().getCourier(nif);
    }

    /**
     * Validates if the Courier with this 
     * id is already in the database, if it is
     * not it saves it
     */
    public void save() {
        try {
            getCourier(String.format("%d", this.nif));
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getCoudb().addCourier(this);
        }
    }

    /**
     * Updates the data of this Courier
     * in the database
     */
    public void update() {
        AplicationPOT.getInstance().getPlatform().getCoudb().updateCourier(this);
    }

    /**
     * Returns a textual description of the
     * Courier presenting the name, nif, 
     * socialSecurityNumber, email and password
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format("%s, %d, %d, %s, %s", name, nif, socialSecurityNumber, email, password);
    }
    
    /**
     * Returns if the object received by parameter
     * is the same as this Courier
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
        Courier otherCourier = (Courier) otherObjeto;
        
        return name.equalsIgnoreCase(otherCourier.name) && nif == otherCourier.nif &&
                socialSecurityNumber == otherCourier.socialSecurityNumber && email.equalsIgnoreCase(otherCourier.email);

    }
}
