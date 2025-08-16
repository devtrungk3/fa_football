package fa.football.entity;

import java.io.Serializable;

public class LeagueInvitationPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private int leagueId;
	private String teamManager;
	
	public LeagueInvitationPK(int leagueId, String teamManager) {
		super();
		this.leagueId = leagueId;
		this.teamManager = teamManager;
	}

	public LeagueInvitationPK() {
		super();
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public String getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(String teamManager) {
		this.teamManager = teamManager;
	}
	
	
}
