package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogmanagement.domain.Identifier;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.teammanagement.domain.UniqueCode;
import java.util.Calendar;

import eapli.framework.time.util.Calendars;

public final class TestDataConstants {

	public static final String TEAM_TYPE_1 = "tt1";
	public static final String TEAM_TYPE_2 = "tt2";
        
	public static final MecanographicNumber COLLABORATOR_1 = new MecanographicNumber(1);
	public static final MecanographicNumber COLLABORATOR_2 = new MecanographicNumber(2);
        public static final MecanographicNumber COLLABORATOR_3 = new MecanographicNumber(3);
	public static final MecanographicNumber COLLABORATOR_4 = new MecanographicNumber(4);
        public static final MecanographicNumber COLLABORATOR_5 = new MecanographicNumber(5);
	public static final MecanographicNumber COLLABORATOR_6 = new MecanographicNumber(6);
        public static final MecanographicNumber COLLABORATOR_7 = new MecanographicNumber(7);
	public static final MecanographicNumber COLLABORATOR_8 = new MecanographicNumber(8);
        
        public static final MecanographicNumber COLLABORATOR_CATALOG_1 = new MecanographicNumber(3);
	public static final MecanographicNumber COLLABORATOR_CATALOG_2 = new MecanographicNumber(4);
        
        public static final MecanographicNumber COLLABORATOR_SERVICE_1 = new MecanographicNumber(3);
        public static final MecanographicNumber COLLABORATOR_SERVICE_2 = new MecanographicNumber(4);
        
	public static final UniqueCode TEAM_1 = new UniqueCode("team1");
	public static final UniqueCode TEAM_2 = new UniqueCode("team2");
        
        public static final Identifier CATALOG_1 = Identifier.valueOf(1L);
        public static final Identifier CATALOG_2 = Identifier.valueOf(2L);

	public static final String USER_TEST1 = "user1";

	@SuppressWarnings("squid:S2068")
	public static final String PASSWORD1 = "Password1";

	@SuppressWarnings("squid:S2885")
	public static final Calendar DATE_TO_BOOK = Calendars.of(2017, 12, 01);

	private TestDataConstants() {
		// ensure utility
	}
}
