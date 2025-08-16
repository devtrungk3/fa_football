package fa.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fa.football.entity.Account;
import fa.football.services.AccountService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	AccountService accountService;
	
	@GetMapping("")
	public String profile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		model.addAttribute("account", accountService.findById(user.getUsername()));
		return "profile";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
		Account oldAccount = accountService.findById(account.getEmail());
		account.setPassword(oldAccount.getPassword());
		account.setCreatedAt(oldAccount.getCreatedAt());
		accountService.update(account);
		redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
		return "redirect:/profile";
	}
}
