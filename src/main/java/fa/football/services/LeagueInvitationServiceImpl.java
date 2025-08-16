package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.Account;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;
import fa.football.repository.LeagueInvitationRepository;

@Service
public class LeagueInvitationServiceImpl implements LeagueInvitationService {

	@Autowired
    private LeagueInvitationRepository leagueInvitationRepository;

    public void inviteTeamToLeague(League league, Account teamManager) {
        LeagueInvitation invitation = new LeagueInvitation();
        invitation.setLeague(league);
        invitation.setTeamManager(teamManager);
        invitation.setStatus("PENDING");
        leagueInvitationRepository.save(invitation);
    }

    public void requestToJoinLeague(League league, Account teamManager) {
        LeagueInvitation invitation = new LeagueInvitation();
        invitation.setLeague(league);
        invitation.setTeamManager(teamManager);
        invitation.setStatus("PENDING");
        leagueInvitationRepository.save(invitation);
    }

    public List<LeagueInvitation> getPendingRequestsForLeague(League league) {
        return leagueInvitationRepository.findByLeagueAndStatus(league, "PENDING");
    }

    public List<LeagueInvitation> getPendingInvitationsForTeamManager(Account teamManager) {
        return leagueInvitationRepository.findByTeamManagerAndStatus(teamManager, "PENDING");
    }

    public void acceptInvitation(LeagueInvitationPK id) {
        LeagueInvitation invitation = leagueInvitationRepository.findById(id).orElseThrow();
        invitation.setStatus("ACCEPTED");
        leagueInvitationRepository.save(invitation);
    }

    public void rejectInvitation(LeagueInvitationPK id) {
        LeagueInvitation invitation = leagueInvitationRepository.findById(id).orElseThrow();
        invitation.setStatus("REJECTED");
        leagueInvitationRepository.save(invitation);
    }

}
