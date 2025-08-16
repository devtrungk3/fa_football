package fa.football.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.football.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Query(value = "SELECT COUNT(email) FROM account WHERE role <> 'ADMIN'", nativeQuery = true)
	int countAllUsers();
	
	Page<Account> findAllUsersByUserNameContaining(String name, Pageable pageable);
	
	@Query("SELECT MONTH(a.createdAt) AS month, COUNT(a) AS count " +
		       "FROM Account a " +
		       "WHERE role <> 'ADMIN' AND YEAR(a.createdAt) = ?1 " +
		       "GROUP BY MONTH(a.createdAt) " +
		       "ORDER BY month ASC")
	List<Object[]> countAccountsByMonth(int year);
	
    @Query("SELECT a.role, COUNT(a) * 100.0 / (SELECT COUNT(ac) FROM Account ac WHERE role <> 'ADMIN') AS percentage " +
            "FROM Account a WHERE role <> 'ADMIN' GROUP BY a.role")
    List<Object[]> calculateRolePercentage();

}
