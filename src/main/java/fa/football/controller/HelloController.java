package fa.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fa.football.services.AccountService;
@Controller
public class HelloController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping({"/home", "/"})
	public String hello(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof User) {
			User user = (User)authentication.getPrincipal();
			model.addAttribute("account", accountService.findById(user.getUsername()));
		}
		return "helloworld";
	}
}
