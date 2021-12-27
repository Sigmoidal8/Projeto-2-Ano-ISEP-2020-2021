/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;


public class RemovePharmacyController {

    /**
     * Platform of the application
     */
    private final Platform plat;
    /**
     * PharmacyList of the Platform
     */
    private final PharmacyList pl;
    
    /**
     * Creates an instance of RegisterPharmacyController initializing the platform and its DronesList.
     */
    public RemovePharmacyController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        pl = plat.getPharmacyList();
    }
    
    /**
     * Returns the PharmacyList from the platform
     * @return
     */
    public List<Pharmacy> getPharmacyList() {
        return pl.getPharmacyList();
    }
    
    /**
     * Removes the pharmacy with the same id as the one passed by parameter
     * from the PharmacyList and from the data base.
     * @return
     */
    public boolean removePharmacy(String pharmacyID) {
        Pharmacy p = pl.getPharmacy(pharmacyID);
        return pl.removePharmacy(p);
    }
    
    /**
     * Removes the Adress of the Pharmacy from the graph
     * of the Platform
     * 
     * @param pharmacyID
     * @return 
     */
    public boolean removePharmacyFromGraph(String pharmacyID){
        Pharmacy p = pl.getPharmacy(pharmacyID);
        return pl.removePharmacyAdressToGraph(p);
    }
}
