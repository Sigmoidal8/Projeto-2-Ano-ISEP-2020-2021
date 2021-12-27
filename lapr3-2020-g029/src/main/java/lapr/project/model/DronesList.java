/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;

public class DronesList {

    /**
     * The list of Drones
     */
    private final List<Drone> listDrones;

    /**
     * Creates an instance of DronesList initializing the listDrones
     */
    public DronesList() {
        listDrones = new ArrayList<>();
    }

    /**
     * Returns the list of Drones
     *
     * @return
     */
    public List<Drone> getDroneList() {
        return listDrones;
    }

    /**
     * Returns the list of Drones that are not in a Pharmacy
     *
     * @return
     */
    public List<Drone> getPharmacyLessDrones() {
        List<Drone> listDronesWithoutPharmacy = new ArrayList<>();
        PharmacyList pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();
        for (Drone d : listDrones) {
            boolean noPharmacy = true;
            for (Pharmacy p : pl.getPharmacyList()) {
                if (p.getAvailableDroneList().getDroneList().contains(d)) {
                    noPharmacy = false;
                }
            }
            if (noPharmacy) {
                listDronesWithoutPharmacy.add(d);
            }
        }
        return listDronesWithoutPharmacy;
    }

    /**
     * Returns the Drone in the list that has the same id has the one received
     * by parameter
     *
     * @param id
     * @return
     */
    public Drone getDrone(int id) {
        for (Drone d : listDrones) {
            if (d.getID() == (id)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Returns if the Drone received by parameter has been added to the list of
     * drones
     *
     * @param d
     * @return
     */
    public boolean addDrone(Drone d) {
        if (!listDrones.contains(d)) {
            return listDrones.add(d);
        } else {
            return false;
        }
    }

    /**
     * Generates the id of the Drone based on the number of drones in the list
     *
     * @return
     */
    public int generateID() {
        int id;
        if (listDrones.isEmpty()) {
            id = 1;
        } else {
            id = listDrones.get(listDrones.size() - 1).getID() + 1;
        }
        return id;
    }

    /**
     * Returns an instance of Drone with the data received by parameter
     *
     * @param id
     * @param fullCharge
     * @param power
     * @return
     */
    public Drone newDrone(int id, double fullCharge, double power) {
        return new Drone(id, fullCharge, power);
    }

    /**
     * Returns if the Drone received by parameter was added to the database
     *
     * @param d
     * @return
     */
    public boolean registerDrone(Drone d) {
        AplicationPOT.getInstance().getPlatform().getDrdb().addDrone(d);
        if (!listDrones.contains(d)) {
            addDrone(d);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifies if the Drone received by parameter is already in the list or if
     * it has invalid data
     *
     * @param d
     * @return
     */
    public boolean validateDrone(Drone d) {
        return !listDrones.contains(d) && (d.getPower() > 0) && (d.getFullCharge() > 0);
    }
}
