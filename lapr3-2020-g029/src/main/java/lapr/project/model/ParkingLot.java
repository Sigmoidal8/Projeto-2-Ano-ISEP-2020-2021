/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.controller.AplicationPOT;

public class ParkingLot {

    /**
     * The id of the ParkingLot
     */
    private String id;

    /**
     * The total spots to park in the ParkingLot
     */
    private int totalSpots;

    /**
     * The charge spots to park in the ParkingLot
     */
    private int chargeSpots;

    /**
     * The type of park of the ParkingLot
     */
    private final String type;

    /**
     * Creates an instance of ParkingLot with the totalSpots, chargeSpots and
     * type received by parameter
     *
     * @param totalSpots
     * @param chargeSpots
     * @param type
     */
    public ParkingLot(int totalSpots, int chargeSpots, String type) {
        this.totalSpots = totalSpots;
        this.chargeSpots = chargeSpots;
        this.type = type;
    }

    /**
     * Creates an instance of ParkingLoat with the id, totalSpots, chargeSpots
     * and type received by parameter
     *
     * @param id
     * @param totalSpots
     * @param chargeSpots
     * @param type
     */
    public ParkingLot(String id, int totalSpots, int chargeSpots, String type) {
        this.id = id;
        this.totalSpots = totalSpots;
        this.chargeSpots = chargeSpots;
        this.type = type;
    }

    /**
     * Returns the id of the ParkingLot
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the totalSpots of the ParkingLOt
     *
     * @return
     */
    public int getTotalSpots() {
        return totalSpots;
    }

    /**
     * Returns the chargeSpots of the ParkingLot
     *
     * @return
     */
    public int getChargeSpots() {
        return chargeSpots;
    }

    /**
     * Returns the type of the ParkingLot
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Changes the totalSpots of the ParkingLot to the one received by parameter
     *
     * @param totalSpots
     */
    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    /**
     * Changes the totalSpots of the ParkingLot to the one received by parameter
     *
     * @param chargeSpots
     */
    public void setChargeSpots(int chargeSpots) {
        this.chargeSpots = chargeSpots;
    }

    /**
     * Returns the ParkingLot saved in the database with the same id of this
     * ParkingLot
     *
     * @param id
     * @return
     */
    public ParkingLot getParkingLot(String id) {
        return AplicationPOT.getInstance().getPlatform().getPldb().getParkingLot(id);
    }

    /**
     * Generates the id of the ParkingLot
     *
     * @param numberOfParkingLots
     * @param pharmacyID
     */
    public void generateID(int numberOfParkingLots, String pharmacyID) {
        id = pharmacyID + numberOfParkingLots + type;
    }

    /**
     * Returns a textual description of the Invoice presenting the id
     *
     * @return
     */
    @Override
    public String toString() {
        return "ParkingLot " + id;
    }

    /**
     * Returns if the object received by parameter is the same as this
     * ParkingLot
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
        final ParkingLot other = (ParkingLot) obj;
        return Objects.equals(this.id, other.id);
    }
}
