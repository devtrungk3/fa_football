package fa.football.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private int eventId;
	
	@Column(name = "event_name")
	private String eventName;
	
	@OneToMany(mappedBy = "event")
	private List<MatchEvent> matchEvents;

	public Event(int eventId, String eventName) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
	}

	public Event() {
		super();
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<MatchEvent> getMatchEvents() {
		return matchEvents;
	}

	public void setMatchEvents(List<MatchEvent> matchEvents) {
		this.matchEvents = matchEvents;
	}
	
	
}
