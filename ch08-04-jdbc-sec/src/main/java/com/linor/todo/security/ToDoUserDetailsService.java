package com.linor.todo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.linor.todo.domain.Person;
import com.linor.todo.repository.PersonRepository;

public class ToDoUserDetailsService implements UserDetailsService{
	private PersonRepository repository;
	
	public ToDoUserDetailsService(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			final Person person = this.repository.findByEmailIgnoreCase(username);
			if(person != null) {
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				String password = encoder.encode(person.getPassword());
				return User.withUsername(person.getEmail())
						.accountLocked(!person.isEnabled())
						.password(password)
						.roles(person.getRole())
						.build();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		throw new UsernameNotFoundException(username);
	}

}
