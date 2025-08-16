package fa.football.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fa.football.dto.TeamStandingDTO;
import fa.football.entity.Account;
import fa.football.entity.FootballMatch;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;
import fa.football.entity.LeagueStage;
import fa.football.entity.Standing;
import fa.football.entity.Team;
import fa.football.entity.Teamsize;
import fa.football.services.AccountService;
import fa.football.services.FootballMatchService;
import fa.football.services.LeagueInvitationService;
import fa.football.services.LeagueService;
import fa.football.services.LeagueStageService;
import fa.football.services.PlayerService;
import fa.football.services.StandingService;
import fa.football.services.TeamService;
import fa.football.services.TeamsizeService;

@Controller
@RequestMapping("/league")
public class LeagueController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private TeamsizeService teamsizeService;

	@Autowired
	private LeagueStageService leagueStageService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private FootballMatchService footballMatchService;

	@Autowired
	private LeagueInvitationService leagueInvitationService;

	@Autowired
	private StandingService standingService;

	@Autowired
	private PlayerService playerService;

	@GetMapping("/create")
	public String showFromCreateLeague(Model model) {
		List<Teamsize> teamsizes = teamsizeService.getAllTeamsizes();
		List<LeagueStage> leagueStage = leagueStageService.getLeagueStages();
		model.addAttribute("league", new League());
		model.addAttribute("stages", leagueStage);
		model.addAttribute("teamsizes", teamsizes);
		return "league/create";
	}

	@PostMapping("/save")
	public String saveLeague(@ModelAttribute("league") League league) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		Account account = accountService.findById(user.getUsername());
		league.setManager(account);
		leagueService.saveLeague(league);

		return "redirect:/league/view-detail?leagueId=" + league.getLeagueId();
	}

	@GetMapping("/list")
	public String showListOfLeague(@RequestParam(value = "format", required = false) String format,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {

		String role = "USER";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();
			role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("USER");
		} catch (Exception e) {
			
		}

		List<League> leagues;

		if (keyword != null && !keyword.isEmpty()) {
			leagues = leagueService.searchLeagues(keyword);
		} else {
			leagues = leagueService.getAllLeagues();
		}

		if (format != null && !format.isEmpty()) {
			leagues = leagues.stream().filter(league -> format.equals(league.getFormat().toLowerCase()))
					.collect(Collectors.toList());
			model.addAttribute("formatName", format);
		}

		if (status != null && !status.isEmpty()) {
			int statusInt = Integer.parseInt(status);
			leagues = leagues.stream()
					.filter(league -> league.getStage() != null && statusInt == league.getStage().getStageId())
					.collect(Collectors.toList());
			model.addAttribute("status", status);
		}

		List<LeagueStage> leagueStage = leagueStageService.getLeagueStages();

		model.addAttribute("leagueStage", leagueStage);
		model.addAttribute("leagues", leagues);
		model.addAttribute("role", role);

		return "league/list";
	}

	@GetMapping("/view-detail")
	public String viewDetailLeague(@RequestParam("leagueId") int leagueId, Model model) {
		String role = "USER";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		League league = leagueService.getLeagueById(leagueId);
		
		if (authentication.getPrincipal() instanceof User) {
			User user = (User)authentication.getPrincipal();
			role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("USER");
			model.addAttribute("owner", league.getManager().getEmail().equals(user.getUsername()));
		}
		
		model.addAttribute("role", role);
		

		
		if (!league.getFormat().equals("knockout")) {
			List<TeamStandingDTO> listOfTeamStanding = standingService.getTeamStandingsByLeagueId(leagueId);
			model.addAttribute("listOfTeamStanding", listOfTeamStanding);
			List<Team> availableTeams = teamService.findTeamsWithoutStanding(leagueId);
			model.addAttribute("availableTeams", availableTeams);
			model.addAttribute("canCreateMatches", availableTeams.isEmpty());
		} else {
			model.addAttribute("canCreateMatches", true);
		}
		List<LeagueStage> stageList = leagueStageService.getLeagueStages();
		List<Team> teams = teamService.getTeamsByLeagueId(leagueId);
		List<FootballMatch> footballMatchs = footballMatchService.getMatchesByLeagueId(leagueId);
		// chỗ này là đang lấy danh sách các vòng đấu từ danh sách trận đấu
		Map<LeagueStage, List<FootballMatch>> matchesByStage = footballMatchs.stream()
				.collect(Collectors.groupingBy(FootballMatch::getStage));
		// sắp xếp các trận đấu theo thời gian
		List<FootballMatch> sortedMatches = footballMatchs.stream()
				.sorted(Comparator.comparing(FootballMatch::getMatchDatetime)).collect(Collectors.toList());
		List<LeagueStage> sortedStages = matchesByStage.keySet().stream()
				.sorted(Comparator.comparing(LeagueStage::getStageId)).collect(Collectors.toList());

		List<LeagueInvitation> pendingRequests = leagueInvitationService.getPendingRequestsForLeague(league);
		model.addAttribute("pendingRequests", pendingRequests);

		
		List<String> existingGroups = standingService.findDistinctStandingsByLeague(leagueId);

		model.addAttribute("now", LocalDateTime.now());

		// số lượng, thống kê
		int teamCount = teams.size();
		int footballMatchCount = footballMatchs.size();
		String countRedCards = leagueService.countRedCards(leagueId);
		String countYellowCards = leagueService.countYellowCards(leagueId);
		String countGoals = leagueService.countGoals(leagueId);
		String playersCount = leagueService.countPlayers(leagueId);
		
		List<Object[]> listPlayerGoals = playerService.getPlayerGoals(leagueId);

		model.addAttribute("matchesByGroup", footballMatchService.getMatchesByGroup(leagueId));
		model.addAttribute("league", league);
		model.addAttribute("teams", teams);
		
		model.addAttribute("existingGroups", existingGroups);
		
		model.addAttribute("footballMatchs", footballMatchs);
		model.addAttribute("matchesByStage", matchesByStage);
		model.addAttribute("sortedMatches", sortedMatches);
		model.addAttribute("sortedStages", sortedStages);
		model.addAttribute("stageList", stageList);
		model.addAttribute("teamCount", teamCount);
		model.addAttribute("countRedCards", countRedCards);
		model.addAttribute("countYellowCards", countYellowCards);
		model.addAttribute("countGoals", countGoals);
		model.addAttribute("footballMatchCount", footballMatchCount);
		
		model.addAttribute("listPlayerGoals", listPlayerGoals);
		if (playersCount != null) {
			model.addAttribute("playersCount", playersCount);
		} else {
			model.addAttribute("playersCount", 0);
		}

		return "league/view-detail";
	}

	@GetMapping("/edit")
	public String showEditLeague(@PathVariable("leagueId") int leagueId, Model model) {
		League league = leagueService.getLeagueById(leagueId);
		if (league != null) {
			model.addAttribute("league", league);
			return "league/edit";
		} else {
			return "redirect:/league/list";
		}
	}

	@GetMapping("/delete")
	public String deleteLeague(@PathVariable("leagueId") int leagueId) {
		return "redirect:league/list";
	}

	// league manager chấp nhận yêu cầu
	@PostMapping("/accept-request")
	public String acceptRequest(@RequestParam("leagueId") int leagueId, @RequestParam("teamManagerId") String email) {
		LeagueInvitationPK id = new LeagueInvitationPK(leagueId, email);
		leagueInvitationService.acceptInvitation(id);
		return "redirect:/league/view-detail?leagueId=" + leagueId;
	}

	// League manager từ chối yêu cầu
	@PostMapping("/reject-request")
	public String rejectRequest(@RequestParam("leagueId") int leagueId, @RequestParam("teamManagerId") String email) {
		LeagueInvitationPK id = new LeagueInvitationPK(leagueId, email);
		leagueInvitationService.rejectInvitation(id);
		return "redirect:/league/view-detail?leagueId=" + leagueId;
	}

	@PostMapping("/addGroup")
	public String saveGroup(@RequestParam("groupName") String groupName, @RequestParam("teamIds") List<Integer> teamIds,
			@RequestParam("leagueId") int leagueId) {
		standingService.addTeamsToGroup(groupName, teamIds, leagueId);
		return "redirect:/league/view-detail?leagueId=" + leagueId;
	}

	@PostMapping("/activate-league")
	public String activeLeague(@RequestParam("leagueId") int leagueId, @RequestParam("stageId") int stageId,
			Model model) {
		League league = leagueService.getLeagueById(leagueId);
		LeagueStage leagueStage = leagueStageService.findById(stageId);
		league.setStage(leagueStage);
		leagueService.saveLeague(league);

		return "redirect:/league/view-detail?leagueId=" + leagueId;
	}

	@PostMapping("/add-match")
	public String addMatch(@RequestParam("stageId") int stageId, @RequestParam("firstTeamId") int firstTeamId,
			@RequestParam("secondTeamId") int secondTeamId,
			@RequestParam("matchDatetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime matchDatetime,
			@RequestParam("location") String location, @RequestParam("leagueId") int leagueId) {

		FootballMatch match = new FootballMatch();
		match.setStage(leagueStageService.findById(stageId));
		match.setFirstTeam(teamService.findTeamById(firstTeamId));
		match.setSecondTeam(teamService.findTeamById(secondTeamId));
		match.setMatchDatetime(matchDatetime);
		match.setLocation(location);
		match.setLeague(leagueService.getLeagueById(leagueId));

		League league = leagueService.getLeagueById(leagueId);

		LeagueStage stage = leagueStageService.findById(stageId);
		league.setStage(stage);
		leagueService.saveLeague(league);

		footballMatchService.saveMatch(match);

		return "redirect:/league/view-detail?leagueId=" + leagueId;
	}

	@GetMapping("/dashboard")
	public String viewDashBoard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		model.addAttribute("account", accountService.findById(user.getUsername()));
		List<League> listOfLeague = leagueService.getLeaguesByManager(user.getUsername());
		model.addAttribute("listOfLeagues", listOfLeague);
		model.addAttribute("numberOfLeagues", listOfLeague.size());
		return "league/dashboard";
	}

	@GetMapping("/dashboard/edit-tournament")
	public String editLeague(@RequestParam("leagueId") int leagueId, Model model) {
		League league = leagueService.getLeagueById(leagueId);
		List<LeagueStage> listOfStages = leagueStageService.getLeagueStages();
		List<Teamsize> listOfTeamsize = teamsizeService.getAllTeamsizes();
		
		
		if (league != null) {
			model.addAttribute("league", league);
			model.addAttribute("listOfStages", listOfStages);
			model.addAttribute("listOfTeamsize", listOfTeamsize);
			return "league/edit-tournament";
		} else {
			return "redirect:/league/dashboard";
		}
	}

	@PostMapping("/dashboard/update-tournament")
	public String updateLeague(@ModelAttribute("league") League league) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		Account account = accountService.findById(user.getUsername());
		league.setManager(account);
		leagueService.saveLeague(league);
		return "redirect:/league/dashboard";
	}

	@GetMapping("/dashboard/delete-tournament")
	public String deleteLeagueDash(@RequestParam("leagueId") int leagueId) {
		List<Team> listTeams = teamService.getAllTeamWithLeagueId(leagueId);
		for (Team team : listTeams) {
			int teamId = team.getTeamId();
			teamService.updateLeagueIdIsNull(teamId);
		}
		leagueService.deleteLeague(leagueId);

		return "redirect:/league/dashboard";
	}
}
