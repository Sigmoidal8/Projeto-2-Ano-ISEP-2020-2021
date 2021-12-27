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
import lapr.project.model.Client;
import oracle.jdbc.OracleTypes;


public class ClientDB extends DataHandler {

    /**
     * Returns a Client from the Data Base with the same id passed by parameter.
     * @param id
     * @return 
     */
    public Client getClient(String id) {

        CallableStatement callStmt3 = null;
        CallableStatement callStmt4 = null;
        try {
            callStmt3 = getConnection().prepareCall("{ ? = call getClient(?) }");

            callStmt3.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt3.setString(2, id);

            callStmt3.execute();

            ResultSet rSet = (ResultSet) callStmt3.getObject(1);

            if (rSet.next()) {
                String clientID = rSet.getString(1);
                String clientName = rSet.getString(2);
                String clientPassword = rSet.getString(3);
                String clientEmail = rSet.getString(4);
                String clientNif = rSet.getString(5);
                double clientCredits = rSet.getDouble(6);
                String adressID = rSet.getString(7);
                callStmt3.close();
                
                callStmt4 = getConnection().prepareCall("{ ? = call getAdress(?) }");

                callStmt4.registerOutParameter(1, OracleTypes.CURSOR);

                callStmt4.setString(2, adressID);

                callStmt4.execute();

                ResultSet rSet2 = (ResultSet) callStmt4.getObject(1);

                if (rSet2.next()) {
                    String adressStreet = rSet2.getString(2);
                    int doorNumber = rSet2.getInt(3);
                    String adressPostalCode = rSet2.getString(4);
                    String adressLocality = rSet2.getString(5);
                    double adressDecimalDegree1 = rSet2.getDouble(6);
                    double adressDecimalDegree2 = rSet2.getDouble(7);

                    return new Client(clientID,clientName,clientPassword,clientEmail,clientNif, clientCredits, new Address(adressID,adressStreet,doorNumber ,adressPostalCode,adressLocality,adressDecimalDegree1,adressDecimalDegree2));
                }
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with ID:" + id);
    }
    
    /**
     * Adds a Client to the Data Base
     * @param client 
     */
     public void addClient(Client client) {
        addClient(client.getId(), client.getName(), client.getPassword(), client.getEmail(), client.getNIF(), client.getCredits(), client.getAdress());
    }

    /**
     * Adds a Client to the Data Base with the id, name, password, email, nif, credits and adress passed by parameter
     * @param id
     * @param name
     * @param password
     * @param email
     * @param nif
     * @param credits
     * @param adress 
     */ 
    private void addClient(String id, String name, String password, String email, int nif, double credits, Address adress) {

        CallableStatement callStmt3 = null;
        try {
            callStmt3 = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?) }");

            callStmt3.setString("ID", id);
            callStmt3.setString("NAME", name);
            callStmt3.setString("PASSWORD", password);
            callStmt3.setString("EMAIL", email);
            callStmt3.setString("NIF", String.format("%d", nif));
            callStmt3.setDouble("CREDITS", credits);
            callStmt3.setString("ADRESSID", adress.getId());

            callStmt3.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
    }
    /**
     * Removes a Client from the Data Base with the same id passed by parameter.
     * @param id 
     */
    public void removeClient(String id) {

        CallableStatement callStmt3 = null;
        try {
            openConnection();
            callStmt3 = getConnection().prepareCall("{ call removeClient(?) }");

            callStmt3.setString(1, id);

            callStmt3.execute();

        } catch (SQLException e3) {
            e3.printStackTrace();
        }finally {
            closeAll();
        }
    }
    
    /**
     * Updates the Client passed by parameter in the data base
     * @param client 
     */
    public void updateClient(Client client) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call updateClient(?,?,?,?,?,?,?) }");

            callStmt.setString("ID", client.getId());
            callStmt.setString("NAME", client.getName());
            callStmt.setString("PASSWORD", client.getPassword());
            callStmt.setString("EMAIL", client.getEmail());
            callStmt.setString("NIF", String.format("%d", client.getNIF()));
            callStmt.setDouble("CREDITS", client.getCredits());
            callStmt.setString("ADRESSID", client.getAdress().getId());

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

    }

}
