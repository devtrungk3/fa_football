package fa.football.entity;

import java.io.Serializable;

public class MatchEventPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private int matchId;
	private int playerId;
	private int eventId;
	private int minute;
	public MatchEventPK(int matchId, int playerId, int eventId, int minute) {
		super();
		this.matchId = matchId;
		this.playerId = playerId;
		this.eventId = eventId;
		this.minute = minute;
	}
	public MatchEventPK() {
		super();
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
}
