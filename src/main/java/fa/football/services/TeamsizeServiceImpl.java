package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.football.entity.Teamsize;
import fa.football.repository.TeamsizeRepository;

@Service
public class TeamsizeServiceImpl implements TeamsizeService{
	
	@Autowired
	TeamsizeRepository repository;

	@Override
	public void create(Teamsize teamsize) {
		repository.save(teamsize);
	}

	@Override
	public void update(Teamsize teamsize) {
		repository.save(teamsize);
	}

	@Override
	public void delete(int teamsizeId) {
		repository.deleteById(teamsizeId);
	}

	@Override
	public Teamsize findById(int teamsizeId) {
		return repository.findById(teamsizeId).orElse(null);
	}

	@Override
	public List<Teamsize> getAllTeamsizes() {
		return repository.findAll();
	}

	@Override
	public Page<Teamsize> getTeamsizesWithPaging(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Teamsize> getTeamsizesBySize(int size, Pageable pageable) {
		return repository.findBySize(size, pageable);
	}
	
}
