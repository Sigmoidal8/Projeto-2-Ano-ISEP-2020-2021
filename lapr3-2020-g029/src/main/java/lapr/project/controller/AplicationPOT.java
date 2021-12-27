/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.ui.autorization.UserSession;
import lapr.project.model.Platform;


public class AplicationPOT {
    /**
     * The plaform
     */
    private final Platform plat;
    
    /**
     * The autorization 
     */
    private final AutorizationFacade autorization;
    
    /**
     * Instance of AplicationPot
     */
    private static AplicationPOT singleton = null;
    
    /**
     * Creates an instance of AplicationPOT
     */
    public AplicationPOT() {
        plat = new Platform();
        autorization = this.plat.getAutorizationFacade();
        singleton = this;
        bootstrap();
    }

    /**
     * Returns the platform associated to the AplicationPOT
     * 
     * @return the platform
     */
    public Platform getPlatform() {
        return this.plat;
    }

    /**
     * Returrns the atual session
     * 
     * @return the session
     */
    public UserSession getAtualSession() {
        return this.autorization.getAtualSession();
    }
    
    /**
     * Returns the AplicationPOT that is being used
     * 
     * @return the AplicationPOT
     */
    public static AplicationPOT getInstance() {
        if (singleton == null) {
            synchronized (AplicationPOT.class) {
                singleton = new AplicationPOT();
            }
        }
        return singleton;
    }

    /**
     * Does the login of the user receiving the email and password by parameter
     * 
     * @param strId
     * @param strPwd
     * @return if the user was logged in
     */
    public boolean doLogin(String strId, String strPwd) {
        return this.autorization.doLogin(strId, strPwd) != null;
    }

    /**
     * Does the logout of the user
     */
    public void doLogout() {
        this.autorization.doLogout();
    }

    /**
     * Initializes the administrators of the platform
     */
    private void bootstrap() {
        this.autorization.registerUserWithRole("Admin", "lapr3g029@gmail.com", "Lapr32021", "Administrator");
    }

}
