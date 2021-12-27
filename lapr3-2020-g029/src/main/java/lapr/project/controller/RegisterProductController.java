/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ProductList;


public class RegisterProductController {
    
    /**
     * Platform of the application
     */
    private final Platform plat;
    
    /**
     * PharmacyList of the Platform
     */
    private PharmacyList phl;
    
    /**
     * ProductList of the Platform
     */
    private ProductList pl;
    
    /**
     * Creates an instance of RegisterProductController initializing the platform.
     */
    public RegisterProductController(){
        plat = AplicationPOT.getInstance().getPlatform();
    }
    
    
     /**
     * Creates a new instance of Prduct with name,price and weight received by parameter
     * and adds it to the pharmacy with the same ID passed by parameter
     *
     * @param name
     * @param price
     * @param weight
     * @return
     */
    public Product newProduct(String name, float price, float weight,  String pharmacyID){
        phl = plat.getPharmacyList();
        Pharmacy ph = phl.getPharmacy(pharmacyID);
        pl = ph.getProductList();
        Product p = pl.newProduct(name, price, weight);
        ph.setProductList(pl);
        return p;
    }
    
    /**
     * Validates the product passed by parameter and if it is valid, registates it in the dataBase.
     *
     * @param p
     * @return true or false, if the product is valid or not
     */
    public boolean registerProduct(Product p){
        return pl.registerProduct(p);
    }
    
    /**
     * Returns the PharmacyList of the Platform
     * @return 
     */
    public PharmacyList getPharmacyList() {
        phl = plat.getPharmacyList();
        return phl;
    }
    
}
