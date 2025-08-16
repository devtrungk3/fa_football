package fa.football.repository;

import fa.football.entity.Team;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	List<Team> findByLeague_LeagueId(int leagueId);
	List<Team> findByLeagueIsNull();
	@Query("SELECT t FROM Team t WHERE t.league.leagueId = :leagueId AND t.teamId NOT IN (SELECT s.team.teamId FROM Standing s WHERE s.league.leagueId = :leagueId)")
    List<Team> findTeamsWithoutStanding(@Param("leagueId") int leagueId);
	@Query("SELECT t FROM Team t WHERE t.league.leagueId = :leagueId")
	List<Team> findAllByLeagueId(@Param("leagueId") int leagueId);
	@Modifying
	@Transactional
	@Query("UPDATE Team t SET t.league.leagueId = NULL WHERE t.teamId = :teamId")
	void setLeagueIdIsNullForTeam(@Param("teamId") int teamId);
	@Query("SELECT t FROM Team t WHERE t.league.leagueId = :leagueId")
	List<Team> findAllTeamWithLeagueId(@Param("leagueId") int leagueId);
    @Modifying
    @Query(value = "select * from team where team.manager=?",nativeQuery = true)
    List<Team> findByManager(String manager);

    @Modifying
    @Query(value = "update team set coach_name=?, team_name=? where team_id=?", nativeQuery = true)
    void updateTeam(String coach, String team, int id);


}
