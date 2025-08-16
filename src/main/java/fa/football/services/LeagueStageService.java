package fa.football.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.football.entity.LeagueStage;

public interface LeagueStageService {
	void create(LeagueStage leagueStage);
	void update(LeagueStage leagueStage);
	void delete(int stageId);
	LeagueStage findById(int stageId);
	List<LeagueStage> getLeagueStages();
	Page<LeagueStage> getLeagueStagesWithPaging(Pageable pageable);
	Page<LeagueStage> getLeagueStagesByName(String name, Pageable pageable);
}
