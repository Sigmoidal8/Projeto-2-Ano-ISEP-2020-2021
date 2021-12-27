/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import lapr.project.controller.AddDroneController;
import lapr.project.controller.AddScooterController;
import lapr.project.controller.AplicationPOT;
import lapr.project.controller.BuyAProductController;
import lapr.project.controller.EditParkingLotCharacteristicsController;
import lapr.project.controller.InsertParkingLotController;
import lapr.project.controller.InsertPharmacyDataController;
import lapr.project.controller.MakeDeliveryController;
import lapr.project.controller.RegisterClientController;
import lapr.project.controller.RegisterCourierController;
import lapr.project.controller.RegisterDroneController;
import lapr.project.controller.RegisterProductController;
import lapr.project.controller.RegisterScooterController;
import lapr.project.controller.RemoveDroneController;
import lapr.project.controller.RemovePharmacyController;
import lapr.project.controller.RemoveScooterController;
import lapr.project.controller.UpdateStockController;
import lapr.project.data.InvoiceDB;
import lapr.project.data.PharmacyProductDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.ClientRegister;
import lapr.project.model.Courier;
import lapr.project.model.Delivery;
import lapr.project.model.DeliveryList;
import lapr.project.model.Drone;
import lapr.project.model.Invoice;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ProductList;
import lapr.project.model.Scooter;
import lapr.project.utils.GraphAlgorithms;
import lapr.project.utils.MailUtil;

public class ReadFile {

    private static final String PHARMACY_FILE = "Files/Pharmacies.txt";
    private static final String SCOOTER_FILE = "Files/Scooters.txt";
    private static final String DRONE_FILE = "Files/Drones.txt";
    private static final String CLIENT_FILE = "Files/Clients.txt";
    private static final String COURIER_FILE = "Files/Couriers.txt";
    private static final String PRODUCT_FILE = "Files/Products.txt";
    private static final String ADD_DRONE_FILE = "Files/AddDrones.txt";
    private static final String REMOVE_DRONE_FILE = "Files/RemoveDrones.txt";
    private static final String ADD_SCOOTER_FILE = "Files/AddScooter.txt";
    private static final String REMOVE_SCOOTER_FILE = "Files/RemoveScooter.txt";
    private static final String ADD_PARKING_LOT_FILE = "Files/AddParkingLot.txt";
    private static final String REMOVE_PHARMACY_FILE = "Files/RemovePharmacy.txt";
    private static final String EDIT_PARKIG_LOT_CHARACTERISTICS_FILE = "Files/EditParkingLotCharacteristics.txt";
    private static final String UPDATE_STOCK_FILE = "Files/UpdateStock.txt";
    private static final String MAKE_DELIVERY_FILE = "Files/MakeDelivery.txt";
    private static final String BUY_PRODUCT_FILE = "Files/BuyProduct.txt";
    private static final String DELIVERY_DATA_FILE = "Files/DeliveryData.txt";
    private static final String TERRESTRIAL_SCENARIO0_FILE = "Files/TerrestrialScenario0.txt";
    private static final String AERIAL_SCENARIO0_FILE = "Files/AerialScenario0.txt";
    private static final String DRONE_RESTRICTIONS_FILE = "Files/AerealRestrictions.txt";
    private static final String SCOOTER_RESTRICTIONS_FILE = "Files/TerrestrialRestrictions.txt";
    private static final String PRODUCTS_AERIAL_FILE = "FilesAerialScenarios/Products.txt";
    private static final String MAKE_DELIVERY_AERIAL_FILE = "FilesAerialScenarios/MakeDelivery.txt";

    private static final String FILE_NOT_FOUND = "File Not Found";
    private static final String ERROR_IN_THE_DATA = "Error in the data of the file";

    private final MainWindowUI main;
    private final InsertPharmacyDataController controllerPharmacy;
    private final RegisterScooterController controllerScooter;
    private final RegisterDroneController controllerDrone;
    private final RegisterClientController controllerClient;
    private final RegisterCourierController controllerCourier;
    private final RegisterProductController controllerProduct;
    private final AddDroneController controllerAddDrone;
    private final RemoveDroneController controllerRemoveDrone;
    private final AddScooterController controllerAddScooter;
    private final RemoveScooterController controllerRemoveScooter;
    private final InsertParkingLotController controllerInsertParkingLot;
    private final RemovePharmacyController controllerRemovePharmacy;
    private final EditParkingLotCharacteristicsController controllerEditParkingLotCharacteristics;
    private final UpdateStockController controllerUpdateStock;

    public ReadFile(MainWindowUI main) {
        this.main = main;
        controllerPharmacy = main.getGetInsertPharmacyDataController();
        controllerScooter = main.getGetRegisterScooterController();
        controllerDrone = main.getRegisterDroneController();
        controllerClient = main.getRegisterClientController();
        controllerCourier = main.getRegisterCourierController();
        controllerProduct = main.getRegisterProductController();
        controllerAddDrone = main.getAddDroneController();
        controllerRemoveDrone = main.getRemoveDroneController();
        controllerAddScooter = main.getGetAddScooterController();
        controllerRemoveScooter = main.getGetRemoveScooterController();
        controllerInsertParkingLot = main.getInsertParkingLotController();
        controllerRemovePharmacy = main.getRemovePharmacyController();
        controllerEditParkingLotCharacteristics = main.getEditParkingLotCharacteristicsController();
        controllerUpdateStock = main.getUpdateStockController();
    }

