/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;


public class EditParkingLotCharacteristicsController {

    /**
     * Platform of the aplication
     */
    private final Platform plat;

    /**
     * Pharmacy selling the product
     */
    private Pharmacy p;

    /**
     * PharmacyList of the Platform
     */
    private PharmacyList pl;

    /**
     * ParkingLot of a Pharmacy
     */
    private ParkingLot prkl;

    /**
     * List of ParkingLots
     */
    private List<ParkingLot> prklL;

    /**
     * Creates an instance of EditParkingLotCharacteristicsController
     * initializing the platform and its PharmacyList
     */
    public EditParkingLotCharacteristicsController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();
    }

    /**
     * Returns the PharmacyList registered in the Platform
     *
     * @return PharmacyList
     */
    public PharmacyList getPharmacyList() {
        pl = plat.getPharmacyList();
        return pl;
    }

    /**
     * Returns a Pharmacy with the same id passed by parameter
     * and sets the prklL to the Parking Lot List of that pharmacy
     *
     * @param pharmacyID the pharmacy's id
     * @return Pharmacy
     */
    public Pharmacy getPharmacy(String pharmacyID) {
        p = pl.getPharmacy(pharmacyID);
        prklL = p.getParkingLotList();
        return p;
    }

    /**
     * Returns the List of ParkingLots of a pharmacy
     *
     * @return List of ParkingLots 
     */
    public List<ParkingLot> getParkingLotList() {
        return p.getParkingLotList();
    }

      /**
     * Returns the ParkingLot of a pharmacy passed by parameter
     *
     * @return ParkingLot
     */
    public ParkingLot getParkingLot(String parkingLotID) {
        for (ParkingLot prk : prklL) {
            if (prk.getId().equals(parkingLotID)) {
                prkl = prk;
                return prk;
            }
        }
        prkl = null;
        return null;
    }

    /**
     * Sets the total number of parking spots of a Parking Lot
     *
     * @return ParkingLot
     */
    public ParkingLot setTotalSpots(int totalSpots) {
        prkl.setTotalSpots(totalSpots);
        plat.getPldb().updateParkingLot(prkl, p.getId());
        return prkl;
    }

    /**
     * Sets the total number of charging spots of a Parking Lot
     *
     * @return ParkingLot
     */
    public ParkingLot setChargeSpots(int chargeSpots) {
        prkl.setChargeSpots(chargeSpots);
        plat.getPldb().updateParkingLot(prkl, p.getId());
        return prkl;

    }

    public ParkingLot getPrkl() {
        return prkl;
    }
    
}
