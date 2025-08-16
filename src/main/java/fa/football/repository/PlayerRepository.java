package fa.football.repository;

import fa.football.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Modifying
    @Query(value = "select * from player where team_id = ?", nativeQuery = true)
    List<Player> getPlayersByTeam(int teamId);

    @Modifying
    @Query(value = "update player set player_name=?, shirt_number=?, phone=?, birth=? where player_id=?",nativeQuery = true)
    void updatePlayer(String name, int number, String phone, Date birth, int id);
}
