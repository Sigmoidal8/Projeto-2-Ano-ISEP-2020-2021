/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.ParkingLot;
import oracle.jdbc.OracleTypes;


public class ParkingLotDB extends DataHandler{
    
        private static final String CHARGE_SPOT = "CHARGESPOTS";
        private static final String LOCALSPOTS = "LOCALSPOTS";
        
    /**
     * Returns a ParkingLot from the Data Base with the same id passed by parameter.
     *
     * @param id
     * @return
     */
    public ParkingLot getParkingLot(String id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingLot(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String parkingLotId = rSet.getString("ID");
                int parkingLotTotalSpots = rSet.getInt(LOCALSPOTS);
                int parkingLotChargeSpots = rSet.getInt(CHARGE_SPOT);
                String type = rSet.getString("TYPE");

                return new ParkingLot(parkingLotId, parkingLotTotalSpots, parkingLotChargeSpots,type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        throw new IllegalArgumentException("No ParkingLot with ID:" + id);
    }
    
    /**
     * Adds a ParkingLot to the Data Base with the id,totalSpots,chargeSpots and type from the parkingLot and the pharmacyID passed by parameter.
     *
     * @param pl
     * @param pharmacyID
     */
    public void addParkingLot(ParkingLot pl, String pharmacyID) {
        addParkingLot(pl.getId(), pl.getTotalSpots(), pl.getChargeSpots(), pl.getType(), pharmacyID);
    }
    
    /**
     * Adds a ParkingLot to the Data Base with the id,totalSpots,chargeSpots,type and pharmacyID passed by parameter
     *
     * @param id
     * @param totalSpots
     * @param chargeSpots
     * @param type
     * @param pharmacyID
     * @return
     */
    private void addParkingLot(String id, int totalSpots, int chargeSpots, String type, String pharmacyID) {

        CallableStatement callStmt6 = null;
        try {
            callStmt6 = getConnection().prepareCall("{ call addParkingLot(?,?,?,?,?) }");

            callStmt6.setString("ID", id);
            callStmt6.setInt(LOCALSPOTS, totalSpots);
            callStmt6.setInt(CHARGE_SPOT, chargeSpots);
            callStmt6.setString("TYPE", type);
            callStmt6.setString("PHARMACYID", pharmacyID);

            callStmt6.execute();

        } catch (SQLException e6) {
            e6.printStackTrace();
        }finally {
            closeAll();
        }
    }
    
    /**
     * Removes a ParkingLot from the Data Base with the same id passed by parameter.
     *
     * @param id
     */
    public void removeParkingLot(String id) {

        CallableStatement callStmt6 = null;
        try {
            callStmt6 = getConnection().prepareCall("{ call removeParkingLot(?) }");

            callStmt6.setString(1, id);

            callStmt6.execute();

        } catch (SQLException e6) {
            e6.printStackTrace();
        }finally {
            closeAll();
        }

    }
    
    /**
     * Updates the ParkingLot to the pharmacy with the id passed by parameter in the data base
     *
     * @param pl
     * @param pharmacyID
     */
    public void updateParkingLot(ParkingLot pl, String pharmacyID) {

        CallableStatement callStmt6 = null;
        try {
            callStmt6 = getConnection().prepareCall("{ call updateParkingLot(?,?,?,?,?) }");

            callStmt6.setString("ID", pl.getId());
            callStmt6.setInt("LOCALSPOTS", pl.getTotalSpots());
            callStmt6.setInt(CHARGE_SPOT, pl.getChargeSpots());
            callStmt6.setString("TYPE", pl.getType());
            callStmt6.setString("PHARMACYID", pharmacyID);
            
            callStmt6.execute();
            
        } catch (SQLException e6) {
            e6.printStackTrace();
        }finally {
            closeAll();
        }
    }
    
}
