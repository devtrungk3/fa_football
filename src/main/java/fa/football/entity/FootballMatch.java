package fa.football.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "football_match")
public class FootballMatch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_id")
	private int matchId;
	
	@ManyToOne
	@JoinColumn(name = "first_team", referencedColumnName = "team_id")
	private Team firstTeam;
	@ManyToOne
	@JoinColumn(name = "second_team", referencedColumnName = "team_id")
	private Team secondTeam;
	
	@Column(name = "first_team_score")
	private int firstTeamScore;
	@Column(name = "second_team_score")
	private int secondTeamScore;
	private LocalDateTime matchDatetime;
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;
	
	@ManyToOne
	@JoinColumn(name = "stage_id")
	private LeagueStage stage;

	@OneToMany(mappedBy = "match")
	private List<MatchEvent> matchEvent;

	public FootballMatch(int matchId, Team firstTeam, Team secondTeam, int firstTeamScore, int secondTeamScore,
			LocalDateTime matchDatetime, String location, League league, LeagueStage stage) {
		super();
		this.matchId = matchId;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.firstTeamScore = firstTeamScore;
		this.secondTeamScore = secondTeamScore;
		this.matchDatetime = matchDatetime;
		this.location = location;
		this.league = league;
		this.stage = stage;
	}

	public FootballMatch() {
		super();
	}



	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public Team getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}

	public Team getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}

	public int getFirstTeamScore() {
		return firstTeamScore;
	}

	public void setFirstTeamScore(int firstTeamScore) {
		this.firstTeamScore = firstTeamScore;
	}

	public int getSecondTeamScore() {
		return secondTeamScore;
	}

	public void setSecondTeamScore(int secondTeamScore) {
		this.secondTeamScore = secondTeamScore;
	}

	public LocalDateTime getMatchDatetime() {
		return matchDatetime;
	}

	public void setMatchDatetime(LocalDateTime matchDatetime) {
		this.matchDatetime = matchDatetime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public LeagueStage getStage() {
		return stage;
	}

	public void setStage(LeagueStage stage) {
		this.stage = stage;
	}

	public List<MatchEvent> getMatchEvent() {
		return matchEvent;
	}

	public void setMatchEvent(List<MatchEvent> matchEvent) {
		this.matchEvent = matchEvent;
	}
	
	
}
