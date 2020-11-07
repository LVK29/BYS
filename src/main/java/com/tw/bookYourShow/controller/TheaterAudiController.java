package com.tw.bookYourShow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.TheaterAudiDTO;
import com.tw.bookYourShow.facade.TheaterAudiFacade;

/**
 * Controller that has related to TheaterAudi Entity related data
 * 
 * @author LVK
 *
 */
@RestController
public class TheaterAudiController {

	Logger log = LoggerFactory.getLogger(TheaterAudiController.class);

	@Autowired
	TheaterAudiFacade theaterAudiFacade;

	/**
	 * Creates theaterAudi includeing the audiSeat data, and links it to the theater
	 * specified
	 * 
	 * @param theaterId
	 * @param theaterAudiDTO
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi", method = RequestMethod.POST)
	public void createTheaterAudi(@PathVariable int theaterId, @RequestBody TheaterAudiDTO theaterAudiDTO) {
		theaterAudiFacade.createTheaterAudi(theaterAudiDTO, theaterId);
	}

	/**
	 * 
	 * Gets theaterAudi details and its respective movieShows data as well.
	 * 
	 * @param theaterId
	 * @param theaterAudiId
	 * @return
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi/{theaterAudiId}", method = RequestMethod.GET)
	public TheaterAudiDTO getTheaterAudi(@PathVariable int theaterId, @PathVariable int theaterAudiId) {
		return theaterAudiFacade.getTheaterAudi(theaterAudiId);
	}

	/**
	 * 
	 * Updates the theaterAudi and Audi Seat data
	 * 
	 * @param theaterId
	 * @param theaterAudiId
	 * @param theaterAudiDTO
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi/{theaterAudiId}", method = RequestMethod.PUT)
	public void updateTheaterAudi(@PathVariable int theaterId, @PathVariable int theaterAudiId,
			@RequestBody TheaterAudiDTO theaterAudiDTO) {
		theaterAudiFacade.updateTheaterAudi(theaterAudiDTO, theaterAudiId);

	}

	
	/**
	 * Deletes the theaterAudi and Audi Seats for audis that dont have booking
	 * @param theaterId
	 * @param theaterAudiId
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi/{theaterAudiId}", method = RequestMethod.DELETE)
	public void deleteTheaterAudi(@PathVariable int theaterId, @PathVariable int theaterAudiId) {
		theaterAudiFacade.deleteTheaterAudi(theaterAudiId);
	}

}
