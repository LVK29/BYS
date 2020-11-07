package com.tw.bookYourShow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.tw.bookYourShow.service.EmailService;

/**
 * Controller used for registration and activation of users
 * 
 * @author LVK
 *
 */
@RestController
public class RegistrationController {

	Logger log = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	BYSUserFacade bysUserFacade;

	/**
	 * Used to create an inactive userEntity based on customer's registration data
	 * 
	 * @param userDto
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void createUser(@RequestBody BYSUserDTO userDto) {

		bysUserFacade.createBYSUser(userDto);

	}

	/**
	 * Used to verify the registered account and mark it active based on the
	 * verification code
	 * 
	 * @param activationCode
	 * @param userEmail
	 */
	@RequestMapping(value = "/activateUser", method = RequestMethod.PUT)
	public void createUser(@RequestHeader String activationCode, @RequestHeader String userEmail) {

		bysUserFacade.activateBYSUser(activationCode, userEmail);

	}

}
