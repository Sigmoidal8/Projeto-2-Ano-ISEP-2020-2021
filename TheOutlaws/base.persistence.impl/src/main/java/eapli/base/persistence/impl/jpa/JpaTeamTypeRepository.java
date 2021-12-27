package eapli.base.persistence.impl.jpa;

import eapli.base.teammanagement.domain.TeamType;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamTypeRepository;

public class JpaTeamTypeRepository extends BasepaRepositoryBase<TeamType,UniqueCode, UniqueCode> implements TeamTypeRepository {
    public JpaTeamTypeRepository() {
        super("code");
    }
}
