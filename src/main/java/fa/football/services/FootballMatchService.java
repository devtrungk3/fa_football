package fa.football.services;

import java.util.List;
import java.util.Map;

import fa.football.entity.Event;
import fa.football.entity.FootballMatch;
import fa.football.entity.MatchEvent;
import fa.football.entity.Player;

public interface FootballMatchService {
	Map<String, Object> getMatchesHeldInCurrentYearByMonth();
	int countAllMatches();
	List<FootballMatch> getAllMatches();
	List<FootballMatch> getMatchesByLeagueId(int leagueId);
	List<FootballMatch> getMatchesByLeagueIdAndStageId(int leagueId, int stageId);
	Map<String, List<FootballMatch>> getMatchesByGroup(int leagueId);
	List<Player> findByTeam(int teamId);
	List<MatchEvent> getEventsByMatchId(int matchId);
	List<MatchEvent> getEventsByMatchIdFirstTeam(int matchId);
	List<MatchEvent> getEventsByMatchIdSecondTeam(int matchId);
	List<MatchEvent> getEventsByMatchIdOrderByTime(int matchId);
	List<Event> findAllEvent();
	void addEvent(int matchId, int playerId, int eventId, int minute, String description);
	void deleteEvent(int matchId, int playerId, int eventId, int minute);
	FootballMatch findMatchById(int matchId);
	FootballMatch saveMatch(FootballMatch match);
	void deleteMatch(int matchId);


}
