package fa.football.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.football.entity.Teamsize;

public interface TeamsizeRepository extends JpaRepository<Teamsize, Integer>{
	Page<Teamsize> findBySize(int size, Pageable pageable);
}
