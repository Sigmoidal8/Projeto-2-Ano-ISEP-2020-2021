
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.user.console.presentation.catalogs;

import eapli.base.catalogmanagement.application.SearchCatalogController;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.languagemanagement.application.FormValidator;
import eapli.base.languagemanagement.domain.form.FormVisitor;
import eapli.base.requestmanagement.application.RequestServiceController;
import eapli.base.requestmanagement.domain.Priority;
import eapli.base.servicemanagement.domain.Attribute;
import eapli.base.servicemanagement.domain.Form;
import eapli.base.servicemanagement.domain.Service;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author José Forno
 */
public class SearchCatalogUI extends AbstractUI {

    private final SearchCatalogController theController = new SearchCatalogController();
    private final RequestServiceController theController2 = new RequestServiceController();

    @Override
    protected boolean doShow() {
        final Iterable<Catalog> catalogs = this.theController.catalogs();
        if (catalogs == null) {
            System.out.println("Can't search catalogs since there is none\n");
            return false;
        }

        List<Catalog> catalogsL = new ArrayList<>();
        catalogs.forEach(catalogsL::add);
        int i = 1;
        System.out.println("Catalogs:");
        for (Catalog c : catalogsL) {
            System.out.printf("%d- %s\n", i, c);
            List<Service> serviceL = c.services();
            int j = 1;
            for (Service s : serviceL) {
                if (s.isDraft()) {
                    System.out.printf("      - %d,%d- Available soon\n", i, j);
                } else {
                    System.out.printf("      - %d,%d- %s\n", i, j, s);
                    j++;
                }
            }
            i++;
        }

        System.out.println("What do you want to do?");
        System.out.println("1-Request a service");
        System.out.println("2-Search a servide");

        Integer option = 0;

        do {
            option = Console.readInteger("");
        } while (option < 1 || option > 2);

        switch (option) {

            case 1:
                String serv;
                do {
                    serv = Console.readLine("Select a service (catalog,service)\n");
                } while (!serv.contains(","));
                String[] aux = serv.split(",");
                if (aux[1].contains("Available soon")) {
                    System.out.println("Service will be available soon");
                    return false;
                }
                int catalN = Integer.parseInt(aux[0]);
                int servN = Integer.parseInt(aux[1]);
                Service selected = (catalogsL.get(catalN - 1).services().get(servN - 1));
                requestServiceUI(selected);
                break;
            case 2:
                do {
                    System.out.printf("\n%d/- Search\n", 1);
                    System.out.printf("%d- Exit\n", 0);
                    option = Console.readInteger("\nSelect an option");

                    if (option == 0) {
                        return false;
                    }
                    if (option == 1) {

                        System.out.printf("\n%d/ Keyords\n", 1);
                        System.out.printf("%d- Title\n", 2);

                        int option2 = Console.readInteger("\nSelect an Search Option");

                        if (option2 == 1) {

                            String optionKeyword = Console.readLine("\nInsert keyword");
                            i = 1;
                            for (Catalog c1 : catalogsL) {
                                List<Service> services = theController.searchByKeyword(c1, optionKeyword);
                                if (!services.isEmpty()) {
                                    System.out.printf("%d- %s\n", i, c1);
                                    int j = 1;
                                    for (Service s1 : services) {
                                        if (s1.isDraft()) {
                                            System.out.printf("     -%d,%d- Available soon\n", i, j);
                                        } else {
                                            System.out.printf("     -%d,%d- %s\n", i, j, s1);
                                            j++;
                                        }

                                    }
                                    i++;
                                }

                            }
                            System.out.printf("\nWant to request one of these services?\n");
                            System.out.println("1- Yes");
                            System.out.println("0- No");

                            int option3 = Console.readInteger("Select an option\n");

                            if (option3 == 1) {
                                String serv1;
                                serv1 = Console.readLine("Select a service (catalog,service)\n");
                                String[] aux1 = serv1.split(",");
                                if (aux1[1].contains("Available soon")) {
                                    System.out.println("Service will be available soon");
                                    return false;
                                }
                                int catalN1 = Integer.parseInt(aux1[0]);
                                int servN1 = Integer.parseInt(aux1[1]);
                                Service selected1 = (theController.searchByKeyword(catalogsL.get(catalN1 - 1), optionKeyword)).get(servN1 - 1);
                                requestServiceUI(selected1);
                                break;

                            }

                        } else if (option2 == 2) {
                            String optionTitle = Console.readLine("\nInsert title");
                            i = 1;
                            for (Catalog c1 : catalogsL) {
                                List<Service> services = theController.searchByTitle(c1, optionTitle);
                                if (!services.isEmpty()) {
                                    System.out.printf("%d- %s\n", i, c1);
                                    int j = 1;
                                    for (Service s1 : services) {
                                        if (s1.isDraft()) {
                                            System.out.printf("     -%d,%d- Available soon\n", i, j);
                                        } else {
                                            System.out.printf("     -%d,%d- %s\n", i, j, s1);
                                            j++;
                                        }
                                    }
                                    i++;
                                }
                            }

                            System.out.printf("\nWant to request one of these services?\n");
                            System.out.println("1- Yes");
                            System.out.println("0- No");

                            option = Console.readInteger("Select an option\n");

                            if (option == 1) {
                                String serv1;
                                serv1 = Console.readLine("Select a service (catalog,service)\n");
                                String[] aux2 = serv1.split(",");
                                if (aux2[1].contains("Available soon")) {
                                    System.out.println("Service will be available soon");
                                    return false;
                                }
                                int catalN2 = Integer.parseInt(aux2[0]);
                                int servN2 = Integer.parseInt(aux2[1]);
                                Service selected2 = (theController.searchByTitle(catalogsL.get(catalN2 - 1), optionTitle)).get(servN2 - 1);
                                requestServiceUI(selected2);
                                break;
                            }
                        } else {
                            System.out.println("\nOption does not exist");
                            return false;
                        }
                    }
                } while (option != 0);
                break;
        }
        return false;
    }

