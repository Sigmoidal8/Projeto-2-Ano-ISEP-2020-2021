/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.AvailableScooterList;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;


public class RemoveScooterController {

    /**
     * Platform of the aplication
     */
    private final Platform plat;

    /**
     * AvailableScooterList
     */
    private AvailableScooterList asctl;

    /**
     * PharmacyList of the Platform
     */
    private PharmacyList phl;

    /**
     * Creates an instance of RemoveScooterController initializing the platform and its PharmacyList
     */
    public RemoveScooterController() {
        plat = AplicationPOT.getInstance().getPlatform();
        phl = plat.getPharmacyList();
    }

    /**
     * Returns the PharmacyList of this AddDroneController
     *
     * @return
     */
    public PharmacyList getPharmacyList() {
        phl = plat.getPharmacyList();
        return phl;
    }

    /**
     * Returns a Pharmacy with the same id passed by parameter
     *
     * @param pharmacyID
     * @return
     */
    public Pharmacy getPharmacy(String pharmacyID) {
        return phl.getPharmacy(pharmacyID);
    }

    /**
     * Returns the AvailableScooterList of the Pharmacy passed by parameter
     *
     * @param phar
     * @return
     */
    public AvailableScooterList getAvailableScooterList(Pharmacy phar) {
        return phar.getAsl();
    }

    /**
     * Returns a Scooter with the same id passed by parameter
     *
     * @param scooterID
     * @return
     */
    public Scooter getScooter(int scooterID, Pharmacy p) {
        asctl = p.getAsl();
        return asctl.getScooter(scooterID);
    }

    /**
     * Removes the Scooter of the list of scooters of the pharmacy passed by parameter and updates it in the database.
     *
     * @param ph
     * @param sc
     */
    public boolean removeScooterFromAvailableScooterList(Scooter sc, Pharmacy ph) {
        asctl = getAvailableScooterList(ph);
        boolean remove = asctl.removeScooter(sc);
        sc.update();
        return remove;
    }

    /**
     * Returns the Platform of this AddDroneController
     *
     * @return
     */
    public Platform getPlat() {
        return plat;
    }

}
