package fa.football.services;

import fa.football.entity.Player;
import fa.football.entity.Team;
import fa.football.repository.MatchEventRepository;
import fa.football.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository pRepository;

    @Autowired
    private MatchEventRepository mRepository;

    private DateFormat df= new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<Player> getPlayersByTeam(int teamId) {
        List<Player> list = new ArrayList<>();
        list=pRepository.getPlayersByTeam(teamId);
        return list;
    }

    @Override
    public void addPlayer(String name, String birth, String phone, int num, Team team) {
        Player player = new Player();
        try {
            player.setTeam(team);
            player.setPlayerName(name);
            java.util.Date utilDate = df.parse(birth);
            player.setBirth(new Date(utilDate.getTime()));
            player.setPhone(phone);
            player.setShirtNumber(num);
        } catch (Exception e){
            e.printStackTrace();
        }
        pRepository.save(player);
    }

    @Override
    public void deletePlayer(int playerId) {
        pRepository.deleteById(playerId);
    }

    @Transactional
    @Override
    public void updatePlayer(String name, String birth, String phone, int num, int id) {
        java.sql.Date sqlDate = null;
        try{
            java.util.Date utilDate = df.parse(birth);
            sqlDate = new Date(utilDate.getTime());
        } catch (Exception e){
            e.printStackTrace();
        }
        pRepository.updatePlayer(name, num, phone, sqlDate,id);
    }

    @Override
    public Player findById(int id) {
        return pRepository.findById(id).orElse(null);
    }

    @Override
    public int[] getEvent(int playerId) {
        int goal = 1, redCard = 2, yellowCard = 3;
        int[] rs = new int[3];
        List<Object[]> list = mRepository.getEventByPlayer(playerId);
        for (Object[] o: list) {
            if(Integer.parseInt(o[0].toString()) == goal){
                rs[0] = Integer.parseInt(o[1].toString());
            } else if (Integer.parseInt(o[0].toString()) == redCard){
                rs[1] = Integer.parseInt(o[1].toString());
            } else if (Integer.parseInt(o[0].toString()) == yellowCard){
                rs[2] = Integer.parseInt(o[1].toString());
            }
        }
        return rs;
    }
    
	@Override
	public List<Object[]> getPlayerGoals(int leagueId) {
		return mRepository.getPlayerGoals(leagueId);
	}

}
