package com.tw.bookYourShow.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.BYSUserDTO;
import com.tw.bookYourShow.facade.BYSUserFacade;
import com.tw.bookYourShow.repository.BYSUserRepository;
import com.tw.bookYourShow.repository.BookingRepository;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.TheaterAudiRepository;
import com.tw.bookYourShow.repository.TheaterRepository;

@RestController
public class MyUserController {

	Logger log = LoggerFactory.getLogger(MyUserController.class);

	@Autowired
	BYSUserFacade bysUserFacade;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public BYSUserDTO getUser(Principal auth) {
		return bysUserFacade.getBYSUser(auth.getName());
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void updateUser(@RequestBody BYSUserDTO updatedUserDTO, Principal auth) {
		bysUserFacade.updateBYSUser(updatedUserDTO, auth.getName());
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUser(Principal auth) {
		bysUserFacade.deleteBYSUser(auth.getName());
	}

}
