package fa.football.controller;

import fa.football.entity.LeagueInvitation;
import fa.football.entity.Team;
import fa.football.services.PlayerService;
import fa.football.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping({"/","/home"})
    public String teamHome(Model model){
    	String role = "USER";
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		try {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			User user = (User) authentication.getPrincipal();
			role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("USER");
		} catch (Exception e) {
			
		}
		
        
        Team team = teamService.findByManager(user.getUsername());
        LeagueInvitation leagueInvitation = teamService.findInvitationByManager(user.getUsername());
        model.addAttribute("invitation", leagueInvitation);
        model.addAttribute("role", role);
        if (leagueInvitation != null){
            if (team == null || leagueInvitation.getStatus().equals("PENDING") || leagueInvitation.getStatus().equals("REJECTED")) {
                model.addAttribute("noteam",0);
                System.out.print("no team or none accepted");
            } else {
                model.addAttribute("matchs", teamService.findMatchByTeam(team.getTeamId()));
                model.addAttribute("team", team);
                model.addAttribute("players", playerService.getPlayersByTeam(team.getTeamId()));
                System.out.print("accepted");
            }
        }
        return "teamHome";
    }

    @GetMapping("edit")
    public String editInfo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Team team = teamService.findByManager(user.getUsername());
        model.addAttribute("team", team);
        return "teamInfo";
    }

    @PostMapping("edit/{id}")
    public String editInfo(@PathVariable(name = "id")int id, Model model, @RequestParam(name = "coach")String coach, @RequestParam(name = "team")String team){
        teamService.updateTeam(coach, team, id);

        //redirect toi trang chu,,, nho sua lai
        return "redirect:/team/home";
    }

    @GetMapping("summary")
    public String summary(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Team team = teamService.findByManager(user.getUsername());
        model.addAttribute("standing", teamService.findStandingByTeam(team.getTeamId()));
        return "summaryTeam";
    }

    @GetMapping("goalHistory")
    public String goalHistory(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Team team = teamService.findByManager(user.getUsername());
        model.addAttribute("goals", teamService.findGoalByTeam(team.getTeamId()));
        return "goalHistory";
    }

    @PostMapping("registerLeague/{id}")
    public String registerLeague(@PathVariable(name = "id")int leagueId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String status = "PENDING";
        
        teamService.registerLeague(user.getUsername(), leagueId, status);

        return "redirect:/team/home";
    }

    @GetMapping("create")
    public String createTeam(){
        return "createTeam";
    }

    @PostMapping("create")
    public String createTeam(@RequestParam(name="teamName")String teamName, @RequestParam(name = "coachName")String coachName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        teamService.createTeam(teamName, coachName, user.getUsername());
        return "redirect:/team/home";
    }

    @GetMapping("all")
    public String allTeams(Model model){
        model.addAttribute("teamList", teamService.findAll());
        return "club";
    }
    
    @GetMapping("detail/{id}")
    public String detailTeam(@PathVariable(name = "id")int teamId, Model model){
        model.addAttribute("players", playerService.getPlayersByTeam(teamId));
        model.addAttribute("matchs", teamService.findMatchByTeam(teamId));
        model.addAttribute("team", teamService.findById(teamId));

        return "detailTeam";
    }
    
}
