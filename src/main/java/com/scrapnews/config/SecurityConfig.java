package com.scrapnews.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// for later, if we want to encrypt password
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(userDetailsService);
	}

	// TODO adapt with front end
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("**").permitAll();
		http
			.httpBasic()
			.and()
			.authorizeRequests()
				.antMatchers("/resources/**", "/login.html", "/**.js", "/user", "/logout","/zizi","/hello" )
				.permitAll()
				.antMatchers("/users/**").hasRole("ADMIN")
				.antMatchers("users.html").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.defaultSuccessUrl("/success")
					.loginPage("/login.html")
					.loginProcessingUrl("/authenticate")
					.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login.html")
					.and().csrf().disable();
	}

	
}