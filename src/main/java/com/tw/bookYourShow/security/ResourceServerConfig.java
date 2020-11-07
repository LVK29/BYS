package com.tw.bookYourShow.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/register").permitAll()
				.antMatchers("/allAvailableMovieShows").permitAll().antMatchers("/activateUser").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN").antMatchers("/**")
				.hasAnyAuthority("ADMIN", "CUSTOMER").anyRequest().authenticated();

	}
}