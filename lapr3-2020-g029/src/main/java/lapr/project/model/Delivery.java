/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class Delivery {
    
    /**
     * The ID of the delivery
     */
    private String id;
    /**
     * The date of the delivery
     */
    private Date dateDelivery;
    /**
     * The total weight of the delivery
     */
    private double totalWeight;
    /**
     * The invoice of the delivery
     */
    private Invoice invoice;
    /**
     * The pharmacy of the delivery
     */
    private Pharmacy pharmacy;
    
    /**
     * Creates an instance of the Delivery receiving
     * the id, dateDelivery, totalWeight, invoice,
     * pharmacy by parameter
     * @param id
     * @param dateDelivery
     * @param totalWeight
     * @param invoice
     * @param pharmacy 
     */
    public Delivery(String id, Date dateDelivery, double totalWeight, Invoice invoice, Pharmacy pharmacy) {
        this.id = id;
        this.dateDelivery = new Date(dateDelivery.getTime());
        this.totalWeight = totalWeight;
        this.invoice = invoice;
        this.pharmacy = pharmacy;
    }

    /**
     * @return the id of
     * the delivery
     */
    public String getId() {
        return id;
    }

    /**
     * @return the date of
     * the Delivery
     */
    public Date getDateDelivery() {
        return new Date(dateDelivery.getTime());
    }

    /**
     * @return the total Weight
     * of the Delivery
     */
    public double getTotalWeight() {
        return totalWeight;
    }

    /**
     * @return the pharmacy of 
     * the Delivery
     */
    public Pharmacy getPharmacy() {
        return pharmacy;
    }
    
    /**
     * @return the invoice
     * of the delivery
     */
    public Invoice getInvoice() {
        return invoice;
    }
    
    /**
     * Changes the id of the 
     * Delivery to the one received by parameter
     * 
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Changes the date of the 
     * Delivery to the one received by parameter
     * 
     * @param dateDelivery
     */
    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = new Date(dateDelivery.getTime());
    }

    /**
     * Changes the total weight of the 
     * Delivery to the one received by parameter
     * 
     * @param totalWeight the totalWeight to set
     */
    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
    
    /**
     * Changes the invoice of the 
     * Delivery to the one received by parameter
     * 
     * @param invoice the invoice to set
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
    /**
     * Changes the pharmacy of the 
     * Delivery to the one received by parameter
     * 
     * @param pharmacy
     */
    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
    
    /**
     * Returns a textual description of the
     * Delivery presenting the id, date
     * and totalWeight
     * 
     * @return 
     */
    @Override
    public String toString() {
        TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
        return String.format(Locale.US, "%s, %s, %f", id, dateDelivery, totalWeight);
    }
    
    /**
     * Returns if the object received by parameter
     * is the same as this Delivery
     * 
     * @param obj
     * @return true or false
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
        final Delivery other = (Delivery) obj;
        return Objects.equals(this.id, other.id);
    }
}
