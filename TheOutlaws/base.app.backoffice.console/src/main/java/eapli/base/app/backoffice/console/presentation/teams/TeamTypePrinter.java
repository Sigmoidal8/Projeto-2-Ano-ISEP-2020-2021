package eapli.base.app.backoffice.console.presentation.teams;

import eapli.base.teammanagement.domain.TeamType;
import eapli.framework.visitor.Visitor;

public class TeamTypePrinter implements Visitor<TeamType> {

    @Override
    public void visit(final TeamType visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
