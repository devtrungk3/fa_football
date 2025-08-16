package fa.football.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "league_stage")
public class LeagueStage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stage_id")
	private int stageId;
	
	@Column(name = "stage_name")
	private String stageName;
	
	@OneToMany(mappedBy = "stage")
	List<League> leagues;
	
	@OneToMany(mappedBy = "stage")
	List<FootballMatch> matches;

	public LeagueStage(int stageId, String stageName) {
		super();
		this.stageId = stageId;
		this.stageName = stageName;
	}

	public LeagueStage() {
		super();
	}

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public List<FootballMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<FootballMatch> matches) {
		this.matches = matches;
	}
	
	
}
