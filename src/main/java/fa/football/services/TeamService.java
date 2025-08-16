package fa.football.services;

import fa.football.entity.*;

import java.util.List;

public interface TeamService {
    public Team findById(int id);

    public Team findByManager(String manager);

    public void updateTeam(String coach, String team, int id);

    List<FootballMatch> findMatchByTeam(int id);

    List<Standing> findStandingByTeam(int id);

    List<MatchEvent> findGoalByTeam(int id);

    void registerLeague(String manager, int leagueId, String status);

    LeagueInvitation findInvitationByManager(String manager);

    void createTeam(String teamName, String coachName, String manager);

    List<Team> findAll();
    
    int countAllTeams();
    
	List<Team> getAllTeam();
	Team findTeamById(int teamId);
	Team saveTeam(Team team);
	void addTeamToLeague(int leagueId, int teamId);
	
	void addTeamToLeague(int leagueId, Team team);
	void addMultipleTeamsToLeague(int leagueId, List<Team> teams);
	List<Team> getTeamWithoutLeague();
	List<Team> getTeamsByLeagueId(int leagueId);
	List<Team> findTeamsWithoutStanding(int leagueId);
	void updateLeagueIdIsNull(int teamId);
	List<Team> getAllTeamWithLeagueId(int leagueId);

}
