package eapli.base.persistence.impl.inmemory;

import eapli.base.teammanagement.domain.TeamType;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamTypeRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryTeamTypeRepository extends InMemoryDomainRepository<TeamType, UniqueCode> implements TeamTypeRepository {
}
