/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;



import java.util.Locale;
import lapr.project.controller.AplicationPOT;


public class Drone {

    /**
     * The id of the Drone
     */
    private final int id;
    
    /**
     * The full charge of the Drone
     */
    private double fullCharge;
    
    /**
     * The currentCharge of the Drone
     */
    private double currentCharge;
    
    /**
     * The power of the Drone
     */
    private double power;
    
    /**
     * The capacity of the Drone
     */
    private static double capacity=15;
    
    /**
     * The average speed of the Drone
     */
    private static double averageSpeed=50;

    /**
     * Creates an instance of Drone receiving
     * the id, fullCharge and power by parameter
     * and sets the currentCharge to the fullCharge
     * 
     * @param id
     * @param fullCharge
     * @param power 
     */
    public Drone(int id, double fullCharge, double power) {
        this.id = id;
        this.fullCharge = fullCharge;
        this.currentCharge = fullCharge;
        this.power = power;

    }
    
    /**
     * Creates an instance of the Drone receiving
     * the id, fullCharge, currentCharge and power
     * by parameter
     * 
     * @param id
     * @param fullCharge
     * @param currentCharge
     * @param power 
     */
    public Drone(int id, double fullCharge,double currentCharge, double power) {
        this.id = id;
        this.fullCharge = fullCharge;
        this.currentCharge = currentCharge;
        this.power = power;
    }
    
    /**
     * Returns the ID of the
     * Drone
     * 
     * @return 
     */
    public int getID() {
        return id;
    }

    /**
     * Returns the fullCharge
     * of the Drone
     * 
     * @return 
     */
    public double getFullCharge() {
        return fullCharge;
    }

    /**
     * Returns the currentCharge
     * of the Drone
     * 
     * @return 
     */
    public double getCurrentCharge() {
        return currentCharge;
    }

    /**
     * Returns the power of
     * the Drone
     * 
     * @return 
     */
    public double getPower() {
        return power;
    }
    
    /**
     * Returns the averageSpeed
     * of the Drone
     * 
     * @return 
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }
    
    /**
     * Returns the capacity of
     * the Drone
     * 
     * @return 
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Changes the fullCharge of the Drone
     * to the one received by parameter
     * 
     * @param fullCharge 
     */
    public void setFullCharge(double fullCharge) {
        this.fullCharge = fullCharge;
    }

    /**
     * Changes the currentCharge of the Drone
     * to the one received by parameter
     * 
     * @param currentCharge 
     */
    public void setCurrentCharge(double currentCharge) {
        this.currentCharge = currentCharge;
    }

    /**
     * Changes the power of the Drone
     * to the one received by parameter
     * 
     * @param power 
     */
    public void setPower(double power) {
        this.power = power;
    }
    
    /**
     * Returns the Drone saved in the database
     * with the same id of this Drone
     * 
     * @param id
     * @return 
     */
    public  Drone getDrone(int id) {
        return AplicationPOT.getInstance().getPlatform().getDrdb().getDrone(id);
    }

    /**
     * Validates if the Client with this 
     * id is already in the database, if it is
     * not it saves it
     */
    public void save() {
        try {
            getDrone(this.getID());
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getDrdb().addDrone(this);
        }
    }

    /**
     * Updates the data of this Drone
     * in the database
     */
    public void update() {
        AplicationPOT.getInstance().getPlatform().getDrdb().updateDrone(this);
    }
    

    /**
     * Returns a textual description of the
     * Drone presenting the id, fullCharge
     * and CurrentCharge and power
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format(Locale.US,"Drone %d, Full Charge:%.2f, Current Charge:%.2f, Power:%.2f", id,fullCharge,currentCharge,power);
    }

    /**
     * Returns if the object received by parameter
     * is the same as this Drone
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
        final Drone other = (Drone) obj;
        return this.id == other.id;
    }

  
    }
    
