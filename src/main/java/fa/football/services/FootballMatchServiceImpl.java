package fa.football.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.Event;
import fa.football.entity.FootballMatch;
import fa.football.entity.MatchEvent;
import fa.football.entity.MatchEventPK;
import fa.football.entity.Player;
import fa.football.entity.Standing;
import fa.football.repository.EventRepository;
import fa.football.repository.FootballMatchRepository;
import fa.football.repository.MatchEventRepository;
import fa.football.repository.PlayerRepository;
import fa.football.repository.StandingRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FootballMatchServiceImpl implements FootballMatchService{
	
	@Autowired
	FootballMatchRepository repository;
	@Autowired
	private FootballMatchRepository footballMatchRepository;

	@Autowired
	private StandingRepository standingRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
    private EventRepository eventRepository;

	@Autowired
    private MatchEventRepository matchEventRepository;

	
	@Override
	public List<FootballMatch> getAllMatches() {
		return footballMatchRepository.findAll();
	}

	@Override
	public List<FootballMatch> getMatchesByLeagueId(int leagueId) {
		return footballMatchRepository.findByLeague_LeagueId(leagueId);
	}

	@Override
	public List<FootballMatch> getMatchesByLeagueIdAndStageId(int leagueId, int stageId) {
		return footballMatchRepository.findByLeague_LeagueIdAndStage_StageId(leagueId, stageId);
	}

	@Override
	public FootballMatch saveMatch(FootballMatch match) {
		return footballMatchRepository.save(match);
	}

	@Override
	public void deleteMatch(int matchId) {
		footballMatchRepository.deleteById(matchId);
	}

	@Override
	public FootballMatch findMatchById(int matchId) {
		return footballMatchRepository.findById(matchId);
	}

	@Override
	public Map<String, List<FootballMatch>> getMatchesByGroup(int leagueId) {
		Map<String, List<FootballMatch>> matchesByGroup = new HashMap<>();

		// Lấy danh sách bảng đấu theo leagueId
		List<Standing> standings = standingRepository.findByLeague_LeagueId(leagueId);

		// Tạo bản đồ nhóm với danh sách các đội
		Map<String, List<Integer>> groupsWithTeams = standings.stream().collect(Collectors.groupingBy(Standing::getStandingName, Collectors.mapping(standing -> standing.getPk().getTeamId(), Collectors.toList())));

		// Lấy tất cả các trận đấu theo leagueId
		List<FootballMatch> matches = footballMatchRepository.findByLeague_LeagueId(leagueId);

		// Lặp qua các nhóm và tìm các trận đấu tương ứng
		for (Map.Entry<String, List<Integer>> entry : groupsWithTeams.entrySet()) {
			String groupName = entry.getKey();
			List<Integer> teamIds = entry.getValue();

			// Tìm các trận đấu mà đội trong nhóm tham gia
			List<FootballMatch> groupMatches = matches.stream().filter(
					match -> teamIds.contains(match.getFirstTeam().getTeamId()) || teamIds.contains(match.getSecondTeam().getTeamId()))
					.collect(Collectors.toList());

			matchesByGroup.put(groupName, groupMatches);
		}
		
		return matchesByGroup;
	}
	
	@Override
	public List<Player> findByTeam(int teamId){
        return playerRepository.getPlayersByTeam(teamId);
    }
	
	@Override
	public List<Event> findAllEvent(){
        return eventRepository.findAll();
    }

	@Override
    public void addEvent(int matchId, int playerId, int eventId, int minute, String description){
        MatchEventPK matchEventPK = new MatchEventPK(matchId,playerId,eventId,minute);
        MatchEvent m = new MatchEvent(matchEventPK, minute, description);
        
        Event event = new Event();
        event.setEventId(eventId);
        
        FootballMatch footballMatch = new FootballMatch();
        footballMatch.setMatchId(matchId);
        
        Player player = new Player();
        player.setPlayerId(playerId);
        
        m.setEvent(event);
        m.setMatch(footballMatch);
        m.setPlayer(player);

        matchEventRepository.save(m);
    }
	
	@Override
	public void deleteEvent(int matchId, int playerId, int eventId, int minute) {
	    MatchEventPK matchEventPK = new MatchEventPK(matchId, playerId, eventId, minute);
	    if (matchEventRepository.existsById(matchEventPK)) {
	        matchEventRepository.deleteById(matchEventPK);
	    } else {
	        throw new EntityNotFoundException("Event not found");
	    }
	}


	@Override
	public List<MatchEvent> getEventsByMatchId(int matchId) {
		return matchEventRepository.findByMatch_MatchId(matchId);
	}
	
	@Override
	public List<MatchEvent> getEventsByMatchIdFirstTeam(int matchId) {
		return matchEventRepository.findByMatchId_FirstTeam(matchId);
	}
	
	@Override
	public List<MatchEvent> getEventsByMatchIdSecondTeam(int matchId) {
		return matchEventRepository.findByMatchId_SecondTeam(matchId);
	}

	@Override
	public List<MatchEvent> getEventsByMatchIdOrderByTime(int matchId) {
		return matchEventRepository.findByMatchOrderByTime(matchId);
	}


	@Override
    public Map<String, Object> getMatchesHeldInCurrentYearByMonth() {
        Map<String, Object> data = new HashMap<>();
        List<Object[]> matchData = repository.findMatchesHeldInCurrentYearByMonth();

        // Chuyển đổi dữ liệu thành các mảng cho biểu đồ
        String[] matchMonth = new String[12];
        Integer[] matchCounts = new Integer[12];

        for (int i = 0; i < 12; i++) {
            matchMonth[i] = "T" + String.valueOf(i + 1);
            matchCounts[i] = 0;
        }

        for (Object[] result : matchData) {
            int month = Integer.parseInt(result[0].toString());
            int count = Integer.parseInt(result[1].toString());
            matchCounts[month - 1] = count;
        }

        // Thêm dữ liệu vào model để hiển thị trên view
        data.put("unit", "month");
        data.put("time", matchMonth);
        data.put("matchCounts", matchCounts);

        return data;
    }

	@Override
	public int countAllMatches() {
		return (int) repository.count();
	}

	
}
