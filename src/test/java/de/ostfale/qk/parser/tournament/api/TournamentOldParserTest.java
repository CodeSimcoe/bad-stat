package de.ostfale.qk.parser.tournament.api;

import de.ostfale.qk.parser.BaseTest;
import de.ostfale.qk.parser.discipline.DisciplineParserService;
import de.ostfale.qk.parser.tournament.TournamentParserService;
import de.ostfale.qk.parser.tournament.model.TournamentYearParserModel;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Read all Information DM 2024")
@QuarkusTest
@Tag("unittest")
class TournamentOldParserTest extends BaseTest {

    @Inject
    TournamentParserService parser;

    @Inject
    DisciplineParserService disciplineParser;

    HtmlElement content;

    @BeforeEach
    void setUp() {
        String testFileName = "tournaments/TournamentDMBonn.txt";
        HtmlPage page = loadHtmlPage(testFileName);
        content = page.getActiveElement();
    }

    @Test
    @DisplayName("Parse all tournament infos for DM 2024 Bonn")
    void parseTournamentInfos() {
        // given
        var expectedYear = "2024";
        var expectedTournaments = 1;

        var expectedTournamentId = "8FE45CBC-7603-47EA-A189-2CD5A6FC6505";
        var expectedTournamentName = "Deutsche Einzelmeisterschaft U15 und U17 Bonn 2024";
        var expectedTournamentOrga = "Deutscher Badminton Verband (U19)";
        var expectedTournamentLocation = "Bonn";
        var expectedTournamentDate = "29.11.2024 bis 01.12.2024";

        // when
        TournamentYearParserModel result = parser.parseTournamentYear("2024", content);
        var firstTournament = result.tournaments().getFirst();

        // then
        assertAll("Parse tournament general information",
                () -> assertEquals(expectedYear, result.year()),
                () -> assertEquals(expectedTournaments, result.tournaments().size()),
                () -> assertEquals(expectedTournamentId, firstTournament.getTournamentId()),
                () -> assertEquals(expectedTournamentName, firstTournament.getTournamentName()),
                () -> assertEquals(expectedTournamentOrga, firstTournament.getTournamentOrganisation()),
                () -> assertEquals(expectedTournamentLocation, firstTournament.getTournamentLocation()),
                () -> assertEquals(expectedTournamentDate, firstTournament.getTournamentDate())
        );
    }

    @Test
    @DisplayName("Parse general information for all disciplines played")
    void parseTournamentDisciplines() {
        // given
        var expectedTournamentDisciplinesSize = 3;
        var expectedTournamentDisciplineAgeGroup = "U15";
        var expectedTournamentFirstDisciplineName = "Doppel U15";
        var expectedTournamentFirstDiscipline = "DOUBLE";
        var expectedTournamentSecondDisciplineName = "Einzel U15";
        var expectedTournamentSecondDiscipline = "SINGLE";
        var expectedTournamentThirdDisciplineName = "Mixed U15";
        var expectedTournamentThirdDiscipline = "MIXED";


        // when
        TournamentYearParserModel result = parser.parseTournamentYear("2024", content);
        var firstTournament = result.tournaments().getFirst();
        var doubleDiscipline = firstTournament.getTournamentDisciplines().getFirst();
        var singleDiscipline = firstTournament.getTournamentDisciplines().get(1);
        var mixedDiscipline = firstTournament.getTournamentDisciplines().get(2);

        // then
        assertAll("Test parameter for general discipline information",
                () -> assertEquals(expectedTournamentDisciplinesSize, firstTournament.getTournamentDisciplines().size()),
                () -> assertEquals(expectedTournamentDisciplineAgeGroup, doubleDiscipline.getAgeClass().name()),
                () -> assertEquals(expectedTournamentFirstDisciplineName, doubleDiscipline.getDisciplineName()),
                () -> assertEquals(expectedTournamentFirstDiscipline, doubleDiscipline.getDiscipline().name()),
                () -> assertEquals(expectedTournamentSecondDisciplineName, singleDiscipline.getDisciplineName()),
                () -> assertEquals(expectedTournamentSecondDiscipline, singleDiscipline.getDiscipline().name()),
                () -> assertEquals(expectedTournamentDisciplineAgeGroup, singleDiscipline.getAgeClass().name()),
                () -> assertEquals(expectedTournamentThirdDisciplineName, mixedDiscipline.getDisciplineName()),
                () -> assertEquals(expectedTournamentThirdDiscipline, mixedDiscipline.getDiscipline().name()),
                () -> assertEquals(expectedTournamentDisciplineAgeGroup, mixedDiscipline.getAgeClass().name())
        );
    }


    @Test
    @DisplayName("Parse discipline match information")
    void parseDisciplineGroups() {
        // given

        // when
        //   List<DisciplineDTO> result = di.

        // then
    }

    @Test
    @DisplayName("Parse all matches information")
    void parseMatches() {
        // given

        // when

        // then
    }
}

