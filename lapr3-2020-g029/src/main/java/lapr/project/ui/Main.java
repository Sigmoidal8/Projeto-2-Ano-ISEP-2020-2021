package lapr.project.ui;

import java.io.File;
import lapr.project.data.DataHandler;

import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.FullFillLists;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.utils.MailUtil;


class Main extends DataHandler {

    private static Platform plat;

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Charge charge = new Charge();
        charge.setDaemon(true);
        charge.start();

        //load database properties
        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        AplicationPOT app = new AplicationPOT();
        plat = app.getPlatform();

        FullFillLists ffl = new FullFillLists();
        ffl.fullfillPharmacyList(plat);
        ffl.fullfillProductList(plat);
        ffl.fullFillClientList(plat);
        ffl.fullfillScootersList(plat);
        ffl.fullfillCourierList(plat);
        ffl.fullFillDeliveryList(plat);
        ffl.fullfillPharmacyProductList(plat);
        ffl.fullFillDroneList(plat);
        ffl.fullFillParkingLotList(plat);
          
        MainWindowUI main = new MainWindowUI();
    }

    public static class Charge extends Thread {

        List<Scooter> chargingScooterList = new ArrayList<>();
        List<Drone> chargingDroneList = new ArrayList<>();

        @Override
        public void run() {
            try {
                Charge.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (true) {
                try {
                    File dir = new File("src-C/FilesJava");
                    File[] matches = dir.listFiles(new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                            return name.endsWith(".flag");
                        }
                    });
                    if (matches != null) {
                        if (matches.length != 0) {
                            File[] matches1 = dir.listFiles(new FilenameFilter() {
                                public boolean accept(File dir, String name) {
                                    return name.endsWith(".data");
                                }
                            });
                            File f = matches[0];
                            File f1 = matches1[0];
                            Scanner sc = new Scanner(f1);
                            sc.useLocale(Locale.US);
                            double hours = sc.nextDouble();
                            int scooterNumber = (int) sc.nextDouble();
                            if (plat.getScootersList().getScooter(scooterNumber) != null) {
                                int courierNIF = (int) sc.nextDouble();
                                Scooter scooter = plat.getScootersList().getScooter(scooterNumber);
                                Courier courier = plat.getCourierList().getCourier(courierNIF);
                                scooter.setCurrentCharge(scooter.getFullCharge());
                                scooter.update();
                                PharmacyList pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();
                                Pharmacy scooterPharmacy = null;
                                for (Pharmacy p : pl.getPharmacyList()) {
                                    if (p.getAsl().getAvailableScooterList().contains(scooter)) {
                                        scooterPharmacy = p;
                                    }
                                }
                                boolean newScooter = true;
                                for (Scooter s : chargingScooterList) {
                                    if (scooter.equals(s)) {
                                        newScooter = false;
                                        MailUtil.sendEmail(courier.getEmail(), "Another scooter is now charging. It will now take approximately " + hours + " hours.", "New Scooter Charging", scooterPharmacy);
                                    }
                                }
                                if (newScooter) {
                                    MailUtil.sendEmail(courier.getEmail(), "Your scooter is charging! It will take approximately " + hours + " hours.", "Scooter Charging", scooterPharmacy);
                                    chargingScooterList.add(scooter);
                                }
                            } else {
                                Drone drone = plat.getDronesList().getDrone(scooterNumber);
                                drone.setCurrentCharge(drone.getFullCharge());
                                drone.update();
                                PharmacyList pl = AplicationPOT.getInstance().getPlatform().getPharmacyList();
                                Pharmacy dronePharmacy = null;
                                for (Pharmacy p : pl.getPharmacyList()) {
                                    if (p.getAvailableDroneList().getDroneList().contains(drone)) {
                                        dronePharmacy = p;
                                    }
                                }
                                boolean newDrone = true;
                                for (Drone d : chargingDroneList) {
                                    if (drone.equals(d)) {
                                        newDrone = false;
                                        MailUtil.sendEmail(AplicationPOT.getInstance().getPlatform().getAutorizationFacade().getAdministrator().getEmail(), "A new drone is charging. The " + drone.toString() + " will now take approximately " + hours + " hours to charge.", "New Drone Charging", dronePharmacy);
                                    }
                                }
                                if (newDrone) {
                                    MailUtil.sendEmail(AplicationPOT.getInstance().getPlatform().getAutorizationFacade().getAdministrator().getEmail(), "The " + drone.toString() + " is charging! It will take approximately " + hours + " hours.", "Drone Charging", dronePharmacy);
                                    chargingDroneList.add(drone);
                                }
                            }
                            sc.close();
                            f.delete();
                            f1.delete();
                        }
                    }
                    Charge.sleep(60000);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Charge.sleep(60000);
                    } catch (Exception ex) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
