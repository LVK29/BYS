package com.tw.bookYourShow.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.ShowSeatDTO;
import com.tw.bookYourShow.facade.ShowSeatFacade;

/**
 * Controller used for creating showSeats for a audi and movieShow
 * 
 * @author LVK
 *
 */
@RestController
public class ShowSeatController {

	Logger log = LoggerFactory.getLogger(ShowSeatController.class);

	@Autowired
	ShowSeatFacade showSeatFacade;

	/**
	 * Creates showSeat with same price for all the audiSeats of audi specified by
	 * audiId
	 * 
	 * @param theaterAudiId
	 * @param showSeatdto
	 * @throws ParseException
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/audiSeat/{audiSeatId}/showSeat", method = RequestMethod.POST)
	public void createShowSeat(@PathVariable int theaterAudiId, @RequestBody ShowSeatDTO showSeatdto)
			throws ParseException {

		showSeatFacade.createShowSeat(showSeatdto, theaterAudiId);

	}

	/**
	 * Gets showSeat data based on showSeatId
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/audiSeat/{audiSeatId}/showSeat/{id}", method = RequestMethod.GET)
	public ShowSeatDTO getShowSeat(@PathVariable int id) {
		return showSeatFacade.getShowSeat(id);


	}

	/**
	 * Can update ShowSeat data like price and availability (can create unique
	 * prices after creating showSeats)
	 * 
	 * @param id
	 * @param updatedShowSeatDto
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/audiSeat/{audiSeatId}/showSeat/{id}", method = RequestMethod.PUT)
	public void updateShowSeat(@PathVariable int id, @RequestBody ShowSeatDTO updatedShowSeatDto) {
		showSeatFacade.updateShowSeat(updatedShowSeatDto, id);

	}

	/**
	 * Can delete a showSeat, (likely used in case of damaged seat)
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/audiSeat/{audiSeatId}/showSeat/{id}", method = RequestMethod.DELETE)
	public void deleteShowSeat(@PathVariable int id) {
		showSeatFacade.deleteShowSeat(id);
		// ShowSeat showSeat = showSeatRepository.findById(id).get();
//		showSeat.getMovieShow().getShowSeats().remove(showSeat);
//		showSeatRepository.deleteById(id);
	}

}
