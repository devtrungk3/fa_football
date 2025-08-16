package fa.football.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.football.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	Page<Event> findAllEventsByEventNameContaining(String name, Pageable pageable); 
}
