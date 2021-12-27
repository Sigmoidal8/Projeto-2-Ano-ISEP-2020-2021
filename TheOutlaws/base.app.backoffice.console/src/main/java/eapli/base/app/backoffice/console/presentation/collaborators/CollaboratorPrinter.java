/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.collaborators;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author raulcoelho
 */
public class CollaboratorPrinter implements Visitor<Collaborator>{

    @Override
    public void visit(final Collaborator visitee) {
        System.out.printf("%s",visitee.toString());
    }
    
}
