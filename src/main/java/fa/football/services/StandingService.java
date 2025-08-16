package fa.football.services;

import java.util.List;

import fa.football.dto.TeamStandingDTO;

public interface StandingService {
	List<String> findDistinctStandingsByLeague(int leagueId);
    void addTeamsToGroup(String groupName, List<Integer> teamIds, int leagueId);
    List<TeamStandingDTO> getTeamStandingsByLeagueId(int leagueId);
    
}
