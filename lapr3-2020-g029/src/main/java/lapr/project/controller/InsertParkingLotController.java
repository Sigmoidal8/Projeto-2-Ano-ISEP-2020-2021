/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;


public class InsertParkingLotController {
    
    private Pharmacy pharmacy;

    private ParkingLot parkingLot;

    private final Platform plat;

    private final PharmacyList pharmacyList;
    

    public InsertParkingLotController() {

        plat = AplicationPOT.getInstance().getPlatform();

        pharmacyList = plat.getPharmacyList();
    }

    public void getPharmacyFromID(String pharmacyID) {
        pharmacy = pharmacyList.getPharmacy(pharmacyID);
    }

    public void createParkingLot(int parkingLotSpots, int parkingLotChargeSpots, String type) {
        parkingLot = new ParkingLot(parkingLotSpots,parkingLotChargeSpots,type);
    }

   public boolean registerParkingLot() {
        return pharmacy.registerParkingLot(parkingLot);
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public ParkingLot getParkingLot() {
       return parkingLot;
    }

    public PharmacyList getPharmacyList() {
        return pharmacyList;
    }
}
