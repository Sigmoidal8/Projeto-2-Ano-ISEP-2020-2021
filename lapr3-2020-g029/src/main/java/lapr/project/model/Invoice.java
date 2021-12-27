/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public class Invoice {

    /**
     * The id of the Invoice
     */
    private String id;

    /**
     * The date of the Invoice
     */
    private Date dateInvoice;

    /**
     * The price in the Invoice
     */
    private double price;

    /**
     * The Client in the Invoice
     */
    private Client client;

    /**
     * The Map of products and their
     * respective quantities
     */
    private Map<Product, Integer> mp;

    /**
     * Creates an instance of Invoice with
     * the id, dateInvoice, price and Client
     * received by parameter
     * 
     * @param id
     * @param dateInvoice
     * @param price
     * @param client 
     */
    public Invoice(String id, Date dateInvoice, double price, Client client) {

        this.id = id;
        this.dateInvoice = new Date(dateInvoice.getTime());
        this.price = price;
        this.client = client;
    }

    /**
     * Returns the id of
     * the Invoice
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns the map of products
     * and their quantities of the
     * Invoice
     * 
     * @return the mp
     */
    public Map<Product, Integer> getMp() {
        return mp;
    }
    
    /**
     * Returns the price in
     * the Invoice
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the Client in 
     * the Invoice
     * 
     * @return the client
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * Returns the Date in
     * the Invoice
     * 
     * @return the date_invoice
     */
    public Date getDateinvoice() {
        return new Date(dateInvoice.getTime());
    }

    /**
     * Changes the id of the Invoice
     * to the one received by parameter
     * 
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Changes the Date of the Invoice
     * to the one received by parameter
     * 
     * @param dateInvoice the dateInvoice to set
     */
    public void setDateinvoice(Date dateInvoice) {
        this.dateInvoice = new Date(dateInvoice.getTime());
    }
    
    /**
     * Changes the map of Products of the Invoice
     * to the one received by parameter
     * 
     * @param mp the mp to set
     */
    public void setMp(Map<Product, Integer> mp) {
        this.mp = mp;
    }

    /**
     * Changes the price of the Invoice
     * to the one received by parameter
     * 
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Changes the Client of the Invoice
     * to the one received by parameter
     * 
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Returns a textual description of the
     * Invoice presenting the id, dateInvoice,
     * price and Client
     * 
     * @return 
     */
    @Override
    public String toString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return String.format(Locale.US, "%s, %s, %f, %s", id, dateInvoice, price, client);
    }

    /**
     * Returns if the object received by parameter
     * is the same as this Invoice
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invoice other = (Invoice) obj;
        return Objects.equals(this.id, other.id);
    }

}
