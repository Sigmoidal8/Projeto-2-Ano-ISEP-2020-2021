/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;


public class RemoveDroneController {
    
    /**
     * Platform of the application
     */
    private final Platform plat;
    
    /**
     * PharmacyList of the Platform
     */
    private final PharmacyList phl;
    
    /**
     * Creates an instance of RemoveDroneController
     * initializing the platform and its
     * PharmacyList
     */
    public RemoveDroneController() {
        plat = AplicationPOT.getInstance().getPlatform();
        phl=plat.getPharmacyList();
    }
    
     /**
     * Returns the PharmacyList of this AddDroneController
     * @return 
     */
     public PharmacyList getPharmacyList() {
        return phl;
    }
    
     /**
     * Returns the DronesList of the Pharmacy with the same
     * id passed by parameter
     * @param pharmacyID
     * @return 
     */
    public DronesList getDronesPharmacy(String pharmacyID) {
        
         Pharmacy p=phl.getPharmacy(pharmacyID);
         return p.getAvailableDroneList();
         
    }
    
    /**
     * Removes the drone with the same id passed by parameter
     * of the list of drones passed by parameter
     * and updates it in the database.
     * @param id
     * @param drlPharmacy
     */
    public void removeDronePharmacy(int id,DronesList drlPharmacy){
        Drone d= drlPharmacy.getDrone(id);
        drlPharmacy.getDroneList().remove(d);
        d.update();
    }
}
