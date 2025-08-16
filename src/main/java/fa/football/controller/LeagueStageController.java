package fa.football.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.football.entity.LeagueStage;
import fa.football.services.AccountService;
import fa.football.services.LeagueStageService;

@Controller
@RequestMapping("/admin/leaguestage")
public class LeagueStageController {
	@Autowired
	LeagueStageService leagueStageService;
	@Autowired
	AccountService accountService;
	
	@GetMapping("/table")
	public String list(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "search", required = false) String search) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		model.addAttribute("account", accountService.findById(user.getUsername()));
		model.addAttribute("title", "Danh sách vòng đấu");
		model.addAttribute("nameOfTable", "leaguestage");
		Page<LeagueStage> leagueStages;
		if (search != null && !search.isEmpty()) {
			leagueStages = leagueStageService.getLeagueStagesByName(search, PageRequest.of(page-1, size));
		}
		else {
			leagueStages = leagueStageService.getLeagueStagesWithPaging(PageRequest.of(page-1, size));
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("currentSize", size);
		List<String> columnNames = Arrays.asList("ID", "tên vòng đấu", "sửa", "xóa");
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> info = new ArrayList<String>();
		leagueStages.forEach(e -> {
			info.add(e.getStageId()+"");
			info.add(e.getStageName());
			data.add(new ArrayList<>(info));
			info.clear();
		});
		model.addAttribute("id", "stageId");
		model.addAttribute("pages", leagueStages);
		model.addAttribute("data", data);
		model.addAttribute("columnNames", columnNames);
		return "admin/templates/table";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("leagueStage", new LeagueStage());
		return "admin/leaguestage-form";
	}
	
	@GetMapping("update/{stageId}")
	public String edit(Model model, @PathVariable("stageId") int stageId) {
		model.addAttribute("leagueStage", leagueStageService.findById(stageId));
		return "admin/leaguestage-form";
	}
	
	@PostMapping("/add")
	public String create(@ModelAttribute("leagueStage") LeagueStage leagueStage, RedirectAttributes redirectAttributes) {
		leagueStageService.create(leagueStage);
		redirectAttributes.addFlashAttribute("message", "Thêm vòng đấu thành công");
		return "redirect:/admin/leaguestage/table";
	}
	
	@PostMapping("/update/{stageId}")
	public String update(@ModelAttribute("leagueStage") LeagueStage leagueStage, RedirectAttributes redirectAttributes) {
		leagueStageService.update(leagueStage);
		redirectAttributes.addFlashAttribute("message", "Cập nhật vòng đấu thành công");
		return "redirect:/admin/leaguestage/table";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("stageId") int stageId, RedirectAttributes redirectAttributes) {
		leagueStageService.delete(stageId);
		redirectAttributes.addFlashAttribute("message", "Xóa vòng đấu thành công");
		return "redirect:/admin/leaguestage/table";
	}
}
