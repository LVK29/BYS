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

/**
 * @author LVK
 *
 *         Controller for booking related API
 */
@RestController
public class BookingController {

	@Autowired
	BookingFacade bookingFacade;

	Logger log = LoggerFactory.getLogger(BookingController.class);

	/**
	 * Controller method that creates booking of one or more showSeats
	 * 
	 * @param bookingDto
	 * @param auth
	 */
	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public void createBooking(@RequestBody BookingDTO bookingDto, Principal auth) {
		bookingFacade.createBooking(bookingDto.getShowSeatIds(), auth.getName());

	}

	/**
	 * Method returns bookingDTO for given bookingId
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	public BookingDTO getBooking(@PathVariable int id) {
		return bookingFacade.getBooking(id);

	}

	/**
	 * Method deletes booking for given bookingId
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
	public void deleteBooking(@PathVariable int id) {
		bookingFacade.deleteBooking(id);

	}

	/**
	 * Method used by admin to confirm that the customer has paid bookingPrice
	 * before watching show
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/admin/booking/confirmPayment/{id}", method = RequestMethod.PUT)
	public void confirmBookingPayment(@PathVariable int id) {
		bookingFacade.confirmBookingPayment(id);

	}

}
