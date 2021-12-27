/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import lapr.project.controller.AplicationPOT;
import lapr.project.model.Delivery;
import oracle.jdbc.OracleTypes;


public class DeliveryDB extends DataHandler {

    /**
     * Returns a Delivery from the Data Base with the same id passed by parameter.
     * @param id
     * @return 
     */
    public Delivery getDelivery(String id) {

        CallableStatement callStmt4 = null;
        try {
            callStmt4 = getConnection().prepareCall("{ ? = call getDelivery(?) }");

            callStmt4.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt4.setString(2, id);

            callStmt4.execute();

            ResultSet rSet = (ResultSet) callStmt4.getObject(1);

            if (rSet.next()) {
                String deliveryID = rSet.getString(1);
                Date deliveryDate = rSet.getDate(2);
                double deliveryTotalweigth = rSet.getDouble(3);
                String deliveryInvoice = rSet.getString(4);
                String pharmacyID = rSet.getString(5);

                return new Delivery(deliveryID, deliveryDate, deliveryTotalweigth, AplicationPOT.getInstance().getPlatform().getIdb().getInvoice(deliveryInvoice), AplicationPOT.getInstance().getPlatform().getPdb().getPharmacy(pharmacyID));
            }

        } catch (SQLException e4) {
            e4.printStackTrace();
        }finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Delivery with Id:" + id);
    }

    /**
     * Adds a Delivery to the Data Base
     * @param delivery 
     */
    public void addDelivery(Delivery delivery) {
        addDelivery(delivery.getId(), delivery.getDateDelivery(), delivery.getTotalWeight(), delivery.getInvoice().getId(), delivery.getPharmacy().getId());
    }

    /**
     * Adds a Delivery to the Data Base with the id, date, weight, invoiceid, pharmacyID
     * @param id
     * @param date
     * @param weigth
     * @param invoiceid
     * @param pharmacyID 
     */
    private void addDelivery(String id, Date date, double weigth, String invoiceid, String pharmacyID) {

        CallableStatement callStmt4 = null;
        try {
            callStmt4 = getConnection().prepareCall("{ call addDelivery(?,?,?,?,?) }");

            callStmt4.setString("ID", id);
            callStmt4.setDate("DATE2",new java.sql.Date(date.getTime()));
            callStmt4.setDouble("TOTALWEIGHT", weigth);
            callStmt4.setString("INVOICEID", invoiceid);
            callStmt4.setString("PHARMACYID", pharmacyID);

            callStmt4.execute();

            closeAll();
        } catch (SQLException e4) {
            e4.printStackTrace();
        }finally {
            closeAll();
        }
    }

    /**
     * Removes a Delivery from the Data Base with the same id passed by parameter.
     * @param id 
     */
    public void removeDelivery(String id) {

        CallableStatement callStmt4 = null;
        try {
            callStmt4 = getConnection().prepareCall("{ call removeDelivery(?) }");

            callStmt4.setString(1, id);

            callStmt4.execute();

        } catch (SQLException e4) {
            e4.printStackTrace();
        }finally {
            closeAll();
        }

    }

    /**
     * Updates the Delivery passed by parameter in the data base
     * @param delivery 
     */
    public void updateDelivery(Delivery delivery) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call updateDelivery(?,?,?,?,?) }");

            callStmt.setString("ID", delivery.getId());
            callStmt.setDate("DATE2", new java.sql.Date(delivery.getDateDelivery().getTime()));
            callStmt.setDouble("TOTALWEIGHT", delivery.getTotalWeight());
            callStmt.setString("INVOICEID", delivery.getInvoice().getId());
            callStmt.setString("PHARMACYID", delivery.getPharmacy().getId());

            callStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {           
                closeAll();   
        }
    }
}
