/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.mail.MessagingException;
import lapr.project.model.Address;
import lapr.project.model.AvailableScooterList;
import lapr.project.model.Client;
import lapr.project.model.Courier;
import lapr.project.model.CourierList;
import lapr.project.model.Delivery;
import lapr.project.model.DeliveryList;
import lapr.project.model.Drone;
import lapr.project.model.DronesList;
import lapr.project.model.Invoice;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import lapr.project.utils.GraphAlgorithms;
import lapr.project.utils.MailUtil;

public class MakeDeliveryController {

    /**
     * Platform of the aplication
     */
    private final Platform plat;

    /**
     * The list of deliverys
     */
    private DeliveryList dl;

    /**
     * The list of couriers
     */
    private final CourierList cl;

    /**
     * PharmacyList of the Platform
     */
    private final PharmacyList pl;

    /**
     * List of orders ready to be delivered by drone
     */
    private final ArrayList<Delivery> listTotalOrdersToDeliverScooter;

    /**
     * List of orders ready to be delivered by drone
     */
    private final ArrayList<Delivery> listTotalOrdersToDeliverDrone;

    /**
     * Creates an instance of MakeDeliveryController initializing the platformn and its PharmacyList, its DeliveryList, its CourierList its listTotalOrdersToDeliverScooter and its listTotalOrdersToDeliverDrone
     */
    public MakeDeliveryController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        dl = plat.getDeliveryList();
        cl = plat.getCourierList();
        pl = plat.getPharmacyList();
        listTotalOrdersToDeliverScooter = new ArrayList<>();
        listTotalOrdersToDeliverDrone = new ArrayList<>();
    }

    /**
     * Returns the distance traveled doing a delivery
     *
     * @return distance
     */
    public double getDistance() {
        return dl.getDistanceFromDelivery();
    }

    /**
     * Returns the DeliveryList registered in the Platform
     *
     * @return DeliveryList
     */
    public DeliveryList getDeliveryList() {
        dl = plat.getDeliveryList();
        return dl;
    }

    /**
     * Returns the AvailableScooterList of a specific pharmacy
     *
     * @param p pharmacy you want to get the scooters list from
     * @return AvailableScooterList
     */
    public AvailableScooterList getScootersList(Pharmacy p) {
        return p.getAsl();
    }

    /**
     * Returns the DronesList of a specific pharmacy
     *
     * @param p pharmacy you want to get the scooters list from
     * @return DronesList
     */
    public DronesList getDroneList(Pharmacy p) {
        return p.getAvailableDroneList();
    }

    /**
     * Returns a list of deliveries going to be performed by a scooter
     *
     * @return list of deliveries
     */
    public List<Delivery> getDeliverySelectedScooter() {
        return listTotalOrdersToDeliverScooter;
    }

    /**
     * Returns a list of deliveries going to be performed by a drone
     *
     * @return list of deliveries
     */
    public List<Delivery> getDeliverySelectedDrone() {
        return listTotalOrdersToDeliverDrone;
    }

    /**
     * Returns a courier seaching him by email
     *
     * @param email the email
     * @return Courier
     */
    public Courier getCourier(String email) {
        return cl.getCourier(email);
    }

    /**
     * Returns the PharmacyList registered in the Platform
     *
     * @return PharmacyList
     */
    public List<Pharmacy> getPharmacyList() {
        return pl.getPharmacyList();
    }

    /**
     * Returns a delivery with the same id passed by parameter
     *
     * @param id the delivery's id
     * @return Delivery
     */
    public Delivery getChoosenDelivery(String id) {
        return dl.verifyDelivery(id);
    }

    /**
     * Returns the shortest path possible to make a delivery via drone
     *
     * @return List of adresses the scooter has to go through
     */
    public List<Address> getShortestPathDroneDelivery() throws IOException {
        List<Address> ordersToDelivery = new ArrayList<>();
        ordersToDelivery.add(listTotalOrdersToDeliverDrone.get(0).getPharmacy().getAdress());
        for (Delivery d : listTotalOrdersToDeliverDrone) {
            ordersToDelivery.add(d.getInvoice().getClient().getAdress());
        }
        return dl.getShortestPathDroneAdress(ordersToDelivery);
    }

    /**
     * Returns the energy spent in a delivery via scooter from a adress to another
     *
     * @param scooter that is doing the delivery
     * @param a1 first adress
     * @param a2 second adress
     *
     * @return list of energy spent
     */
    public ArrayList<Double> getEnergySpentInDeliver(Scooter scooter, Address a1, Address a2) throws IOException {
        double distance = dl.getDistanceFromDelivery();
        return dl.getEnergySpentInDeliver(distance, scooter, a1, a2, calcWeight(listTotalOrdersToDeliverScooter));
    }

    /**
     * Returns the energy spent in a delivery via drone
     *
     * @param drone that is doing the delivery
     *
     * @return list of energy spent
     */
    public ArrayList<Double> getEnergySpentInDeliver(Drone drone) {
        double distance = dl.getDistanceFromDelivery();
        return dl.getEnergySpentInDeliver(distance, drone, calcWeight(listTotalOrdersToDeliverDrone));
    }

    /**
     * Returns the pharmacy where the has an available park, receving its adress by parameter
     *
     * @param pharmacyAD pharmacy where the drone has park
     *
     * @return pharmacy to park
     */
    public Pharmacy getPharmacyToPark(Address pharmacyAD) {
        return AplicationPOT.getInstance().getPlatform().getPharmacyList().getPharmacy(pharmacyAD);
    }

    public List<Address> getPathToPharmacy(List<Address> list) throws IOException {
        return dl.getShortestPathDroneAdress(list);
    }

    /**
     * Returns the energy spent by a scooter in a certain distance
     *
     * @param distance distance traveled
     * @param scooter that did the delivery
     * @param deliveries list of deliveries
     *
     * @return list of energy spent
     */
    public ArrayList<Double> getEnergySpent(double distance, Scooter scooter, List<Address> deliveries) throws IOException {
        return dl.getEnergySpentInDeliver(distance, scooter, deliveries.get(0), deliveries.get(deliveries.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
    }

    /**
     * Returns the energy spent by a drone in a certain distance
     *
     * @param distance distance traveled
     * @param drone that did the delivery
     *
     * @return list of energy spent
     */
    public ArrayList<Double> getEnergySpent(double distance, Drone drone) {
        return dl.getEnergySpentInDeliver(distance, drone, calcWeight(listTotalOrdersToDeliverDrone));
    }

    /**
     * Removes a delivery passed by parameter from the DeliveryList
     *
     * @param delivery that will get removed
     *
     */
    public void removeDeliveryFromList(Delivery delivery) {
        dl.removedDeliveryFromList(delivery);
    }

    /**
     * Checks if a delivery passed by parameter is possible to be made by a courier also passed by parameter
     *
     * @param delivery the delivery
     * @param courier the courier
     *
     * @return boolean true or false
     */
    public Boolean addDeliverySelected(Delivery delivery, Courier courier) {
        double totalWeight = 0;
        for (Delivery d : listTotalOrdersToDeliverScooter) {
            totalWeight += d.getTotalWeight();
        }
        double total = totalWeight + delivery.getTotalWeight();
        if (total <= courier.getBackpackCapacity()) {
            listTotalOrdersToDeliverScooter.add(delivery);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a delivery passed by parameter is possible to be made by a drone also passed by parameter
     *
     * @param delivery the delivery
     * @param drone the drone
     *
     * @return boolean true or false
     */
    public Boolean addDeliverySelected(Delivery delivery, Drone drone) {
        double totalWeight = 0;
        for (Delivery d : listTotalOrdersToDeliverDrone) {
            totalWeight += d.getTotalWeight();
        }
        double total = totalWeight + delivery.getTotalWeight();
        if (total <= drone.getCapacity()) {
            listTotalOrdersToDeliverDrone.add(delivery);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the full path a scooter will make to deliver a list of deliveries
     *
     * @param deliveries the list of deliveries going to be made
     * @param scooter the scooter that will perform the deliveries
     *
     * @return list of adresses going to be traveled
     */
    public List<Address> calculateFullPathScooter(List<Delivery> deliveries, Scooter scooter) throws IOException {
        double energyCharged2 = scooter.getCurrentCharge();
        double energySpentWithoutCharging2 = 0;
        double aux2 = 0;
        List<Address> finalPath = new ArrayList<>();
        List<Address> finalList = new ArrayList<>();
        List<Address> path;
        Pharmacy initialPharmacy = deliveries.get(0).getPharmacy();
        finalList.add(initialPharmacy.getAdress());
        int i = 1;
        for (Delivery d : deliveries) {
            finalList.add(d.getInvoice().getClient().getAdress());
            path = dl.getShortestPathScooterAdress(finalList);
            finalList.remove(d.getInvoice().getClient().getAdress());
            ArrayList<Double> energyTime = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path.get(0), path.get(path.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
            double energy = energyTime.get(0);
            if (energy >= energyCharged2) {
                double distMin = Double.MAX_VALUE;
                Pharmacy p = null;
                for (Pharmacy phar : pl.getPharmacyList()) {
                    if (phar.hasScooterParkingLot()) {
                        double distance = Double.parseDouble(GraphAlgorithms.DistanceCalculator(phar.getAdress().getDecimalDegree1().toString(), phar.getAdress().getDecimalDegree2().toString(), path.get(path.size() - 1).getDecimalDegree1().toString(), path.get(path.size() - 1).getDecimalDegree2().toString()));
                        if (distance < distMin) {
                            distMin = distance;
                            p = phar;
                        }
                    }
                }
                if (p != null) {
                    finalList.remove(d.getInvoice().getClient().getAdress());
                    finalList.add(p.getAdress());
                    path = dl.getShortestPathScooterAdress(finalList);
                    ArrayList<Double> energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path.get(0), path.get(path.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
                    double energyPharmacy2 = energyTime2.get(0);
                    if (energyPharmacy2 > energyCharged2) {
                        return Collections.emptyList();
                    } else {
                        aux2 += energySpentWithoutCharging2;
                        energySpentWithoutCharging2 = 0;
                        energyCharged2 = energyCharged2 + aux2;
                        for (int j = i; j < path.size(); j++) {
                            finalList.add(path.get(j));
                        }
                        i = finalList.size();
                        dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path.get(0), path.get(path.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
                        finalList.add(d.getInvoice().getClient().getAdress());
                        path = dl.getShortestPathScooterAdress(finalList);
                        energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path.get(0), path.get(path.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
                        energyPharmacy2 = energyTime2.get(0);
                        if (energyPharmacy2 > energyCharged2) {
                            return Collections.emptyList();
                        } else {
                            for (int j = i; j < path.size(); j++) {
                                finalList.add(path.get(j));
                            }
                            i = finalList.size();
                        }
                        dl.getShortestPathScooterAdress(finalList);
                    }
                } else {
                    return Collections.emptyList();
                }
            } else {
                energySpentWithoutCharging2 = energy;
                for (int j = i; j < path.size(); j++) {
                    finalList.add(path.get(j));
                }
                i = finalList.size();
            }
        }
        dl.getShortestPathScooterAdress(finalList);
        double energySpent = scooter.getFullCharge() - energySpentWithoutCharging2;
        List<Address> PathBack = getPharmacyParkScooter(finalList, scooter, energyCharged2, initialPharmacy, energySpent);
        if (PathBack.isEmpty()) {
            return Collections.emptyList();
        }
        finalList.addAll(PathBack);
        dl.getShortestPathScooterAdress(finalList);
        for (int k = 0; k < finalList.size() - 1; k++) {
            List<Address> pathBetween = dl.getPath(finalList.get(k).getDecimalDegree1().toString(), finalList.get(k).getDecimalDegree2().toString(), finalList.get(k + 1).getDecimalDegree1().toString(), finalList.get(k + 1).getDecimalDegree2().toString());
            finalPath.add(finalList.get(k));
            finalPath.addAll(pathBetween);
        }
        finalPath.add(finalList.get(finalList.size() - 1));
        return finalPath;
    }

    private List<Address> getPharmacyParkScooter(List<Address> finalList, Scooter scooter, double energyCharged, Pharmacy initialPharmacy, double energySpent) throws IOException {
        int i = 1;
        List<Address> pathBack2 = new ArrayList<>();
        List<Address> path2 = new ArrayList<>();
        pathBack2.add(finalList.get(finalList.size() - 1));
        pathBack2.add(finalList.get(0));
        path2 = dl.getShortestPathScooterAdress(pathBack2);
        ArrayList<Double> energyTime = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path2.get(0), path2.get(path2.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
        double energy = energyTime.get(0);
        if (energy > energySpent) {
            double distMin = Double.MAX_VALUE;
            Pharmacy p = null;
            for (Pharmacy phar : pl.getPharmacyList()) {
                if (phar.hasScooterParkingLot() && !phar.equals(initialPharmacy)) {
                    double distance = Double.parseDouble(GraphAlgorithms.DistanceCalculator(phar.getAdress().getDecimalDegree1().toString(), phar.getAdress().getDecimalDegree2().toString(), path2.get(path2.size() - 1).getDecimalDegree1().toString(), path2.get(path2.size() - 1).getDecimalDegree2().toString()));
                    if (distance < distMin) {
                        distMin = distance;
                        p = phar;
                    }
                }
            }
            if (p != null) {
                pathBack2.remove(finalList.get(0));
                pathBack2.add(p.getAdress());
                path2 = dl.getShortestPathScooterAdress(pathBack2);
                ArrayList<Double> energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path2.get(0), path2.get(path2.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
                double energyPharmacy = energyTime2.get(0);
                if (energyPharmacy > energySpent) {
                    return Collections.emptyList();
                } else {
                    energyCharged += energyPharmacy;
                    for (int j = 1; j < path2.size(); j++) {
                        pathBack2.add(path2.get(j));
                    }
                    pathBack2.add(finalList.get(0));
                    path2 = dl.getShortestPathScooterAdress(pathBack2);
                    energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), scooter, path2.get(0), path2.get(path2.size() - 1), calcWeight(listTotalOrdersToDeliverScooter));
                    energyPharmacy = energyTime2.get(0);
                    if (energyPharmacy > scooter.getFullCharge() + energyCharged) {
                        return Collections.emptyList();
                    } else {
                        pathBack2 = path2;
                    }
                }
            } else {
                return Collections.emptyList();
            }
        } else {
            pathBack2.remove(finalList.get(0));
            for (int j = 1; j < path2.size(); j++) {
                pathBack2.add(path2.get(j));
            }
        }
        pathBack2.remove(0);
        return pathBack2;
    }

    /**
     * Calculates the full path a drone will make to deliver a list of deliveries
     *
     * @param deliveries the list of deliveries going to be made
     * @param drone the drone that will perform the deliveries
     *
     * @return list of adresses going to be traveled
     */
    public List<Address> calculateFullPathDrone(List<Delivery> deliveries, Drone drone) throws IOException {
        double energyCharged = drone.getCurrentCharge();
        double energySpentWithoutCharging = 0;
        double aux = 0;
        List<Address> finalList = new ArrayList<>();
        List<Address> path;
        Pharmacy initialPharmacy = deliveries.get(0).getPharmacy();
        finalList.add(initialPharmacy.getAdress());
        int i = 1;
        for (Delivery d : deliveries) {
            finalList.add(d.getInvoice().getClient().getAdress());
            path = dl.getShortestPathDroneAdress(finalList);
            finalList.remove(d.getInvoice().getClient().getAdress());
            ArrayList<Double> energyTime = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
            double energy = energyTime.get(0);
            if (energy >= energyCharged) {
                double distMin = Double.MAX_VALUE;
                Pharmacy p = null;
                for (Pharmacy phar : pl.getPharmacyList()) {
                    if (phar.hasDroneParkingLot()) {
                        double distance = GraphAlgorithms.calcularDistancia(phar.getAdress().getDecimalDegree1(), phar.getAdress().getDecimalDegree2(), path.get(path.size() - 1).getDecimalDegree1(), path.get(path.size() - 1).getDecimalDegree2());
                        if (distance < distMin) {
                            distMin = distance;
                            p = phar;
                        }
                    }
                }
                if (p != null) {
                    finalList.remove(d.getInvoice().getClient().getAdress());
                    finalList.add(p.getAdress());
                    path = dl.getShortestPathDroneAdress(finalList);
                    ArrayList<Double> energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
                    double energyPharmacy = energyTime2.get(0);
                    if (energyPharmacy > energyCharged) {
                        return Collections.emptyList();
                    } else {
                        aux += energySpentWithoutCharging;
                        energySpentWithoutCharging = 0;
                        energyCharged = energyCharged + aux;
                        for (int j = i; j < path.size(); j++) {
                            finalList.add(path.get(j));
                        }
                        i = finalList.size();
                        dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
                        finalList.add(d.getInvoice().getClient().getAdress());
                        path = dl.getShortestPathDroneAdress(finalList);
                        energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
                        energyPharmacy = energyTime2.get(0);
                        if (energyPharmacy > energyCharged) {
                            return Collections.emptyList();
                        } else {
                            for (int j = i; j < path.size(); j++) {
                                finalList.add(path.get(j));
                            }
                            i = finalList.size();
                        }
                        dl.getShortestPathDroneAdress(finalList);
                    }
                } else {
                    return Collections.emptyList();
                }
            } else {
                energySpentWithoutCharging = energy;
                for (int j = i; j < path.size(); j++) {
                    finalList.add(path.get(j));
                }
                i = finalList.size();
            }
        }
        dl.getShortestPathDroneAdress(finalList);
        double energySpent = drone.getFullCharge() - energySpentWithoutCharging;
        List<Address> PathBack = getPharmacyParkDrone(finalList, drone, energyCharged, initialPharmacy, energySpent);
        if (PathBack.isEmpty()) {
            return Collections.emptyList();
        }
        finalList.addAll(PathBack);
        dl.getShortestPathDroneAdress(finalList);
        return finalList;
    }

    private List<Address> getPharmacyParkDrone(List<Address> finalList, Drone drone, double energyCharged, Pharmacy initialPharmacy, double energySpent) throws IOException {
        int i = 1;
        List<Address> pathBack = new ArrayList<>();
        List<Address> path = new ArrayList<>();
        pathBack.add(finalList.get(finalList.size() - 1));
        pathBack.add(finalList.get(0));
        path = dl.getShortestPathDroneAdress(pathBack);
        ArrayList<Double> energyTime = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
        double energy = energyTime.get(0);
        if (energy > energySpent) {
            double distMin = Double.MAX_VALUE;
            Pharmacy p = null;
            for (Pharmacy phar : pl.getPharmacyList()) {
                if (phar.hasDroneParkingLot() && !phar.equals(initialPharmacy)) {
                    double distance = GraphAlgorithms.calcularDistancia(phar.getAdress().getDecimalDegree1(), phar.getAdress().getDecimalDegree2(), path.get(path.size() - 1).getDecimalDegree1(), path.get(path.size() - 1).getDecimalDegree2());
                    if (distance < distMin) {
                        distMin = distance;
                        p = phar;
                    }
                }
            }
            if (p != null) {
                pathBack.remove(finalList.get(0));
                pathBack.add(p.getAdress());
                path = dl.getShortestPathDroneAdress(pathBack);
                ArrayList<Double> energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
                double energyPharmacy = energyTime2.get(0);
                if (energyPharmacy > energySpent) {
                    return Collections.emptyList();
                } else {
                    energyCharged += energyPharmacy;
                    for (int j = 1; j < path.size(); j++) {
                        pathBack.add(path.get(j));
                    }
                    pathBack.add(finalList.get(0));
                    path = dl.getShortestPathDroneAdress(pathBack);
                    energyTime2 = dl.getEnergySpentInDeliver(dl.getDistanceFromDelivery(), drone, calcWeight(listTotalOrdersToDeliverDrone));
                    energyPharmacy = energyTime2.get(0);
                    if (energyPharmacy > drone.getFullCharge() + energyCharged) {
                        return Collections.emptyList();
                    } else {
                        pathBack = path;
                    }
                }
            } else {
                return Collections.emptyList();
            }
        } else {
            pathBack.remove(finalList.get(0));
            for (int j = 1; j < path.size(); j++) {
                pathBack.add(path.get(j));
            }
        }
        pathBack.remove(0);
        return pathBack;
    }

    /**
     * Calculates the sum of the weight of a list of deliveries
     *
     * @param listTotalOrdersToDeliver the list of deliveries
     *
     * @return the total weight
     */
    public double calcWeight(List<Delivery> listTotalOrdersToDeliver) {
        double totalWeight = 0;
        for (Delivery d : listTotalOrdersToDeliver) {
            totalWeight += d.getTotalWeight();
        }
        return totalWeight;
    }

    /**
     * Send an email for a client with his invoice
     *
     * @param c the client receiving the email
     * @param i clients' invoice
     * @param p the pharmacy that sold the products
     *
     */
    public void sendEmail(Client c, Invoice i, Pharmacy p) throws MessagingException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String invoiceInfo = "INVOICE INFORMATION\n=========================\n"
                + "Invoice: " + i.getId() + "    Date:  " + formatter.format(i.getDateinvoice()) + "\n\nTotal Price: " + i.getPrice() + " €";
        StringBuilder str1 = new StringBuilder();
        for (Product produ : i.getMp().keySet()) {
            str1.append("\n\nProduct: ");
            str1.append(produ.getNome());
            str1.append("   Quantity: ");
            str1.append(i.getMp().get(produ));
        }
        str1.append("\n=========================");

        String message = invoiceInfo + str1.toString() + "\n\nThis delivery just started!";

        MailUtil.sendEmail(c.getEmail(), message, "Delivery", p);
    }
}
