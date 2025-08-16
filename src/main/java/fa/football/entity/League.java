package fa.football.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class League {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "league_id")
	private int leagueId;
	
	@Column(name = "league_name")
	private String leagueName;
	
	@Column(name = "team_number")
	private int teamNumber;
	
	private String format;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "manager", referencedColumnName = "email")
	private Account manager;
	
	@ManyToOne
	@JoinColumn(name = "teamsize_id")
	private Teamsize teamsize;
	
	@ManyToOne
	@JoinColumn(name = "stage_id")
	private LeagueStage stage;
	
	@OneToMany(mappedBy = "league")
	private List<Team> teams;
	
	@OneToMany(mappedBy = "league")
	private List<Standing> standings;
	
	@OneToMany(mappedBy = "league")
	private List<FootballMatch> matches; 
	
	@OneToMany(mappedBy = "league")
	private List<LeagueInvitation> invitations;

	public League(int leagueId, String leagueName, int teamNumber, String format, Date startDate, Date endDate,
			String description) {
		super();
		this.leagueId = leagueId;
		this.leagueName = leagueName;
		this.teamNumber = teamNumber;
		this.format = format;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public League() {
		super();
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getManager() {
		return manager;
	}

	public void setManager(Account manager) {
		this.manager = manager;
	}

	public Teamsize getTeamsize() {
		return teamsize;
	}

	public void setTeamsize(Teamsize teamsize) {
		this.teamsize = teamsize;
	}

	public LeagueStage getStage() {
		return stage;
	}

	public void setStage(LeagueStage stage) {
		this.stage = stage;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Standing> getStandings() {
		return standings;
	}

	public void setStandings(List<Standing> standings) {
		this.standings = standings;
	}

	public List<FootballMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<FootballMatch> matches) {
		this.matches = matches;
	}

	public List<LeagueInvitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<LeagueInvitation> invitations) {
		this.invitations = invitations;
	}
	
	
}
