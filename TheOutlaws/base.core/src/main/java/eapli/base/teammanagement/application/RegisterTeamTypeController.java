package eapli.base.teammanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Color;
import eapli.base.teammanagement.domain.TeamType;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamTypeRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegisterTeamTypeController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeamTypeRepository teamTypeRepository = PersistenceContext.repositories().teamTypes();

    public TeamType registerTeamType(String code, String description, int red, int green, int blue, String colorName){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER);

        Color color = new Color(red, green, blue, colorName);
        TeamType teamType = new TeamType(new UniqueCode(code), Description.valueOf(description), color);
        return teamTypeRepository.save(teamType);
    }
}
