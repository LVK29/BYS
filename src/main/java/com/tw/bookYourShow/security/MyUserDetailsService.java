package com.tw.bookYourShow.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.model.BYSUser;
import com.tw.bookYourShow.model.CustomUserDetails;
import com.tw.bookYourShow.repository.BYSUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	BYSUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<BYSUser> user = userRepository.findByEmail(userName);
		if (user == null) {
			log.error("User with userId" + userName + "not found");
			throw new UsernameNotFoundException("User not found");
		}

		BYSUser userModel = user.get();
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userModel.getUserType().toString()));

		return getUserForAuth(userModel, grantedAuthorities);

	}

	private UserDetails getUserForAuth(BYSUser userModel, Collection<GrantedAuthority> grantedAuthorities) {
		CustomUserDetails customUserDeatils = new CustomUserDetails(userModel);
		return customUserDeatils;
	}
}
