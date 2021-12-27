/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;

public class ScootersList {
    /**
     * The list of scooters
     */
    private final List<Scooter> listScooters;
    /**
     * Creates an instance of ScooterList
     * initializing the listScooters
     * 
     */
    public ScootersList() {
        listScooters = new ArrayList<>();
    }
    /**
     * Returns if the the scooter received
     * as a parameter was added the list of
     * the scooters
     * 
     * @param s
     * @return 
     */
    public boolean addScooter(Scooter s) {
        if (!listScooters.contains(s)) {
            listScooters.add(s);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Return the list of scooters
     * 
     * @return the listScooters
     */
    public List<Scooter> getScooterList() {
        return listScooters;
    }
    /**
     * Return a ArrayList with the scooters
     * that aren't registered in a pharmacy
     * 
     * @return the list of scooters
     */
    public List<Scooter> getPharmacyLessScooters() {
        List<Scooter> listScootersWithoutPharmacy = new ArrayList<>();
        PharmacyList pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();
        for (Scooter s : listScooters) {
            boolean noPharmacy = true;
            for (Pharmacy p : pl.getPharmacyList()) {
                if (p.getAsl().getAvailableScooterList().contains(s)) {
                    noPharmacy = false;
                }
            }
            if(noPharmacy){
                listScootersWithoutPharmacy.add(s);
            }
        }
        return listScootersWithoutPharmacy;
    }
    /**
     * Returns the scooter with the
     * exclusive number received by 
     * parameter in the list
     * 
     * @param exclusiveNumber
     * @return 
     */
    public Scooter getScooter(int exclusiveNumber) {
        for (Scooter s : listScooters) {
            if (s.getExclusiveNumber() == (exclusiveNumber)) {
                return s;
            }
        }
        return null;
    }
    /**
     * Create a unique exclusive
     * number for each scooter
     * 
     * @return the exclusive number
     */
    public int generateExclusiveNumber() {
        int exclusiveNumber;
        if (listScooters.isEmpty()) {
            exclusiveNumber = 1;
        } else {
            exclusiveNumber = listScooters.get(listScooters.size() - 1).getExclusiveNumber() + 1;
        }
        return exclusiveNumber;
    }
    /**
     * Creates a new instance of scooter
     * with the exclusive number, full charge
     * power and operacional received by parameter
     * 
     * @param excNumb
     * @param fullCharge
     * @param power
     * @param operational
     * @return 
     */
    public Scooter newScooter(int excNumb, double fullCharge, double power, int operational) {
        return new Scooter(excNumb, fullCharge, power, operational);
    }
    /**
     * Register the scooter
     * received as a parameter
     * 
     * @param s
     * @return true or false
     */
    public boolean registerScooter(Scooter s) {
        AplicationPOT.getInstance().getPlatform().getSdb().addScooter(s);
        addScooter(s);
        return true;
    }
    /**
     * Verify if the scooter received
     * as a parameter is valid
     * 
     * @param s
     * @return true or false
     */
    public boolean validateScooter(Scooter s) {
        return !listScooters.contains(s) && (s.getPower() > 0) && (s.getFullCharge() > 0);
    }

}
