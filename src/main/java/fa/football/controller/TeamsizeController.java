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

import fa.football.entity.Teamsize;
import fa.football.services.AccountService;
import fa.football.services.TeamsizeService;

@Controller
@RequestMapping("/admin/teamsize")
public class TeamsizeController {
	@Autowired 
	TeamsizeService teamsizeService;
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
		model.addAttribute("title", "Danh sách teamsize");
		model.addAttribute("nameOfTable", "teamsize");
		
		Page<Teamsize> teamsizes;
		if (search != null && !search.isEmpty()) {
			teamsizes = teamsizeService.getTeamsizesBySize(Integer.parseInt(search), PageRequest.of(page-1, size));
		} else {
			teamsizes = teamsizeService.getTeamsizesWithPaging(PageRequest.of(page-1, size));
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("currentSize", size);
		List<String> columnNames = Arrays.asList("ID", "kích cỡ đội", "sửa", "xóa");
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> info = new ArrayList<String>();
		teamsizes.forEach(e -> {
			info.add(e.getTeamsizeId()+"");
			info.add(e.getSize()+"");
			data.add(new ArrayList<>(info));
			info.clear();
		});
		model.addAttribute("id", "teamsizeId");
		model.addAttribute("pages", teamsizes);
		model.addAttribute("data", data);
		model.addAttribute("columnNames", columnNames);
		return "admin/templates/table";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("teamsize", new Teamsize());
		return "admin/teamsize-form";
	}
	
	@GetMapping("/update/{teamsizeId}")
	public String edit(Model model, @PathVariable("teamsizeId") int teamsizeId) {
		model.addAttribute("teamsize", teamsizeService.findById(teamsizeId));
		return "admin/teamsize-form";
	}
	
	@PostMapping("/add")
	public String create(@ModelAttribute("teamsize") Teamsize teamsize, RedirectAttributes redirectAttributes) {
		teamsizeService.create(teamsize);
		redirectAttributes.addFlashAttribute("message", "Tạo kích thước đội hình thành công");
		return "redirect:/admin/teamsize/table";
	}
	
	@PostMapping("/update/{teamsizeId}")
	public String update(@ModelAttribute("teamsize") Teamsize teamsize, RedirectAttributes redirectAttributes) {
		teamsizeService.update(teamsize);
		redirectAttributes.addFlashAttribute("message", "Cập nhật kích thước đội hình thành công");
		return "redirect:/admin/teamsize/table";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("teamsizeId") int teamsizeId, RedirectAttributes redirectAttributes) {
		teamsizeService.delete(teamsizeId);
		redirectAttributes.addFlashAttribute("message", "Xóa kích thước đội hình thành công");
		return "redirect:/admin/teamsize/table";
	}
}
