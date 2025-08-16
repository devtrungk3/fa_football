package fa.football.dto;

import fa.football.entity.Standing;
import fa.football.entity.Team;

public class TeamStandingDTO {
	private Team team;
    private Standing standing;
    
	public TeamStandingDTO(Team team, Standing standing) {
		super();
		this.team = team;
		this.standing = standing;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Standing getStanding() {
		return standing;
	}

	public void setStanding(Standing standing) {
		this.standing = standing;
	}
    
    
}
