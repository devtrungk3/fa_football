package fa.football.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private int teamId;
	
	@Column(name = "team_name")
	private String teamName;
	
	@Column(name = "coach_name")
	private String coachName;
	
	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;
	
	@ManyToOne
	@JoinColumn(name = "manager", referencedColumnName = "email")
	private Account manager;
	
	@OneToMany(mappedBy = "team")
	private List<Player> players;
	
	@OneToMany(mappedBy = "firstTeam")
	private List<FootballMatch> firstMatches;
	
	@OneToMany(mappedBy = "secondTeam")
	private List<FootballMatch> secondMatches;
	
	@OneToOne(mappedBy = "team")
	private Standing standing;

	public Team(int teamId, String teamName, String coachName) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.coachName = coachName;
	}

	public Team() {
		super();
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Account getManager() {
		return manager;
	}

	public void setManager(Account manager) {
		this.manager = manager;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<FootballMatch> getFirstMatches() {
		return firstMatches;
	}

	public void setFirstMatches(List<FootballMatch> firstMatches) {
		this.firstMatches = firstMatches;
	}

	public List<FootballMatch> getSecondMatches() {
		return secondMatches;
	}

	public void setSecondMatches(List<FootballMatch> secondMatches) {
		this.secondMatches = secondMatches;
	}

	public Standing getStanding() {
		return standing;
	}

	public void setStanding(Standing standing) {
		this.standing = standing;
	}
	
	
}
