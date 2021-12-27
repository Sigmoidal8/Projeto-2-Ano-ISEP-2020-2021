/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.catalogs;

import eapli.base.app.backoffice.console.presentation.criticity.CriticalityPrinter;
import eapli.base.app.backoffice.console.presentation.services.ServicePrinter;
import eapli.base.catalogmanagement.application.AssignCriticalityController;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.criticitymanagement.domain.Criticity;
import eapli.base.servicemanagement.domain.Service;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author migue
 */
public class AssignCriticalityToCatalogUI extends AbstractUI {

    private final AssignCriticalityController theController = new AssignCriticalityController();

    @Override
    protected boolean doShow() {

        //Select the catalogs
        final Iterable<Catalog> catalogs = this.theController.catalogs();

        final SelectWidget<Catalog> selector = new SelectWidget<>("Catalogs:", catalogs, new CatalogPrinter());
        selector.show();

        final Catalog theCatalog = selector.selectedElement();

        //seleciona o criticity
        final Iterable<Criticity> criticity = this.theController.criticalities();
        final SelectWidget<Criticity> selector2 = new SelectWidget<>("Criticalities:", criticity, new CriticalityPrinter());
        selector2.show();

        final Criticity theCriticality = selector2.selectedElement();

        int option;
        int option2;
        if (selector != null && selector2 != null) {

            do {
                System.out.println("Do you want to change the objectives of this Criticality level?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");

                option = Console.readInteger("Select an option:");
            } while (option < 1 || option > 2);

            if (option == 1) {
                changeObjectives(theCriticality);
            }
        }
        do {
            do {
                System.out.println("Do you want to choose other Criticality level for any Service of this Catalog?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");

                option2 = Console.readInteger("Select an option:");
            } while (option2 < 1 || option2 > 2);
            
            if (option2 == 1) {
                
                
                final List<Service> services = theCatalog.services();

                final SelectWidget<Service> selector3 = new SelectWidget<>("Services:", services, new ServicePrinter());
                selector3.show();

                final Service theService = selector3.selectedElement();

                //seleciona o criticity
                final Iterable<Criticity> criticity2 = this.theController.criticalities();
                final SelectWidget<Criticity> selector4 = new SelectWidget<>("Criticalities:", criticity2, new CriticalityPrinter());
                selector4.show();

                final Criticity theCriticality2 = selector4.selectedElement();

                int option3;
                if (selector3 != null && selector4 != null) {
                    do {
                        System.out.println("Do you want to change the objectives of this Criticality level?");
                        System.out.println("1 - Yes");
                        System.out.println("2 - No");

                        option3 = Console.readInteger("Select an option:");
                    } while (option3 < 1 || option3 > 2);
                    if (option3 == 1) {
                        changeObjectives(theCriticality2);
                    }
                    
                    theController.assignCriticality(theCatalog, theCriticality);
                    theController.assignCriticalityToServices(theService, theCriticality2);
                }
            } else if (option2 == 2) {
                theController.assignCriticality(theCatalog, theCriticality);
                
            }

        } while (option2 != 2);
        return false;
    }

    public boolean changeObjectives(Criticity theCriticality) {
        int option;
        int option2;
        System.out.println("Do you want to change the objectives of this Criticality level?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");

        option = Console.readInteger("Select an option:\n");

        if (option == 1) {
            do {
                System.out.println("Criticaly that you choose:");
                System.out.println("===========================");
                System.out.println(theCriticality.toString());
                System.out.println("===========================");

                System.out.println("Whitch objective do you want to change?");
                System.out.println("1 - Average Approval Time");
                System.out.println("2 - Max Approval Time");
                System.out.println("3 - Average Resolution Time");
                System.out.println("4 - Max Resolution Time");
                System.out.println("0 - Exit and save");

                option2 = Console.readInteger("Select an option:\n");

                switch (option2) {
                    case 1:
                        int[] time;
                        do {
                            String newValue = Console.readLine("Introduce the new value for Average Approval Time(HH:MM:SS):");

                            String[] auxStringArray = newValue.split(":");
                            time = timeConversion(auxStringArray);
                            if (time != null) {
                                final LocalTime averageApprovalTime = LocalTime.of(time[0], time[1], time[2]);
                                theController.changeAverageApprovalTime(averageApprovalTime, theCriticality);
                            }
                        } while (time == null);
                        break;
                    case 2:
                        int[] time2;
                        do {
                            String newValue = Console.readLine("Introduce the new value for Max Approval Time(HH:MM:SS):");

                            String[] auxStringArray = newValue.split(":");
                            time2 = timeConversion(auxStringArray);
                            if (time2 != null) {
                                final LocalTime maxApprovalTime = LocalTime.of(time2[0], time2[1], time2[2]);
                                theController.changeMaxApprovalTime(maxApprovalTime, theCriticality);
                            }
                        } while (time2 == null);
                        break;
                    case 3:
                        int[] time3;
                        do {
                            String newValue = Console.readLine("Introduce the new value for Average Resolution Time(HH:MM:SS):");

                            String[] auxStringArray = newValue.split(":");
                            time3 = timeConversion(auxStringArray);
                            if (time3 != null) {
                                final LocalTime averageResolutionTime = LocalTime.of(time3[0], time3[1], time3[2]);
                                theController.changeAverageResolutionTime(averageResolutionTime, theCriticality);
                            }
                        } while (time3 == null);
                        break;
                    case 4:
                        int[] time4;
                        do {
                            String newValue = Console.readLine("Introduce the new value for Max Resolution Time(HH:MM:SS):");

                            String[] auxStringArray = newValue.split(":");
                            time4 = timeConversion(auxStringArray);
                            if (time4 != null) {
                                final LocalTime maxResolutionTime = LocalTime.of(time4[0], time4[1], time4[2]);
                                theController.changeMaxResolutionTime(maxResolutionTime, theCriticality);
                            }
                        } while (time4 == null);
                        break;
                    default:
                        System.out.println("\nOption does not exist");
                        return false;
                }
            } while (option2 != 0);
        }
        return true;
    }

    public int[] timeConversion(String[] time) {
        int[] averageApproval = {0, 0, 0};
        try {
            for (int i = 0; i < 3; i++) {
                averageApproval[i] = Integer.parseInt(time[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You didn't insert the right format");
            return null;
        }
        return averageApproval;
    }

    @Override
    public String headline() {
        return "Assign criticality to Catalog";
    }

}
