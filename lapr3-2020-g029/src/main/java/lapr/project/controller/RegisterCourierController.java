/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.model.Courier;
import lapr.project.model.CourierList;
import lapr.project.model.Platform;


public class RegisterCourierController {

    /**
     * Platform of the application
     */
    private final Platform plat;
    
    /**
     * Courier which is going to be registered 
     */
    private Courier c;
    
    /**
     * CourierList of the Platform
     */
    private CourierList cl;

    /**
     * The Autorization
     */
    private final AutorizationFacade autorization;
    
    /**
     * Creates an instance of RegisterCourierController initializing the platform.
     */
    public RegisterCourierController() {
        plat = AplicationPOT.getInstance().getPlatform();
        autorization = plat.getAutorizationFacade();
    }
    
    /**
     * Creates a new instance of Courier with name,nif,SSN, email and password received by parameter
     * and adds it to courierList
     *
     * @param name
     * @param nif
     * @param socialSecurityNumber
     * @param email
     * @param password
     * @return
     */
    public Courier newCourier(String name, String nif, String socialSecurityNumber, String email, String password) {
        cl = plat.getCourierList();
        c = cl.newCourier(name, nif, socialSecurityNumber, email, password);
        return c;
    }
    
    /**
     * Validates the courier passed by parameter and if it is valid, registates it in the dataBase and in the system with the role Courier.
     *
     * @return true or false, if the product is valid or not
     */
    public boolean registerCourier() {
        return cl.registerCourier(c, plat);
    }

    public Courier getCourier() {
        return c;
    }

    /**
     * Searchs if the email already exists in the platform
     *
     * @param email
     * @return if the email exists
     */
    public boolean existsEmail(String email) {
        return autorization.hasUser(email);
    }

}
