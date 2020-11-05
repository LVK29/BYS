package com.tw.bookYourShow.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.BookingDTO;
import com.tw.bookYourShow.facade.BookingFacade;
import com.tw.bookYourShow.repository.BYSUserRepository;
import com.tw.bookYourShow.repository.BookingRepository;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.ShowSeatRepository;
import com.tw.bookYourShow.service.BYSUserService;

//TODO
@RestController
public class BookingController {

	@Autowired
	BookingFacade bookingFacade;

	Logger log = LoggerFactory.getLogger(BookingController.class);

	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public void createBooking(@RequestBody BookingDTO bookingDto, Principal auth) {
		bookingFacade.createBooking(bookingDto.getShowSeatIds(), auth.getName());

	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	public BookingDTO getBooking(@PathVariable int id) {
		return bookingFacade.getBooking(id);

	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
	public void deleteBooking(@PathVariable int id) {
		bookingFacade.deleteBooking(id);

	}

	@RequestMapping(value = "/admin/booking/confirmPayment/{id}", method = RequestMethod.PUT)
	public void confirmBookingPayment(@PathVariable int id) {
		bookingFacade.confirmBookingPayment(id);

	}

}
