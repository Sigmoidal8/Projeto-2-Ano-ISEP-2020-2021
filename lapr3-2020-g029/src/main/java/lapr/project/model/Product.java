/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Locale;
import lapr.project.controller.AplicationPOT;

public class Product {

    /**
     * Id of the Product
     */
    private String id;
    /**
     * Name of the Product
     */
    private String name;
    /**
     * Price of the Product
     */
    private float price;
    /**
     * Weight of the Product
     */
    private float weight;

    /**
     * Creates an instance of Product receiving the id, name, price and weight by parameter.
     *
     * @param id
     * @param nome
     * @param price
     * @param weight
     */
    public Product(String id, String nome, float price, float weight) {
        this.id = id;
        this.name = nome;
        this.price = price;
        this.weight = weight;
    }

    /**
     * Returns the id of the
     * Product
     * 
     * @return the id
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the name of the
     * Product
     * 
     * @return the name
     */
    public String getNome() {
        return name;
    }

    /**
     * Returns the price of the
     * Product
     * 
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Returns the weight of the
     * Product
     * 
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Changes the ID of the
     * Product
     * 
     * @param id the id to set
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Changes the name of the
     * Product
     * 
     * @param name the id to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the price of the
     * Product
     * 
     * @param price the id to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Changes the weight of the
     * Product
     * 
     * @param weight the id to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    /**
     * Returns the Product saved in the database with the same id of this Product
     *
     * @param id
     * @return
     */
    public Product getProduct(String id) {
        return AplicationPOT.getInstance().getPlatform().getPrdb().getProduct(id);
    }
    
    /**
     * Validates if the Scooter with this exclusiveNumber is already in the database, if it isn't, it saves it
     */
    public void save() {
        try {
            getProduct(this.getID());
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getPrdb().addProduct(this);
        }
    }
    
    /**
     * Updates the data of this Scooter in the database
     */
    public void update() {
        AplicationPOT.getInstance().getPlatform().getPrdb().updateProdcut(this);
    }
    
    /**
     * Returns a textual description of the Product presenting the id,price,name and weight.
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "%s, %s, %.2f, %.2f", id, name, price, weight);
    }

    /**
     * Returns if the object received by parameter
     * is the same as this Product
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
        Product otherProduct = (Product) otherObjeto;

        return id.equalsIgnoreCase(otherProduct.id) && name.equalsIgnoreCase(otherProduct.name)
                && price == otherProduct.price && weight == otherProduct.weight;

    }
}
