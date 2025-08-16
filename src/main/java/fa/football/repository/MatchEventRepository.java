package fa.football.repository;

import fa.football.entity.MatchEvent;
import fa.football.entity.MatchEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchEventRepository extends JpaRepository<MatchEvent, MatchEventPK> {
	@Modifying
    @Query(value = "SELECT  event_id, count(*) as value FROM fa_football.match_event where player_id=? group by event_id;", nativeQuery = true)
    List<Object[]> getEventByPlayer(int playerId);

    @Modifying
    @Query(value = "SELECT match_event.* FROM fa_football.player join fa_football.match_event on player.player_id=match_event.player_id where player.team_id=? and event_id=1;", nativeQuery = true)
    List<MatchEvent> getGoalByTeam(int teamId);
    
    List<MatchEvent> findByMatch_MatchId(int matchId);
    
    @Modifying
    @Query(value = "select * from match_event where player_id in (select player_id from player where team_id =(SELECT first_team FROM football_match where match_id=?))", nativeQuery = true)
    List<MatchEvent> findByMatchId_FirstTeam(int matchId);
    
    @Modifying
    @Query(value = "select * from match_event where player_id in (select player_id from player where team_id =(SELECT second_team FROM football_match where match_id=?))", nativeQuery = true)
    List<MatchEvent> findByMatchId_SecondTeam(int matchId);
    
    @Modifying
    @Query(value = "SELECT * FROM match_event WHERE match_id=? ORDER BY minute", nativeQuery = true)
    List<MatchEvent> findByMatchOrderByTime(int matchId);

	@Modifying
	@Query(value = "SELECT team.team_name, player.player_name, player.shirt_number, count(*) as goal FROM  fa_football.match_event join player on match_event.player_id=player.player_id join team on player.team_id=team.team_id join football_match on match_event.match_id=football_match.match_id where event_id=1 AND football_match.league_id=:leagueId group by match_event.player_id order by goal desc;", nativeQuery = true)
	List<Object[]> getPlayerGoals(@Param("leagueId") int leagueId);

}
