/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.DeliveryDB;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class DeliveryList {

    /**
     * Instance of GraphAlgorithms
     */
    private final GraphAlgorithms gA;
    /**
     * List of Deliveries pending
     */
    private final List<Delivery> listDelivery;
    /**
     * The Drag coefficient of the Scooter
     */
    private double cdScooter;

    /**
     * The area of the scooter
     */
    private double areaScooter;
    /**
     * The area of the drone
     */
    private double areaDrone;
    /**
     * The density
     */
    private double density;
    /**
     * The roling coeficient of the scooter
     */
    private double rolingCoeficient;
    /**
     * The gravity force on Earth
     */
    private double gravityForce;
    /**
     * The total mass of the Scooter
     */
    private double totalMassScooter;
    /**
     * The total mass of the drone
     */
    private double totalMassDrone;
    /**
     * The wind speed
     */
    private double windSpeed;
    /**
     * The wind angle
     */
    private double windAngle;
    /**
     *
     *
     *
     * of the Scooter
     */
    private double nPower;
    /**
     *
     *
     *
     * of the drone
     */
    private double rDrone;
    /**
     * The power required for all avionics on the drone
     */
    private double pavio;
    /**
     * The lift Speed of the drone
     */
    private double liftSpeed;
    /**
     * The lift Distance of the drone
     */
    private double liftDistance;

    /**
     * Creates an instance of ClientRegister
     */
    public DeliveryList() {
        listDelivery = new ArrayList<>();
        gA = new GraphAlgorithms();
        this.cdScooter = 0.7;           //coeficiente de arrasto por omissão.
        this.areaScooter = 1;           //area media do estafeta por omissão.
        this.areaDrone = 0.3;           //area media do drone por omissão.
        this.density = 1.225;           //densidade média do ar ao nivel da agua do mar e a 15ºC 
        this.rolingCoeficient = 0.008;  //coeficiente de rolamento por omissão
        this.gravityForce = 9.8;
        this.totalMassScooter = 120;    //massa total(scooter(45Kg) + estafeta(75Kg de média)) por omissão
        this.totalMassDrone = 5;        //massa total(drone(5Kg)) por omissão
        this.windAngle = 0;             //angulo do vento (direção do movimento) 
        this.windSpeed = 0;             //velocidade do vento
        this.nPower = 0.5;
        this.rDrone = 3.5;
        this.pavio = 0.1;
        this.liftSpeed = 6;
        this.liftDistance = 150;
    }

    /**
     * Return the delivery list of the pharmacy recived as a parameter
     *
     * @param p
     * @return the list of deliveries
     */
    public List<Delivery> getDeliveryList(Pharmacy p) {
        List<Delivery> list = new ArrayList<>();
        for (Delivery d : listDelivery) {
            if (d.getPharmacy().equals(p)) {
                list.add(d);
            }
        }
        return list;
    }

    /**
     *
     * @return the list of deliveries
     */
    public List<Delivery> getDeliveryList() {
        return listDelivery;
    }

    /**
     *
     * @return the drag coefficient of the scooter
     */
    public double getCdScooter() {
        return cdScooter;
    }

  
    /**
     *
     * @return the area of the scooter
     */
    public double getAreaScooter() {
        return areaScooter;
    }

    /**
     *
     * @return the area of the drone
     */
    public double getAreaDrone() {
        return areaDrone;
    }

    /**
     *
     * @return the density
     */
    public double getDensity() {
        return density;
    }

    /**
     *
     * @return the roling coeficient
     */
    public double getRolingCoeficient() {
        return rolingCoeficient;
    }

    /**
     *
     * @return the gravity force
     */
    public double getGravityForce() {
        return gravityForce;
    }

    /**
     *
     * @return the total mass of the scooter
     */
    public double getTotalMassScooter() {
        return totalMassScooter;
    }

    /**
     *
     * @return the total mass of the drone
     */
    public double getTotalMassDrone() {
        return totalMassDrone;
    }

    /**
     *
     * @return the wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     *
     * @return the wind angle
     */
    public double getWindAngle() {
        return windAngle;
    }

    public double getLiftSpeed() {
        return liftSpeed;
    }

    public double getLiftDistance() {
        return liftDistance;
    }

    public double getnPower() {
        return nPower;
    }

    public double getrDrone() {
        return rDrone;
    }

    public double getPavio() {
        return pavio;
    }
    /**
     * Changes the drag coefficient to the one received by parameter
     *
     * @param cdScooter
     */
    public void setCdScooter(double cdScooter) {
        this.cdScooter = cdScooter;
    }


    /**
     * Changes the area of the scooter to the one received by parameter
     *
     * @param areaScooter
     */
    public void setAreaScooter(double areaScooter) {
        this.areaScooter = areaScooter;
    }

    /**
     * Changes the area of the drone to the one received by parameter
     *
     * @param areaDrone
     */
    public void setAreaDrone(double areaDrone) {
        this.areaDrone = areaDrone;
    }

    /**
     * Changes the density of the drone to the one received by parameter
     *
     * @param density
     */
    public void setDensity(double density) {
        this.density = density;
    }

    /**
     * Changes the roling Coeficient to the one received by parameter
     *
     * @param rolingCoeficient
     */
    public void setRolingCoeficient(double rolingCoeficient) {
        this.rolingCoeficient = rolingCoeficient;
    }

    /**
     * Changes the gravity Force to the one received by parameter
     *
     * @param gravityForce
     */
    public void setGravityForce(double gravityForce) {
        this.gravityForce = gravityForce;
    }

    /**
     * Changes the total Mass of the Scooter to the one received by parameter
     *
     * @param totalMassScooter
     */
    public void setTotalMassScooter(double totalMassScooter) {
        this.totalMassScooter = totalMassScooter;
    }

    /**
     * Changes the total Mass of the Drone to the one received by parameter
     *
     * @param totalMassDrone
     */
    public void setTotalMassDrone(double totalMassDrone) {
        this.totalMassDrone = totalMassDrone;
    }

    /**
     * Changes the wind speed to the one received by parameter
     *
     * @param windSpeed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Changes the wind angle to the one received by parameter
     *
     * @param windAngle
     */
    public void setWindAngle(double windAngle) {
        this.windAngle = windAngle;
    }

    /**
     * Changes the lift speed to the one received by parameter
     *
     * @param liftSpeed
     */
    public void setLiftSpeed(double liftSpeed) {
        this.liftSpeed = liftSpeed;
    }

    /**
     * Changes the lift distance to the one received by parameter
     *
     * @param liftDistance
     */
    public void setLiftDistance(double liftDistance) {
        this.liftDistance = liftDistance;
    }

    /**
     * Add the delivery recived as a parameter
     *
     * @param d
     * @return the list of deliveries
     */
    public boolean addDelivery(Delivery d) {
        if (!listDelivery.contains(d)) {
            listDelivery.add(d);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates a new instance of delivery with the invoice and pharmacy received
     * by parameter
     *
     * @param inv
     * @param pharmacy
     * @return
     */
    public Delivery newDelivery(Invoice inv, Pharmacy pharmacy) {
        Date date = new Date();
        String id = generateId(date);
        double totalWeight = calcWeight(inv);

        return new Delivery(id, date, totalWeight, inv, pharmacy);
    }

    /**
     * Verify if the delivery recived as a parameter is valid
     *
     * @param id
     * @return null or the delivery
     */
    public Delivery verifyDelivery(String id) {
        for (Delivery d : listDelivery) {
            if (d.getId().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Remove the delivery received as a parameter from the list of deliveries
     *
     * @param delivery
     */
    public void removedDeliveryFromList(Delivery delivery) {
        listDelivery.remove(delivery);
        DeliveryDB deliveryDB = AplicationPOT.getInstance().getPlatform().getDdb();
        deliveryDB.removeDelivery(delivery.getId());
    }

    /**
     * Method that return the shortest path for the adresses recived as a
     * parameter for the scooter
     *
     * @param deliveryList
     * @return the shortest path for the adresses recived as a parameter
     * @throws IOException
     */
    public List<Address> getShortestPathScooterAdress(List<Address> deliveryList) throws IOException {
        return gA.suggestionMostEconomicScooterPath(deliveryList);
    }

    /**
     * Method that return the shortest path for the adresses recived as a
     * parameter for the drone
     *
     * @param deliveryList
     * @return the shortest path for the deliveries received as a parameter
     * @throws IOException
     */
    public List<Address> getShortestPathDroneAdress(List<Address> deliveryList) throws IOException {
        return gA.suggestionMostEconomicDronePath(deliveryList);
    }

    /**
     *
     * @return the distance for the deliveries
     */
    public double getDistanceFromDelivery() {
        return gA.getFinalPathDistance();
    }

    /**
     * Return the energy spent in the delivery, receiving as a parameter the
     * distance, scooter, two addresses and the total weight
     *
     * @param distance
     * @param scooter
     * @param adress1
     * @param adress2
     * @param totalWeight
     * @return the energy spent in the deliver
     * @throws IOException
     */
    public ArrayList<Double> getEnergySpentInDeliver(double distance, Scooter scooter, Address adress1, Address adress2, double totalWeight) throws IOException {
        ArrayList<Double> energyTime = new ArrayList<>();
        double power;
        double averageSpeed = (scooter.getAverageSpeed() / 3.6) - ((windSpeed / 3.6) * Math.cos(windAngle));     //velocidade Media da scooter
        double time;
        double energySpent;
        String elevation = elevationCalculator(adress1.getDecimalDegree1().toString(), adress1.getDecimalDegree2().toString(), adress2.getDecimalDegree1().toString(), adress2.getDecimalDegree2().toString());

        String elevation1 = elevation.split(";")[0];
        String elevation2 = elevation.split(";")[1];
        double elevationDiferance = Double.parseDouble(elevation2) - Double.parseDouble(elevation1);
        double g = (elevationDiferance) / distance;       //regra dde 3 simples para saber qual o gradiente de inclinação por cada 100 metros

        //Calculo da resistencia do ar.
        double airResistance = 0.5 * cdScooter * areaScooter * density * (averageSpeed * averageSpeed);

        //calculo do atrito
        double atrito = rolingCoeficient * gravityForce * (totalMassScooter + totalWeight) * Math.cos(g);

        double elevationForce = (totalMassScooter + totalWeight) * gravityForce * Math.sin(g);

        //calculo do power
        power = (airResistance + atrito + elevationForce) * averageSpeed;

        if (power > scooter.getPower()) {
            averageSpeed = (averageSpeed * scooter.getPower()) / power;
            power = scooter.getPower();
        }

        //Calculo do tempo que demorou a fazer a viagem.
        time = distance / averageSpeed;  //time in secounds
        energySpent = (power * time) / 3600;    //energySpent in W/h

        energyTime.add(energySpent);
        energyTime.add(time);
        energyTime.add(averageSpeed);

        return energyTime;
    }

    /**
     * Return the energy spent in the delivery, receiving as a parameter the
     * distance, drone and the total weight.
     *
     * @param distance
     * @param drone
     * @param totalWeight
     * @return the energy spent in the delivery
     */
    public ArrayList<Double> getEnergySpentInDeliver(double distance, Drone drone, double totalWeight) {
        ArrayList<Double> energyTime = new ArrayList<>();
        double power;
        double averageSpeed = drone.getAverageSpeed();
        double time;
        double energySpent;

        double liftTime = liftDistance / liftSpeed;
        double liftPower = (Math.pow((totalMassDrone + totalWeight) * gravityForce, (1.5))) / Math.sqrt((2 * density * areaDrone));
        double liftEnergy = liftPower * (liftTime / 3600);

        double energy = 0;
        if (windSpeed != 0) {
            double ratio = (windSpeed * Math.cos(windAngle));
            energy = (((distance / 1000) / (1 - (ratio / windSpeed))) * ((totalMassDrone + totalWeight) / (370 * nPower * rDrone) + pavio / averageSpeed)) * 1000;
        }else{
            
            energy = ((totalMassDrone + totalWeight)/(370 * nPower * rDrone) + pavio) * 1000;
            energy = ((distance)/1000)/(averageSpeed) * energy;
        }

        time = (2 * liftTime) + ((distance) / (averageSpeed / 3.6));  //time in seconds
        energySpent = 2 * liftEnergy + energy;    //energySpent in W/h

        energyTime.add(energySpent);
        energyTime.add(time);
       
        return energyTime;
    }

    /**
     * Generate a unique ID for each delivery
     *
     * @param date
     * @return the id
     */
    public String generateId(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH/mm/ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        return "DEL/" + formatter2.format(date) + "/" + formatter.format(date);
    }

    /**
     * Calculates the weight of the delivery receiving as a parameter an invoice
     *
     * @param inv
     * @return the total weight of a delivery
     */
    public double calcWeight(Invoice inv) {
        double weight = 0;
        Map<Product, Integer> mp = inv.getMp();

        for (Map.Entry<Product, Integer> map : mp.entrySet()) {
            Product p = map.getKey();
            float we = p.getWeight();
            double aux = Double.parseDouble(Float.toString(we));
            weight += (aux * (mp.get(p)));

        }
        return weight;
    }

    /**
     * Register a delivery received as a parameter
     *
     * @param delivery
     */
    public void registerDelivery(Delivery delivery) {
        listDelivery.add(delivery);
        DeliveryDB deliveryDB = AplicationPOT.getInstance().getPlatform().getDdb();
        deliveryDB.addDelivery(delivery);
    }

    /**
     * Return the distance between the last client and the pharmacy receiving as
     * a parameter the adress of the client and the pharmacy
     *
     * @param lastClient
     * @param p
     * @return the distance between the last client and the pharmacy
     */
    public double isPharmacyAChargeAway(Address lastClient, Pharmacy p) {
        return gA.calcularDistancia(lastClient.getDecimalDegree1(), lastClient.getDecimalDegree2(), p.getAdress().getDecimalDegree1(), p.getAdress().getDecimalDegree2());
    }

    /**
     * Return the values of the elevation receiving as a parameter the value of
     * four decimal degrees
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return the values of the elevation
     * @throws IOException
     */
    public String elevationCalculator(String x1, String y1, String x2, String y2) throws IOException {
        StringBuilder api = new StringBuilder();
        api.append("https://router.hereapi.com/v8/routes?apiKey=ZS4HN-O9CLS4SwrC2lJujJufnGZ4N9tHo4aaMvEcyAo&origin=");
        api.append(x1.trim());
        api.append(",");
        api.append(y1.trim());
        api.append("&transportMode=pedestrian&destination=");
        api.append(x2.trim());
        api.append(",");
        api.append(y2.trim());
        api.append("&return=polyline,elevation");

        URL url2 = new URL(api.toString());
        HttpURLConnection con = (HttpURLConnection) url2.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();
        String elevation1 = content.toString().split("\"elv\":")[1].split("}")[0];
        String elevation2 = content.toString().split("\"elv\":")[2].split("}")[0];

        return (elevation1 + ";" + elevation2);
    }

    /**
     * Returns the streets between two points
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     * @throws IOException
     */
    public List<Address> getPath(String x1, String y1, String x2, String y2) throws IOException {
        return gA.pathCalculator(x1, y1, x2, y2);
    }
}
