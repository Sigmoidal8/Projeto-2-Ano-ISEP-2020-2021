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
import lapr.project.model.ScootersList;

public class AddScooterController {

    /**
     * Platform of the application
     */
    private final Platform plat;

    /**
     * ScootersList of the Platform
     */
    private final ScootersList sctl;

    /**
     * PharmacyList of the Platform
     */
    private final PharmacyList phl;

    /**
     * Creates an instance of AddScooterController initializing the platform and
     * its ScootersList and PharmacyList
     */
    public AddScooterController() {
        plat = AplicationPOT.getInstance().getPlatform();
        sctl = plat.getScootersList();
        phl = plat.getPharmacyList();
    }

    /**
     * Returns the ScooterList of this AddDroneController
     *
     * @return
     */
    public ScootersList getScooterList() {
        return sctl;
    }

    /**
     * Returns a Scooter with the same id passed by parameter
     *
     * @param scooterID
     * @return
     */
    public Scooter getScooter(int scooterID) {
        return sctl.getScooter(scooterID);
    }

    /**
     * Returns the PharmacyList of this AddDroneController
     *
     * @return
     */
    public PharmacyList getPharmacyList() {
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
     * Adds the Scooter to the list of scooters of the pharmacy passed by
     * parameter and updates it in the database.
     *
     * @param ph
     * @param sc
     */
    public boolean addAvailableScooterList(Scooter sc, Pharmacy ph) {

        AvailableScooterList asctl = getAvailableScooterList(ph);
        if (!asctl.getAvailableScooterList().contains(sc)) {
            asctl.addScooter(sc);
            sc.update();
            return true;
        } else {
            return false;
        }

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
