/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.criticity;

import eapli.base.criticitymanagement.domain.Criticity;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author migue
 */
public class CriticalityPrinter implements Visitor<Criticity>{

    @Override
    public void visit(final Criticity visitee) {
        System.out.printf("%s",visitee.toString());
    }
    
}
