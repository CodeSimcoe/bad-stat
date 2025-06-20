package de.ostfale.qk.domain.tournament;

import de.ostfale.qk.domain.match.MatchOld;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TournamentOld {

    private Long id;

    private String tournamentID;
    private String tournamentName;
    private String tournamentOrganizer;
    private String tournamentLocation;
    private String tournamentDate;
    private Integer tournamentYear;

    private Set<MatchOld> matchOlds;

    public TournamentOld(String tournamentID, String tournamentName, String tournamentOrganizer, String tournamentLocation, String tournamentDate, Integer tournamentYear) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.tournamentOrganizer = tournamentOrganizer;
        this.tournamentLocation = tournamentLocation;
        this.tournamentDate = tournamentDate;
        this.tournamentYear = tournamentYear;
    }

    public TournamentOld() {
    }

    public boolean containsPlayer(String playerName) {
        return hasPlayerInMatchSet(matchOlds, playerName);
    }

    private <T> boolean hasPlayerInMatchSet(Set<T> matches, String playerName) {
        return matches.stream().anyMatch(match -> ((MatchOld) match).containsPlayer(playerName));
    }

    public List<MatchOld> getMatches() {
        return matchOlds.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void setMatches(Set<MatchOld> matchOlds) {
        this.matchOlds = matchOlds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(String tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentOrganizer() {
        return tournamentOrganizer;
    }

    public void setTournamentOrganizer(String tournamentOrganizer) {
        this.tournamentOrganizer = tournamentOrganizer;
    }

    public String getTournamentLocation() {
        return tournamentLocation;
    }

    public void setTournamentLocation(String tournamentLocation) {
        this.tournamentLocation = tournamentLocation;
    }

    public String getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(String tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

    public Integer getTournamentYear() {
        return tournamentYear;
    }

    public void setTournamentYear(Integer tournamentYear) {
        this.tournamentYear = tournamentYear;
    }
}
