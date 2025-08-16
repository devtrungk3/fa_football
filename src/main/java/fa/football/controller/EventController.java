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

import fa.football.entity.Event;
import fa.football.services.AccountService;
import fa.football.services.EventService;

@Controller
@RequestMapping("/admin/event")
public class EventController {
	@Autowired
	EventService eventService;
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
		model.addAttribute("title", "Danh sách sự kiện trong trận ");
		model.addAttribute("nameOfTable", "event");
		Page<Event> events;
		if (search != null && !search.isEmpty()) {
			events = eventService.getEventsByName(search, PageRequest.of(page-1, size));
		} else {
			events = eventService.getEventsWithPaging(PageRequest.of(page-1, size));
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("currentSize", size);
		List<String> columnNames = Arrays.asList("ID", "tên sự kiện", "sửa", "xóa");
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> info = new ArrayList<String>();
		events.forEach(e -> {
			info.add(e.getEventId()+"");
			info.add(e.getEventName());
			data.add(new ArrayList<>(info));
			info.clear();
		});
		model.addAttribute("id", "eventId");
		model.addAttribute("pages", events);
		model.addAttribute("data", data);
		model.addAttribute("columnNames", columnNames);
		return "admin/templates/table";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("event", new Event());
		return "admin/event-form";
	}
	
	@GetMapping("/update/{eventId}")
	public String edit(Model model, @PathVariable("eventId") int eventId) {
		model.addAttribute("event", eventService.findById(eventId));
		return "admin/event-form";
	}
	
	@PostMapping("/add")
	public String create(@ModelAttribute("event") Event event, RedirectAttributes redirectAttributes) {
		eventService.create(event);
		redirectAttributes.addFlashAttribute("message", "Tạo mới sự kiện thành công");
		return "redirect:/admin/event/table";
	}
	
	@PostMapping("/update/{eventId}")
	public String update(@ModelAttribute("event") Event event, RedirectAttributes redirectAttributes) {
		eventService.update(event);
		redirectAttributes.addFlashAttribute("message", "Cập nhật sự kiện thành công");
		return "redirect:/admin/event/table";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("eventId") int eventId, RedirectAttributes redirectAttributes) {
		eventService.delete(eventId);
		redirectAttributes.addFlashAttribute("message", "Xóa sự kiện thành công");
		return "redirect:/admin/event/table";
	}
}
