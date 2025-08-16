package fa.football.services;

import java.util.List;

import fa.football.entity.League;

public interface LeagueService {
	List<League> getAllLeagues();
	League getLeagueById(int leagueId);
	League saveLeague(League league);
	void deleteLeague(int leagueId);

	List<League> getLeaguesByFormat(String format);
	List<League> getLeaguesByStage(int teamNumber);
	List<League> getLeaguesByManager(String email);
	public List<League> searchLeagues(String keyword);
	String countPlayers(int leagueId);
	String countRedCards(int leagueId);
	String countYellowCards(int leagueId);
	String countGoals(int leagueId);

	int countAllLeagues();
	int countLeagueBefore();
	int countLeaguePresent();
	int countLeagueAfter();
}
