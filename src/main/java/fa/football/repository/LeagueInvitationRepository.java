package fa.football.repository;

import fa.football.entity.Account;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeagueInvitationRepository extends JpaRepository<LeagueInvitation, LeagueInvitationPK> {
	// Lấy tất cả lời mời đang chờ (PENDING) cho một giải đấu cụ thể
    List<LeagueInvitation> findByLeagueAndStatus(League league, String status);

    // Lấy tất cả lời mời đang chờ (PENDING) cho một team manager cụ thể
    List<LeagueInvitation> findByTeamManagerAndStatus(Account teamManager, String status);
    
    @Modifying
    @Query(value = "select * from  league_invitation where team_manager=?", nativeQuery = true)
    List<LeagueInvitation> findByManager(String manager);

}
