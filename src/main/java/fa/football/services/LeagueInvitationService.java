package fa.football.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.football.entity.Account;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;

public interface LeagueInvitationService {
	void inviteTeamToLeague(League league, Account teamManager);
	void requestToJoinLeague(League league, Account teamManager);
	List<LeagueInvitation> getPendingRequestsForLeague(League league);
	List<LeagueInvitation> getPendingInvitationsForTeamManager(Account teamManager);
	void acceptInvitation(LeagueInvitationPK id);
	void rejectInvitation(LeagueInvitationPK id);
}
