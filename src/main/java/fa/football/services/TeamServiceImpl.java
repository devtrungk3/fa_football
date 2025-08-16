package fa.football.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.football.entity.Account;
import fa.football.entity.FootballMatch;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;
import fa.football.entity.MatchEvent;
import fa.football.entity.Standing;
import fa.football.entity.Team;
import fa.football.repository.AccountRepository;
import fa.football.repository.FootballMatchRepository;
import fa.football.repository.LeagueInvitationRepository;
import fa.football.repository.LeagueRepository;
import fa.football.repository.MatchEventRepository;
import fa.football.repository.StandingRepository;
import fa.football.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Override
	public List<Team> getTeamsByLeagueId(int leagueId) {
		return teamRepository.findByLeague_LeagueId(leagueId);
	}

	@Override
	public List<Team> getAllTeam() {
		return teamRepository.findAll();
	}

	@Override
	public Team findTeamById(int teamId) {
		return teamRepository.findById(teamId).orElse(null);
	}

	@Override
	public Team saveTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void addTeamToLeague(int leagueId, int teamId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		League league = leagueRepository.findById(leagueId).orElse(null);
		team.setLeague(league);
		teamRepository.save(team);
	}

	@Override
	public List<Team> getTeamWithoutLeague() {
		return teamRepository.findByLeagueIsNull();
	}

	@Override
	public void addTeamToLeague(int leagueId, Team team) {
		League league = leagueRepository.findById(leagueId).orElse(null);
		team.setLeague(league);
		teamRepository.save(team);
	}

	@Override
	public void addMultipleTeamsToLeague(int leagueId, List<Team> teams) {
		League league = leagueRepository.findById(leagueId).orElse(null);
		for (Team team : teams) {
			team.setLeague(league);
			teamRepository.save(team);	
		}
	}

	@Override
	public List<Team> findTeamsWithoutStanding(int leagueId) {
		return teamRepository.findTeamsWithoutStanding(leagueId);
	}

	@Override
	public void updateLeagueIdIsNull(int teamId) {
		teamRepository.setLeagueIdIsNullForTeam(teamId);
	}

	@Override
	public List<Team> getAllTeamWithLeagueId(int leagueId) {
		return teamRepository.findAllTeamWithLeagueId(leagueId);
	}


    @Autowired
    private TeamRepository tRepository;

    @Autowired
    private FootballMatchRepository footballMatchRepository;

    @Autowired
    private StandingRepository standingRepository;

    @Autowired
    private MatchEventRepository matchEventRepository;

    @Autowired
    private LeagueInvitationRepository leagueInvitationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Team findById(int id) {
        return tRepository.findById(id).orElse(null);
    }

    @Override
    public Team findByManager(String manager) {
        List<Team> teams = tRepository.findByManager(manager);
        if (teams.isEmpty()) {
            return null;
        } else {
            return teams.get(0);
        }
    }

    @Transactional
    @Override
    public void updateTeam(String coach, String team, int id) {
        tRepository.updateTeam(coach,team,id);
    }

    @Override
    public List<FootballMatch> findMatchByTeam(int id) {
        List<FootballMatch> list = footballMatchRepository.findByTeam(id,id);
        return list;
    }

    @Override
    public List<Standing> findStandingByTeam(int id) {
        return standingRepository.findByTeam(id);
    }

    @Override
    public List<MatchEvent> findGoalByTeam(int id) {
        return matchEventRepository.getGoalByTeam(id);
    }

    @Override
    public void registerLeague(String manager, int leagueId, String status) {
        LeagueInvitationPK leagueInvitationPK = new LeagueInvitationPK(leagueId, manager);
        
        LeagueInvitation leagueInvitation = new LeagueInvitation(leagueInvitationPK, status);
        
        League league = new League();
        league.setLeagueId(leagueId);
        
        Account account = new Account();
        account.setEmail(manager);
        
        leagueInvitation.setLeague(league);
        leagueInvitation.setTeamManager(account);

        leagueInvitationRepository.save(leagueInvitation);
    }

    @Override
    public LeagueInvitation findInvitationByManager(String manager) {
        List<LeagueInvitation> list = leagueInvitationRepository.findByManager(manager);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void createTeam(String teamName, String coachName, String manager) {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setCoachName(coachName);
        League league = this.findInvitationByManager(manager).getLeague();
        team.setLeague(league);
        team.setManager(accountRepository.findById(manager).orElse(null));

        tRepository.save(team);
    }

    @Override
    public List<Team> findAll() {
        return tRepository.findAll();
    }

	@Override
	public int countAllTeams() {
		return (int) tRepository.count();
	}
}
