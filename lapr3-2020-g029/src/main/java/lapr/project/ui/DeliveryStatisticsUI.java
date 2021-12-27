/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.AplicationPOT;
import lapr.project.controller.MakeDeliveryController;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Delivery;
import lapr.project.model.Drone;
import lapr.project.model.Invoice;
import lapr.project.model.Pharmacy;
import lapr.project.model.Scooter;

public class DeliveryStatisticsUI {

    static final Scanner sc = new Scanner(System.in);
    private final MainWindowUI main;
    private final MakeDeliveryController controller;
    private static final String SEPARATOR = ("=======================================================");
    private static final String CLOSE = ("Make delivery menu is closing......");

    public DeliveryStatisticsUI(MainWindowUI main) throws Exception {
        this.main = main;
        controller = main.getMakeDeliveryController();
        start();
    }

    private void start() throws Exception {
        try {
            boolean hasScooter2 = false;
            boolean hasDrone2 = false;
            System.out.println(SEPARATOR);
            System.out.println("\t\tShow Delivery Statistics:");
            System.out.println(SEPARATOR);

            //escolhe a farmacia
            Pharmacy p = printPharmacies();
            if (p != null) {
                //escolhe uma scooter
                Scooter scooter = printScooters(p);
                //escolhe um courier
                Courier courier = printcouriers();
                //escolhe um drone
                Drone drone = printDrones(p);
                if (scooter != null || courier != null) {
                    hasScooter2 = true;
                }
                if (drone != null) {
                    hasDrone2 = true;
                }

                if (hasScooter2 && hasDrone2) {
                    //Lista de deliveries daquela farmacia
                    List<Delivery> dl = controller.getDeliveryList().getDeliveryList(p);
                    if (!dl.isEmpty()) {
                        List<Invoice> invoiceList = new ArrayList<>();
                        printDeliveries(dl);
                        boolean choiseDeliver = false;
                        int choise = 1;
                        while (choise != 0) {
                            choise = Integer.parseInt(sc.nextLine());
                            if (choise != 0) {
                                if (choise > dl.size()) {
                                    System.out.println("This delivery doesn´t exist in the list");
                                } else {
                                    if (!choiseDeliver) {
                                        choiseDeliver = true;
                                    }
                                    String deliveryID = dl.get(choise - 1).getId();
                                    Delivery delivery = controller.getChoosenDelivery(deliveryID);
                                    boolean addToScooter = controller.addDeliverySelected(delivery, courier);
                                    if (addToScooter) {
                                        System.out.println("1 Delivery added!");
                                    } else if (!addToScooter) {
                                        System.out.println("This delivery does not fit in your bag because it exceeds the limit weight or exceeds the scooters energy level");
                                    }
                                    boolean addToDrone = controller.addDeliverySelected(delivery, drone);
                                    if (addToDrone) {
                                        System.out.println("1 Delivery added!");
                                    } else if (!addToDrone) {
                                        System.out.println("This delivery does not fit in your bag because it exceeds the limit weight");
                                    }
                                    if (addToScooter || addToDrone) {
                                        invoiceList.add(delivery.getInvoice());

                                    }
                                }
                            }
                        }
                        if (choiseDeliver) {
                            List<Address> pathScooter = controller.calculateFullPathScooter(controller.getDeliverySelectedScooter(), scooter);
                            double distanceScooter2 = controller.getDistance();
                            ArrayList energyTime = controller.getEnergySpent(distanceScooter2, scooter, pathScooter);
                            double energyScooter2 = Double.parseDouble((String) energyTime.get(0));
                            double timeScooter2 = Double.parseDouble((String) energyTime.get(1));
                            List<Address> pathDrone = controller.calculateFullPathDrone(controller.getDeliverySelectedDrone(), drone);
                            double distanceDrone = controller.getDistance();
                            ArrayList energyTime2 = controller.getEnergySpent(distanceDrone, drone);
                            double energyDrone = Double.parseDouble((String) energyTime2.get(0));
                            double timeDrone2 = Double.parseDouble((String) energyTime2.get(1));
                            System.out.println(SEPARATOR);
                            System.out.println("\t\tRoute with Scooter:");
                            System.out.println(SEPARATOR);
                            if (hasScooter2 || !pathScooter.isEmpty()) {
                                System.out.println("\nMost economic route:");
                                for (Address a : pathScooter) {
                                    if (a.getDoorNumber() == 0) {
                                        System.out.printf("%n%s", a.getStreet());
                                    } else {
                                        System.out.printf("%n%s Door:%d ", a.getStreet(), a.getDoorNumber());
                                    }
                                }
                                System.out.printf("%nDistance: %f Km", distanceScooter2);
                                System.out.printf("%nEnergy spent on the delivery: %.2f W", energyScooter2);
                                System.out.printf("%nTime spent on the delivery: %.2f s", timeScooter2);
                                System.out.printf("%nScooter's area: %.2f m^2", controller.getDeliveryList().getAreaScooter());
                                System.out.printf("%nScooter's Aerodynamic Coeficient: %.2f ", controller.getDeliveryList().getCdScooter());
                                System.out.printf("%nRoling Coeficient: %.2f ", controller.getDeliveryList().getRolingCoeficient());
                                System.out.printf("%nDensity: %.2f kg/m^3", controller.getDeliveryList().getDensity());
                                System.out.printf("%nAverage Speed: %.2f km/h", energyTime.get(2));
                                System.out.printf("%nWind Speed: %.2f km/h", controller.getDeliveryList().getWindSpeed());
                                System.out.printf("%nWind Angle: %.2fº ", controller.getDeliveryList().getWindAngle());
                                System.out.printf("%nWeight Delivery: %.2f kg", controller.calcWeight(controller.getDeliverySelectedScooter()));
                            } else {
                                System.out.println("There are no scooters available at the moment or no path possible!");
                            }

                            System.out.println("\n=======================================================");
                            System.out.println("\t\tRoute with Drones:");
                            System.out.println("=======================================================");
                            if (hasDrone2 || !pathDrone.isEmpty()) {
                                System.out.println("Most economic route:");
                                for (Address a : pathDrone) {
                                    System.out.printf("%s Door:%d ->", a.getStreet(), a.getDoorNumber());
                                }
                                System.out.printf("%nDistance: %f Km", distanceDrone / 1000);
                                System.out.printf("%nEnergy spent on the delivery: %.2f W", energyDrone);
                                System.out.printf("%nTime spent on the delivery: %.2f s", timeDrone2);
                                System.out.printf("%nTime spent on the delivery: %.2f s", timeDrone2);
                                System.out.printf("%nDrone's lift distance: %.2f m", controller.getDeliveryList().getLiftDistance());
                                System.out.printf("%nDrone's lift distance: %.2f m", controller.getDeliveryList().getLiftDistance());
                                System.out.printf("%nAverage Speed: %.2f km/h", drone.getAverageSpeed());
                                System.out.printf("%nWind Speed: %.2f km/h", controller.getDeliveryList().getWindSpeed());
                                System.out.printf("%nWind Angle: %.2fº ", controller.getDeliveryList().getWindAngle());
                                System.out.printf("%nLift to drag ratio: %.2f ", controller.getDeliveryList().getrDrone());
                                System.out.printf("%nPower transfer efficiency: %.2f ", controller.getDeliveryList().getnPower());
                                System.out.printf("%nPower Consumption of eletronics: %.2f kW", controller.getDeliveryList().getPavio());
                                System.out.printf("%nWeight Delivery: %.2f kg", controller.calcWeight(controller.getDeliverySelectedDrone()));

                            } else {
                                System.out.println("There are no drones available at the moment or path possible!");
                            }
                            int scan2 = 1;
                            while (scan2 != 0) {
                                menu();
                                try {
                                    scan2 = Integer.parseInt(sc.nextLine());
                                    switch (scan2) {
                                        case 1:
                                            if (hasScooter2) {
                                                double stopsScooter = energyScooter2 / scooter.getFullCharge();
                                                if (stopsScooter < 1) {
                                                    scooter.setCurrentCharge(scooter.getCurrentCharge() - energyScooter2);
                                                } else {
                                                    scooter.setCurrentCharge(energyScooter2 - (stopsScooter * scooter.getFullCharge()));
                                                }
                                                scooter.update();
                                            } else {
                                                System.out.println("There are no scooters available at the moment!");
                                            }
                                            break;
                                        case 2:
                                            if (hasDrone2) {
                                                double stopsDrone = energyDrone / drone.getFullCharge();
                                                if (stopsDrone < 1) {
                                                    drone.setCurrentCharge((drone.getCurrentCharge() - energyDrone));
                                                } else {
                                                    drone.setCurrentCharge(energyDrone - (stopsDrone * drone.getFullCharge()));
                                                }

                                                Pharmacy pharmacyPark = controller.getPharmacyToPark(pathDrone.get(pathDrone.size() - 1));
                                                System.out.printf("%nIt will park in the Pharmacy: %s", pharmacyPark.toString());
                                                ArrayList energyTime3 = controller.getEnergySpentInDeliver(drone);
                                                double energyDrone2 = Double.parseDouble((String) energyTime3.get(0));
                                                if (drone.getCurrentCharge() - energyDrone2 < 0) {
                                                    drone.setCurrentCharge(0);
                                                } else {
                                                    drone.setCurrentCharge(drone.getCurrentCharge() - energyDrone2);
                                                }
                                                drone.update();
                                            } else {
                                                System.out.println("There are no drones available at the moment!");
                                            }
                                            break;

                                        case 0:
                                            break;
                                        default:
                                            System.out.println("\nEssa opção não se encontra disponível no menu.\nPor favor ensira uma nova opção.\n");
                                    }
                                } catch (InputMismatchException ime) {
                                    System.out.println("O valor escrito não faz parte das seleções possiveis");
                                    break;
                                }
                            }
                            for (Invoice i : invoiceList) {
                                controller.sendEmail(i.getClient(), i, p);
                            }
                            for (Delivery deli : controller.getDeliverySelectedScooter()) {
                                controller.removeDeliveryFromList(deli);
                            }
                            for (Delivery deli : controller.getDeliverySelectedDrone()) {
                                controller.removeDeliveryFromList(deli);
                            }

                        } else if (!choiseDeliver) {
                            System.out.println("You don't selected any delivery. If you want to do it, please try again!!");
                            System.out.println(CLOSE);
                        }
                    } else {
                        System.out.println("There are no deliveries available at the moment!");
                        System.out.println(CLOSE);
                    }
                } else {
                    System.out.println("There are no Scooters or Drones available at the moment!");
                    System.out.println(CLOSE);

                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Error in the introduction of data");
            main.menuStartAdmin();
        }
    }

    private void printDeliveries(List<Delivery> dl) {
        int auxd = 1;
        System.out.println("Deliveries available. Choose a number to select one.");
        System.out.println(SEPARATOR + "\n");
        for (Delivery deli : dl) {
            System.out.println(auxd + " - " + deli.toString());
            auxd++;
        }
        System.out.println("0 - close selection");
    }

    private Scooter printScooters(Pharmacy p) {
        List<Scooter> sl = controller.getScootersList(p).getAvailableScooterList();
        Scooter scooter = null;
        int auxs = 1;
        System.out.println("Scooters available. Choose a number to select one to compare.");
        System.out.println(SEPARATOR + "\n");
        if (!sl.isEmpty()) {
            for (Scooter s : sl) {
                System.out.println(auxs + " - " + s.toString());
                auxs++;
            }
            int indexS = Integer.parseInt(sc.nextLine());
            int scooterNmb = sl.get(indexS - 1).getExclusiveNumber();
            scooter = controller.getScootersList(p).getScooter(scooterNmb);
        } else {
            return null;
        }
        return scooter;
    }

    private Pharmacy printPharmacies() {
        List<Pharmacy> pl = controller.getPharmacyList();
        Pharmacy p = null;
        int aux = 1;
        System.out.println("\nPharmacys available. Choose a number to select one.");
        System.out.println(SEPARATOR + "\n");
        if (!pl.isEmpty()) {
            for (Pharmacy phar : pl) {
                System.out.println(aux + " - " + phar.getDesignation());
                aux++;
            }
            int index = Integer.parseInt(sc.nextLine());
            p = controller.getPharmacyList().get(index - 1);
        } else {
            return null;
        }
        return p;
    }

    private Drone printDrones(Pharmacy p) {
        List<Drone> dl = controller.getDroneList(p).getDroneList();
        Drone drone = null;
        int auxs = 1;
        System.out.println("Drones available. Choose a number to select one to compare.");
        System.out.println(SEPARATOR + "\n");
        if (!dl.isEmpty()) {
            for (Drone d : dl) {
                System.out.println(auxs + " - " + d.toString());
                auxs++;
            }
            int index = Integer.parseInt(sc.nextLine());
            int droneNmb = dl.get(index - 1).getID();
            drone = controller.getDroneList(p).getDrone(droneNmb);
        } else {
            return null;
        }
        return drone;
    }

    private Courier printcouriers() {

        List<Courier> cl = AplicationPOT.getInstance().getPlatform().getCourierList().getListCourier();
        Courier courier = null;
        int auxs = 1;
        System.out.println("Couriers available. Choose a number to select one to compare.");
        System.out.println(SEPARATOR + "\n");
        if (!cl.isEmpty()) {
            for (Courier c : cl) {
                System.out.println(auxs + " - " + c.toString());
                auxs++;
            }
            int index = Integer.parseInt(sc.nextLine());
            int courierNif = cl.get(index - 1).getNIF();
            courier = AplicationPOT.getInstance().getPlatform().getCourierList().getCourier(courierNif);
        } else {
            return null;
        }
        return courier;
    }

    private static void menu() {
        System.out.println("\n\tMENU DELIVERY");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Make a delivery with scooter.");
        System.out.println("2-Make a delivery with drone.");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }
}