    public void readFilePharmacies() {

        try {
            Scanner sc = new Scanner(new File(PHARMACY_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                String designation = itens[0];
                String email = itens[1];
                String password = itens[2];
                int doorNumber = Integer.parseInt(itens[4]);
                String street = itens[3];
                String locality = itens[6];
                String postalCode = itens[5];
                Double decimalDegree1 = Double.parseDouble(itens[7]);
                Double decimalDegree2 = Double.parseDouble(itens[8]);
                controllerPharmacy.createAdress(doorNumber, street, locality, postalCode, decimalDegree1, decimalDegree2);
                controllerPharmacy.createPharmacy(designation, email, password);
                controllerPharmacy.registerPharmacy();
            }
            System.out.println("Pharmacies Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileScooters() {

        try {
            Scanner sc = new Scanner(new File(SCOOTER_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                int exclusiveNumber = controllerScooter.generateExclusiveNumber();
                File image = controllerScooter.generateQRCode(exclusiveNumber);
                double fullCharge = Double.parseDouble(itens[0]);
                double power = Double.parseDouble(itens[1]);
                int operational = Integer.parseInt(itens[2]);
                Scooter s = controllerScooter.newScooter(exclusiveNumber, fullCharge, power, operational, image);
                controllerScooter.registerScooter(s);
            }
            System.out.println("Scooters Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileDrones() {
        try {
            Scanner sc = new Scanner(new File(DRONE_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                int id = controllerDrone.generateID();

                double fullCharge = Double.parseDouble(itens[0]);
                double power = Double.parseDouble(itens[1]);

                Drone d = controllerDrone.newDrone(id, fullCharge, power);
                controllerDrone.registerDrone(d);
            }
            System.out.println("Drones Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileClients() {
        try {
            Scanner sc = new Scanner(new File(CLIENT_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                String name = itens[0];
                String password = itens[1];
                String email = itens[2];
                String nif = itens[3];
                String street = itens[4];
                int doorNumber = Integer.parseInt(itens[5]);
                String locality = itens[7];
                String postalCode = itens[6];
                double decimalDegree1 = Double.parseDouble(itens[8]);
                double decimalDegree2 = Double.parseDouble(itens[9]);

                boolean existClient = controllerClient.existsEmail(email);
                controllerClient.newClient(name, password, email, nif, street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
                controllerClient.registerClient();
            }
            System.out.println("Clients Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileCouriers() {
        try {
            Scanner sc = new Scanner(new File(COURIER_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                String name = itens[0];
                String nif = itens[1];
                String ssc = itens[2];
                String email = itens[3];
                String pass = itens[4];

                boolean existCourier = controllerCourier.existsEmail(email);
                if (!existCourier) {
                    controllerCourier.newCourier(name, nif, ssc, email, pass);
                    controllerCourier.registerCourier();
                }
            }
            System.out.println("Couriers Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileProduct() {
        try {
            Scanner sc2 = new Scanner(new File(PRODUCT_FILE));
            sc2.nextLine();
            ProductList proL2 = AplicationPOT.getInstance().getPlatform().getProductList();
            while (sc2.hasNextLine()) {

                String line = sc2.nextLine();
                String[] itens2 = line.split(";");

                String name = itens2[0];
                float price2 = Float.parseFloat(itens2[1]);
                float weight2 = Float.parseFloat(itens2[2]);
                int stock2 = Integer.parseInt(itens2[3]);

                List<Pharmacy> pl = controllerProduct.getPharmacyList().getPharmacyList();
                int index = Integer.parseInt(itens2[4]);
                String pharmacyID2 = pl.get(index).getId();
                Pharmacy p2 = controllerProduct.getPharmacyList().getPharmacy(pharmacyID2);

                Product prod = controllerProduct.newProduct(name, price2, weight2, pharmacyID2);
                if (proL2.getProduct(prod.getID()) == null) {
                    proL2.addProduct(prod);
                    controllerProduct.registerProduct(prod);
                } else {
                    p2.getProductList().addProduct(prod);
                }
                new PharmacyProductDB().addPharmacyProduct(prod, p2, stock2);
            }
            System.out.println("Products Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileAddDrone() {
        try {
            Scanner sc = new Scanner(new File(ADD_DRONE_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                int dID = Integer.parseInt(itens[0]);
                Drone d = controllerAddDrone.getDrone(dID);
                String pharID = itens[1];
                controllerAddDrone.setDronePharmacy(pharID, d);
            }
            System.out.println("Drones added to the Pharmacies");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileRemoveDrone() {
        try {
            Scanner sc = new Scanner(new File(REMOVE_DRONE_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String pharID = itens[0];
                int dID = Integer.parseInt(itens[1]);
                controllerRemoveDrone.removeDronePharmacy(dID, controllerRemoveDrone.getDronesPharmacy(pharID));

            }
            System.out.println("Drones Removed from the Pharmacies");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileAddScooter() {
        try {
            Scanner sc = new Scanner(new File(ADD_SCOOTER_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                int scotID = Integer.parseInt(itens[0]);
                Scooter scot = controllerAddScooter.getScooter(scotID);

                String pharID = itens[1];
                Pharmacy phar = controllerAddScooter.getPharmacy(pharID);
                controllerAddScooter.addAvailableScooterList(scot, phar);

            }
            System.out.println("Scooters added to the pharmacies");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileRemoveScooter() {
        try {
            Scanner sc = new Scanner(new File(REMOVE_SCOOTER_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String pharID = itens[0];
                Pharmacy phar = controllerRemoveScooter.getPharmacy(pharID);

                int scotID = Integer.parseInt(itens[1]);
                Scooter scot = controllerRemoveScooter.getScooter(scotID, phar);

                controllerRemoveScooter.removeScooterFromAvailableScooterList(scot, phar);
            }
            System.out.println("Scooters removed from the pharmacies");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileInsertParkingLot() {
        try {
            Scanner sc = new Scanner(new File(ADD_PARKING_LOT_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String pharmacyID = itens[0];
                controllerInsertParkingLot.getPharmacyFromID(pharmacyID);

                int parkingLotSpots = Integer.parseInt(itens[1]);
                int parkingLotChargeSpots = Integer.parseInt(itens[2]);
                int choice2 = Integer.parseInt(itens[3]);
                String parkingLotType = "";
                if (choice2 == 1) {
                    parkingLotType = "scooter";
                } else {
                    parkingLotType = "drone";
                }
                controllerInsertParkingLot.createParkingLot(parkingLotSpots, parkingLotChargeSpots, parkingLotType);
                controllerInsertParkingLot.registerParkingLot();

            }
            System.out.println("Parking Lots added to the pharmacies");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileRemovePharmacy() {
        try {
            Scanner sc = new Scanner(new File(REMOVE_PHARMACY_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String pharmacyID = itens[0];
                controllerRemovePharmacy.removePharmacy(pharmacyID);

            }
            System.out.println("Pharmacies removed");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileEditParkingLotCharacteristics() {
        try {
            Scanner sc = new Scanner(new File(EDIT_PARKIG_LOT_CHARACTERISTICS_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String pharmacyID = itens[0];
                Pharmacy p = controllerEditParkingLotCharacteristics.getPharmacy(pharmacyID);

                List<ParkingLot> prklL = controllerEditParkingLotCharacteristics.getParkingLotList();

                int cmd2 = Integer.parseInt(itens[3]);
                String parkID = prklL.get(cmd2).getId();

                ParkingLot prkl = controllerEditParkingLotCharacteristics.getParkingLot(parkID);

                int comando2 = Integer.parseInt(itens[1]);

                int comando3 = Integer.parseInt(itens[2]);

                if (comando2 == 1) {
                    prkl = controllerEditParkingLotCharacteristics.setTotalSpots(comando3);

                } else {
                    if (comando2 == 2) {
                        prkl = controllerEditParkingLotCharacteristics.setChargeSpots(comando3);

                    }
                }
            }
            System.out.println("Parking Lots edited");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error in the data of the file");
        }
    }

    public void readFileUpdateStock() {
        try {
            Scanner sc = new Scanner(new File(UPDATE_STOCK_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String pharmacyID = itens[0];
                Pharmacy p = controllerUpdateStock.getPharmacy(pharmacyID);
                String productID = itens[1];

                Product pr = controllerUpdateStock.getProduct(productID);
                int st = controllerUpdateStock.getStock(pr.getID(), p.getId());
                int comando3 = Integer.parseInt(itens[2]);
                controllerUpdateStock.setStock(comando3, pr.getID(), p.getId());
            }
            System.out.println("Stock updated");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileMakeDelivery() throws IOException {
        try {
            Scanner sc = new Scanner(new File(MAKE_DELIVERY_FILE));
            sc.nextLine();
            while (sc.hasNextLine()) {

                MakeDeliveryController controllerMakeDelivery = new MakeDeliveryController();

                String line = sc.nextLine();
                String[] itens = line.split(";");

                boolean hasPharmacy = false;
                boolean hasScooter = false;
                boolean hasDrone = false;
                System.out.println("=======================================================");
                System.out.println("\t\tShow Delivery Statistics:");
                System.out.println("=======================================================");

                //escolhe a farmacia
                Pharmacy p;
                int index = Integer.parseInt(itens[0]);
                p = controllerMakeDelivery.getPharmacyList().get(index - 1);
                if (!p.equals(null)) {
                    hasPharmacy = true;
                    //escolhe uma scooter
                    Scooter scooter;
                    List<Scooter> sl = controllerMakeDelivery.getScootersList(p).getAvailableScooterList();
                    int indexS = Integer.parseInt(itens[1]);
                    int scooterNmb = sl.get(indexS - 1).getExclusiveNumber();
                    scooter = controllerMakeDelivery.getScootersList(p).getScooter(scooterNmb);
                    //escolhe um courier
                    List<Courier> cl2 = AplicationPOT.getInstance().getPlatform().getCourierList().getListCourier();
                    Courier courier2 = null;

                    int index2 = Integer.parseInt(itens[2]);
                    int courierNif = cl2.get(index2 - 1).getNIF();
                    courier2 = AplicationPOT.getInstance().getPlatform().getCourierList().getCourier(courierNif);
                    //escolhe um drone
                    List<Drone> drl2 = controllerMakeDelivery.getDroneList(p).getDroneList();
                    Drone drone = null;
                    int index3 = Integer.parseInt(itens[3]);
                    int droneNmb = drl2.get(index3 - 1).getID();
                    drone = controllerMakeDelivery.getDroneList(p).getDrone(droneNmb);
                    if (!scooter.equals(null) || !courier2.equals(null)) {
                        hasScooter = true;
                    }
                    if (!drone.equals(null)) {
                        hasDrone = true;
                    }

                    if (hasScooter && hasDrone) {
                        //Lista de deliveries daquela farmacia
                        List<Delivery> dl2 = controllerMakeDelivery.getDeliveryList().getDeliveryList(p);
                        if (!dl2.isEmpty()) {
                            List<Invoice> invoiceList = new ArrayList<>();
                            boolean choiseDeliver2 = false;
                            int choise2 = 1;
                            int i = 4;
                            while (choise2 != 0) {
                                choise2 = Integer.parseInt(itens[i]);
                                if (choise2 != 0) {
                                    if (choise2 > dl2.size()) {
                                        System.out.println("This delivery doesn´t exist in the list");
                                    } else {
                                        if (!choiseDeliver2) {
                                            choiseDeliver2 = true;
                                        }
                                        String deliveryID = dl2.get(choise2 - 1).getId();
                                        Delivery delivery = controllerMakeDelivery.getChoosenDelivery(deliveryID);
                                        boolean addToScooter = controllerMakeDelivery.addDeliverySelected(delivery, courier2);
                                        if (!addToScooter) {
                                            System.out.println("This delivery does not fit in your bag because it exceeds the limit weight or exceeds the scooters energy level");
                                        }
                                        boolean addToDrone = controllerMakeDelivery.addDeliverySelected(delivery, drone);
                                        if (!addToDrone) {
                                            System.out.println("This delivery does not fit in your bag because it exceeds the limit weight");
                                        }
                                        if (addToScooter || addToDrone) {
                                            invoiceList.add(delivery.getInvoice());
                                        }
                                    }
                                }
                                i++;
                            }
                            if (choiseDeliver2) {
                                List<Address> pathScooter = controllerMakeDelivery.calculateFullPathScooter(controllerMakeDelivery.getDeliverySelectedScooter(), scooter);
                                double distanceScooter = controllerMakeDelivery.getDistance();
                                ArrayList<Double> energyTime = controllerMakeDelivery.getEnergySpent(distanceScooter, scooter, pathScooter);
                                double energyScooter = energyTime.get(0);
                                double timeScooter = energyTime.get(1);
                                List<Address> pathDrone = controllerMakeDelivery.calculateFullPathDrone(controllerMakeDelivery.getDeliverySelectedDrone(), drone);
                                double distanceDrone = controllerMakeDelivery.getDistance();
                                ArrayList<Double> energyTime2 = controllerMakeDelivery.getEnergySpent(distanceDrone, drone);
                                double energyDrone = energyTime2.get(0);
                                double timeDrone = energyTime2.get(1);
                                System.out.println("\n=======================================================");
                                System.out.println("\t\tRoute with Scooter:");
                                System.out.println("=======================================================");
                                if (hasScooter || !pathScooter.isEmpty()) {
                                    System.out.println("\nMost economic route:");
                                    for (Address a : pathScooter) {
                                        if (a.getDoorNumber() == 0) {
                                            System.out.printf("%n%s", a.getStreet());
                                        } else {
                                            System.out.printf("%n%s Door:%d ", a.getStreet(), a.getDoorNumber());
                                        }
                                    }
                                    System.out.println();
                                    System.out.printf("%nDistance: %f Km", distanceScooter / 1000);
                                    System.out.printf("%nEnergy spent on the delivery: %.2f Wh", energyScooter);
                                    System.out.printf("%nTime spent on the delivery: %.2f s", timeScooter);
                                    System.out.printf("%nScooter's area: %.2f m^2", controllerMakeDelivery.getDeliveryList().getAreaScooter());
                                    System.out.printf("%nScooter's Aerodynamic Coeficient: %.2f ", controllerMakeDelivery.getDeliveryList().getCdScooter());
                                    System.out.printf("%nRoling Coeficient: %.2f ", controllerMakeDelivery.getDeliveryList().getRolingCoeficient());
                                    System.out.printf("%nDensity: %.2f kg/m^3", controllerMakeDelivery.getDeliveryList().getDensity());
                                    System.out.printf("%nAverage Speed: %.2f km/h", energyTime.get(2));
                                    System.out.printf("%nWind Speed: %.2f km/h", controllerMakeDelivery.getDeliveryList().getWindSpeed());
                                    System.out.printf("%nWind Angle: %.2fº ", controllerMakeDelivery.getDeliveryList().getWindAngle());
                                    System.out.printf("%nWeight Delivery: %.2f kg", controllerMakeDelivery.calcWeight(controllerMakeDelivery.getDeliverySelectedScooter()));
                                } else {
                                    System.out.println("There are no scooters available at the moment or no path possible!");
                                }

                                System.out.println("\n=======================================================");
                                System.out.println("\t\tRoute with Drones:");
                                System.out.println("=======================================================");
                                if (hasDrone || !pathDrone.isEmpty()) {
                                    System.out.println("Most economic route:");
                                    for (Address a2 : pathDrone) {
                                        if (a2.getDoorNumber() == 0) {
                                            System.out.printf("%n%s", a2.getStreet());
                                        } else {
                                            System.out.printf("%n%s Door:%d ", a2.getStreet(), a2.getDoorNumber());
                                        }
                                    }
                                    System.out.println();
                                    System.out.printf("%nDistance: %f Km", distanceDrone / 1000);
                                    System.out.printf("%nEnergy spent on the delivery: %.2f Wh", energyDrone);
                                    System.out.printf("%nTime spent on the delivery: %.2f s", timeDrone);
                                    System.out.printf("%nTime spent on the delivery: %.2f s", timeDrone);
                                    System.out.printf("%nDrone's lift distance: %.2f m", controllerMakeDelivery.getDeliveryList().getLiftDistance());
                                    System.out.printf("%nDrone's lift distance: %.2f m", controllerMakeDelivery.getDeliveryList().getLiftDistance());
                                    System.out.printf("%nAverage Speed: %.2f km/h", drone.getAverageSpeed());
                                    System.out.printf("%nWind Speed: %.2f km/h", controllerMakeDelivery.getDeliveryList().getWindSpeed());
                                    System.out.printf("%nWind Angle: %.2fº ", controllerMakeDelivery.getDeliveryList().getWindAngle());
                                    System.out.printf("%nLift to drag ratio: %.2f ", controllerMakeDelivery.getDeliveryList().getrDrone());
                                    System.out.printf("%nPower transfer efficiency: %.2f ", controllerMakeDelivery.getDeliveryList().getnPower());
                                    System.out.printf("%nPower Consumption of eletronics: %.2f kW", controllerMakeDelivery.getDeliveryList().getPavio());
                                    System.out.printf("%nWeight Delivery: %.2f kg", controllerMakeDelivery.calcWeight(controllerMakeDelivery.getDeliverySelectedDrone()));
                                } else {
                                    System.out.println("There are no drones available at the moment or path possible!");
                                }
                                int scan = 1;
                                while (scan != 0) {
                                    scan = Integer.parseInt(itens[i]);
                                    switch (scan) {
                                        case 1:
                                            if (hasScooter) {
                                                double stopsScooter = energyScooter / scooter.getFullCharge();
                                                if (stopsScooter < 1) {
                                                    scooter.setCurrentCharge(scooter.getCurrentCharge() - energyScooter);
                                                } else {
                                                    scooter.setCurrentCharge(energyScooter - (stopsScooter * scooter.getFullCharge()));
                                                }
                                                scooter.update();
                                            } else {
                                                System.out.println("There are no scooters available at the moment!");
                                            }
                                            i++;
                                            break;
                                        case 2:
                                            if (hasDrone) {
                                                double stopsDrone2 = energyDrone / drone.getFullCharge();
                                                if (stopsDrone2 < 1) {
                                                    drone.setCurrentCharge((drone.getCurrentCharge() - energyDrone));
                                                } else {
                                                    drone.setCurrentCharge(energyDrone - (stopsDrone2 * drone.getFullCharge()));
                                                }

                                                Pharmacy pharmacyPark2 = controllerMakeDelivery.getPharmacyToPark(pathDrone.get(pathDrone.size() - 1));
                                                System.out.printf("%nIt will park in the Pharmacy: %s\n", pharmacyPark2.getDesignation());
                                                List<Address> pathBack2 = new ArrayList<>();
                                                pathBack2.add(pathDrone.get(pathDrone.size() - 1));
                                                pathBack2.add(pharmacyPark2.getAdress());
                                                controllerMakeDelivery.getPathToPharmacy(pathBack2);
                                                List<Double> energyTime32 = controllerMakeDelivery.getEnergySpentInDeliver(drone);
                                                double energyDrone2 = energyTime32.get(0);
                                                if (drone.getCurrentCharge() - energyDrone2 < 0) {
                                                    drone.setCurrentCharge(0);
                                                } else {
                                                    drone.setCurrentCharge(drone.getCurrentCharge() - energyDrone2);
                                                }
                                                drone.update();
                                            } else {
                                                System.out.println("There are no drones available at the moment!");
                                            }
                                            i++;
                                            break;

                                        case 0:
                                            break;
                                    }
                                }
                                for (Invoice in2 : invoiceList) {
                                    controllerMakeDelivery.sendEmail(in2.getClient(), in2, p);
                                }
                                for (Delivery deli2 : controllerMakeDelivery.getDeliverySelectedScooter()) {
                                    controllerMakeDelivery.removeDeliveryFromList(deli2);
                                }
                                for (Delivery deli2 : controllerMakeDelivery.getDeliverySelectedDrone()) {
                                    controllerMakeDelivery.removeDeliveryFromList(deli2);
                                }
                            } else if (!choiseDeliver2) {
                                System.out.println("You don't selected any delivery. If you want to do it, please try again!!");
                                System.out.println("Make delivery menu is closing......");
                            }
                        } else {
                            System.out.println("There are no deliveries available at the moment!");
                            System.out.println("Make delivery menu is closing......");
                        }
                    } else {
                        System.out.println("There are no Scooters or Drones available at the moment!");
                        System.out.println("Make delivery menu is closing......");
                    }
                }
                System.out.println("");
                System.out.println("Delivery Made");
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileBuyProduct(String file) throws MessagingException, IOException {
        try {
            Scanner sc = new Scanner(new File(file));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");

                BuyAProductController controllerBuyProduct = new BuyAProductController();

                Platform plat = AplicationPOT.getInstance().getPlatform();
                ClientRegister rcl = plat.getClientRegister();
                Client client = rcl.getClientByEmail(itens[0]);
                controllerBuyProduct.setClient(client);
                Address clientAdress = client.getAdress();
                Pharmacy p = controllerBuyProduct.fowardDeliveryToClosestPharmacy(controllerBuyProduct.getPharmacyList().getPharmacyList(), clientAdress);

                String menu;
                String verify;
                Map<Product, Integer> mp = new HashMap<>();
                mp.clear();
                int i = 1;
                do {
                    List<Product> prl = controllerBuyProduct.getPharmacy(p.getId()).getProductList().getProductList();
                    int indexProduct = Integer.parseInt(itens[i]);
                    String productID = prl.get(indexProduct).getID();

                    Product pr = controllerBuyProduct.getProduct(productID);
                    int st = controllerBuyProduct.getStock(pr.getID(), p.getId());

                    int quantity = Integer.parseInt(itens[i + 1]);
                    if (quantity <= st) {
                        mp = controllerBuyProduct.preencherMapa(pr, quantity);
                        controllerBuyProduct.updateStock(p.getId(), pr.getID(), st, quantity);

                    } else {
                        int numberOfProductsTransfered = quantity - st;
                        Pharmacy closestPharmacy = controllerBuyProduct.closestPharmacyWithStock(plat.getPharmacyList().getPharmacyList(), p, pr, numberOfProductsTransfered);
                        if (closestPharmacy != null) {
                            int st2 = controllerBuyProduct.getStock(pr.getID(), closestPharmacy.getId());
                            if (st2 < numberOfProductsTransfered) {
                                System.out.println("This product is not available in this pharmacy, neither in a pharmacy close enough to get it in one of our scooters!");
                            } else {
                                controllerBuyProduct.updateStock(p.getId(), pr.getID(), st, st);
                                controllerBuyProduct.updateStock(closestPharmacy.getId(), pr.getID(), st2, numberOfProductsTransfered);//pharmacy that will lend the product
                                MailUtil.sendEmail(closestPharmacy.getEmail(),
                                        "This pharmacy will be sending a total of " + numberOfProductsTransfered + " " + pr.getNome() + ". The receiving pharmacy:" + p.getDesignation(),
                                        "Transfer Note",
                                        p);
                                MailUtil.sendEmail(p.getEmail(),
                                        "This pharmacy will be receiving a total of " + numberOfProductsTransfered + " " + pr.getNome() + ". The providing pharmacy: " + closestPharmacy.getDesignation(),
                                        "Delivery Note",
                                        closestPharmacy);
                            }
                        } else {
                            System.out.println("Currently, there is no scooter with enough charge to make this trip.");
                        }

                    }
                    verify = itens[i + 2];
                    i = i + 3;
                } while (!"-1".equals(verify));

                Invoice inv = controllerBuyProduct.newInvoice(mp, client);

                for (Product prrr : mp.keySet()) {
                    System.out.println("\n" + prrr.getNome() + " quantity: " + mp.get(prrr));
                }
                System.out.printf("\nTotal Price: %.2f €\n", inv.getPrice());

                int cred = Integer.parseInt(itens[i]);

                if (cred == 1) {
                    double vCred = controllerBuyProduct.verifyCredits();
                    if (vCred > 0) {

                        double iPrice = inv.getPrice();
                        inv.setPrice(iPrice - controllerBuyProduct.TAX);
                        controllerBuyProduct.addCredits(vCred);

                    } else {
                        System.out.println("Not enough credits");
                    }
                }

                String ccInfo = itens[i + 1];

                //meter na base de dados a invoice
                new InvoiceDB().addInvoice(inv);

                String invID = inv.getId();
                for (Product prod : inv.getMp().keySet()) {
                    String prodID = prod.getID();
                    new InvoiceDB().addInvoiceProduct(invID, prodID, mp.get(prod));
                }

                double credits = controllerBuyProduct.calcCredits(inv);
                controllerBuyProduct.addCredits(credits);
                controllerBuyProduct.newDelivery(inv, p);
                controllerBuyProduct.registerDelivery();
                System.out.println("\nPurchase successful");
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileDeliveryData() {
        try {
            Scanner sc = new Scanner(new File(DELIVERY_DATA_FILE));
            sc.nextLine();
            String line = sc.nextLine();
            String[] itens = line.split(";");
            DeliveryList delList = AplicationPOT.getInstance().getPlatform().getDeliveryList();
            delList.setCdScooter(Double.parseDouble(itens[0]));
            delList.setAreaScooter(Double.parseDouble(itens[2]));
            delList.setAreaDrone(Double.parseDouble(itens[3]));
            delList.setDensity(Double.parseDouble(itens[4]));
            delList.setRolingCoeficient(Double.parseDouble(itens[5]));
            delList.setGravityForce(Double.parseDouble(itens[6]));
            delList.setTotalMassScooter(Double.parseDouble(itens[7]));
            delList.setTotalMassDrone(Double.parseDouble(itens[8]));
            delList.setWindAngle(Double.parseDouble(itens[9]));
            delList.setWindSpeed(Double.parseDouble(itens[10]));
            delList.setLiftDistance(Double.parseDouble(itens[11]));
            delList.setLiftSpeed(Double.parseDouble(itens[12]));
            System.out.println("Delivery Data added");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error setting the data");
        }
    }

    public void readFileTerrestresScenario0() {
        try {
            Scanner sc = new Scanner(new File(TERRESTRIAL_SCENARIO0_FILE));
            String cabecalho = sc.nextLine();
            System.out.println("Terrestrial Scenarios");
            int i = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                double distance = Double.parseDouble(itens[0]);
                double powerScooter = Double.parseDouble(itens[1]);
                double chargeScooter = Double.parseDouble(itens[2]);
                double rollingCoeficient = Double.parseDouble(itens[3]);
                double windAngle = Double.parseDouble(itens[4]);
                double windSpeed = Double.parseDouble(itens[5]);
                double totalWeightDelivery = Double.parseDouble(itens[6]);
                double dd1 = Double.parseDouble(itens[7]);
                double dd2 = Double.parseDouble(itens[8]);
                double dd3 = Double.parseDouble(itens[9]);
                double dd4 = Double.parseDouble(itens[10]);
                DeliveryList dl = AplicationPOT.getInstance().getPlatform().getDeliveryList();
                dl.setRolingCoeficient(rollingCoeficient);
                dl.setWindAngle(windAngle);
                dl.setWindSpeed(windSpeed);
                Scooter scooter = new Scooter(1000, chargeScooter, powerScooter, 1);
                Address adress1 = new Address("Scenarios0", "Rua Scenarios", 1, " 4400-312", "Beirute", dd1, dd2);
                Address adress2 = new Address("Scenarioss0", "Rua Scenarioss", 1, " 4401-312", "Beirute", dd3, dd4);
                ArrayList<Double> energy = dl.getEnergySpentInDeliver(distance, scooter, adress1, adress2, totalWeightDelivery);
                System.out.println("Scenario " + i + ".0");
                System.out.println(energy.get(0) < chargeScooter);
                System.out.println("Energy Consumption: " + energy.get(0) + " Wh");
                i++;
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error setting the data");
        }
    }

    public void readFileAerialScenario0() {
        try {
            Scanner sc = new Scanner(new File(AERIAL_SCENARIO0_FILE));
            String cabecalho = sc.nextLine();
            int i = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                double distance = Double.parseDouble(itens[0]);
                double powerDrone = Double.parseDouble(itens[1]);
                double chargeDrone = Double.parseDouble(itens[2]);
                double windAngle = Double.parseDouble(itens[3]);
                double windSpeed = Double.parseDouble(itens[4]);
                double totalWeightDelivery = Double.parseDouble(itens[5]);
                DeliveryList dl = AplicationPOT.getInstance().getPlatform().getDeliveryList();
                dl.setWindAngle(windAngle);
                dl.setWindSpeed(windSpeed);
                Drone drone = new Drone(1000, chargeDrone, powerDrone);
                ArrayList<Double> energy = dl.getEnergySpentInDeliver(distance, drone, totalWeightDelivery);
                System.out.println("Scenario " + i + ".0");
                System.out.println(energy.get(0) < chargeDrone);
                System.out.println("Energy Consumption: " + energy.get(0) + " Wh");
                i++;
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error setting the data");
        }
    }

    public void readGraphDroneRestrictions() {
        try {
            Scanner sc = new Scanner(new File(DRONE_RESTRICTIONS_FILE));
            String cabecalho = sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                Map<String, List<String>> mapRestrictionsDrone = AplicationPOT.getInstance().getPlatform().getListRestrictionsDrone();
                if (mapRestrictionsDrone.get(itens[0]) != null) {
                    mapRestrictionsDrone.get(itens[0]).add(itens[1]);
                } else {
                    mapRestrictionsDrone.put(itens[0], new ArrayList<>());
                    mapRestrictionsDrone.get(itens[0]).add(itens[1]);
                }
            }
            System.out.println("Aerial Restrictions Added");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error setting the data");
        }
    }

    public void readGraphScooterRestrictions() {
        try {
            Scanner sc = new Scanner(new File(SCOOTER_RESTRICTIONS_FILE));
            String cabecalho = sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] itens = line.split(";");
                Map<String, List<String>> mapRestrictionsScooter = AplicationPOT.getInstance().getPlatform().getListRestrictionsScooter();
                if (mapRestrictionsScooter.get(itens[0]) != null) {
                    mapRestrictionsScooter.get(itens[0]).add(itens[1]);
                } else {
                    mapRestrictionsScooter.put(itens[0], new ArrayList<>());
                    mapRestrictionsScooter.get(itens[0]).add(itens[1]);
                }
            }
            System.out.println("Terrestrial Restrictions Added");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error setting the data");
        }
    }

    public void readFileProductAerial() {
        try {
            Scanner sc = new Scanner(new File(PRODUCTS_AERIAL_FILE));
            sc.nextLine();
            ProductList proL = AplicationPOT.getInstance().getPlatform().getProductList();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] itens = line.split(";");

                String name = itens[0];
                float price = Float.parseFloat(itens[1]);
                float weight = Float.parseFloat(itens[2]);
                int stock = Integer.parseInt(itens[3]);

                List<Pharmacy> pl = controllerProduct.getPharmacyList().getPharmacyList();
                int index = Integer.parseInt(itens[4]);
                String pharmacyID = pl.get(index).getId();
                Pharmacy p = controllerProduct.getPharmacyList().getPharmacy(pharmacyID);

                Product prod = controllerProduct.newProduct(name, price, weight, pharmacyID);
                if (proL.getProduct(prod.getID()) == null) {
                    proL.addProduct(prod);
                    controllerProduct.registerProduct(prod);
                } else {
                    p.getProductList().addProduct(prod);
                }
                new PharmacyProductDB().addPharmacyProduct(prod, p, stock);
            }
            System.out.println("Products Registered");
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }

    public void readFileMakeDeliveryAerial() throws MessagingException {
        try {
            Scanner sc = new Scanner(new File(MAKE_DELIVERY_AERIAL_FILE));
            sc.nextLine();
            for (int j = 0; j < 4; j++) {

                MakeDeliveryController controllerMakeDelivery = new MakeDeliveryController();

                String line = sc.nextLine();
                String[] itens = line.split(";");
                AplicationPOT.getInstance().getPlatform().getDeliveryList().setWindSpeed(Double.parseDouble(itens[0]));
                AplicationPOT.getInstance().getPlatform().getDeliveryList().setWindAngle(Double.parseDouble(itens[1]));

                boolean hasPharmacy = false;
                boolean hasDrone = false;
                System.out.println("=======================================================");
                System.out.println("\t\tShow Delivery Statistics:");
                System.out.println("=======================================================");

                //escolhe a farmacia
                Pharmacy p;
                int index = Integer.parseInt(itens[2]);
                p = controllerMakeDelivery.getPharmacyList().get(index - 1);
                if (!p.equals(null)) {
                    hasPharmacy = true;
                    //escolhe um courier
                    List<Courier> cl = AplicationPOT.getInstance().getPlatform().getCourierList().getListCourier();
                    Courier courier = null;

                    int index2 = Integer.parseInt(itens[3]);
                    int courierNif = cl.get(index2 - 1).getNIF();
                    courier = AplicationPOT.getInstance().getPlatform().getCourierList().getCourier(courierNif);
                    //escolhe um drone
                    List<Drone> drl = controllerMakeDelivery.getDroneList(p).getDroneList();
                    Drone drone = null;
                    int index3 = Integer.parseInt(itens[4]);
                    int droneNmb = drl.get(index3 - 1).getID();
                    drone = controllerMakeDelivery.getDroneList(p).getDrone(droneNmb);
                    if (!drone.equals(null)) {
                        hasDrone = true;
                    }

                    if (hasDrone) {
                        //Lista de deliveries daquela farmacia
                        List<Delivery> dl = controllerMakeDelivery.getDeliveryList().getDeliveryList(p);
                        if (!dl.isEmpty()) {
                            List<Invoice> invoiceList = new ArrayList<>();
                            boolean choiseDeliver = false;
                            int choise = 1;
                            int i = 5;
                            while (choise != 0) {
                                choise = Integer.parseInt(itens[i]);
                                if (choise != 0) {
                                    if (choise > dl.size()) {
                                        System.out.println("This delivery doesn´t exist in the list");
                                    } else {
                                        if (!choiseDeliver) {
                                            choiseDeliver = true;
                                        }
                                        String deliveryID = dl.get(choise - 1).getId();
                                        Delivery delivery = controllerMakeDelivery.getChoosenDelivery(deliveryID);
                                        boolean addToDrone = controllerMakeDelivery.addDeliverySelected(delivery, drone);
                                        if (!addToDrone) {
                                            System.out.println("This delivery does not fit in your bag because it exceeds the limit weight");
                                        }
                                        if (addToDrone) {
                                            invoiceList.add(delivery.getInvoice());
                                        }
                                    }
                                }
                                i++;
                            }
                            if (choiseDeliver) {
                                List<Address> pathDrone = controllerMakeDelivery.calculateFullPathDrone(controllerMakeDelivery.getDeliverySelectedDrone(), drone);
                                double distanceDrone = controllerMakeDelivery.getDistance();
                                ArrayList<Double> energyTime2 = controllerMakeDelivery.getEnergySpent(distanceDrone, drone);
                                double energyDrone = energyTime2.get(0);
                                double timeDrone = energyTime2.get(1);

                                System.out.println("\n=======================================================");
                                System.out.println("\t\tRoute with Drones:");
                                System.out.println("=======================================================");
                                if (hasDrone && !pathDrone.isEmpty()) {
                                    System.out.println("Most economic route:");
                                    for (Address a : pathDrone) {
                                        if (a.getDoorNumber() == 0) {
                                            System.out.printf("%n%s", a.getStreet());
                                        } else {
                                            System.out.printf("%n%s Door:%d ", a.getStreet(), a.getDoorNumber());
                                        }
                                    }
                                    System.out.println();
                                    System.out.printf("%nDistance: %f Km", distanceDrone / 1000);
                                    System.out.printf("%nEnergy spent on the delivery: %.2f Wh", energyDrone);
                                    System.out.printf("%nTime spent on the delivery: %.2f s", timeDrone);
                                    System.out.printf("%nTime spent on the delivery: %.2f s", timeDrone);
                                    System.out.printf("%nDrone's lift distance: %.2f m", controllerMakeDelivery.getDeliveryList().getLiftDistance());
                                    System.out.printf("%nDrone's lift distance: %.2f m", controllerMakeDelivery.getDeliveryList().getLiftDistance());
                                    System.out.printf("%nAverage Speed: %.2f km/h", drone.getAverageSpeed());
                                    System.out.printf("%nWind Speed: %.2f km/h", controllerMakeDelivery.getDeliveryList().getWindSpeed());
                                    System.out.printf("%nWind Angle: %.2fº ", controllerMakeDelivery.getDeliveryList().getWindAngle());
                                    System.out.printf("%nLift to drag ratio: %.2f ", controllerMakeDelivery.getDeliveryList().getrDrone());
                                    System.out.printf("%nPower transfer efficiency: %.2f ", controllerMakeDelivery.getDeliveryList().getnPower());
                                    System.out.printf("%nPower Consumption of eletronics: %.2f kW", controllerMakeDelivery.getDeliveryList().getPavio());
                                    System.out.printf("%nWeight Delivery: %.2f kg", controllerMakeDelivery.calcWeight(controllerMakeDelivery.getDeliverySelectedDrone()));
                                    double stopsDrone = energyDrone / drone.getFullCharge();
                                    if (stopsDrone < 1) {
                                        drone.setCurrentCharge((drone.getCurrentCharge() - energyDrone));
                                    } else {
                                        drone.setCurrentCharge(energyDrone - (stopsDrone * drone.getFullCharge()));
                                    }

                                    Pharmacy pharmacyPark = controllerMakeDelivery.getPharmacyToPark(pathDrone.get(pathDrone.size() - 1));
                                    System.out.printf("%nIt will park in the Pharmacy: %s\n", pharmacyPark.getDesignation());
                                    List<Address> pathBack = new ArrayList<>();
                                    pathBack.add(pathDrone.get(pathDrone.size() - 1));
                                    pathBack.add(pharmacyPark.getAdress());
                                    controllerMakeDelivery.getPathToPharmacy(pathBack);
                                    List<Double> energyTime3 = controllerMakeDelivery.getEnergySpentInDeliver(drone);
                                    double energyDrone2 = energyTime3.get(0);
                                    if (drone.getCurrentCharge() - energyDrone2 < 0) {
                                        drone.setCurrentCharge(0);
                                    } else {
                                        drone.setCurrentCharge(drone.getCurrentCharge() - energyDrone2);
                                    }
                                    drone.update();
                                    i++;
                                    System.out.println("");
                                    System.out.println("Delivery Made");
                                } else {
                                    System.out.println("There are no drones available at the moment or path possible!");
                                    i++;
                                }
                                for (Invoice in : invoiceList) {
                                    controllerMakeDelivery.sendEmail(in.getClient(), in, p);
                                }
                                for (Delivery deli : controllerMakeDelivery.getDeliverySelectedScooter()) {
                                    controllerMakeDelivery.removeDeliveryFromList(deli);
                                }
                                for (Delivery deli : controllerMakeDelivery.getDeliverySelectedDrone()) {
                                    controllerMakeDelivery.removeDeliveryFromList(deli);
                                }
                            } else if (!choiseDeliver) {
                                System.out.println("You don't selected any delivery. If you want to do it, please try again!!");
                                System.out.println("Make delivery menu is closing......");
                            }
                        } else {
                            System.out.println("There are no deliveries available at the moment!");
                            System.out.println("Make delivery menu is closing......");
                        }
                    } else {
                        System.out.println("There are no Drones available at the moment!");
                        System.out.println("Make delivery menu is closing......");
                    }
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ERROR_IN_THE_DATA);
        }
    }
}
