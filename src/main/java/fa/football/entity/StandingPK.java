package fa.football.entity;

import java.io.Serializable;

public class StandingPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private int leagueId;
	private int teamId;
	public StandingPK(int leagueId, int teamId) {
		super();
		this.leagueId = leagueId;
		this.teamId = teamId;
	}
	public StandingPK() {
		super();
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	
}
