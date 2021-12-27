/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.Courier;
import oracle.jdbc.OracleTypes;

public class CourierDB extends DataHandler {

    /**
     * Returns a Courier from the Data Base with the same nif passed by
     * parameter.
     *
     * @param nif
     * @return
     */
    public Courier getCourier(String nif) {

        CallableStatement callStmt12 = null;
        try {
            callStmt12 = getConnection().prepareCall("{ ? = call getCourier(?) }");

            callStmt12.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt12.setString(2, nif);

            callStmt12.execute();

            ResultSet rSet = (ResultSet) callStmt12.getObject(1);

            if (rSet.next()) {
                String courierNIF = rSet.getString(1);
                String courierName = rSet.getString(2);
                String clientEmail = rSet.getString(3);
                String clientSocialSecurity = rSet.getString(4);
                String clientPassword = rSet.getString(5);

                closeAll();
                return new Courier(courierName, courierNIF, clientSocialSecurity, clientEmail, clientPassword);
            }

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
            throw new IllegalArgumentException("No Courier with NIF:" + nif);
        }
    }

    /**
     * Adds a Courier to the Data Base
     *
     * @param courier
     */
    public void addCourier(Courier courier) {
        addCourier(String.format("%d", courier.getNIF()), courier.getName(), String.format("%d", courier.getSocialSecurityNumber()), courier.getEmail(), courier.getPassword());
    }

    /**
     * Adds a Courier to the Data Base with the nif, name, socialSecurity, email
     * and password passed by parameter
     *
     * @param nif
     * @param name
     * @param socialSecurity
     * @param email
     * @param password
     */
    private void addCourier(String nif, String name, String socialSecurity, String email, String password) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?) }");

            callStmt.setString(1, nif);
            callStmt.setString(2, name);
            callStmt.setString(3, email);
            callStmt.setString(4, socialSecurity);
            callStmt.setString(5, password);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    /**
     * Removes a Courier from the Data Base with the same nif passed by
     * parameter.
     *
     * @param nif
     */
    public void removeCourier(String nif) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call removeCourier(?) }");

            callStmt.setString(1, nif);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

    /**
     * Updates the Courier passed by parameter in the data base
     *
     * @param courier
     */
    public void updateCourier(Courier courier) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call updateCourier(?,?,?,?,?) }");

            callStmt.setString("NIF", String.format("%d", courier.getNIF()));
            callStmt.setString("NAME", courier.getName());
            callStmt.setString("SOCIALSCURITYNUMBER", String.format("%d", courier.getSocialSecurityNumber()));
            callStmt.setString("EMAIL", courier.getEmail());
            callStmt.setString("PASSWORD", courier.getPassword());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

}
