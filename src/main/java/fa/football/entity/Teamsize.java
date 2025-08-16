package fa.football.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Teamsize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teamsize_id")
	private int teamsizeId;
	private int size;
	@OneToMany(mappedBy = "teamsize")
	private List<League> leagues;
	
	public Teamsize() {
		super();
	}

	public Teamsize(int teamsizeId, int size) {
		super();
		this.teamsizeId = teamsizeId;
		this.size = size;
	}

	public int getTeamsizeId() {
		return teamsizeId;
	}

	public void setTeamsizeId(int teamsizeId) {
		this.teamsizeId = teamsizeId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}
}
