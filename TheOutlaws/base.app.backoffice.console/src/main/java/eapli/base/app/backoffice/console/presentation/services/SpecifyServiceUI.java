/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.services;

import eapli.base.app.backoffice.console.presentation.catalogs.CatalogPrinter;
import eapli.base.app.backoffice.console.presentation.collaborators.CollaboratorPrinter;
import eapli.base.app.backoffice.console.presentation.tasklibrary.AutomaticTaskRepresentation;
import eapli.base.app.backoffice.console.presentation.tasklibrary.ManualTaskRepresentation;
import eapli.base.app.backoffice.console.presentation.teams.TeamPrinter;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.languagemanagement.application.FormValidator;
import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.*;
import eapli.base.taskmanagement.domain.Task;
import eapli.base.taskmanagement.domain.TaskStatus;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.taskmanagement.domain.TypeCollaboratorApproval;
import eapli.base.teammanagement.domain.Team;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.general.domain.model.Description;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Utilizador
 */
public class SpecifyServiceUI extends AbstractUI {
    private final SpecifyServiceController theController = new SpecifyServiceController();

    @Override
    protected boolean doShow() {

        int i = 0;
        while (true) {
            i = Console.readInteger("Do you to create a new Service or continue a uncompleted one\n1-New one\n2-Continue");
            if (i == 1) {
                return createNewOne();
            }
            if (i == 2) {
                return continueOldOne();
            }
        }
    }

    public boolean createNewOne() {
        theController.specifyCollaborator();

        final Iterable<Catalog> catalogs = this.theController.catalogs();
        final SelectWidget<Catalog> selector = new SelectWidget<>("Select a catalog \nCatalogs:", catalogs,
                new CatalogPrinter());
        selector.show();
        final Catalog theCatalog = selector.selectedElement();

        if (theCatalog == null) {
            return false;
        }

        final String uniqueCode = Console.readLine("Unique Code:");
        theController.specifyUniqueCode(uniqueCode);

        final String title = Console.readLine("Title:");
        theController.specifyTitle(title);

        int i = 1;
        while (i != 0) {
            System.out.println("1-Brief Description");
            System.out.println("2-Complete Description");
            System.out.println("3-Type(Manual or Automatic)");
            System.out.println("4-Keywords");
            System.out.println("5-Icone");
            System.out.println("6-Soliciting Form");
            System.out.println("7-Tasks to be done");
            System.out.println("0-Exit");
            i = Console.readInteger("What do you want to specify?");

            switch (i) {
                case 1:
                    specifyBriefDescription();
                    break;
                case 2:
                    specifyCompleteDescription();
                    break;
                case 3:
                    specifyType();
                    break;
                case 4:
                    specifyKeywords();
                    break;
                case 5:
                    specifyIcone();
                    break;
                case 6:
                    specifyForms();
                    break;
                case 7:
                    specifyWorkflow(theCatalog);
                    break;
                default:
                    break;
            }
        }

        savingMenu(theCatalog, true);
        return false;
    }

    public boolean continueOldOne() {
        final Iterable<Service> services = this.theController.uncompletedServices();

        final SelectWidget<Service> selector = new SelectWidget<>("Select the service to edit\nServices:", services,
                new ServicePrinter());
        selector.show();
        final Service theService = selector.selectedElement();

        if (theService == null) {
            return false;
        }

        this.theController.continueServiceSpecification(theService);

        Catalog theCatalog = this.theController.catalogOfSelectedService(theService);

        int i = 1;
        while (i != 0) {
            System.out.println("1-Brief Description");
            System.out.println("2-Complete Description");
            System.out.println("3-Type(Manual or Automatic)");
            System.out.println("4-Keywords");
            System.out.println("5-Icone");
            System.out.println("6-Soliciting Form");
            System.out.println("7-Tasks to be done");
            System.out.println("0-Exit");
            i = Console.readInteger("What do you want to specify?");

            switch (i) {
                case 1:
                    specifyBriefDescription();
                    break;

                case 2:
                    specifyCompleteDescription();
                    break;

                case 3:
                    specifyType();
                    break;
                case 4:
                    addKeywords();
                    break;
                case 5:
                    specifyIcone();
                    break;
                case 6:
                    specifyForms();
                    break;
                case 7:
                    specifyWorkflow(theCatalog);
                    break;
                default:
                    break;
            }
        }

        savingMenu(theCatalog, false);
        return false;
    }

