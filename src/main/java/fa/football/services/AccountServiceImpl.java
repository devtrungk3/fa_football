package fa.football.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import fa.football.entity.Account;
import fa.football.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository repository;

	@Override
	public void create(Account account) {
		repository.save(account);
	}

	@Override
	public void update(Account account) {
		repository.save(account);
	}

	@Override
	public void delete(String email) {
		repository.deleteById(email);
	}

	@Override
	public Account findById(String email) {
		return repository.findById(email).orElse(null);
	}

	@Override
	public List<Account> getAccounts() {
		return repository.findAll();
	}

	@Override
	public Page<Account> getAccountsWithPaging(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public int countAllUsers() {
		return repository.countAllUsers();
	}

	@Override
	public Page<Account> findAccountByName(String name, Pageable pageable) {
		return repository.findAllUsersByUserNameContaining(name, pageable);
	}

	@Override
	public Map<Integer, Integer> countAccountsByMonth(int year) {
		List<Object[]> results = repository.countAccountsByMonth(year);
		Map<Integer, Integer> stats = new HashMap<Integer, Integer>();
		for (int i=1; i<=12; i++) {
			stats.put(i, 0);
		}
		for (Object[] r : results) {
			stats.put(((Number) r[0]).intValue(), ((Number) r[1]).intValue());
		}
		return stats;
	}

	@Override
    public List<Object[]> getRolePercentage() {
        return repository.calculateRolePercentage();
    }


}
