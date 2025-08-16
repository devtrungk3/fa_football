package fa.football.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.football.entity.FootballMatch;
import fa.football.entity.MatchEvent;
import fa.football.entity.Player;
import fa.football.services.FootballMatchService;

@Controller
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private FootballMatchService footballMatchService;

	@GetMapping("/detail/{id}")
	public String detailMatch(@PathVariable("id") int matchId, Model model) {
		
		String role = "USER";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();
			role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("USER");
		} catch (Exception e) {
			
		}
		model.addAttribute("role", role);
		
		FootballMatch footballMatch = footballMatchService.findMatchById(matchId);
		List<MatchEvent> eventMatch1 = footballMatchService.getEventsByMatchIdFirstTeam(matchId);
		List<MatchEvent> eventMatch2 = footballMatchService.getEventsByMatchIdSecondTeam(matchId);
		List<MatchEvent> eventMatchTime = footballMatchService.getEventsByMatchIdOrderByTime(matchId);
		List<Player> players1 = footballMatchService.findByTeam(footballMatch.getFirstTeam().getTeamId());
		List<Player> players2 = footballMatchService.findByTeam(footballMatch.getSecondTeam().getTeamId());

		model.addAttribute("match", footballMatch);
		model.addAttribute("eventMatch1", eventMatch1);
		model.addAttribute("eventMatch2", eventMatch2);
		model.addAttribute("eventMatchTime", eventMatchTime);
		model.addAttribute("players1", players1);
		model.addAttribute("players2", players2);
		model.addAttribute("events", footballMatchService.findAllEvent());

		return "match/match-detail";
	}
	
	@PostMapping("addEvent/{id}")
	public String addEvent(@PathVariable(name = "id") int matchId, @RequestParam(name = "playerId") int playerId,
			@RequestParam(name = "eventId") int eventId, @RequestParam(name = "minute") int minute,
			@RequestParam(name = "description") String description) {
		footballMatchService.addEvent(matchId, playerId, eventId, minute, description);
		return "redirect:/match/detail/" + matchId;
	}

	@GetMapping("/edit/{id}")
	public String viewEditMatch(@PathVariable("id") int matchId, Model model) {
		FootballMatch footballMatch = footballMatchService.findMatchById(matchId);
		List<MatchEvent> eventMatch1 = footballMatchService.getEventsByMatchIdFirstTeam(matchId);
		List<MatchEvent> eventMatch2 = footballMatchService.getEventsByMatchIdSecondTeam(matchId);
		List<MatchEvent> eventMatchTime = footballMatchService.getEventsByMatchIdOrderByTime(matchId);
		List<Player> players1 = footballMatchService.findByTeam(footballMatch.getFirstTeam().getTeamId());
		List<Player> players2 = footballMatchService.findByTeam(footballMatch.getSecondTeam().getTeamId());

		model.addAttribute("match", footballMatch);
		model.addAttribute("eventMatch1", eventMatch1);
		model.addAttribute("eventMatch2", eventMatch2);
		model.addAttribute("eventMatchTime", eventMatchTime);
		model.addAttribute("players1", players1);
		model.addAttribute("players2", players2);
		model.addAttribute("events", footballMatchService.findAllEvent());

		return "match/match-edit";
	}

	@PostMapping("/deleteEvent")
	public String deleteEvent(@RequestParam("matchId") int matchId, @RequestParam("playerId") int playerId,
			@RequestParam("eventId") int eventId, @RequestParam("minute") int minute,
			RedirectAttributes redirectAttributes) {
		try {
			footballMatchService.deleteEvent(matchId, playerId, eventId, minute);
			redirectAttributes.addFlashAttribute("successMessage", "Event deleted successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the event.");
		}
		return "redirect:/match/edit/" + matchId;
	}

	@PostMapping("/editMatch")
	public String editMatch(@RequestParam("matchId") int matchId, @RequestParam("firstTeamScore") int firstTeamScore,
			@RequestParam("secondTeamScore") int secondTeamScore, @RequestParam("location") String location,
			@RequestParam("matchDateTime") String matchDatetime, RedirectAttributes redirectAttributes) {
		try {
			FootballMatch match = footballMatchService.findMatchById(matchId);
			match.setFirstTeamScore(firstTeamScore);
			match.setSecondTeamScore(secondTeamScore);
			match.setLocation(location);
			match.setMatchDatetime(LocalDateTime.parse(matchDatetime));
			footballMatchService.saveMatch(match);
			redirectAttributes.addFlashAttribute("successMessage", "Match updated successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update the match.");
		}
		return "redirect:/match/edit/" + matchId;
	}

	@PostMapping("/deleteMatch")
	public String deleteMatch(@RequestParam("matchId") int matchId, RedirectAttributes redirectAttributes) {
		FootballMatch m = footballMatchService.findMatchById(matchId);
		int leagueId = m.getLeague().getLeagueId();
		try {
			footballMatchService.deleteMatch(matchId);
			redirectAttributes.addFlashAttribute("successMessage", "Match deleted successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the match.");
		}
		return "redirect:/league/view-detail?leagueId="+ leagueId;
	}

}