    public boolean requestServiceUI(Service serv) {

        System.out.println("Priority:\n");
        System.out.println("1-Reduced");
        System.out.println("2-Moderate");
        System.out.println("3-Urgent");

        int priorityOption;
        Priority priority = null;

        do {

            priorityOption = Console.readInteger("Select a priority level\n");

            if (priorityOption == 1) {
                priority = Priority.Reduced;
            } else if (priorityOption == 2) {
                priority = Priority.Moderate;
            } else if (priorityOption == 3) {
                priority = Priority.Urgent;
            } else {
                System.out.println("\nOption does not exist");
            }
        } while (priorityOption > 3 || priorityOption < 1);

        Date deadline;
        Date currentDate = new Date();
        int aux;
        do {
            deadline = Console.readDate("Deadline (DD/MM/YYYY):", "dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());
            try {
                currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);

            } catch (ParseException ex) {
            }

            aux = deadline.compareTo(currentDate);

            if (aux < 1) {
                System.out.println("Deadline has to be after the current date");
            }

        } while (aux < 1);

        List<String> files = new ArrayList<>();

        while (Console.readInteger("Want to Select a file?\n1 to continue;0 to stop") != 0) {

            String file = Console.readLine("File:");
            files.add(file);
        }

        Form form = serv.forms();
        List<Attribute> attributeL = form.atributes();
        List<String> formResponse = new ArrayList<>();

        System.out.println("Complete the following attributes:");
        do {
            formResponse = new ArrayList<>();
            for (Attribute attr : attributeL) {
                String attributeResponse;
                int valido = 1;
                do {
                    System.out.printf("-%s(%s)\n", attr.name(), attr.description());
                    attributeResponse = Console.readLine("");

                    if (!(this.theController2.isValidByExpressaoRegular(attributeResponse, attr.regularExpression().toString()))) {
                        valido = 0;
                        System.out.println("Atribute is not valid");
                    } else {
                        valido = 1;
                    }
                } while (valido == 0);

                formResponse.add(attributeResponse);
            }
        } while (!FormValidator.verifyData(form.script(), formResponse, attributeL));
        int save, opt;

        do {
            opt = Console.readInteger("1- Send Request, 2- Save as Draft");
        } while (opt > 2 || opt < 1);

        if (opt == 1) {
            save = 0;
        } else {
            save = 1;
        }

        try {
            this.theController2.requestService(priority, deadline, files, serv, save, formResponse);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a catalog which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Search Catalogs";
    }

}
