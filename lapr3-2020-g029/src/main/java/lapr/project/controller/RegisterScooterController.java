/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;


import java.io.File;
import lapr.project.utils.GenerateQRCode;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.ScootersList;


public class RegisterScooterController {
    
    /**
     * Platform of the application
     */
    private final Platform plat;
    
    /**
     * ScootersList of the Platform
     */
    private ScootersList sctl;
    
    /**
     * GenerateQRCode to generate the qrCodes of the scooters
     */
    private final GenerateQRCode gqrc;
    
    
    /**
     * Creates an instance of RegisterScooterController
     * initializing the platform and its
     * ScooterList and an object of GenerateQRCode
     */
    public RegisterScooterController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        sctl  = plat.getScootersList();
        gqrc = new GenerateQRCode();
    }
    
     /**
     * Generates an exclusiveNumber to the Scooter
     *
     * @return the exclusiveNumber
     */
    public int generateExclusiveNumber(){ 
        sctl=plat.getScootersList();
        return sctl.generateExclusiveNumber();
    }
    
    /**
     * Generates a qrCode to the Scooter
     * with the same esclusiveNumber
     * passed by parameter
     *
     * @return the file with the qrCode
     */
    public File generateQRCode(int excNumb) throws Exception{      
        return gqrc.generateQRCode(excNumb);
    }
    
    /**
     * Creates a new instance of Scooter
     * with exclusiveNumber,fullCharge,power,operation and qrCode
     * received by parameter
     * @param excNumb
     * @param fullCharge
     * @param power
     * @param operational
     * @param qrCode
     * @return 
     */
    public Scooter newScooter(int excNumb,double fullCharge, double power, int operational,File qrCode){
        Scooter s = sctl.newScooter(excNumb, fullCharge, power, operational);
        s.setQrCode(qrCode);
        return s;
    }
    
    /**
     * Validates the scooter passed by parameter and if
     * it is valid, registates it in the system and in the dataBase.
     * @param s
     * @return true or false, if the scooter is valid or not
     */
        public boolean registerScooter(Scooter s) {
        if (sctl.validateScooter(s)) {
            sctl.registerScooter(s);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the Platfrom of this AddDroneController
     * @return 
     */    
    public Platform getPlat() {
        return plat;
    }
}

