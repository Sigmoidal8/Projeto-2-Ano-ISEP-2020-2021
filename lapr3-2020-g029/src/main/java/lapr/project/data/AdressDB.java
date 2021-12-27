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
import oracle.jdbc.OracleTypes;


public class AdressDB extends DataHandler {

    
    private static final String STREETDB = "STREET";
    private static final String DOORNUMBERDB = "DOORNUMBER";
    private static final String POSTALCODEDB = "POSTALCODE";
    private static final String LOCALITYDB = "LOCALITY";
    private static final String DECIMALDEGREE1DB = "DECIMALDEGREE1";
    private static final String DECIMALDEGREE2DB = "DECIMALDEGREE2";

    /**
     * Returns an Adress from the Data Base with the same id passed by parameter.
     * @param id
     * @return 
     */
    public Address getAdress(String id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAdress(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String adressID = rSet.getString("ID");
                String adressStreet = rSet.getString(STREETDB);
                int doorNumber = rSet.getInt(DOORNUMBERDB);
                String adressPostalCode = rSet.getString(POSTALCODEDB);
                String adressLocality = rSet.getString(LOCALITYDB);
                double adressDecimalDegree1 = rSet.getDouble(DECIMALDEGREE1DB);
                double adressDecimalDegree2 = rSet.getDouble(DECIMALDEGREE2DB);
                return new Address(adressID, adressStreet, doorNumber, adressPostalCode, adressLocality, adressDecimalDegree1, adressDecimalDegree2);
            }
            
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
                closeAll();
        }
        throw new IllegalArgumentException("No Adress with ID:" + id);
    }

    /**
     * Adds an adress to the Data Base
     * @param adress 
     */
    public void addAdress(Address adress) {
        addAdress(adress.getId(), adress.getStreet(), adress.getDoorNumber(), adress.getPostalCode(), adress.getLocality(), adress.getDecimalDegree1(), adress.getDecimalDegree2());
    }

    /**
     * Adds an adress to the Data Base with the id, Stree, doorNumber, postalCode, locality, decimalDegree1 and decimalDegree2 passed by parameter 
     * @param id
     * @param street
     * @param doorNumber
     * @param postalCode
     * @param locality
     * @param decimalDegree1
     * @param decimalDegree2 
     */
    private void addAdress(String id, String street, int doorNumber, String postalCode, String locality, double decimalDegree1, double decimalDegree2) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addAdress(?,?,?,?,?,?,?) }");

            callStmt.setString("ID", id);
            callStmt.setString(STREETDB, street);
            callStmt.setInt(DOORNUMBERDB, doorNumber);
            callStmt.setString(POSTALCODEDB, postalCode);
            callStmt.setString(LOCALITYDB, locality);
            callStmt.setDouble(DECIMALDEGREE1DB, decimalDegree1);
            callStmt.setDouble(DECIMALDEGREE2DB, decimalDegree2);

            callStmt.execute();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            closeAll();
        }
    }

    /**
     * Removes an Adress from the Data Base with the same id passed by parameter.
     * @param id 
     */
    public void removeAdress(String id) {

        CallableStatement callStmt2 = null;
        try {
            callStmt2 = getConnection().prepareCall("{ call removeAdress(?) }");

            callStmt2.setString(1, id);

            callStmt2.execute();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            closeAll();
        }

    }

    /**
     * Updates the Adress passed by parameter in the data base
     * @param adress 
     */
    public void updateAdress(Address adress) {

        CallableStatement callStmt2 = null;
        try {
            callStmt2 = getConnection().prepareCall("{ call updateAdress(?,?,?,?,?,?,?) }");

            callStmt2.setString("ID", adress.getId());
            callStmt2.setString(STREETDB, adress.getStreet());
            callStmt2.setInt(DOORNUMBERDB, adress.getDoorNumber());
            callStmt2.setString(POSTALCODEDB, adress.getPostalCode());
            callStmt2.setString(LOCALITYDB, adress.getLocality());
            callStmt2.setDouble(DECIMALDEGREE1DB, adress.getDecimalDegree1());
            callStmt2.setDouble(DECIMALDEGREE2DB, adress.getDecimalDegree2());

            callStmt2.execute();

        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
             closeAll();
        }
    }
}
