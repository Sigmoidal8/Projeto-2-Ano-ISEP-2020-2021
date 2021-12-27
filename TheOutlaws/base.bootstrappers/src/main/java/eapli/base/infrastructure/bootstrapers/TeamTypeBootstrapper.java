package eapli.base.infrastructure.bootstrapers;

import eapli.base.teammanagement.application.RegisterTeamTypeController;
import eapli.base.teammanagement.domain.TeamType;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TeamTypeBootstrapper implements Action{

    private static final Logger LOGGER = LogManager.getLogger(TeamTypeBootstrapper.class);

    @Override
    public boolean execute() {
        registerTeamType("tt1", "Equipa de Vendas", 255,0,0, "Red");
        registerTeamType("tt2","Equipa de HR", 0, 0, 255,"Blue");
        registerTeamType("tt3","Equipa de GSH",0,255,0,"Green");

        return false;
    }


    private void registerTeamType(final String code, final String description, final int red, final int green, final int blue, final String colorName) {
        final RegisterTeamTypeController controller = new RegisterTeamTypeController();
        try {
            controller.registerTeamType(code, description, red,green,blue, colorName);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", code);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
