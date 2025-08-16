package fa.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.football.entity.League;

public interface LeagueRepository extends JpaRepository<League, Integer>{
	@Query(value = "SELECT * FROM league WHERE league.manager = :email", nativeQuery = true)
	List<League> findByManager(@Param("email") String email);
	List<League> findByFormat(String format);
    @Query("SELECT l FROM League l WHERE l.stage.stageId = :stageId")
    List<League> findByStageId(@Param("stageId") Integer stageId);
    List<League> findByLeagueNameContaining(String keyword);

	@Query("SELECT COUNT(p.playerId) FROM Player p " + "JOIN p.team t " + "JOIN t.league l "
			+ "WHERE l.leagueId = :leagueId " + "GROUP BY l.leagueId")
	String countPlayersByLeagueId(@Param("leagueId") int leagueId);

	@Query(value = "SELECT COUNT(match_event.event_id) AS redCardCount FROM match_event INNER JOIN football_match ON match_event.match_id = football_match.match_id WHERE match_event.event_id = 5 AND football_match.league_id = :leagueId GROUP BY match_event.event_id;", nativeQuery = true)
	String countRedCards(@Param("leagueId") int leagueId);
	
	@Query(value = "SELECT COUNT(match_event.event_id) AS yellowCardCount FROM match_event INNER JOIN football_match ON match_event.match_id = football_match.match_id WHERE match_event.event_id = 6 AND football_match.league_id = :leagueId GROUP BY match_event.event_id;", nativeQuery = true)
	String countYellowCards(@Param("leagueId") int leagueId);
	
	@Query(value = "SELECT COUNT(match_event.event_id) AS goals FROM match_event INNER JOIN football_match ON match_event.match_id = football_match.match_id WHERE match_event.event_id = 1 AND football_match.league_id = :leagueId GROUP BY match_event.event_id;", nativeQuery = true)
	String countGoals(@Param("leagueId") int leagueId);

	@Query(value = "SELECT COUNT(league_id) FROM league WHERE end_date < current_date()", nativeQuery = true)
	int countByEndDateBefore();
	@Query(value = "SELECT COUNT(league_id) FROM league WHERE start_date <= current_date() AND end_date >= current_date()", nativeQuery = true)
	int countByDatePresent();
	@Query(value = "SELECT COUNT(league_id) FROM league WHERE start_date > current_date()", nativeQuery = true)
	int countByStartDateAfter();
}
