package eapli.base.app.backoffice.console.presentation.services;

import eapli.base.servicemanagement.domain.Service;
import eapli.framework.visitor.Visitor;

public class ServicePrinter implements Visitor<Service> {

    @Override
    public void visit(Service visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
