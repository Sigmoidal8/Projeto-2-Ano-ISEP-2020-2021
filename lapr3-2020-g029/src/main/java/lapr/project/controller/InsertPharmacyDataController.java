/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;


public class InsertPharmacyDataController {

    /**
     * The pharmacy to be inserted
     */
    private Pharmacy pharmacy;
    
    /**
     * The pharmacy adress
     */
    private Address adress;
    
    /**
     * The pharmacy parking lot
     */
    private ParkingLot parkingLot;
    
    /**
     * PharmacyList of the platform
     */
    private final PharmacyList pl;

    /**
     * Creates an instance of InsertPharmacyDataController initializing the platform and its PharmacyList.
     */
    public InsertPharmacyDataController() {
        Platform plat = AplicationPOT.getInstance().getPlatform();
        pl = plat.getPharmacyList();
    }
    
    /**
     * Creates an instance of Adress.
     * @param doorNumber 
     * @param street 
     * @param locality 
     * @param postalCode 
     * @param decimalDegree1 
     * @param decimalDegree2 
     */
    public void createAdress(int doorNumber, String street, String locality,
                            String postalCode, Double decimalDegree1, Double decimalDegree2) {
        adress = new Address(street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
    }
    
    /**
     * Creates the pharmacy to be inserted in the system
     * @param designation
     * @param email
     * @param password 
     */
    public void createPharmacy(String designation,String email,String password) {
        pharmacy = pl.createPharmacy(designation,email,password,adress);
    }
    
    /**
     * Creates an instance of ParkingLot
     * @param parkingLotSpots
     * @param parkingLotChargeSpots
     * @param type 
     */
    public void createParkingLot(int parkingLotSpots, int parkingLotChargeSpots, String type) {
        parkingLot = new ParkingLot(parkingLotSpots,parkingLotChargeSpots,type);
    }
    
    /**
     * Registers the created pharmacy in the PharmacyList of the platform.
     * @return true if sucessfully added, false otherwise
     */
    public boolean registerPharmacy() {
        return pl.registerPharmacy(pharmacy);
    }
    
    public boolean addPharmacyToGraph(){
        return pl.addPharmacyAdressToGraph(pharmacy);
    }
    
    /**
     * Returns the pharmacy to be inserted.
     * @return pharmacy to be inserted
     */
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    /**
     * Returns the adress of the pharmacy to be inserted.
     * @return adress of the pharmacy to be inserted
     */
    public Address getAdress() {
        return adress;
    }

    /**
     * Returns the parking lot of the pharmacy to be inserted.
     * @return parking lot of the pharmacy to be inserted 
     */
    public ParkingLot getParkingLot() {
        return parkingLot;
    }
    
    /**
     * Returns the platform's PharmacyList.
     * @return platform's PharmacyList
     */
    public PharmacyList getPharmacyList() {
        return pl;
    }
}
