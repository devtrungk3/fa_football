package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.Account;
import fa.football.entity.League;
import fa.football.repository.LeagueRepository;

@Service
public class LeagueServiceImpl implements LeagueService{
	
	@Autowired
	LeagueRepository repository;

	@Override
	public int countAllLeagues() {
		return (int) repository.count();
	}
	
	@Override
	public List<League> getAllLeagues() {
		return repository.findAll();
	}

	@Override
	public League getLeagueById(int leagueId) {
		return repository.findById(leagueId).orElse(null);
	}

	@Override
	public League saveLeague(League league) {
		return repository.save(league);
	}

	@Override
	public void deleteLeague(int leagueId) {
		repository.deleteById(leagueId);
	}
	
	@Override
	public List<League> getLeaguesByFormat(String format) {
		return repository.findByFormat(format);
	}

	@Override
	public List<League> getLeaguesByStage(int teamNumber) {
		return repository.findByStageId(teamNumber);
	}

	@Override
	public List<League> searchLeagues(String keyword) {
		return repository.findByLeagueNameContaining(keyword);
	}

	@Override
	public String countPlayers(int leagueId) {
		return repository.countPlayersByLeagueId(leagueId);
	}

	@Override
	public String countRedCards(int leagueId) {
		return repository.countRedCards(leagueId);
	}

	@Override
	public String countYellowCards(int leagueId) {
		return repository.countYellowCards(leagueId);
	}

	@Override
	public String countGoals(int leagueId) {
		return repository.countGoals(leagueId);
	}


	@Override
	public int countLeagueBefore() {
		return repository.countByEndDateBefore();
	}

	@Override
	public int countLeaguePresent() {
		return repository.countByDatePresent();
	}

	@Override
	public int countLeagueAfter() {
		return repository.countByStartDateAfter();
	}

	@Override
	public List<League> getLeaguesByManager(String email) {
		return repository.findByManager(email);
	}

}
