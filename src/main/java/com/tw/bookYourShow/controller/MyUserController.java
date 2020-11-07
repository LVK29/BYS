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

/**
 * Controller having API methods related to User Entity
 * 
 * @author LVK
 *
 */
@RestController
public class MyUserController {

	Logger log = LoggerFactory.getLogger(MyUserController.class);

	@Autowired
	BYSUserFacade bysUserFacade;

	/**
	 * Gets the user based on the emailId for from the auth token
	 * 
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public BYSUserDTO getUser(Principal auth) {
		return bysUserFacade.getBYSUser(auth.getName());
	}

	/**
	 * Updates the userEntity based on the user email
	 * 
	 * @param updatedUserDTO
	 * @param auth
	 */
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void updateUser(@RequestBody BYSUserDTO updatedUserDTO, Principal auth) {
		bysUserFacade.updateBYSUser(updatedUserDTO, auth.getName());
	}

	/**
	 * Performs soft delete of the user entity
	 * 
	 * @param auth
	 */
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUser(Principal auth) {
		bysUserFacade.deleteBYSUser(auth.getName());
	}

}
