package fa.football.repository;

import fa.football.entity.Standing;
import fa.football.entity.StandingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StandingRepository extends JpaRepository<Standing, StandingPK> {
	@Query("SELECT DISTINCT s.standingName FROM Standing s WHERE s.league.leagueId = :leagueId")
	List<String> findDistinctStandingNamesByLeagueId(@Param("leagueId") int leagueId);

	List<Standing> findByLeague_LeagueId(int leagueId);
	
	@Query("SELECT s FROM Standing s WHERE s.league.leagueId = :leagueId")
	List<Standing> findAllByLeagueId(@Param("leagueId") int leagueId);
	
    @Modifying
    @Query(value = "select * from standing where team_id =?", nativeQuery = true)
    List<Standing> findByTeam(int id);

}
