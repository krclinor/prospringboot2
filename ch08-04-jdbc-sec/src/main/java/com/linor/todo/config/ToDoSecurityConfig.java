package com.linor.todo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.linor.todo.repository.PersonRepository;
import com.linor.todo.security.ToDoUserDetailsService;

@Configuration
public class ToDoSecurityConfig extends WebSecurityConfigurerAdapter {
	private PersonRepository personRePository;
	
	public ToDoSecurityConfig(PersonRepository personRepository) {
		this.personRePository = personRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/**").hasRole("ADMIN")
			.and()
			.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new ToDoUserDetailsService(this.personRePository));
	}
	
	
}
