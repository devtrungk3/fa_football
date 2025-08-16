package fa.football.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fa.football.repository.AccountRepository;
import fa.football.entity.Account;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	AccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Account account = repository.findById(email).orElse(null);
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + account.getRole());
		
		return new org.springframework.security.core.userdetails.User(email, account.getPassword(), Collections.singleton(authority));
	}

}
