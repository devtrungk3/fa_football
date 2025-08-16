package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.football.entity.Event;
import fa.football.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventRepository repository;

	@Override
	public void create(Event event) {
		repository.save(event);
	}

	@Override
	public void update(Event event) {
		repository.save(event);
	}

	@Override
	public void delete(int eventId) {
		repository.deleteById(eventId);
	}

	@Override
	public Event findById(int eventId) {
		return repository.findById(eventId).orElse(null);
	}

	@Override
	public List<Event> getEvents() {
		return repository.findAll();
	}

	@Override
	public Page<Event> getEventsWithPaging(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Event> getEventsByName(String name, Pageable pageable) {
		return repository.findAllEventsByEventNameContaining(name, pageable);
	}
	
}
