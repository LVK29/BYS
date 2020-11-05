package com.tw.bookYourShow.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.BYSUser;
import com.tw.bookYourShow.repository.BYSUserRepository;

@Service
public class BYSUserService {

	Logger log = LoggerFactory.getLogger(BYSUserService.class);

	@Autowired
	BYSUserRepository bysUserRepository;
	@Autowired
	EmailService emailService;
	

	@Autowired
	CommonUtils commonUtils;

	@Transactional
	public void createBYSUser(BYSUser user) {

		String accountActivationCode = commonUtils.generateRandomAlphaNumericValue();
		user.setActivationCode(accountActivationCode);
		try {
			bysUserRepository.save(user);
			log.info("New user created with id " + user.getId() + " and email" + user.getEmail());
			emailService.sendMail(user.getEmail(), "Book Your Show: Authentication",
					"This is you verification code " + accountActivationCode);
			log.info("Sending authentication mail to " + user.getEmail());

		} catch (Exception e) {
			 
			throw new BYSException("User registration failed");
		}

	}

	public void activateBYSUser(String userEmail, String activationCode) {
		BYSUser user = getBYSUserByEmail(userEmail);
		if (!user.isActive() && user.getActivationCode().equals(activationCode)) {
			user.setActive(true);
			user.setActivationCode(null);
			bysUserRepository.save(user);
			log.info(userEmail + " activated");
		} else {
			log.error(userEmail + " activation failed");
			throw new BYSException("User Activation failed");
		}
	}

	public BYSUser getBYSUser(int userId) {

		Optional<BYSUser> user = bysUserRepository.findById(userId);
		if (user.isEmpty()) {
			log.error("User with id " + userId + " not found");
			throw new BYSException("User with id " + userId + " not found");
		}
		return user.get();
	}

	public BYSUser getBYSUserByEmail(String userEmail) {

		Optional<BYSUser> user = bysUserRepository.findByEmail(userEmail);
		if (user.isEmpty()) {
			 
			throw new BYSException("User with email " + userEmail + " not found");
		}
		return user.get();
	}

	public void updateBYSUser(BYSUser updatedBYSUser, String userEmail) {
		BYSUser bysUser = getBYSUserByEmail(userEmail);
		bysUser.setDateOfBirth(updatedBYSUser.getDateOfBirth());
		bysUser.setName(updatedBYSUser.getName());
		bysUser.setPhoneNumber(updatedBYSUser.getPhoneNumber());
		bysUserRepository.save(bysUser);
		log.debug("User" + userEmail + " updated successfully");
	}

	public void deleteBYSUser(String userEmail) {
		// perform soft delete
		BYSUser bysUser = getBYSUserByEmail(userEmail);
		bysUser.setActive(false);
		bysUserRepository.save(bysUser);
		log.info(userEmail + " soft delete completed");
	}
}
