package fa.football.services;

import fa.football.entity.Player;
import fa.football.entity.Team;

import java.util.List;

public interface PlayerService {
    public List<Player> getPlayersByTeam(int teamId);

    public void addPlayer(String name, String birth, String phone, int num, Team team);

    public void deletePlayer(int playerId);

    public void updatePlayer(String name, String birth, String phone, int num, int id);

    public Player findById(int id);

    public int[] getEvent(int id);
    
    public List<Object[]> getPlayerGoals(int leagueId);
}
