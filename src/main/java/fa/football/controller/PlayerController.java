package fa.football.controller;

import fa.football.entity.Player;
import fa.football.entity.Team;
import fa.football.services.PlayerService;
import fa.football.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("player")
public class PlayerController {
    @Autowired
    private PlayerService pService;

    @Autowired
    private TeamService tService;

    @GetMapping("view")
    public String getPlayersById(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)  authentication.getPrincipal();
        Team team = tService.findByManager(user.getUsername());

        List<Player> list = pService.getPlayersByTeam(team.getTeamId());
        model.addAttribute("players", list);
        return "viewTeamPlayer";
    }

    @GetMapping("addPlayer")
    public String addPlayer() {
        return "addPlayer";
    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestParam(name = "name") String name,@RequestParam(name = "birth") String birth, @RequestParam(name = "phone")String phone,@RequestParam(name = "num") int num) {
        //lay thong tin nguoi dung
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)  authentication.getPrincipal();
        //lay tem tu manager email
        Team team = tService.findByManager(user.getUsername());

        pService.addPlayer(name, birth,phone,num,team);
        return "redirect:view";
    }

    @GetMapping("{id}")
    public String detailPlayer(@PathVariable(name = "id") int id, Model model) {
        Player p = pService.findById(id);
        model.addAttribute("player", p);
        int[] event = pService.getEvent(id);
        model.addAttribute("goal",event[0]);
        model.addAttribute("redCard",event[1]);
        model.addAttribute("yellowCard",event[2]);
        return "detailPlayer";
    }

    @GetMapping("delete/{id}")
    public String deletePlayer(@PathVariable(name = "id") int id, Model model) {
        pService.deletePlayer(id);
        return "redirect:/player/view";
    }

    @GetMapping("edit/{id}")
    public String editPlayer(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("player", pService.findById(id));
        model.addAttribute("id", id);
        return "editPlayer";
    }

    @PostMapping("edit/{id}")
    public String editPlayer(@PathVariable(name = "id")int id,@RequestParam(name = "name") String name,@RequestParam(name = "birth") String birth, @RequestParam(name = "phone")String phone,@RequestParam(name = "num") int num) {
        pService.updatePlayer(name, birth, phone, num, id);
        return "redirect:/player/view";
    }
}
