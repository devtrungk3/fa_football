package fa.football.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.football.entity.Teamsize;

public interface TeamsizeService {
	void create(Teamsize teamsize);
	void update(Teamsize teamsize);
	void delete(int teamsizeId);
	Teamsize findById(int teamsizeId);
	List<Teamsize> getAllTeamsizes();
	Page<Teamsize> getTeamsizesWithPaging(Pageable pageable);
	Page<Teamsize> getTeamsizesBySize(int size, Pageable pageable);
}
