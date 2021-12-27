/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.mail.MessagingException;
import lapr.project.controller.AddDroneController;
import lapr.project.controller.AddScooterController;
import lapr.project.controller.AplicationPOT;
import lapr.project.controller.BuyAProductController;
import lapr.project.controller.EditParkingLotCharacteristicsController;
import lapr.project.controller.InsertParkingLotController;
import lapr.project.controller.InsertPharmacyDataController;
import lapr.project.controller.LoginController;
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

public class MainWindowUI {

    private final Scanner sc = new Scanner(System.in);

    private final UpdateStockController getUpdateStockController;

    private final RegisterScooterController getRegisterScooterController;

    private final AddScooterController getAddScooterController;

    private final RemoveScooterController getRemoveScooterController;

    private final RegisterProductController getRegisterProductController;

    private final RegisterClientController getRegisterClientController;

    private final RegisterCourierController getRegisterCourierController;

    private final InsertPharmacyDataController getInsertPharmacyDataController;

    private final BuyAProductController getBuyAProductController;

    private final LoginController loginController;

    private final MakeDeliveryController makeDeliveryController;

    private final RemovePharmacyController removePharmacyController;

    private final RegisterDroneController registerDroneController;

    private final EditParkingLotCharacteristicsController editParkingLotCharacteristicsController;

    private final AddDroneController addDroneController;

    private final RemoveDroneController removeDroneController;

    private final InsertParkingLotController insertParkingLotController;

    private final ReadFile readFile;

    public MainWindowUI() throws Exception {
        getUpdateStockController = new UpdateStockController();
        getRegisterScooterController = new RegisterScooterController();
        getAddScooterController = new AddScooterController();
        getRemoveScooterController = new RemoveScooterController();
        getRegisterProductController = new RegisterProductController();
        getRegisterClientController = new RegisterClientController();
        getRegisterCourierController = new RegisterCourierController();
        getInsertPharmacyDataController = new InsertPharmacyDataController();
        getBuyAProductController = new BuyAProductController();
        makeDeliveryController = new MakeDeliveryController();
        loginController = new LoginController();
        removePharmacyController = new RemovePharmacyController();
        registerDroneController = new RegisterDroneController();
        editParkingLotCharacteristicsController = new EditParkingLotCharacteristicsController();
        removeDroneController = new RemoveDroneController();
        addDroneController = new AddDroneController();
        insertParkingLotController = new InsertParkingLotController();
        readFile = new ReadFile(this);
        menuStart();
    }

    public void menuStart() throws Exception {
        Scanner sc = new Scanner(System.in);
        int scan = 1;
        while (scan != 0) {
            menu();
            try {
                scan = Integer.parseInt(sc.nextLine());
                switch (scan) {
                    case 1:
                        readFile.readGraphDroneRestrictions();
                        readFile.readGraphScooterRestrictions();
                        AplicationPOT.getInstance().getPlatform().initializeDroneGraph();
                        AplicationPOT.getInstance().getPlatform().initializeScooterGraph();
                        menuLogin();
                        break;
                    case 2:
                        PrintStream printStream = new PrintStream(new FileOutputStream("output.txt", true), true);
                        System.setOut(printStream);
                        System.setErr(printStream);
                        readFile.readGraphDroneRestrictions();
                        readFile.readGraphScooterRestrictions();
                        readFile.readFilePharmacies();
                        readFile.readFileDeliveryData();
                        readFile.readFileCouriers();
                        readFile.readFileClients();
                        readFile.readFileScooters();
                        readFile.readFileDrones();
                        AplicationPOT.getInstance().getPlatform().initializeDroneGraph();
                        AplicationPOT.getInstance().getPlatform().initializeScooterGraph();
                        readFile.readFileAddScooter();
                        readFile.readFileAddDrone();
                        readFile.readFileInsertParkingLot();
                        readFile.readFileProduct();
                        readFile.readFileBuyProduct("Files/BuyProduct.txt");
                        readFile.readFileMakeDelivery();
                        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                        System.out.println("Completed");
                        break;
                    case 4:
                        readFile.readGraphDroneRestrictions();
                        readFile.readGraphScooterRestrictions();
                        readFile.readFilePharmacies();
                        readFile.readFileDeliveryData();
                        readFile.readFileCouriers();
                        readFile.readFileClients();
                        readFile.readFileDrones();
                        AplicationPOT.getInstance().getPlatform().initializeDroneGraph();
                        AplicationPOT.getInstance().getPlatform().initializeScooterGraph();
                        readFile.readFileAddDrone();
                        readFile.readFileInsertParkingLot();
                        readFile.readFileProductAerial();
                        readFile.readFileBuyProduct("FilesAerialScenarios/BuyProduct.txt");
                        readFile.readFileMakeDeliveryAerial();
                        break;
                    case 3:
                        readFile.readFileTerrestresScenario0();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nEssa opção não se encontra disponível no menu.\nPor favor insira uma nova opção.\n");
                }
            } catch (InputMismatchException ime) {
                System.out.println("O valor escrito não faz parte das seleções possiveis");
                break;
            }
        }
    }

