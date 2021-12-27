/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;


public class StockDB extends DataHandler {
    
    /**
     * Returns the stock from a product in a Pharmacy the Data Base with the same ProductID and PharmacyID passed by parameter.
     *
     * @param idPharmacy
     * @param idProduct
     * @return
     */
    public Integer getStock(String idPharmacy, String idProduct) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getStock(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);

            callStmt.setString(2, idPharmacy);
            callStmt.setString(3, idProduct);

            callStmt.execute();

            return callStmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Stock with ID:" + idPharmacy + " and " + idProduct);
    }
    
    /**
     * Changes the stock from a product in a Pharmacy the Data Base with the same ProductID and PharmacyID passed by parameter.
     *
     * @param idPharmacy
     * @param idProduct
     * @param stock
     */
    public boolean setStock(String idPharmacy, String idProduct, int stock) {

        CallableStatement callStmt11 = null;
        try {
            callStmt11 = getConnection().prepareCall("{ call updateStock(?,?,?) }");
            callStmt11.setString("PHARMACYID", idPharmacy);
            callStmt11.setString("PRODUCTID", idProduct);
            callStmt11.setInt("STOCK", stock);

            callStmt11.execute();
            closeAll();
            return true;

        } catch (SQLException e11) {
            e11.printStackTrace();
            return false;
        } finally {
            closeAll();
        }

    }
}
