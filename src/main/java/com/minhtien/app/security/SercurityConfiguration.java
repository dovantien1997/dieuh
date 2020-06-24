//package com.minhtien.app.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SercurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	private UserPrincipalDetailsService detailsService;
//	
//
//
//
//	public SercurityConfiguration(UserPrincipalDetailsService detailsService) {
//		this.detailsService = detailsService;
//	}
//	
//	
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.authenticationProvider(authenticationProvider());
//
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http
//		.authorizeRequests()
//		.antMatchers("/admin/**").hasRole("ADMIN")
//		.antMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
//		.antMatchers("/index.html").permitAll()
//		.and()
//		.formLogin().loginPage("/admin/login").permitAll()
//		.loginProcessingUrl("/admin/login")
//		.usernameParameter("username")
//		.passwordParameter("password").and()
//		.rememberMe().key("rem-me-key")
//		.rememberMeParameter("remember-me")
//		.tokenValiditySeconds(200000)
//		.rememberMeCookieName("rememberlogin").and()
//		.logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/admin");
//
//	}
//
//	@Bean
//	DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		daoAuthenticationProvider.setUserDetailsService(this.detailsService);
//		return daoAuthenticationProvider;
//	}
//
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//
//
//
//}
