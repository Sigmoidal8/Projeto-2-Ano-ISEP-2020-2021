/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Locale;
import lapr.project.controller.AplicationPOT;

public class Address {

    /**
     * Id of the adress
     */
    private final String id;
    
    /**
     * Name of the street
     */
    private String street;
    
    /**
     * Door number of the adress
     */
    private int doorNumber;
    
    /**
     * Postal code of the adress
     */
    private String postalCode;
    
    /**
     * Locality of the adress
     */
    private String locality;
    
    /**
     * The latitude of the adress
     */
    private Double decimalDegree1;
    
    /**
     * The longitude of the adress
     */
    private Double decimalDegree2;
    
    /**
     * Creates an instance of Adress receiving the
     * street, doorNUmber, postalCode, locality,decimalDegree1
     * and decimalDegree2 by parameter
     * 
     * @param street
     * @param doorNumber
     * @param postalCode
     * @param locality
     * @param decimalDegree1
     * @param decimalDegree2 
     */
    public Address(String street, int doorNumber, String postalCode, String locality, double decimalDegree1, double decimalDegree2) {
        this.id = generateId(doorNumber, postalCode);
        this.street = street;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
        this.locality = locality;
        this.decimalDegree1 = decimalDegree1;
        this.decimalDegree2 = decimalDegree2;
    }

    /**
     * Creates an instance of Adress receiving the id,
     * street, doorNUmber, postalCode, locality,decimalDegree1
     * and decimalDegree2 by parameter
     * 
     * @param id
     * @param street
     * @param doorNumber
     * @param postalCode
     * @param locality
     * @param decimalDegree1
     * @param decimalDegree2 
     */
    public Address(String id, String street, int doorNumber, String postalCode, String locality, double decimalDegree1, double decimalDegree2) {
        this.id = id;
        this.street = street;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
        this.locality = locality;
        this.decimalDegree1 = decimalDegree1;
        this.decimalDegree2 = decimalDegree2;
    }
    
    /**
     * Creates an instance of Adress receiving the street,
     * decimalDegree1 and decimalDegree2 by parameter
     * 
     * @param street
     * @param decimalDegree1
     * @param decimalDegree2 
     */
    public Address(String street, double decimalDegree1, double decimalDegree2) {
        this.id = "0";
        this.street = street;
        this.decimalDegree1 = decimalDegree1;
        this.decimalDegree2 = decimalDegree2;
    }

    /**
     * Returns the id of the Adress
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the street name of 
     * the Adress
     * 
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Returns the door number of 
     * the Adress
     * 
     * @return 
     */
    public int getDoorNumber() {
        return doorNumber;
    }

    /**
     * Returns the postal code of
     * the Adress
     * 
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns the locality of
     * the Adress
     * 
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Returns the latitude of 
     * the Adress
     * 
     * @return the decimalDegree1
     */
    public Double getDecimalDegree1() {
        return decimalDegree1;
    }

    /**
     * Returns the longitude of
     * the Adress
     * 
     * @return the decimalDegree2
     */
    public Double getDecimalDegree2() {
        return decimalDegree2;
    }

    /**
     * Changes the street name of the Adress
     * to the one received by parameter
     * 
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Changes the door number of the Adress
     * to the one received by paramater
     * 
     * @param doorNumber
     */
    public void setdoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    /**
     * Changes the postal code of the Adress
     * to the one received by parameter
     * 
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Changes the localiry of the Adress
     * to the one received by parameter
     * 
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * Changes the latitude of the Adress
     * to the one received by parameter
     * 
     * @param decimalDegree1 the decimalDegree1 to set
     */
    public void setDecimalDegree1(Double decimalDegree1) {
        this.decimalDegree1 = decimalDegree1;
    }

    /**
     * Changes the longitude of the Adress
     * to the one received by parameter
     * 
     * @param decimalDegree2 the decimalDegree2 to set
     */
    public void setDecimalDegree2(Double decimalDegree2) {
        this.decimalDegree2 = decimalDegree2;
    }

    /**
     * Generates a id to the Adress
     *
     * @param doorNumber
     * @param postalCode
     * @return the id
     */
    public String generateId(int doorNumber, String postalCode) {
        return doorNumber + postalCode;
    }

    /**
     * Returns the Adress saved in the database
     * with the same id of this Adress
     * 
     * @param id
     * @return 
     */
    public Address getAdress(String id) {
        return AplicationPOT.getInstance().getPlatform().getAdb().getAdress(id);
    }

    /**
     * Validates if the adress with this 
     * id is already in the database, if it is
     * not, then it saves it
     */
    public void save() {
        try {
            getAdress(this.getId());
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getAdb().addAdress(this);
        }
    }

    /**
     * Updates the data of this adress
     * in the database
     */
    public void update() {
        AplicationPOT.getInstance().getPlatform().getAdb().updateAdress(this);
    }

    /**
     * Returns a textual description of the
     * Adress presenting the street, latitude
     * and longitude
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format(Locale.US,"%s, %.8f, %.8f", street, decimalDegree1, decimalDegree2);
    }

    /**
     * Returns if the object received by parameter
     * is the same as this adress
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
        Address otherclient = (Address) otherObjeto;

        return id.equalsIgnoreCase(otherclient.getId());
    }
}