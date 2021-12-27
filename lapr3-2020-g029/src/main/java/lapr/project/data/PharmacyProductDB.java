/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;


public class PharmacyProductDB extends DataHandler{
    
    /**
     * Adds a Product in a Pharmacy to the Data Base with the stock passed by parameter.
     *
     * @param pharmacy
     * @param product
     * @param stock
     */
    public void addPharmacyProduct(Product product, Pharmacy pharmacy, int stock) {
        addPharmacyProduct(pharmacy.getId(), product.getID(), stock);
    }
    
    /**
     * Adds a Product in a Pharmacy to the Data Base with the stock passed by parameter.
     *
     * @param pharmacy
     * @param product
     * @param stock
     */
    private void addPharmacyProduct(String idPharmacy, String idProduct, int stock) {

        CallableStatement callStmt8 = null;
        try {
            callStmt8 = getConnection().prepareCall("{ call addProductPharmacy(?,?,?) }");

            callStmt8.setString("PHARMACYID", idPharmacy);
            callStmt8.setString("PRODUCTID", idProduct);
            callStmt8.setInt("STOCK", stock);
            
            callStmt8.execute();
        } catch (SQLException e8) {
            e8.printStackTrace();
        }finally {
            closeAll();
        }
    }


    
}
