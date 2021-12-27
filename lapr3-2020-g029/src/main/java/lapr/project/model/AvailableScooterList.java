/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class AvailableScooterList {

    /**
     * The list of scooters available
     */
    private final List<Scooter> availableScootersList;

    /**
     * Creates an instance of AvailableScooterList
     * initializing the availableScootersList
     */
    public AvailableScooterList() {
        availableScootersList = new ArrayList<>();
    }

    /**
     * Returns the list of available 
     * scooters
     * 
     * @return 
     */
    public List<Scooter> getAvailableScooterList() {
        return availableScootersList;
    }
    
    /**
     * Returns the scooter saved in the list
     * that has the same exclusive Number has
     * the one received by parameter
     * 
     * @param exclusiveNumber
     * @return 
     */
    public Scooter getScooter(int exclusiveNumber) {
        for (Scooter s : availableScootersList) {
            if (s.getExclusiveNumber() == (exclusiveNumber)) {
                return s;
            }
        }
        return null;
    }
    
    /**
     * Returns if the Scooter received by
     * parameter has been added to the list
     * of available scooters
     * 
     * @param s
     * @return 
     */
    public boolean addScooter(Scooter s) {
        if (!availableScootersList.contains(s)) {
            availableScootersList.add(s);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if the Scooter received by
     * parameter has been removed of the
     * list of available scooters
     * 
     * @param s
     * @return 
     */
    public boolean removeScooter(Scooter s) {
        return availableScootersList.remove(s);
    }

}
