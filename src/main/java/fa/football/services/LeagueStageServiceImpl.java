package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.football.entity.LeagueStage;
import fa.football.repository.LeagueStageRepository;

@Service
public class LeagueStageServiceImpl implements LeagueStageService{
	
	@Autowired
	LeagueStageRepository repository;
	
	@Override
	public void create(LeagueStage leagueStage) {
		repository.save(leagueStage);
	}

	@Override
	public void update(LeagueStage leagueStage) {
		repository.save(leagueStage);
	}

	@Override
	public void delete(int stageId) {
		repository.deleteById(stageId);
	}

	@Override
	public LeagueStage findById(int stageId) {
		return repository.findById(stageId).orElse(null);
	}

	@Override
	public List<LeagueStage> getLeagueStages() {
		return repository.findAll();
	}

	@Override
	public Page<LeagueStage> getLeagueStagesWithPaging(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<LeagueStage> getLeagueStagesByName(String name, Pageable pageable) {
		return repository.findAllLeagueStagesByStageNameContaining(name, pageable);
	}

}
