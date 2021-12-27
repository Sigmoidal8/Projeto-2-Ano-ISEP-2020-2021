/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lapr.project.controller.AplicationPOT;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;


public class InvoiceDB extends DataHandler {

    /**
     * Returns an Invoice from the Data Base with the same id passed by parameter.
     * @param id
     * @return 
     */
    public Invoice getInvoice(String id) {

        CallableStatement callStmt = null;
        Statement stmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setString(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String invoiceID = rSet.getString("ID");
                Date dateInvoice = rSet.getDate("DATE_INVOICE");
                double price = rSet.getDouble("PRICE");
                String clientID = rSet.getString("CLIENTID");

                closeAll();
                
                callStmt = getConnection().prepareCall("{ ? = call getClient(?) }");
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);

                callStmt.setString(2, clientID);
                callStmt.execute();

                ResultSet rSet2 = (ResultSet) callStmt.getObject(1);

                if (rSet2.next()) {
                    String clientName = rSet2.getString(2);
                    String clientPassword = rSet2.getString(3);
                    String clientEmail = rSet2.getString(4);
                    String clientNif = rSet2.getString(5);
                    double clientCredits = rSet2.getDouble(6);
                    String adressID = rSet2.getString(7);
                    
                    closeAll();
                    
                    callStmt = getConnection().prepareCall("{ ? = call getAdress(?) }");

                    callStmt.registerOutParameter(1, OracleTypes.CURSOR);

                    callStmt.setString(2, adressID);

                    callStmt.execute();

                    ResultSet rSet3 = (ResultSet) callStmt.getObject(1);

                    if (rSet3.next()) {
                        String adressStreet = rSet3.getString(2);
                        int doorNumber = rSet3.getInt(3);
                        String adressPostalCode = rSet3.getString(4);
                        String adressLocality = rSet3.getString(5);
                        double adressDecimalDegree1 = rSet3.getDouble(6);
                        double adressDecimalDegree2 = rSet3.getDouble(7);
                        Invoice inv = new Invoice(invoiceID, dateInvoice, price, (new Client(clientID, clientName, clientPassword, clientEmail, clientNif, clientCredits, new Address(adressID, adressStreet, doorNumber, adressPostalCode, adressLocality, adressDecimalDegree1, adressDecimalDegree2))));
                        
                        stmt = getConnection().createStatement();
                        ResultSet rSet4 = stmt.executeQuery("SELECT * FROM INVOICE_PRODUCT where INVOICEID = '"+invoiceID+"'");
                        Map<Product, Integer> maap = new HashMap<>();
                        
                        while(rSet4.next()){
                            String productID = rSet4.getString("PRODUCTID");
                            int quantity = rSet4.getInt("QUANTITY");
                            Product prod = AplicationPOT.getInstance().getPlatform().getProductList().getProduct(productID);
                            maap.put(prod,quantity);
                        }
                        inv.setMp(maap);
                        return inv;
                        
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Invoice with ID:" + id);
    }

    /**
     * Adds an Invoice to the Data Base
     * @param invoice 
     */
    public void addInvoice(Invoice invoice) {
        addInvoice(invoice.getId(), invoice.getDateinvoice(), invoice.getPrice(), invoice.getClient());
    }

    /**
     * Adds an Invoice to the Data Base with the id, dateinvoice, price and client passed by parameter
     * @param id
     * @param dateinvoice
     * @param price
     * @param client 
     */
    private void addInvoice(String id, Date dateinvoice, double price, Client client) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addInvoice(?,?,?,?) }");

            callStmt.setString(1, id);
            callStmt.setDate(2, new java.sql.Date(dateinvoice.getTime()));
            callStmt.setDouble(3, price);
            callStmt.setString(4, client.getId());

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

    /**
     * Adds an InvoiceProducs to the Data Base with the invoiceID, productID and quantity passed by parameter
     * @param invoiceID
     * @param productID
     * @param quantity 
     */
    public void addInvoiceProduct(String invoiceID, String productID, int quantity) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addInvoice_Product(?,?,?) }");

            callStmt.setString(1, invoiceID);
            callStmt.setString(2, productID);
            callStmt.setInt(3, quantity);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

}
