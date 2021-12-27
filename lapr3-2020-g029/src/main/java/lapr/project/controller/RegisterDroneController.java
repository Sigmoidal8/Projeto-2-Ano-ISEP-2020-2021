/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.Platform;


public class RegisterDroneController {

    /**
     * Platform of the application
     */
    private final Platform plat;

    /**
     * DronesList of the Platform
     */
    private final DronesList drl;

    /**
     * Creates an instance of RegisterDroneController initializing the platform and its DronesList.
     */
    public RegisterDroneController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        drl = plat.getDronesList();
    }

    /**
     * Generates a id to the Drone
     *
     * @return the id
     */
    public int generateID() {
        return drl.generateID();
    }

    /**
     * Creates a new instance of Drone with id,fullCharge and power received by parameter
     *
     * @param id
     * @param fullCharge
     * @param power
     * @return
     */
    public Drone newDrone(int id, double fullCharge, double power) {
        return drl.newDrone(id, fullCharge, power);
    }

    /**
     * Validates the drone passed by parameter and if it is valid, registates it in the system and in the dataBase.
     *
     * @param d
     * @return true or false, if the drone is valid or not
     */
    public boolean registerDrone(Drone d) {
        if (drl.validateDrone(d)) {
            drl.registerDrone(d);
            return true;
        } else {
            return false;
        }
    }

}
