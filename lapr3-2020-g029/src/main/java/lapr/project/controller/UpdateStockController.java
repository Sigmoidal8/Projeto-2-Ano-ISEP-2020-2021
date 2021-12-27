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


public class UpdateStockController {

    /**
     * Platform of the application
     */
    private final Platform plat;

    /**
     * The ProductList of the platform
     */
    private ProductList prl;

    /**
     * The PharmacyList of the platfrom
     */
    private PharmacyList pl;
    /**
     * A pharmacy
     */
    private Pharmacy p;
    
    /**
     * Creates an instance of UpdateStockController initializing the platform and the PharmacyList.
     */
    public UpdateStockController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        pl = plat.getPharmacyList();
    }
    
    /**
     * Returns the PharmacyList of the Platform
     * @return 
     */
    public PharmacyList getPharmacyList() {
        pl = plat.getPharmacyList();
        return pl;
    }
    
    /**
     * Returns the Pharmacy of the PharmacyList with the same
     * id passed by parameter
     * @param pharmacyID
     * @return 
     */
    public Pharmacy getPharmacy(String pharmacyID) {
        p = pl.getPharmacy(pharmacyID);
        return p;
    }
    
    /**
     * Returns the ProductsList of the Platform
     * @return 
     */
    public ProductList getProductList() {
        prl = p.getProductList();
        return prl;
    }
    
    /**
     * Returns the Product of the Product with the same
     * id passed by parameter
     * @param productID
     * @return 
     */
    public Product getProduct(String productID) {
        prl = p.getProductList();
        return prl.getProduct(productID);
    }
    
    /**
     * Changes the stock of the product in the data base with the id passed by parameter,
     * in the pharmacy passed by parameter
     * @param stock
     * @param idProduct
     * @param idPharmacy
     */
    public boolean setStock(int stock, String idProduct, String idPharmacy) {
        return plat.getStdb().setStock(idPharmacy, idProduct, stock);
    }
    
    /**
     * Returns the stock of the product in the data base with the id passed by parameter,
     * in the pharmacy passed by parameter
     * @param idProduct
     * @param idPharmacy
     * @return 
     */
    public int getStock(String idProduct, String idPharmacy) {
        return plat.getStdb().getStock(idPharmacy, idProduct);
    }
}
