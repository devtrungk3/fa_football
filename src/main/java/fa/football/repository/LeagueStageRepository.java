package fa.football.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.football.entity.LeagueStage;

public interface LeagueStageRepository extends JpaRepository<LeagueStage, Integer>{
	Page<LeagueStage> findAllLeagueStagesByStageNameContaining(String name, Pageable pageable);
}
