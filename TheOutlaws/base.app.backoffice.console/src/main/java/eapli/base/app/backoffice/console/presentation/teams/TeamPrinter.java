/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.teams;

import eapli.base.teammanagement.domain.Team;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author migue
 */
public class TeamPrinter implements Visitor<Team>{

    @Override
    public void visit(final Team visitee) {
        System.out.printf("%s",visitee.toString());
    }
    
}
