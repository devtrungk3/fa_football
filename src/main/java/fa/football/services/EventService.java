package fa.football.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.football.entity.Event;

public interface EventService {
	void create(Event event);
	void update(Event event);
	void delete(int eventId);
	Event findById(int eventId);
	List<Event> getEvents();
	Page<Event> getEventsWithPaging(Pageable pageable);	
	Page<Event> getEventsByName(String name, Pageable pageable);
}
