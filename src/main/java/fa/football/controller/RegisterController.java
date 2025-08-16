package fa.football.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import fa.football.entity.Account;
import fa.football.services.AccountService;

@Controller
public class RegisterController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("account", new Account());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("account") Account account, Model model) {
		account.setCreatedAt(LocalDateTime.now());
		accountService.create(account);
		return "redirect:/login";
	}
}
