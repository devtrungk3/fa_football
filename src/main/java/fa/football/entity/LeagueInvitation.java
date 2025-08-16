package fa.football.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "league_invitation")
public class LeagueInvitation {
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "leagueId", column = @Column(name = "league_id")),
		@AttributeOverride(name = "teamManager", column = @Column(name = "team_manager")),
	})
	LeagueInvitationPK pk;
	
	private String status;
	
	@ManyToOne
	@MapsId("leagueId")
	@JoinColumn(name = "league_id")
	private League league;
	
	@ManyToOne
	@MapsId("teamManager")
	@JoinColumn(name = "team_manager", referencedColumnName = "email")
	private Account teamManager;

	public LeagueInvitation(LeagueInvitationPK pk, String status) {
		super();
		this.pk = pk;
		this.status = status;
	}

	public LeagueInvitation() {
		super();
	}

	public LeagueInvitationPK getPk() {
		return pk;
	}

	public void setPk(LeagueInvitationPK pk) {
		this.pk = pk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Account getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(Account teamManager) {
		this.teamManager = teamManager;
	}
	
	
}
