/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.ui.autorization.AutorizationFacade;
import lapr.project.data.AdressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.CourierDB;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DroneDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyProductDB;
import lapr.project.data.ProductDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.StockDB;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class Platform {

    /**
     * The registry of clients
     */
    private final ClientRegister rclient;

    /**
     * The Autorization
     */
    private final AutorizationFacade autorization;
    /**
     * The list of pharmacies
     */
    private final PharmacyList pl;
    /**
     * The list of scooters
     */
    private final ScootersList sl;
    /**
     * The list of scooters available
     */
    private final AvailableScooterList asl;
    /**
     * The list of couriers
     */
    private final CourierList cl;
    /**
     * The list of Products
     */
    private final ProductList prol;
    /**
     * The list of deliveries 
     */
    private final DeliveryList dl;
    /**
     * The list of drones
     */
    private final DronesList drl;
    /**
     * The adress database
     */
    private AdressDB adb;
    /**
     * The client database
     */
    private ClientDB cdb;
    /**
     * The courier database
     */
    private CourierDB coudb;
    /**
     * The delivery database
     */
    private DeliveryDB ddb;
    /**
     * The invoice database
     */
    private InvoiceDB idb;
    /**
     * The parking lot database
     */
    private ParkingLotDB pldb;
    /**
     * The pharmacy database
     */
    private PharmacyDB pdb;
    /**
     * The pharmacyProduct database
     */
    private PharmacyProductDB ppdb;
    /**
     * The product database
     */
    private ProductDB prdb;
    /**
     * The scooter database
     */
    private ScooterDB sdb;
    /**
     * The stock database
     */
    private StockDB stdb;
    /**
     * The drone database
     */
    private DroneDB drdb;
    /**
     * The drone's graph
     */
    private final Graph droneMap;
    /**
     * The scooter's graph
     */
    private final Graph scooterMap;
    /**
     * The map containing the
     * restrictions for the
     * drone
     */
    private final Map<String, List<String>> listRestrictionsDrone;
    /**
     * The map containing the
     * restrictions for the
     * scooter
     */
    private final Map<String, List<String>> listRestrictionsScooter;
    
    /**
     * Inicialize the platform
     */
    public Platform() {
        autorization = new AutorizationFacade();
        this.rclient = new ClientRegister();
        this.sl = new ScootersList();
        this.pl = new PharmacyList();
        this.asl = new AvailableScooterList();
        this.cl = new CourierList();
        this.prol = new ProductList();
        this.dl = new DeliveryList();
        this.adb = new AdressDB();
        this.cdb = new ClientDB();
        this.coudb = new CourierDB();
        this.ddb = new DeliveryDB();
        this.idb = new InvoiceDB();
        this.pldb = new ParkingLotDB();
        this.pdb = new PharmacyDB();
        this.ppdb = new PharmacyProductDB();
        this.prdb = new ProductDB();
        this.sdb = new ScooterDB();
        this.stdb = new StockDB();
        this.drdb = new DroneDB();
        this.drl = new DronesList();
        this.droneMap = new Graph(true);
        this.scooterMap = new Graph(true);
        this.listRestrictionsDrone = new HashMap<>();
        this.listRestrictionsScooter = new HashMap<>();
    }

    /**
     * Returns the autorizationfacade
     *
     * @return the autorization
     */
    public AutorizationFacade getAutorizationFacade() {
        return this.autorization;
    }

    /**
     * @return the client registry
     */
    public ClientRegister getClientRegister() {
        return rclient;
    }

    /**
     * @return the Delivery List
     */
    public DeliveryList getDeliveryList() {
        return dl;
    }
    /**
     * 
     * @return the sccoterList
     */
    public ScootersList getScootersList() {
        return sl;
    }
    /**
     * 
     * @return the CourierList
     */
    public CourierList getCourierList() {
        return cl;
    }
    /**
     * 
     * @return the AvailableScooterList
     */
    public AvailableScooterList getAvailableScooterList() {
        return asl;
    }
    /**
     * 
     * @return the PharmacyList
     */
    public PharmacyList getPharmacyList() {
        return pl;
    }
    /**
     * 
     * @return the ProductList
     */
    public ProductList getProductList() {
        return prol;
    }
    /**
     * 
     * @return the Adress DataBase
     */
    public AdressDB getAdb() {
        return adb;
    }
    /**
     * 
     * @return Client DataBase
     */
    public ClientDB getCdb() {
        return cdb;
    }
    /**
     * 
     * @return the Courier DataBase
     */
    public CourierDB getCoudb() {
        return coudb;
    }
    /**
     * 
     * @return Delivery DataBase
     */
    public DeliveryDB getDdb() {
        return ddb;
    }
    /**
     * 
     * @return the Invoice DataBase
     */
    public InvoiceDB getIdb() {
        return idb;
    }
    /**
     * 
     * @return the Parking Lot DataBase
     */
    public ParkingLotDB getPldb() {
        return pldb;
    }
    /**
     * 
     * @return the Pharmacy DataBase
     */
    public PharmacyDB getPdb() {
        return pdb;
    }
    /**
     * 
     * @return the PharmacyProduct DataBase
     */
    public PharmacyProductDB getPpdb() {
        return ppdb;
    }
    /**
     * 
     * @return the Product DataBase
     */
    public ProductDB getPrdb() {
        return prdb;
    }
    /**
     * 
     * @return the Scooter DataBase
     */
    public ScooterDB getSdb() {
        return sdb;
    }
    /**
     * 
     * @return the Stock DataBase
     */
    public StockDB getStdb() {
        return stdb;
    }
    /**
     * 
     * @return the Drone DataBase
     */
    public DroneDB getDrdb() {
        return drdb;
    }
    /**
     * 
     * @return the DronesList
     */
    public DronesList getDronesList() {
        return drl;
    }
    /**
     * 
     * @return the drone's graph
     */
    public Graph getDroneMap() {
        return droneMap;
    }
    /**
     * 
     * @return scooter's graph
     */
    public Graph getScooterMap() {
        return scooterMap;
    }
    /**
     * 
     * @return the map containing the
     * drone's restrictions
     */
    public Map<String, List<String>> getListRestrictionsDrone() {
        return listRestrictionsDrone;
    }
    /**
     * 
     *@return the map containing the
     * scooters's restrictions
     */
    public Map<String, List<String>> getListRestrictionsScooter() {
        return listRestrictionsScooter;
    }
    /**
     * Changes the adress database
     * to the one received by parameter
     * 
     * @param adb 
     */
    public void setAdb(AdressDB adb) {
        this.adb = adb;
    }
    /**
     * Changes the client database
     * to the one received by parameter
     * 
     * @param cdb 
     */
    public void setCdb(ClientDB cdb) {
        this.cdb = cdb;
    }
    /**
     * Changes the courier database
     * to the one received by parameter
     * 
     * @param coudb 
     */
    public void setCoudb(CourierDB coudb) {
        this.coudb = coudb;
    }
    /**
     * Changes the delivery database
     * to the one received by parameter
     * 
     * @param ddb 
     */
    public void setDdb(DeliveryDB ddb) {
        this.ddb = ddb;
    }
    /**
     * Changes the invoice database
     * to the one received by parameter
     * 
     * @param idb 
     */
    public void setIdb(InvoiceDB idb) {
        this.idb = idb;
    }
    /**
     * Changes the parking lot database
     * to the one received by parameter
     * 
     * @param pldb 
     */
    public void setPldb(ParkingLotDB pldb) {
        this.pldb = pldb;
    }
    /**
     * Changes the pharmacy database
     * to the one received by parameter
     * 
     * @param pdb 
     */
    public void setPdb(PharmacyDB pdb) {
        this.pdb = pdb;
    }/**
     * Changes the PharmacyProduct database
     * to the one received by parameter
     * 
     * @param ppdb 
     */
    public void setPpdb(PharmacyProductDB ppdb) {
        this.ppdb = ppdb;
    }
    /**
     * Changes the Product database
     * to the one received by parameter
     * 
     * @param prdb 
     */
    public void setPrdb(ProductDB prdb) {
        this.prdb = prdb;
    }
    /**
     * Changes the scooter database
     * to the one received by parameter
     * 
     * @param sdb 
     */
    public void setSdb(ScooterDB sdb) {
        this.sdb = sdb;
    }
    /**
     * Changes the stock database
     * to the one received by parameter
     * 
     * @param stdb 
     */
    public void setStdb(StockDB stdb) {
        this.stdb = stdb;
    }
    /**
     * Changes the drone database
     * to the one received by parameter
     * 
     * @param drdb 
     */
    public void setDrdb(DroneDB drdb) {
        this.drdb = drdb;
    }
    
    /**
     * Method that inicialize
     * the drone graph
     */
    public void initializeDroneGraph() {
        Graph droneMap = this.getDroneMap();
        Map<String, List<String>> restrictions = this.getListRestrictionsDrone();
        for (Pharmacy p : this.getPharmacyList().getPharmacyList()) {
            droneMap.insertVertex(p.getAdress());
        }
        for (Client c : this.getClientRegister().getClientList()) {
            droneMap.insertVertex(c.getAdress());
        }
        Iterable<Address> it = droneMap.vertices();
        for (Address ad : it) {
            for (Address adress : it) {
                if (!adress.equals(ad)) {
                    if (restrictions.get(ad.getId()) != null) {
                        if (!restrictions.get(ad.getId()).contains(adress.getId())) {
                            droneMap.insertEdge(ad, adress, "", GraphAlgorithms.calcularDistancia(ad.getDecimalDegree1(), ad.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
                        }
                    } else {
                        droneMap.insertEdge(ad, adress, "", GraphAlgorithms.calcularDistancia(ad.getDecimalDegree1(), ad.getDecimalDegree2(), adress.getDecimalDegree1(), adress.getDecimalDegree2()));
                    }
                }
            }
        }
    }
    /**
     * Method that inicialize
     * the scooter graph
     */
    public void initializeScooterGraph() throws IOException {
        Graph scooterMap = this.getScooterMap();
        Map<String, List<String>> restrictions = this.getListRestrictionsScooter();
        for (Pharmacy p : this.getPharmacyList().getPharmacyList()) {
            scooterMap.insertVertex(p.getAdress());
        }
        for (Client c : this.getClientRegister().getClientList()) {
            scooterMap.insertVertex(c.getAdress());
        }
        Iterable<Address> it = scooterMap.vertices();
        for (Address ad : it) {
            for (Address adress : it) {
                if (!adress.equals(ad)) {
                    if (restrictions.get(ad.getId()) != null) {
                        if (!restrictions.get(ad.getId()).contains(adress.getId())) {
                            scooterMap.insertEdge(ad, adress, "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
                        }
                    } else {
                        scooterMap.insertEdge(ad, adress, "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString(), adress.getDecimalDegree1().toString(), adress.getDecimalDegree2().toString())));
                    }
                }
            }
        }
    }

}
