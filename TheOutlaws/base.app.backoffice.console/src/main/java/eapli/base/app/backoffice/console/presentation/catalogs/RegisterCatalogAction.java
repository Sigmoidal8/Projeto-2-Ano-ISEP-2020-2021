package eapli.base.app.backoffice.console.presentation.catalogs;

import eapli.framework.actions.Action;

/**
 *
 * @author raulcoelho
 */
public class RegisterCatalogAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterCatalogUI().show();
    }

}
