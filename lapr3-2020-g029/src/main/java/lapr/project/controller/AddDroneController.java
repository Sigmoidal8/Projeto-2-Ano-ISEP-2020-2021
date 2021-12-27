/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Drone;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.DronesList;
import lapr.project.model.Pharmacy;


public class AddDroneController {
    
    /**
     * Platform of the application
     */
    private final Platform plat;
    
    /**
     * DronesList of the Platform
     */
    private final DronesList drl;
    
    /**
     * PharmacyList of the platform
     */
    private final PharmacyList phl;
    
    /**
     * Creates an instance of AddDroneController
     * initializing the platform and its
     * DronesList and PharmacyList
     */
    public AddDroneController() {
        plat = AplicationPOT.getInstance().getPlatform();
        drl = plat.getDronesList();     
        phl = plat.getPharmacyList();
    }
    
    /**
     * Returns a list of Drones without Pharmacy
     * @return 
     */
    public List<Drone> getDronesWithouthPharmacy(){
        return drl.getPharmacyLessDrones();
    }
    
    /**
     * Returns a Drone with the same id
     * passed  by parameter
     * @param id
     * @return 
     */
    public Drone getDrone(int id){
        return drl.getDrone(id);
    }
    
    /**
     * Returns the PharmacyList of this AddDroneController
     * @return 
     */
    public PharmacyList getPharmacyList(){ 
        return phl;
    }
    
    /**
     * Adds the drone to the list of drones of the 
     * pharmacy with the same id passed by parameter
     * and updates it in the database.
     * @param pharmacyID
     * @param d
     */
    public void setDronePharmacy(String pharmacyID, Drone d) {
        Pharmacy p =phl.getPharmacy(pharmacyID);
        p.getAvailableDroneList().addDrone(d);
        d.update();
    }
    
    /**
     * Returns the DronesList of this AddDroneController
     * @return 
     */
    public DronesList getDronesList() {
        return drl;
    } 
    
}
