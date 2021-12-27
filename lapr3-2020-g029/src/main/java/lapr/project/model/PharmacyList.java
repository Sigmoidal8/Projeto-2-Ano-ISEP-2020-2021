/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.AdressDB;
import lapr.project.data.ParkingLotDB;
import lapr.project.data.PharmacyDB;
import lapr.project.utils.Edge;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class PharmacyList {

    /**
     * The list of pharmacies
     */
    private final ArrayList<Pharmacy> listPharmacy;

    /**
     * Creates an instance of PharmacyList initializing the listPharmacy
     */
    public PharmacyList() {
        this.listPharmacy = new ArrayList<>();
    }

    /**
     *
     * @return the list of pharmacies
     */
    public List<Pharmacy> getPharmacyList() {
        return listPharmacy;
    }

    /**
     * Return null or the pharmacy recived as a parameter
     *
     * @param pharmacyID
     * @return null or a pharmacy
     */
    public Pharmacy getPharmacy(String pharmacyID) {
        for (Pharmacy p : listPharmacy) {
            if (p.getId().equals(pharmacyID)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Return null or the pharmacy with the same address received as a parameter
     *
     * @param address
     * @return null or a pharmacy
     */
    public Pharmacy getPharmacy(Address ad) {
        for (Pharmacy p : listPharmacy) {
            if (p.getAdress().equals(ad)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Creates a new Pharmacy receiving as a parameter the designation, email,
     * password and adress
     *
     * @param designation
     * @param email
     * @param password
     * @param adress
     * @return a new pharmacy
     */
    public Pharmacy createPharmacy(String designation, String email, String password, Address adress) {
        Pharmacy pharmacy = new Pharmacy(designation, email, password, adress);

        String id = String.valueOf(listPharmacy.size() + 1);

        pharmacy.setId(id);

        return pharmacy;
    }

    /**
     * Verify if the pharmacy received by parameter is valid
     *
     * @param pharmacy
     * @return true or false
     */
    public boolean validatePharmacy(Pharmacy pharmacy) {
        if (pharmacy == null) {
            return false;
        }

        return !listPharmacy.contains(pharmacy);
    }

    /**
     * Register the pharmacy recived as a parameter
     *
     * @param pharmacy
     * @return true or false
     */
    public boolean registerPharmacy(Pharmacy pharmacy) {
        if (listPharmacy.contains(pharmacy)) {
            return false;
        }

        AplicationPOT.getInstance().getPlatform().getAdb().addAdress(pharmacy.getAdress());
        AplicationPOT.getInstance().getPlatform().getPdb().addPharmacy(pharmacy);

        addPharmacy(pharmacy);

        return true;
    }

    /**
     * Verify if the pharmacy recived as a parameter exist in the list and add
     * it
     *
     * @param pharmacy
     * @return true or false
     */
    public boolean addPharmacy(Pharmacy pharmacy) {
        if (!listPharmacy.contains(pharmacy)) {
            listPharmacy.add(pharmacy);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove the pharmacy received as a parameter
     *
     * @param pharmacy
     * @return the list of pharmacies
     */
    public boolean removePharmacy(Pharmacy pharmacy) {
        if (listPharmacy.contains(pharmacy)) {
            for (ParkingLot park : pharmacy.getParkingLotList()) {
                ParkingLotDB parkingLotDB = AplicationPOT.getInstance().getPlatform().getPldb();
                parkingLotDB.removeParkingLot(park.getId());
            }
            PharmacyDB pharmacyDB = AplicationPOT.getInstance().getPlatform().getPdb();
            pharmacyDB.removePharmacy(pharmacy.getId());

            AdressDB adressDB = AplicationPOT.getInstance().getPlatform().getAdb();
            adressDB.removeAdress(pharmacy.getAdress().getId());

            listPharmacy.remove(pharmacy);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the closest pharmacy with the product revived as a parameter
     *
     * @param listPharmacy
     * @param startingPharmacy
     * @param product
     * @param numberOfProductsTransfered
     * @return a closest pharmacy
     * @throws IOException
     */
    public Pharmacy closestPharmacyWithStock(List<Pharmacy> listPharmacy, Pharmacy startingPharmacy, Product product, int numberOfProductsTransfered) throws IOException {
        DeliveryList dl = new DeliveryList();

        List<Pharmacy> listPharmaciesWStockAdress = new ArrayList<>();
        Pharmacy closestPharmacy = null;
        Scooter chosenScooter = null;

        for (Pharmacy p : listPharmacy) {
            if (AplicationPOT.getInstance().getPlatform().getStdb().getStock(p.getId(), product.getID()) >= numberOfProductsTransfered) {
                listPharmaciesWStockAdress.add(p);
            }
        }

        double minDistance = Double.MAX_VALUE;
        for (Pharmacy ph : listPharmaciesWStockAdress) {
            double distance = Double.parseDouble(GraphAlgorithms.DistanceCalculator(startingPharmacy.getAdress().getDecimalDegree1().toString(), startingPharmacy.getAdress().getDecimalDegree2().toString(), ph.getAdress().getDecimalDegree1().toString(), ph.getAdress().getDecimalDegree2().toString()));
            if (distance < minDistance && ph != startingPharmacy) {
                minDistance = distance;
                closestPharmacy = ph;
            }
        }

        double goingDistance = Double.parseDouble(GraphAlgorithms.DistanceCalculator(startingPharmacy.getAdress().getDecimalDegree1().toString(), startingPharmacy.getAdress().getDecimalDegree2().toString(), closestPharmacy.getAdress().getDecimalDegree1().toString(), closestPharmacy.getAdress().getDecimalDegree2().toString()));
        double comingDistance = Double.parseDouble(GraphAlgorithms.DistanceCalculator(closestPharmacy.getAdress().getDecimalDegree1().toString(), closestPharmacy.getAdress().getDecimalDegree2().toString(), startingPharmacy.getAdress().getDecimalDegree1().toString(), startingPharmacy.getAdress().getDecimalDegree2().toString()));
        double totalDistance = goingDistance + comingDistance;

        double scooterMostCharge = 0.0;
        for (Scooter sc : startingPharmacy.getAsl().getAvailableScooterList()) {
            if (sc.getCurrentCharge() > scooterMostCharge) {
                scooterMostCharge = sc.getCurrentCharge();
                chosenScooter = sc;
            }
        }
        ArrayList energyTime = dl.getEnergySpentInDeliver(totalDistance, chosenScooter, startingPharmacy.getAdress(), closestPharmacy.getAdress(), product.getWeight());
        double energySpent = (double) energyTime.get(0);
        if (scooterMostCharge < energySpent) {
            return null;
        } else {
            return closestPharmacy;
        }
    }

    /**
     *
     * @param listPharmacy
     * @param clientAdress
     * @return a pharmacy
     * @throws IOException
     */
    public Pharmacy fowardDeliveryToClosestPharmacy(List<Pharmacy> listPharmacy, Address clientAdress) throws IOException {
        Pharmacy closestPharmacy = null;
        double minDistance = Double.MAX_VALUE;
        for (Pharmacy phAdress : listPharmacy) {
            double distance = Double.parseDouble(GraphAlgorithms.DistanceCalculator(clientAdress.getDecimalDegree1().toString(), clientAdress.getDecimalDegree2().toString(), phAdress.getAdress().getDecimalDegree1().toString(), phAdress.getAdress().getDecimalDegree2().toString()));
            if (distance < minDistance) {
                minDistance = distance;
                closestPharmacy = phAdress;
            }
        }
        return closestPharmacy;
    }

    /**
     * Adds the pharmacy Adress created to the graph of the Platform
     *
     * @param pharmacy to add
     * @throws IOException
     */
    public boolean addPharmacyAdressToGraph(Pharmacy pharmacy) {
        try {
            Graph scooterMap = AplicationPOT.getInstance().getPlatform().getScooterMap();
            Map<String, List<String>> restrictionsScooter = AplicationPOT.getInstance().getPlatform().getListRestrictionsScooter();
            scooterMap.insertVertex(pharmacy.getAdress());
            Iterable<Address> it = scooterMap.vertices();
            for (Address ad : it) {
                if (!pharmacy.getAdress().equals(ad)) {
                    if (restrictionsScooter.get(ad.getId()) != null) {
                        if (!restrictionsScooter.get(ad.getId()).contains(pharmacy.getAdress().getId())) {
                            scooterMap.insertEdge(ad, pharmacy.getAdress(), "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString(), pharmacy.getAdress().getDecimalDegree1().toString(), pharmacy.getAdress().getDecimalDegree2().toString())));
                        }
                    } else {
                        scooterMap.insertEdge(ad, pharmacy.getAdress(), "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString(), pharmacy.getAdress().getDecimalDegree1().toString(), pharmacy.getAdress().getDecimalDegree2().toString())));
                    }
                    if (restrictionsScooter.get(pharmacy.getAdress().getId()) != null) {
                        if (!restrictionsScooter.get(pharmacy.getAdress().getId()).contains(ad.getId())) {
                            scooterMap.insertEdge(pharmacy.getAdress(), ad, "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(pharmacy.getAdress().getDecimalDegree1().toString(), pharmacy.getAdress().getDecimalDegree2().toString(), ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString())));
                        }
                    } else {
                        scooterMap.insertEdge(pharmacy.getAdress(), ad, "", Double.parseDouble(GraphAlgorithms.DistanceCalculator(pharmacy.getAdress().getDecimalDegree1().toString(), pharmacy.getAdress().getDecimalDegree2().toString(), ad.getDecimalDegree1().toString(), ad.getDecimalDegree2().toString())));
                    }
                }
            }

            Graph droneMap = AplicationPOT.getInstance().getPlatform().getDroneMap();
            Map<String, List<String>> restrictionsDrone = AplicationPOT.getInstance().getPlatform().getListRestrictionsDrone();
            droneMap.insertVertex(pharmacy.getAdress());
            Iterable<Address> it2 = droneMap.vertices();
            for (Address ad : it2) {
                if (!pharmacy.getAdress().equals(ad)) {
                    if (restrictionsDrone.get(ad.getId()) != null) {
                        if (!restrictionsDrone.get(ad.getId()).contains(pharmacy.getAdress().getId())) {
                            droneMap.insertEdge(ad, pharmacy.getAdress(), "", GraphAlgorithms.calcularDistancia(ad.getDecimalDegree1(), ad.getDecimalDegree2(), pharmacy.getAdress().getDecimalDegree1(), pharmacy.getAdress().getDecimalDegree2()));
                        }
                    } else {
                        droneMap.insertEdge(ad, pharmacy.getAdress(), "", GraphAlgorithms.calcularDistancia(ad.getDecimalDegree1(), ad.getDecimalDegree2(), pharmacy.getAdress().getDecimalDegree1(), pharmacy.getAdress().getDecimalDegree2()));
                    }
                    if (restrictionsDrone.get(pharmacy.getAdress().getId()) != null) {
                        if (!restrictionsDrone.get(pharmacy.getAdress().getId()).contains(ad.getId())) {
                            droneMap.insertEdge(pharmacy.getAdress(), ad, "", GraphAlgorithms.calcularDistancia(pharmacy.getAdress().getDecimalDegree1(), pharmacy.getAdress().getDecimalDegree2(), ad.getDecimalDegree1(), ad.getDecimalDegree2()));
                        }
                    } else {
                        droneMap.insertEdge(pharmacy.getAdress(), ad, "", GraphAlgorithms.calcularDistancia(pharmacy.getAdress().getDecimalDegree1(), pharmacy.getAdress().getDecimalDegree2(), ad.getDecimalDegree1(), ad.getDecimalDegree2()));
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Adds the pharmacy Address created to the graph of the Platform
     *
     * @param pharmacy to add
     * @throws IOException
     */
    public boolean removePharmacyAdressToGraph(Pharmacy pharmacy) {
        Graph scooterMap = AplicationPOT.getInstance().getPlatform().getScooterMap();
        return scooterMap.removeVertex(pharmacy.getAdress());
    }
}
