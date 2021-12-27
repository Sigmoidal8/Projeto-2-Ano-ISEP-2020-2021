/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.File;
import lapr.project.controller.AplicationPOT;

public class Scooter {

    /**
     * ExclusiveNumber of the Scooter
     */
    private int exclusiveNumber;
    /**
     * FullCharge of the Scooter
     */
    private double fullCharge;
    /**
     * CurrentCharge of the Scooter
     */
    private double currentCharge;
    /**
     * Power of the Scooter
     */
    private double power;
    /**
     * Operability of the Scooter
     */
    private boolean operational;
    
    private static final double AVERAGE_SPEED = 30;

    File qrCode;
    
    Pharmacy p;

    /**
     * Creates an instance of Scooter receiving the exclusiveNumber, fullCharge, power, email and operational by parameter and initializing its currentCharge equal to the fullCharge .
     *
     * @param exclusiveNumber
     * @param fullCharge
     * @param power
     * @param operational
     */
    public Scooter(int exclusiveNumber, double fullCharge, double power, int operational) {
        this.exclusiveNumber = exclusiveNumber;
        this.fullCharge = fullCharge;
        this.currentCharge = fullCharge;
        this.power = power;
        this.operational = operational != 0;
    }

    /**
     **Creates an instance of Scooter receiving the exclusiveNumber, fullCharge, power, email, operational and currentCharge. by parameter.
     *
     * @param exclusiveNumber
     * @param fullCharge
     * @param currentCharge
     * @param power
     * @param operational
     */
    public Scooter(int exclusiveNumber, double fullCharge, double currentCharge, double power, int operational) {
        this.exclusiveNumber = exclusiveNumber;
        this.fullCharge = fullCharge;
        this.currentCharge = currentCharge;
        this.power = power;
        this.operational = operational != 0;
    }
    
     /**
     **Creates an instance of Scooter receiving the exclusiveNumber, fullCharge, power, email, operational,currentCharge and Pharmacy. by parameter.
     *
     * @param exclusiveNumber
     * @param fullCharge
     * @param currentCharge
     * @param power
     * @param operational
     * @param p
     */
    public Scooter(int exclusiveNumber, double fullCharge, double currentCharge, double power, int operational,Pharmacy p) {
        this.exclusiveNumber = exclusiveNumber;
        this.fullCharge = fullCharge;
        this.currentCharge = currentCharge;
        this.power = power;
        this.operational = operational != 0;
        this.p = p;
    }
    
    /**
     * Returns the exclusiveNumber of the Scooter
     *
     * @return the exclusiveNumber
     */
    public int getExclusiveNumber() {
        return exclusiveNumber;
    }

    /**
     * Returns the fullCharge of the Scooter
     *
     * @return the fullCharge
     */
    public double getFullCharge() {
        return fullCharge;
    }

    /**
     * Returns the currentCharge of the Scooter
     *
     * @return the currentCharge
     */
    public double getCurrentCharge() {
        return currentCharge;
    }

    /**
     * Returns the power of the Scooter
     *
     * @return the power
     */
    public double getPower() {
        return power;
    }

    /**
     * Returns the AverageSpeed of the Scooter
     *
     * @return the average speed
     */
    public double getAverageSpeed() {
        return AVERAGE_SPEED;
    }

    /**
     * Returns the operational of the Scooter
     *
     * @return the operational
     */
    public boolean isOperational() {
        return operational;
    }

    /**
     * Returns an int representing if the Scooter is operational or not
     *
     * @return
     */
    public int getOperational() {
        if (operational) {
            return 1;
        }
        return 0;
    }

    /**
     * Changes the exclusiveNumber of the Scooter to the one received by parameter
     *
     * @param exclusiveNumber the exclusiveNumber to set
     */
    public void setExclusiveNumber(int exclusiveNumber) {
        this.exclusiveNumber = exclusiveNumber;
    }

    /**
     * Changes the fullCharge of the Scooter to the one received by parameter
     *
     * @param fullCharge the fullCharge to set
     */
    public void setFullCharge(double fullCharge) {
        this.fullCharge = fullCharge;
    }

    /**
     * Changes the currentCharge of the Scooter to the one received by parameter
     *
     * @param currentCharge the currentCharge to set
     */
    public void setCurrentCharge(double currentCharge) {
        this.currentCharge = currentCharge;
    }

    /**
     * Changes the power of the Scooter to the one received by parameter
     *
     * @param power the power to set
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     *
     * @param operational
     */
    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    /**
     * Changes the qrCode of the Scooter to the one received by parameter
     *
     * @param qrCode the qrCode to set
     */
    public void setQrCode(File qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * Returns the Scooter saved in the database with the same exclusiveNumber of this Scooter
     *
     * @param exclusiveNumber
     * @return
     */
    public Scooter getScooter(int exclusiveNumber) {
        return AplicationPOT.getInstance().getPlatform().getSdb().getScooter(exclusiveNumber);
    }

    /**
     * Validates if the Scooter with this exclusiveNumber is already in the database, if it isn't, it saves it
     */
    public void save() {
        try {
            getScooter(this.getExclusiveNumber());
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getSdb().addScooter(this);
        }
    }

    /**
     * Updates the data of this Scooter in the database
     */
    public void update() {
        AplicationPOT.getInstance().getPlatform().getSdb().updateScooter(this);
    }

    /**
     * Returns a textual description of the Scooter presenting the exclusiveNumber
     *
     * @return
     */
    @Override
    public String toString() {
        return "Scooter " + exclusiveNumber;
    }
    
    /**
     * Returns if the object received by parameter
     * is the same as this Scooter
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
        final Scooter other = (Scooter) obj;
        return this.exclusiveNumber == other.exclusiveNumber;
    }

    /**
     * Returns the qrCode of the Scooter
     *
     * @return the qrCode
     */
    public File getQrCode() {
        return qrCode;
    }

}
