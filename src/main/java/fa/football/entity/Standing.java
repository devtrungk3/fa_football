package fa.football.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;

@Entity
public class Standing {
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "leagueId", column = @Column(name = "league_id")),
		@AttributeOverride(name = "teamId", column = @Column(name = "team_id")),
	})
	private StandingPK pk;
	
	@Column(name = "standing_name")
	private String standingName;
	
	private int win;
	private int loss;
	private int draw;
	private int gf;
	private int ga;
	
	@OneToOne
	@MapsId("teamId")
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToOne
	@MapsId("leagueId")
	@JoinColumn(name = "league_id")
	private League league;

	public Standing(StandingPK pk, String standingName, int win, int loss, int draw, int gf, int ga) {
		super();
		this.pk = pk;
		this.standingName = standingName;
		this.win = win;
		this.loss = loss;
		this.draw = draw;
		this.gf = gf;
		this.ga = ga;
	}

	public Standing() {
		super();
	}

	public StandingPK getPk() {
		return pk;
	}

	public void setPk(StandingPK pk) {
		this.pk = pk;
	}

	public String getStandingName() {
		return standingName;
	}

	public void setStandingName(String standingName) {
		this.standingName = standingName;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getGf() {
		return gf;
	}

	public void setGf(int gf) {
		this.gf = gf;
	}

	public int getGa() {
		return ga;
	}

	public void setGa(int ga) {
		this.ga = ga;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	
	public int getNumberOfMatch() {
		return win + loss + draw;
	}
	
	public int getTotalScore() {
		return win * 3 + draw;
	}

}
