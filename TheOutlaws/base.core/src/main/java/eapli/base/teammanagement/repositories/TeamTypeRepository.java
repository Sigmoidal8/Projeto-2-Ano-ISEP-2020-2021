package eapli.base.teammanagement.repositories;

import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.TeamType;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.framework.domain.repositories.DomainRepository;

public interface TeamTypeRepository extends DomainRepository<UniqueCode, TeamType> {
    @Override
    Iterable<TeamType> findAll();
}
