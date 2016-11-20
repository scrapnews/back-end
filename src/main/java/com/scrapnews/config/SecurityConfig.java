package com.scrapnews.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(userDetailsService);
	}

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
					.defaultSuccessUrl("/index.html")
					.loginPage("/login.html")
					.loginProcessingUrl("/authenticate")
					.usernameParameter("username")
					.passwordParameter("password")
					//.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login.html")
					.and().csrf().disable();//csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
	  repository.setHeaderName("X-XSRF-TOKEN");
	  return repository;
	}
	
}