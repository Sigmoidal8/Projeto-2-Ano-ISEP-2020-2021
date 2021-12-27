/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lapr.project.model.Client;
import lapr.project.model.ClientRegister;
import lapr.project.model.Courier;
import lapr.project.model.CourierList;
import lapr.project.model.Delivery;
import lapr.project.model.DeliveryList;
import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ProductList;
import lapr.project.model.Scooter;
import lapr.project.model.ScootersList;


public class FullFillLists extends DataHandler {
    
    /**
     * Runs an SQL script to get all the Pharmacys from the data base
     * and adds them to the Pharmacy List from the platform
     * @param plat
     */
    public void fullfillPharmacyList(Platform plat) {

        Statement stmt = null;
        Pharmacy p;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Pharmacy ");
            PharmacyList phl = plat.getPharmacyList();
            while (rs.next()) {
                p = plat.getPdb().getPharmacy(rs.getString(1));
                phl.getPharmacyList().add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the Scooters from the data base
     * and adds them to the Scooters List from the platform
     * @param plat
     */
    public void fullfillScootersList(Platform plat) {

        Statement stmt = null;
        Pharmacy p;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Scooter ");
            ScootersList sctl = plat.getScootersList();
            while (rs.next()) {
                int exclusiveNumber = rs.getInt(1);
                double fullCharge = rs.getDouble(2);
                double currentCharge = rs.getDouble(3);
                int operational = rs.getInt(4);
                double power = rs.getDouble(5);
                if (rs.getString(6) != null) {
                    p = plat.getPharmacyList().getPharmacy(rs.getString(6));
                } else {
                    p = null;
                }
                if (p == null) {
                    sctl.getScooterList().add(new Scooter(exclusiveNumber, fullCharge, power, operational));
                } else {
                    Scooter s = new Scooter(exclusiveNumber, fullCharge, currentCharge, power, operational);
                    sctl.getScooterList().add(s);
                    p.getAsl().addScooter(s);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the Couriers from the data base
     * and adds them to the Courier List from the platform
     * @param plat
     */
    public void fullfillCourierList(Platform plat) {
        Statement stmt = null;
        CourierList coul = plat.getCourierList();
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courier ");
            while (rs.next()) {
                String nif = rs.getString(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String socialSecurityNumber = rs.getString(4);
                String password = rs.getString(5);
                Courier c = new Courier(name, nif, socialSecurityNumber, email, password);
                coul.addCourier(c);
                coul.registerCourierWithRole(c, plat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the Product from the data base
     * and adds them to the Product List from the platform
     * @param plat
     */
    public void fullfillProductList(Platform plat) {
        Statement stmt = null;
        ProductList pl = plat.getProductList();
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product ");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                float weight = rs.getFloat(4);
                pl.addProduct(new Product(id, name, price, weight));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the information about the products in each pharmacy from the data base
     * and adds them to each Pharmacy's product list 
     * @param plat
     */
    public void fullfillPharmacyProductList(Platform plat){
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Pharmacy_Product ");
            while (rs.next()) {
                String pharmacyId = rs.getString(1);
                String productId = rs.getString(2);
                Pharmacy p = plat.getPharmacyList().getPharmacy(pharmacyId);
                Product prod = plat.getProductList().getProduct(productId);
                p.getProductList().addProduct(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the Clients from the data base
     * and adds them to the Client List from the platform
     * @param plat
     */
    public void fullFillClientList(Platform plat) {
        Statement stmt = null;
        Client c;
        ClientDB cdb = new ClientDB();
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Client ");
            ClientRegister cr = plat.getClientRegister();
            while (rs.next()) {
                c = cdb.getClient(rs.getString(1));
                cr.addClient(c);
                cr.registerClientWithRole(c, plat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the Deliverys from the data base
     * and adds them to the Delivery List from the platform
     * @param plat
     */
    public void fullFillDeliveryList(Platform plat) {
        Statement stmt = null;
        Delivery c;
        DeliveryDB cdb = new DeliveryDB();
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Delivery ");
            DeliveryList cr = plat.getDeliveryList();
            while (rs.next()) {
                c = cdb.getDelivery(rs.getString(1));
                cr.addDelivery(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the Drones from the data base
     * and adds them to the Drones List from the platform
     * @param plat
     */
    public void fullFillDroneList(Platform plat) {
        Statement stmt = null;
        Drone d;
        new DroneDB();
        Pharmacy p;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Drone ");
            DronesList drl = plat.getDronesList();
            while (rs.next()) {
                int id=rs.getInt(1);
                double fullCharge=rs.getDouble(2);
                double currentCharge=rs.getDouble(3);
                double power=rs.getDouble(4);
                if(rs.getString(5)!=null){
                    p=plat.getPharmacyList().getPharmacy(rs.getString(5));
                }else{
                    p=null;
                }
                if(p==null){
                    drl.getDroneList().add(new Drone(id,fullCharge,power));
                }else{
                    d=new Drone(id,fullCharge,currentCharge,power);
                    drl.getDroneList().add(d);
                    p.getAvailableDroneList().getDroneList().add(d);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            closeAll();
        }
    }
    
     /**
     * Runs an SQL script to get all the ParkingLot from the data base
     * and adds them to the Parking Lot List from the platform
     * @param plat
     */
    public void fullFillParkingLotList(Platform plat) {
         Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ParkingLot ");
            while (rs.next()) {
                String pharmacyId = rs.getString(5);
                String parkingLotID = rs.getString(1);
                int totalSpots = rs.getInt(2);
                int chargeSpots = rs.getInt(3);
                String type = rs.getString(4);
                
                Pharmacy p = plat.getPharmacyList().getPharmacy(pharmacyId);
                ParkingLot parkingLot = new ParkingLot(parkingLotID,totalSpots,chargeSpots,type);
                p.getParkingLotList().add(parkingLot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
    }
}