    public void savingMenu(Catalog theCatalog, boolean isNew) {
        int option = 1;
        while (option != 0) {
            option = Console.readInteger("Is the service complete?\n1-Yes\n2-No");
            switch (option) {
                case 1:
                    List<String> data = this.theController.verifyService();
                    if (data.isEmpty()) {
                        if (isNew) {
                            Service service = theController.saveService();
                            theController.specifyCatalog(theCatalog, service);
                        } else {
                            Service service = theController.saveService();
                        }
                    } else {
                        System.out.println("This is the data missing:");
                        for (String s : data) {
                            System.out.printf("%s, ", s);
                        }
                        if (isNew) {
                            Service service = theController.saveServiceAsDraft();
                            theController.specifyCatalog(theCatalog, service);
                        } else {
                            Service service = theController.saveServiceAsDraft();
                        }
                        System.out.printf("\nYour service was saved as a draft.\n");
                    }
                    option = 0;
                    break;
                case 2:
                    List<String> dataUncompleted = this.theController.verifyService();
                    if (dataUncompleted.isEmpty()) {
                        System.out.println("There was no data missing but your service will be saved as a draft.");
                    } else {
                        System.out.println("This is the data missing:");
                        for (String s : dataUncompleted) {
                            System.out.printf("%s, ", s);
                        }
                    }
                    if (isNew) {
                        Service service = theController.saveServiceAsDraft();
                        theController.specifyCatalog(theCatalog, service);
                    } else {
                        Service service = theController.saveServiceAsDraft();
                    }
                    System.out.printf("\nYour service was saved as a draft.\n");
                    option = 0;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public String specifyBriefDescription() {
        String briefDescription = Console.readLine("Brief Description:");
        theController.specifyBriefDescription(briefDescription);
        return briefDescription;
    }

    public String specifyCompleteDescription() {
        String completeDescription = Console.readLine("Complete Description:");
        theController.specifyCompleteDescription(completeDescription);
        return completeDescription;
    }

    public Type specifyType() {
        Type t;
        System.out.printf("1-%s\n", Type.Automatic);
        System.out.printf("2-%s\n", Type.Manual);
        int decision = 0;
        while (decision < 1 || decision > 2) {
            decision = Console.readInteger("Which Type:");
        }
        if (decision == 1) {
            t = Type.Automatic;
        } else {
            t = Type.Manual;
        }
        theController.specifyType(t);
        return t;
    }

    public List<String> specifyKeywords() {
        List<String> keywords = new ArrayList<>();
        askForKeywords(keywords);
        theController.specifyKeywords(keywords);
        return keywords;
    }

    public List<String> addKeywords() {
        List<String> keywords = new ArrayList<>();
        askForKeywords(keywords);
        theController.addKeywords(keywords);
        return keywords;
    }

    public List<String> askForKeywords(List<String> keywords) {
        int option = 1;
        while (option != 0) {
            String keyword = Console.readLine("Keyword:");
            keywords.add(keyword);
            option = Console.readInteger("1-Insert Keyword\n0-Exit");
        }
        return keywords;
    }

    public String specifyIcone() {
        String icone = Console.readLine("Icone:");
        theController.specifyIcone(icone);
        return icone;
    }

    public Form specifyForms() {
        System.out.println("Soliciting Form Data:");
        Form forms = askForForms();
        theController.specifyForms(forms);
        return forms;
    }

    public Form askForForms() {
        String name = Console.readLine("Name:");
        Script script;
        do {
            String scriptLocation = Console.readLine("Validating Script location");
            script = new Script(scriptLocation);
        } while (FormValidator.validateScript(script) == false);
        List<Attribute> attributes = new ArrayList<>();
        int option2 = Console.readInteger("1-New Attribute\n0-Exit");
        while (option2 != 0) {
            String attributeLabel = Console.readLine("Label:");
            String attributeName = Console.readLine("Name:");
            String attributeDescription = Console.readLine("Description:");
            int attributeDataType = 1;
            do {
                System.out.println("Data type");
                System.out.println("1-Text");
                System.out.println("2-Numerical");
                System.out.println("3-Boolean");
                attributeDataType = Console.readInteger("");
            } while (attributeDataType < 1 ||  attributeDataType > 3);
            String attributeRegularExpression = Console.readLine("Regular Expression");
            if( attributeDataType == 1) {
                attributes.add(new Attribute(new Label(attributeLabel), new Name(attributeName), Description.valueOf(attributeDescription), DataType.String,
                        new RegularExpression(attributeRegularExpression)));
            }else if(attributeDataType == 2){
                attributes.add(new Attribute(new Label(attributeLabel), new Name(attributeName), Description.valueOf(attributeDescription), DataType.Integer,
                        new RegularExpression(attributeRegularExpression)));
            }else if(attributeDataType == 3){
                attributes.add(new Attribute(new Label(attributeLabel), new Name(attributeName), Description.valueOf(attributeDescription), DataType.Boolean,
                        new RegularExpression(attributeRegularExpression)));
            }
            option2 = Console.readInteger("1-New Attribute\n0-Exit");
        }
        return theController.createForm(name, script, attributes);
    }

    public void specifyWorkflow(Catalog theCatalog) {
        askForWorkflow(theCatalog);
    }

    public void askForWorkflow(Catalog theCatalog) {
        Type serviceType = theController.verifyServiceType();
        if (serviceType != null) {
            if (serviceType == Type.Manual) {
                new ManualTaskRepresentation(theController, this).show();
            } else if (serviceType == Type.Automatic) {
                new AutomaticTaskRepresentation(theController, this).show();
            }
        }
    }

    @Override
    public String headline() {
        return "Specify Service";
    }
}
