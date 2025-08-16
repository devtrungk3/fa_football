package fa.football.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "match_event")
public class MatchEvent {
	@EmbeddedId
	private MatchEventPK pk;
	
	@Column(insertable = false, updatable = false)
	private int minute;
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@MapsId("matchId")
	@JoinColumn(name = "match_id")
	private FootballMatch match;
	
	@ManyToOne
	@MapsId("playerId")
	@JoinColumn(name = "player_id")
	private Player player;
	
	@ManyToOne
	@MapsId("eventId")
	@JoinColumn(name = "event_id")
	private Event event;

	public MatchEvent(MatchEventPK pk, int minute, String description) {
		super();
		this.pk = pk;
		this.minute = minute;
		this.description = description;
	}

	public MatchEvent() {
		super();
	}

	public MatchEventPK getPk() {
		return pk;
	}

	public void setPk(MatchEventPK pk) {
		this.pk = pk;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FootballMatch getMatch() {
		return match;
	}

	public void setMatch(FootballMatch match) {
		this.match = match;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
