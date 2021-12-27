package eapli.base.app.user.console.presentation.request;

import eapli.base.requestmanagement.domain.Request;
import eapli.framework.visitor.Visitor;

/**
 * @author Raúl Coelho
 */
public class RequestPrinter implements Visitor<Request> {
    @Override
    public void visit(Request visitee) {
        System.out.printf("Unique Code: %s  Type:%s  Service:%s", visitee.uniqueCode(), visitee.type(), visitee.service().toString());
    }
}
