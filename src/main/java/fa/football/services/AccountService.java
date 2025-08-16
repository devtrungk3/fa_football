package fa.football.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.football.entity.Account;

public interface AccountService {
	void create(Account account);
	void update(Account account);
	void delete(String email);
	Account findById(String email);
	int countAllUsers();
	List<Account> getAccounts();
	Page<Account> getAccountsWithPaging(Pageable pageable);
	Page<Account> findAccountByName(String name, Pageable pageable);
	Map<Integer, Integer> countAccountsByMonth(int year);
	List<Object[]> getRolePercentage();
}

