package fa.football.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.football.entity.Account;
import fa.football.services.AccountService;
import fa.football.services.FootballMatchService;
import fa.football.services.LeagueService;
import fa.football.services.TeamService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	LeagueService leagueService;
	@Autowired
	TeamService teamService;
	@Autowired
	FootballMatchService footballMatchService;
	
	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		model.addAttribute("account", accountService.findById(user.getUsername()));
		model.addAttribute("accountNum", accountService.countAllUsers());
		model.addAttribute("leagueNum", leagueService.countAllLeagues());
		model.addAttribute("matchNum", footballMatchService.countAllMatches());
		model.addAttribute("teamNum", teamService.countAllTeams());
		model.addAttribute("leagueBefore", leagueService.countLeagueBefore());
		model.addAttribute("leaguePresent", leagueService.countLeaguePresent());
		model.addAttribute("leagueAfter", leagueService.countLeagueAfter());
		model.addAttribute("accountsByMonth", accountService.countAccountsByMonth(LocalDate.now().getYear()));
		return "admin/index";
	}
	
	@GetMapping("/account/table")
	public String listAcc(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "search", required = false) String search) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		model.addAttribute("account", accountService.findById(user.getUsername()));
		model.addAttribute("title", "Danh sách tài khoản người dùng");
		model.addAttribute("nameOfTable", "account");
		Page<Account> accounts;
		if (search != null && !search.isEmpty()) {
			accounts = accountService.findAccountByName(search, PageRequest.of(page-1, size));
		} else {
			accounts = accountService.getAccountsWithPaging(PageRequest.of(page-1, size));
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("currentSize", size);
		
		List<String> columnNames = Arrays.asList("email", "tên người dùng", "số điện thoại", "vai trò", "địa chỉ", "xóa");
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> info = new ArrayList<String>();
		accounts.forEach(a -> {
			info.add(a.getEmail());
			info.add(a.getUserName());
			info.add(a.getPhone());
			info.add(a.getRole());
			info.add(a.getAddress());
			data.add(new ArrayList<>(info));
			info.clear();
		});
		model.addAttribute("id", "email");
		model.addAttribute("pages", accounts);
		model.addAttribute("data", data);
		model.addAttribute("columnNames", columnNames);
		return "admin/templates/table";
	}
	
	@PostMapping("account/delete")
	public String deleteAcc(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
		if (!email.equals("admin@gmail.com")) {
			accountService.delete(email);
			redirectAttributes.addFlashAttribute("message", "Xóa tài khoản thành công");
		} else {
			redirectAttributes.addFlashAttribute("message", "Không thể xóa tài khoản admin");
		}
		return "redirect:/admin/account/table";
	}
	
    @GetMapping("account/api/account-role-percentage")
    @ResponseBody
    public Map<String, Object> getAccountRolePercentage() {
        Map<String, Object> response = new HashMap<>();
        List<Object[]> rolePercentageData = accountService.getRolePercentage();

        String[] roles = new String[rolePercentageData.size()];
        Double[] percentages = new Double[rolePercentageData.size()];
        for (int i = 0; i < rolePercentageData.size(); i++) {
            roles[i] = (String) rolePercentageData.get(i)[0];
            percentages[i] = (Double) rolePercentageData.get(i)[1];
        }

        response.put("roles", roles);
        response.put("percentages", percentages);
        return response;
    }
    
    @GetMapping("match/api/count/month-of-year")
    @ResponseBody
    public Map<String, Object> matchChart() {
        Map<String, Object> response = footballMatchService.getMatchesHeldInCurrentYearByMonth();
        return response;
    }


}
