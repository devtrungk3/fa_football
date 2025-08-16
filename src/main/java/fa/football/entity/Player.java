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
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
	private int playerId;
	
	@Column(name = "player_name")
	private String playerName;
	
	@Column(name = "shirt_number")
	private int shirtNumber;
	
	private String phone;
	private Date birth;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@OneToMany(mappedBy = "player")
	private List<MatchEvent> matchEvents;

	public Player(int playerId, String playerName, int shirtNumber, String phone, Date birth, Team team) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.shirtNumber = shirtNumber;
		this.phone = phone;
		this.birth = birth;
		this.team = team;
	}

	public Player() {
		super();
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<MatchEvent> getMatchEvents() {
		return matchEvents;
	}

	public void setMatchEvents(List<MatchEvent> matchEvents) {
		this.matchEvents = matchEvents;
	}
	
	
}
