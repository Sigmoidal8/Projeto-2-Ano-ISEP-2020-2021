/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.CourierDB;


public class CourierList {
    
    /**
     * The list of Couriers
     */
    private final List<Courier> listCourier;
    
    /**
     * Creates an instance of CourierList
     * initializing the listCourier
     */
    public CourierList(){
        listCourier = new ArrayList<>();
    }
    
    /**
     * Returns the list of Couriers
     * 
     * @return 
     */
    public List<Courier> getListCourier(){
        return listCourier;
    }
    
    /**
     * Returns the Courier with the
     * NIF received by parameter in
     * the list
     * 
     * @param nif
     * @return 
     */
    public Courier getCourier(int nif){
        for(Courier p : listCourier){
            if (p.getNIF() == nif) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Returns the Courier with the
     * email received by parameter in
     * the list
     * 
     * @param email
     * @return 
     */
    public Courier getCourier(String email){
        for(Courier p : listCourier){
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Returns if the the Courier
     * was added the list of  the
     * Couriers
     * 
     * @param p
     * @return 
     */
    public boolean addCourier(Courier p){
        if (!listCourier.contains(p)) {
            listCourier.add(p);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Creates a new instance of Courier
     * with the name, nif, socialSecurityNumber,
     * email and password received by parameter
     * 
     * @param name
     * @param nif
     * @param socialSecurityNumber
     * @param email
     * @param password
     * @return 
     */
    public Courier newCourier (String name, String nif, String socialSecurityNumber, String email, String password){
        return new Courier (name, nif, socialSecurityNumber, email, password);
    }
    
    /**
     * Verifies if the Courier is already
     * on the list and if not returns if
     * it was added to the list of Couriers
     * in the database
     * 
     * @param cour
     * @param plat
     * @return 
     */
    public boolean registerCourier (Courier cour, Platform plat) {
        if (validateCourier(cour)) {
            registerCourierWithRole(cour, plat);
            CourierDB courierDB = AplicationPOT.getInstance().getPlatform().getCoudb();
            courierDB.addCourier(cour);
            return addCourier(cour);
        }
        return false;
    }
    
    /**
     * Returns if the Couriers received
     * by parameter is already in the
     * list of Couriers
     * 
     * @param cour
     * @return 
     */
    public boolean validateCourier(Courier cour){
        return !listCourier.contains(cour);
    }
    
    /**
     * Registers a Courier as a user
     * 
     * @param cour
     * @param plat 
     */
    public void registerCourierWithRole(Courier cour, Platform plat){
        String name = cour.getName();
        String email = cour.getEmail();
        String pwd = cour.getPassword();
        AutorizationFacade aut = plat.getAutorizationFacade();
        aut.registerUserWithRole(name, email, pwd, "Courier");
    }
    
    
}
