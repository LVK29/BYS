package com.tw.bookYourShow.facade;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.BYSUserDTO;
import com.tw.bookYourShow.dto.MovieDTO;
import com.tw.bookYourShow.model.BYSUser;
import com.tw.bookYourShow.model.Booking;
import com.tw.bookYourShow.service.BYSUserService;

@Component
public class BYSUserFacade {

	Logger log = LoggerFactory.getLogger(BYSUserFacade.class);

	@Autowired
	BYSUserService bysUserService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	BookingFacade bookingFacade;

	public void createBYSUser(BYSUserDTO userDto) {
		BYSUser bysUser = convertToBYSUserEntity(userDto);
		bysUserService.createBYSUser(bysUser);
	}

	public void activateBYSUser(String activationcode, String userEmail) {
		bysUserService.activateBYSUser(userEmail, activationcode);
	}

	public BYSUserDTO getBYSUser(String userEmail) {
		BYSUser bysUser = bysUserService.getBYSUserByEmail(userEmail);
		return convertToBYSUserDto(bysUser);
	}

	public void updateBYSUser(BYSUserDTO userDto, String userEmail) {
		BYSUser updatedBYSUser = convertToBYSUserEntity(userDto);
		bysUserService.updateBYSUser(updatedBYSUser, userEmail);
	}

	public void deleteBYSUser(String userEmail) {
		bysUserService.deleteBYSUser(userEmail);
	}

	private BYSUserDTO convertToBYSUserDto(BYSUser user) {
		BYSUserDTO userDTO = modelMapper.map(user, BYSUserDTO.class);
		for (Booking booking : user.getBookings()) {
			userDTO.getShowsBooked().add(bookingFacade.convertToBookingDto(booking));
		}

		return userDTO;
	}

	private BYSUser convertToBYSUserEntity(BYSUserDTO userDto) {
		BYSUser user = modelMapper.map(userDto, BYSUser.class);
		user.setSaltedHashedPassword(passwordEncoder.encode(userDto.getSaltedHashedPassword()));
		return user;
	}
}
