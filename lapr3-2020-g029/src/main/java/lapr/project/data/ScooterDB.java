/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.Scooter;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.controller.AplicationPOT;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import oracle.jdbc.OracleTypes;

public class ScooterDB extends DataHandler {

    private static final String EXCLUSIVENUMBERDB = "EXCLUSIVENUMBER";
    private static final String FULLCHARGEDB = "FULLCHARGE";
    private static final String CURRENTCHARGEDB = "CURRENTCHARGE";
    private static final String OPERATIONALDB = "OPERATIONAL";
    private static final String POWERDB = "POWER";
    private static final String PHARMACYDB = "PHARMACYID";

    
    /**
     * Returns a Scooter from the Data Base with the same exclusiveNumber passed by parameter.
     *
     * @param exclusiveNumber
     * @return
     */
    public Scooter getScooter(int exclusiveNumber) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, exclusiveNumber);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterExclusiveNumber = rSet.getInt(EXCLUSIVENUMBERDB);
                double scooterFullCharge = rSet.getDouble(FULLCHARGEDB);
                double scooterCurrentCharge = rSet.getDouble(CURRENTCHARGEDB);
                int scooterOperational = rSet.getInt(OPERATIONALDB);
                double scooterPower = rSet.getDouble(POWERDB);
                String scooterPharmacy = rSet.getString(PHARMACYDB);

                Pharmacy pharmacy = AplicationPOT.getInstance().getPlatform().getPdb().getPharmacy(scooterPharmacy);

                return new Scooter(scooterExclusiveNumber, scooterFullCharge, scooterCurrentCharge, scooterPower, scooterOperational, pharmacy);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with Exclusive Number:" + exclusiveNumber);
    }
    
    /**
     * Removes a Scooter from the Data Base with the same id passed by parameter.
     *
     * @param id
     */
    public void removeScooter(int id) {

        CallableStatement callStmt10 = null;
        try {
            callStmt10 = getConnection().prepareCall("{ call removeScooter(?) }");

            callStmt10.setInt(1, id);

            callStmt10.execute();
        } catch (SQLException e10) {
            e10.printStackTrace();
        } finally {
            closeAll();
        }

    }
    
    /**
     * Updates the Scooter passed by parameter in the data base
     *
     * @param scooter
     */
    public void updateScooter(Scooter scooter) {
        CallableStatement callStmt10 = null;
        try {

            callStmt10 = getConnection().prepareCall("{ call updateScooter(?,?,?,?,?,?) }");

            callStmt10.setInt(EXCLUSIVENUMBERDB, scooter.getExclusiveNumber());
            callStmt10.setDouble(FULLCHARGEDB, scooter.getFullCharge());
            callStmt10.setDouble(CURRENTCHARGEDB, scooter.getCurrentCharge());
            callStmt10.setInt(OPERATIONALDB, scooter.getOperational());
            callStmt10.setDouble(POWERDB, scooter.getPower());
            PharmacyList pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();
            boolean noPharmacy = true;
            Pharmacy scooterPharmacy = null;
            for (Pharmacy p : pl.getPharmacyList()) {
                if (p.getAsl().getAvailableScooterList().contains(scooter)) {
                    noPharmacy = false;
                    scooterPharmacy = p;
                }
            }
            if (!noPharmacy) {
                callStmt10.setString(PHARMACYDB, scooterPharmacy.getId());
            } else {
                callStmt10.setString(PHARMACYDB, "");
            }

            callStmt10.execute();
            
        } catch (SQLException e10) {
            e10.printStackTrace();
        } finally {
            closeAll();
        }

    }
    
    
    /**
     * Adds a Scooter to the Data Base with the exclusiveNumber,fullCharge,currentCharge,operational and power passed by parameter,
     * but first he changes the boolean operational to an int.
     *
     * @param s
     */
    public void addScooter(Scooter s) {
        int operational;
        if (s.isOperational()) {
            operational = 1;
        } else {
            operational = 0;
        }
        addScooter(s.getExclusiveNumber(), s.getFullCharge(), s.getCurrentCharge(), operational, s.getPower());
    }
    
    /**
     * Adds a Scooter to the Data Base with the exclusiveNumber,fullCharge,currentCharge,operational and power passed by parameter.
     *
     * @param exclusiveNumber
     * @param fullCharge
     * @param currentCharge
     * @param operational
     * @param power
     * @return
     */
    private void addScooter(int exclusiveNumber, double fullCharge, double currentCharge, int operational, double power) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?) }");

            callStmt.setInt(1, exclusiveNumber);
            callStmt.setDouble(2, fullCharge);
            callStmt.setDouble(3, currentCharge);
            callStmt.setInt(4, operational);
            callStmt.setDouble(5, power);
            callStmt.setString(6, "");

            callStmt.execute();
        } catch (SQLException ex10) {
            ex10.printStackTrace();
        } finally {
            closeAll();
        }
    }

}
