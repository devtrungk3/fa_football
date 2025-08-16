package fa.football.repository;

import fa.football.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
	List<FootballMatch> findByLeague_LeagueId(int leagueId);
	
	@Query("""
		    SELECT fm 
		    FROM FootballMatch fm
		    JOIN fm.firstTeam ft
		    WHERE fm.matchId = :matchId AND ft.teamId = :teamId
		""")	
	Optional<FootballMatch> findByFirstTeam(@Param("matchId") int matchId, @Param("teamId") int teamId);
	@Query("""
		    SELECT fm 
		    FROM FootballMatch fm
		    JOIN fm.secondTeam st
		    WHERE fm.matchId = :matchId AND st.teamId = :teamId
		""")	
	Optional<FootballMatch> findBySecondTeam(@Param("matchId") int matchId, @Param("teamId") int teamId);

	List<FootballMatch> findByLeague_LeagueIdAndStage_StageId(int leagueId, int stageId);

	FootballMatch findById(int id);
	 
    @Modifying
    @Query(value = "SELECT * FROM fa_football.football_match where first_team=? or second_team=?;", nativeQuery = true)
    List<FootballMatch> findByTeam(int id1, int id2);
    
    @Query("SELECT FUNCTION('MONTH', fm.matchDatetime) AS match_month, COUNT(fm) AS matches_held " +
            "FROM FootballMatch fm " +
            "WHERE FUNCTION('YEAR', fm.matchDatetime) = FUNCTION('YEAR', CURRENT_DATE) " +
            "GROUP BY FUNCTION('MONTH', fm.matchDatetime)")
    List<Object[]> findMatchesHeldInCurrentYearByMonth();
}