    public void menuLogin() throws Exception {
        Scanner sc = new Scanner(System.in);
        int scan = 1;
        while (scan != 0) {
            menuLogins();
            try {
                scan = sc.nextInt();
                switch (scan) {
                    case 1:
                        LoginWindowUI login = new LoginWindowUI(this);
                        String role = login.getUserRole();
                        if (role.equalsIgnoreCase("Administrator")) {
                            menuStartAdmin();
                        } else if (role.equalsIgnoreCase("Courier")) {
                            menuStartCourier();
                        } else if (role.equalsIgnoreCase("Client")) {
                            menuStartClient();
                        } else {
                            System.out.println("Invalid data. Your role don´t exist in the system. Please try again!!");
                        }

                        break;
                    case 2:
                        new RegisterClientUI(this);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nEssa opção não se encontra disponível no menu.\nPor favor ensira uma nova opção.\n");
                }
            } catch (InputMismatchException ime) {
                System.out.println("O valor escrito não faz parte das seleções possiveis");
                break;
            }
        }
    }

    public void menuStartAdmin() throws Exception {
        int scan = 1;
        menuAdmin1();
        int scan1 = Integer.parseInt(sc.nextLine());
        while (scan != 0) {
            if (scan1 == 1) {
                try {
                    menuAdmin();
                    scan = sc.nextInt();
                    switch (scan) {
                        case 1:
                            menuManagePharmacy();
                            int scan2 = 1;
                            while (scan2 != 0) {
                                scan2 = sc.nextInt();
                                if (scan2 == 1) {
                                    new InsertPharmacyDataUI(this);
                                } else if (scan2 == 2) {
                                    new RemovePharmacyUI(this);
                                } else if (scan2 == 0) {
                                    break;
                                } else {
                                    System.out.println("Invalid option! Choose another one.");
                                }
                            }
                            break;
                        case 2:
                            new RegisterScooterUI(this);
                            break;
                        case 3:
                            new RegisterProductUI(this);
                            break;
                        case 4:
                            new RegisterCourierUI(this);
                            break;
                        case 5:
                            int scan3 = 1;
                            while (scan3 != 0) {
                                menuManageScooters();
                                scan3 = sc.nextInt();
                                if (scan3 == 1) {
                                    new AddScooterUI(this);
                                } else if (scan3 == 2) {
                                    new RemoveScooterUI(this);
                                } else if (scan3 == 0) {
                                    break;
                                } else {
                                    System.out.println("Invalid option! Choose another one.");
                                }
                            }
                            break;
                        case 6:
                            new UpdateStockUI(this);
                            break;
                        case 7:
                            new RegisterDroneUI(this);
                            break;
                        case 8:
                            new EditParkingLotCharacteristicsUI(this);
                            break;
                        case 9:
                            new InsertParkingLotUI(this);
                            break;
                        case 10:
                            int scan4 = 1;
                            while (scan4 != 0) {
                                menuManageDrones();
                                scan4 = sc.nextInt();
                                if (scan4 == 1) {
                                    new AddDroneUI(this);
                                } else if (scan4 == 2) {
                                    new RemoveDroneUI(this);
                                } else if (scan4 == 0) {
                                    break;
                                } else {
                                    System.out.println("Invalid option! Choose another one.");
                                }
                            }
                            break;
                        case 11:
                            new DeliveryStatisticsUI(this);
                            break;
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("\nEssa opção não se encontra disponível no menu.\nPor favor ensira uma nova opção.\n");
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("O valor escrito não faz parte das seleções possiveis");
                    break;
                }
            } else if (scan1 == 0) {
                System.exit(0);

            } else {
                System.out.println("\nThe selected option is not available in this menu.\nInsert a new option please.\n");
            }
        }
    }

    public void menuStartCourier() throws Exception {
        int scan = 1;
        while (scan != 0) {
            menuCourier();
            try {
                scan = sc.nextInt();
                switch (scan) {
                    case 1:
                        new DeliveryStatisticsUI(this);
                        break;
                    case 2:
                        readFile.readFileMakeDelivery();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nEssa opção não se encontra disponível no menu.\nPor favor ensira uma nova opção.\n");
                }
            } catch (InputMismatchException ime) {
                System.out.println("O valor escrito não faz parte das seleções possiveis");
                break;
            }
        }
    }

    public void menuStartClient() throws IOException, MessagingException {
        int scan = 1;
        while (scan != 0) {
            menuClient();
            try {
                scan = sc.nextInt();
                switch (scan) {
                    case 1:
                        new BuyAProductUI(this);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nEssa opção não se encontra disponível no menu.\nPor favor ensira uma nova opção.\n");
                }
            } catch (InputMismatchException ime) {
                System.out.println("O valor escrito não faz parte das seleções possiveis");
                break;
            }
        }
    }

    static void menu() {
        System.out.println("\n\tMENU");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Use the interface.");
        System.out.println("2-Use all files.");
        System.out.println("3-Use files to Land scenarios.");
        System.out.println("4-Use files to Aereous scenario.");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    /**
     * Menu Principal
     */
    static void menuLogins() {
        System.out.println("\n\tMENU");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Make Login and use the aplication.");
        System.out.println("2-Register client.");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    /**
     * Menu Principal
     */
    public static void menuAdmin() {
        System.out.println("\n\tMENU ADMINISTRATOR");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Manage Pharmacies.");
        System.out.println("2-Register Scooter.");
        System.out.println("3-Register Product.");
        System.out.println("4-Register Courier.");
        System.out.println("5-Manage Scooters.");
        System.out.println("6-Update stock of a pharmacy.");
        System.out.println("7-Register Drone");
        System.out.println("8-Edit Parking Lot Characteristics");
        System.out.println("9-Insert Parking Lot");
        System.out.println("10-Manage Drones");
        System.out.println("11-Make Drone Delivery");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    public static void menuAdmin1() {
        System.out.println("\n\tMENU ADMINISTRATOR");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Use the application manually");
        System.out.println("2-Use the application through files");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    /**
     * Menu Principal
     */
    private static void menuCourier() {
        System.out.println("\n\tMENU COURIER");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Make a delivery.");
        System.out.println("2-Make a delivery through files");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    /**
     * Menu Principal
     */
    private static void menuClient() {
        System.out.println("\n\tMENU CLIENT");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Buy a product.");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    /**
     * Menu Principal
     */
    private static void menuManageScooters() {
        System.out.println("\n\tMENU MANAGE SCOOTER");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Add Scooter to a list");
        System.out.println("2-Remove Scooter from a list");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    private static void menuManageDrones() {
        System.out.println("\n\tMENU MANAGE DRONE");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Add Drone to a Pharmacy");
        System.out.println("2-Remove Drone from a Pharmacy");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    private static void menuManagePharmacy() {
        System.out.println("\n\tMENU MANAGE PHARMACY");
        System.out.println("====================================================================================================================================");
        System.out.println("What do you want to do?");
        System.out.println("1-Register Pharmacy");
        System.out.println("2-Remove Pharmacy from a list");
        System.out.println("0-Exit");
        System.out.println("====================================================================================================================================\n");
    }

    public UpdateStockController getUpdateStockController() {
        return getUpdateStockController;
    }

    public RegisterScooterController getGetRegisterScooterController() {
        return getRegisterScooterController;
    }

    public AddScooterController getGetAddScooterController() {
        return getAddScooterController;
    }

    public RemoveScooterController getGetRemoveScooterController() {
        return getRemoveScooterController;
    }

    public RegisterProductController getRegisterProductController() {
        return getRegisterProductController;
    }

    public RegisterClientController getRegisterClientController() {
        return getRegisterClientController;
    }

    public RegisterCourierController getRegisterCourierController() {
        return getRegisterCourierController;
    }

    public InsertPharmacyDataController getGetInsertPharmacyDataController() {
        return getInsertPharmacyDataController;
    }

    public BuyAProductController getBuyAProductController() {
        return getBuyAProductController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public MakeDeliveryController getMakeDeliveryController() {
        return makeDeliveryController;
    }

    public RemovePharmacyController getRemovePharmacyController() {
        return removePharmacyController;
    }

    public RegisterDroneController getRegisterDroneController() {
        return registerDroneController;
    }

    public EditParkingLotCharacteristicsController getEditParkingLotCharacteristicsController() {
        return editParkingLotCharacteristicsController;
    }

    public AddDroneController getAddDroneController() {
        return addDroneController;
    }

    public RemoveDroneController getRemoveDroneController() {
        return removeDroneController;
    }

    public InsertParkingLotController getInsertParkingLotController() {
        return insertParkingLotController;
    }
}
