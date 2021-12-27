/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;


public class ProductDB extends DataHandler {

    private static final String IDDB = "ID";
    private static final String NAMEDB = "NAME";
    private static final String PRICEDB = "PRICE";
    private static final String WEIGHTDB = "WEIGHT";
    
    
    /**
     * Returns a Product from the Data Base with the same id passed by parameter.
     *
     * @param id
     * @return
     */
    public Product getProduct(String id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String productID = rSet.getString(IDDB);
                String productName = rSet.getString(NAMEDB);
                float productPrice = rSet.getFloat(PRICEDB);
                float weigth = rSet.getFloat(WEIGHTDB);

                return new Product(productID, productName, productPrice, weigth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Product with ID:" + id);
    }
    
    /**
     * Adds a product to the Data Base with the id,name,price
     * and weight from the product passed by parameter.
     *
     * @param product
     */
    public void addProduct(Product product) {
        addProduct(product.getID(), product.getNome(), product.getPrice(), product.getWeight());
    }
    
    /**
     * Adds a Product to the Data Base with the id,name,price and weight passed by parameter.
     *
     * @param id
     * @param name
     * @param price
     * @param weight
     */
    private void addProduct(String id, String name, float price, float weigth) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?) }");

            callStmt.setString(IDDB, id);
            callStmt.setString(NAMEDB, name);
            callStmt.setFloat(PRICEDB, price);
            callStmt.setFloat(WEIGHTDB, weigth);

            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }
    
    /**
     * Removes a Product from the Data Base with the same id passed by parameter.
     *
     * @param id
     */
    public void removeProduct(String id) {

        CallableStatement callStmt9 = null;
        try {
            callStmt9 = getConnection().prepareCall("{ call removeProduct(?) }");

            callStmt9.setString(1, id);

            callStmt9.execute();
        } catch (SQLException e9) {
            e9.printStackTrace();
        } finally {
           closeAll();
        }

    }
    
    /**
     * Updates the Product passed by parameter in the data base
     *
     * @param product
     */
    public void updateProdcut(Product product) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call updateProduct(?,?,?,?) }");

            callStmt.setString(IDDB, product.getID());
            callStmt.setString(NAMEDB, product.getNome());
            callStmt.setFloat(PRICEDB, product.getPrice());
            callStmt.setFloat(WEIGHTDB, product.getWeight());

            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

}
