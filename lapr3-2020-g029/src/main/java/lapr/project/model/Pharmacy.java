/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lapr.project.controller.AplicationPOT;

public class Pharmacy {
    
    /**
     * The id of
     * the pharmacy
     */
    private String id;
    /**
     * The designation of
     * the pharmacy
     */
    private final String designation;
    /**
     * The email of
     * the pharmacy
     */
    private final String email;
    /**
     * The password of
     * the pharmacy
     */
    private final String password;
    /**
     * The adress of
     * the pharmacy
     */
    private final Address adress;
    /**
     * The ArrayList corresponding to
     * the park of the pharmacy
     */
    private final ArrayList<ParkingLot> park;
    /**
     * The product list of 
     * the pharmacy
     */
    private ProductList pl;
    /**
     * The list of available
     * scooters of the pharmacy
     */
    private AvailableScooterList asl;
    /**
     * The list of available
     * drones of the pharmacy
     */
    private final DronesList availableDroneList;
    /**
     * The number of parking lots
     * of the pharmacy
     */
    private int numberOfParkingLots;
    /**
     * 
     * @param id
     * @param designation
     * @param email
     * @param password
     * @param adress 
     */
    public Pharmacy(String id, String designation, String email, String password, Address adress) {
        this.id = id;
        this.designation = designation;
        this.email = email;
        this.password=password;
        this.adress = adress;
        this.park = new ArrayList<>();
        this.pl = new ProductList();
        this.asl = new AvailableScooterList();
        this.availableDroneList=new DronesList();
        this.numberOfParkingLots = 0;
    }
    /**
     * 
     * @param designation
     * @param email
     * @param password
     * @param adress 
     */
    public Pharmacy(String designation, String email,String password, Address adress) {
        this.designation = designation;
        this.email = email;
        this.password=password;
        this.adress = adress;
        this.park = new ArrayList<>();
        this.pl = new ProductList();
        this.asl = new AvailableScooterList();
        this.availableDroneList=new DronesList();
        this.numberOfParkingLots = 0;
    }
    /**
     * 
     * @return the if
     * of the pahrmacy
     */
    public String getId() {
        return id;
    }
    /**
     * 
     * @return the parking lot
     * list of the pahrmacy
     */
    public List<ParkingLot> getParkingLotList() {
        return park;
    }
    /**
     * 
     * @return the designation
     * of the pahrmacy
     */
    public String getDesignation() {
        return designation;
    }
    /**
     * 
     * @return the email
     * of the pahrmacy
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @return the adress
     * of the pahrmacy
     */
    public Address getAdress() {
        return adress;
    }
    /**
     * 
     * @return the product list
     * of the pahrmacy
     */
    public ProductList getProductList() {
        return pl;
    }
    /**
     * 
     * @return the password
     * of the pahrmacy
     */
    public String getPassword() {
        return password;
    }
    /**
     * 
     * @return the list of available
     * scooters of the pahrmacy
     */
    public AvailableScooterList getAsl() {
        return asl;
    }
    /**
     * 
     * @return the list of available
     * drones of the pahrmacy
     */
    public DronesList getAvailableDroneList() {
        return availableDroneList;
    }
    /**
     * 
     * @return the number of parking
     * lots of the pahrmacy
     */
    public int getNumberOfParkingLots() {
        return numberOfParkingLots;
    }
    /**
     * 
     * @param id
     * @return the pharmacy whit
     * the id received by parameter
     */
    public Pharmacy getPharmacy(String id){
        return AplicationPOT.getInstance().getPlatform().getPdb().getPharmacy(id);
    }
    /**
     * 
     * @param parkingLotID
     * @return the parking lot whit
     * the id received by parameter
     * or null
     */
    public ParkingLot getParkingLotFromID(String parkingLotID) {
        for(ParkingLot pkl : park) {
            if(pkl.getId().compareTo(parkingLotID) == 0) {
                return pkl;
            }
        }
        return null;
    }
    /**
     * Changes the id of the 
     * pharmacy to the one received by parameter
     * 
     * @param id of the pharmacy
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Changes the product list of the 
     * pharmacy to the one received by parameter
     * 
     * @param pl of the pharmacy
     */
    public void setProductList(ProductList pl){
        this.pl=pl;
    }
    /**
     * Changes the Available Scooter List of the 
     * pharmacy to the one received by parameter
     * 
     * @param asl the asl to set
     */
    public void setAsl(AvailableScooterList asl) {
        this.asl = asl;
    }
    /**
     * Returns a textual description of the
     * pharmacy presenting the id, designation,
     * email and password
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Pharmacy{" + "id=" + id + ", designation=" + designation + ", email=" + email + ", password=" + password+"}";
    }
    /**
     * Verify if the parking lot received
     * as a parameter can be registered
     * 
     * @param parkingLot
     * @return true or false
     */
    public boolean registerParkingLot(ParkingLot parkingLot) {
        parkingLot.generateID(numberOfParkingLots,id);
            return addParkingLot(parkingLot);
    }

    /**
     * Add the parking lot
     * received as a parameter 
     * 
     * @param parkingLot
     * @return 
     */
    public boolean addParkingLot(ParkingLot parkingLot) {
        numberOfParkingLots++;
        AplicationPOT.getInstance().getPlatform().getPldb().addParkingLot(parkingLot, id);
        if(!park.contains(parkingLot)){
            park.add(parkingLot);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify if the pharmacy
     * has parking lot for drones
     * 
     * @return 
     */
    public boolean hasDroneParkingLot(){
        for(ParkingLot park1 : park){
            if(park1.getType().equalsIgnoreCase("drone")){
                return true;
            }
        }
        return false;
    }
    /**
     * Verify if the pharmacy
     * has parking lot for scooters
     * 
     * @return true or false
     */
    public boolean hasScooterParkingLot(){
        for(ParkingLot park1 : park){
            if(park1.getType().equalsIgnoreCase("scooter")){
                return true;
            }
        }
        return false;
    }
    /**
     * Validates if the pharmacy with this 
     * id is already in the database, if it is
     * not it saves it
     */
    public void save() {
        try {
            getPharmacy(this.getId());
        } catch (IllegalArgumentException ex) {
            AplicationPOT.getInstance().getPlatform().getPdb().addPharmacy(this);
        }
    }
    /**
     * Updates the data of this pharmacy
     * in the database
     */
    public void update(){
        AplicationPOT.getInstance().getPlatform().getPdb().updatePharmacy(this);
    }
    /**
     * Returns if the object received by parameter
     * is the same as this pharmacy
     * 
     * @param obj
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pharmacy other = (Pharmacy) obj;
        return Objects.equals(this.id, other.id);
    }
}
