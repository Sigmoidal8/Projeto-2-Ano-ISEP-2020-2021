/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;


public class PharmacyDB extends DataHandler {

    private static final String DESIGNATION_DB = "DESIGNATION";
    private static final String EMAIL_DB = "EMAIL";
    private static final String PASS_DB = "PASSWORD";
    private static final String ADRESS_DB = "ADRESSID";

    /**
     * Returns a Pharmacy from the Data Base with the same id passed by parameter.
     *
     * @param id
     * @return
     */
    public Pharmacy getPharmacy(String id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String pharmacyID = rSet.getString("ID");
                String pharmacyDesignation = rSet.getString(DESIGNATION_DB);
                String pharmacyEmail = rSet.getString(EMAIL_DB);
                String pharmacyPassword = rSet.getString(PASS_DB);
                String pharmacyAdressID = rSet.getString(ADRESS_DB);

                closeAll();
                
                callStmt = getConnection().prepareCall("{ ? = call getAdress(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setString(2, pharmacyAdressID);
                callStmt.execute();

                ResultSet rSet2 = (ResultSet) callStmt.getObject(1);

                if (rSet2.next()) {
                    String adressStreet = rSet2.getString("STREET");
                    int adressDoorNumber = rSet2.getInt("DOORNUMBER");
                    String adressPostalCode = rSet2.getString("POSTALCODE");
                    String adressLocality = rSet2.getString("LOCALITY");
                    double adressDecimalDegree1 = rSet2.getDouble("DECIMALDEGREE1");
                    double adressDecimalDegree2 = rSet2.getDouble("DECIMALDEGREE2");

                    return new Pharmacy(pharmacyID, pharmacyDesignation, pharmacyEmail, pharmacyPassword, new Address(pharmacyAdressID, adressStreet, adressDoorNumber, adressPostalCode, adressLocality, adressDecimalDegree1, adressDecimalDegree2));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy with ID:" + id);
    }

    /**
     * Adds a Pharmacy to the Data Base with the id,designation, email,password and adress from the pharmacy passed by parameter.
     *
     * @param pharmacy
     */
    public void addPharmacy(Pharmacy pharmacy) {
        addPharmacy(pharmacy.getId(), pharmacy.getDesignation(), pharmacy.getEmail(), pharmacy.getPassword(), pharmacy.getAdress());
    }

    /**
     * Adds a Pharmacy to the Data Base with the id,designation,email,password,adress passed by parameter.
     *
     * @param id
     * @param designation
     * @param email
     * @param password
     * @param adress
     * @return
     */
    private void addPharmacy(String id, String designation, String email, String password, Address adress) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?) }");

            callStmt.setString("ID", id);
            callStmt.setString(DESIGNATION_DB, designation);
            callStmt.setString(EMAIL_DB, email);
            callStmt.setString(PASS_DB, password);
            callStmt.setString(ADRESS_DB, adress.getId());

            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    /**
     * Removes a Pharmacy from the Data Base with the same id passed by parameter.
     *
     * @param id
     */
    public void removePharmacy(String id) {

        CallableStatement callStmt7 = null;
        try {
            openConnection();
            callStmt7 = getConnection().prepareCall("{ call removePharmacy(?) }");

            callStmt7.setString(1, id);

            callStmt7.execute();
        } catch (SQLException e7) {
            e7.printStackTrace();
        } finally {
            closeAll();
        }

    }

    /**
     * Updates the Pharmacy passed by parameter in the data base
     *
     * @param pharmacy
     */
    public void updatePharmacy(Pharmacy pharmacy) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call updatePharmacy(?,?,?,?,?) }");

            callStmt.setString("ID", pharmacy.getId());
            callStmt.setString(DESIGNATION_DB, pharmacy.getDesignation());
            callStmt.setString(EMAIL_DB, pharmacy.getEmail());
            callStmt.setString(PASS_DB, pharmacy.getPassword());
            callStmt.setString(ADRESS_DB, pharmacy.getAdress().getId());

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

    /**
     * Adds a Product in a Pharmacy to the Data Base with the stock passed by parameter.
     *
     * @param pharmacy
     * @param product
     * @param stock
     */
    public void addPharmacyProduct(Pharmacy pharmacy, Product product, int stock) {
        addPharmacyProduct(pharmacy.getId(), product.getID(), stock);
    }

    /**
     * Adds a Product in a Pharmacy to the Data Base with the stock passed by parameter.
     *
     * @param pharmacy
     * @param product
     * @param stock
     */
    private void addPharmacyProduct(String pharmacyID, String productID, int stock) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addPharmacy_Product(?,?,?) }");

            callStmt.setString("PHARMACYID", pharmacyID);
            callStmt.setString("PRODUCTID", productID);
            callStmt.setInt("STOCK", stock);

            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }
    
    /**
     * Removes a Product in a Pharmacy to the Data Base with the same pharmacyID and ProductID passed by parameter.
     *
     * @param pharmacyID
     * @param productID
     */
    public void removePharmacyProduct(String pharmacyID, String productID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call removePharmacy_Product(?,?) }");

            callStmt.setString(1, pharmacyID);
            callStmt.setString(2, productID);

            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }
    
    /**
     * Updates the stock of a product in a pharmacy passed by parameter in the data base
     *
     * @param pharmacy
     * @param product
     * @param stock
     */
    public void updatePharmacyProduct(Pharmacy pharmacy, Product product, int stock) {

        CallableStatement callStmt7 = null;
        try {
            callStmt7 = getConnection().prepareCall("{ call updateStock(?,?,?) }");

            callStmt7.setString("PHARMACYID", pharmacy.getId());
            callStmt7.setString("PRODUCTID", product.getID());
            callStmt7.setInt("STOCK", stock);

            callStmt7.execute();
        } catch (SQLException e7) {
            e7.printStackTrace();
        } finally {
            closeAll();
        }

    }

}
