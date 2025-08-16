package fa.football.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.football.dto.TeamStandingDTO;
import fa.football.entity.League;
import fa.football.entity.Standing;
import fa.football.entity.StandingPK;
import fa.football.entity.Team;
import fa.football.repository.StandingRepository;
import fa.football.repository.TeamRepository;

@Service
public class StandingServiceImpl implements StandingService {

	@Autowired
    private StandingRepository standingRepository;
	
	@Autowired
	private TeamRepository teamRepository;
		
	@Override
	public List<String> findDistinctStandingsByLeague(int leagueId) {
		return standingRepository.findDistinctStandingNamesByLeagueId(leagueId);
	}
	
	@Override
    @Transactional
    public void addTeamsToGroup(String groupName, List<Integer> teamIds, int leagueId) {
        for (int teamId : teamIds) {
            StandingPK standingPK = new StandingPK();
            standingPK.setTeamId(teamId);
            standingPK.setLeagueId(leagueId);

            Standing standing = new Standing();
            standing.setPk(standingPK);
            standing.setStandingName(groupName);
            
            League league = new League();
            league.setLeagueId(leagueId);
            standing.setLeague(league);

            Team team = new Team();
            team.setTeamId(teamId);
            standing.setTeam(team);

            standingRepository.save(standing);
        }
    }

	@Override
	public List<TeamStandingDTO> getTeamStandingsByLeagueId(int leagueId) {
		List<Team> teams = teamRepository.findAllByLeagueId(leagueId);
        List<Standing> standings = standingRepository.findAllByLeagueId(leagueId);
        
        List<TeamStandingDTO> result = new ArrayList<>();
        for (Team team : teams) {
            Standing standing = standings.stream()
                .filter(s -> s.getPk().getTeamId() == team.getTeamId())
                .findFirst()
                .orElse(null);
            
            result.add(new TeamStandingDTO(team, standing));
        }
        
        result.sort((o1, o2) -> Integer.compare(o2.getStanding().getTotalScore(), o1.getStanding().getTotalScore()));
        
        return result;
	}

}
