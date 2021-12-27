/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.controller.AplicationPOT;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import oracle.jdbc.OracleTypes;


public class DroneDB extends DataHandler {
    
        /**
     * Returns a Drone from the Data Base with the same id passed by parameter.
     *
     * @param id
     * @return
     */
    public Drone getDrone(int id) {

        CallableStatement callStmt5 = null;
        try {
            callStmt5 = getConnection().prepareCall("{ ? = call getDrone(?) }");

            callStmt5.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt5.setInt(2, id);

            callStmt5.execute();

            ResultSet rSet = (ResultSet) callStmt5.getObject(1);

            if (rSet.next()) {
                int droneID = rSet.getInt("ID");
                double droneFullCharge = rSet.getDouble("FULLCHARGE");
                double droneCurrentCharge = rSet.getDouble("CURRENTCHARGE");
                double dronePower = rSet.getDouble("POWER");


                return new Drone(droneID, droneFullCharge, droneCurrentCharge, dronePower);
            }
            
        } catch (SQLException e5) {
            e5.printStackTrace();
        }finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Drone with ID:" + id);
    }
    
    /**
     * Removes a Drone from the Data Base with the same id passed by parameter.
     *
     * @param id
     */
    public void removeDrone(int id) {

        CallableStatement callStmt5 = null;
        try {
            callStmt5 = getConnection().prepareCall("{ call removeDrone(?) }");

            callStmt5.setInt(1, id);

            callStmt5.execute();

        } catch (SQLException e5) {
            e5.printStackTrace();
        }finally {
            closeAll();
        }

    }
    
    /**
     * Updates the Drone passed by parameter in the data base
     *
     * @param drone
     */
    public void updateDrone(Drone drone) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call updateDrone(?,?,?,?,?) }");

            callStmt.setInt("ID", drone.getID());
            callStmt.setDouble("FULLCHARGE", drone.getFullCharge());
            callStmt.setDouble("CURRENTCHARGE", drone.getCurrentCharge());
            callStmt.setDouble("POWER", drone.getPower());
            PharmacyList pl=AplicationPOT.getInstance().getPlatform().getPharmacyList();
            boolean noPharmacy=true;
            Pharmacy dronePharmacy=null;
            for(Pharmacy p: pl.getPharmacyList()){
                if(p.getAvailableDroneList().getDroneList().contains(drone)){
                    noPharmacy=false;
                    dronePharmacy=p;
                }
            }
            callStmt.setString("PHARMACYID", dronePharmacy.getId());
            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

    }
    
    /**
     * Adds a Drone to the Data Base with the exclusiveNumber,fullCharge,currentCharge,operational and power passed by parameter.
     *
     * @param id
     * @param fullCharge
     * @param currentCharge
     * @param power
     */
    private void addDrone(int id, double fullCharge, double currentCharge, double power) {

        CallableStatement callStmt5 = null;
        try {
            callStmt5 = getConnection().prepareCall("{ call addDrone(?,?,?,?,?) }");

            callStmt5.setInt(1, id);
            callStmt5.setDouble(2, fullCharge);
            callStmt5.setDouble(3, currentCharge);
            callStmt5.setDouble(4, power);
            callStmt5.setString(5, "");

            callStmt5.execute();

        } catch (SQLException e5) {
            e5.printStackTrace();
        }finally {
            closeAll();
        }
    }
    
    /**
     * Adds a Drone to the Data Base with the drone passed by parameter.
     *
     * @param d
     */
    public void addDrone(Drone d) {

        addDrone(d.getID(), d.getFullCharge(), d.getCurrentCharge(), d.getPower());
    }

}
