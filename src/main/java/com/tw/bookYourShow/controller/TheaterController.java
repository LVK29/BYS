package com.tw.bookYourShow.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.TheaterDTO;
import com.tw.bookYourShow.facade.TheaterFacade;
import com.tw.bookYourShow.model.Theater;
import com.tw.bookYourShow.service.TheaterService;

@RestController
public class TheaterController {

	Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	TheaterFacade theaterFacade;

	@RequestMapping(value = "/admin/theater/", method = RequestMethod.POST)
	public void createTheater(@RequestBody TheaterDTO theaterDTO) {
		theaterFacade.createTheater(theaterDTO);
	}

	@RequestMapping(value = "/admin/theater/{theaterId}", method = RequestMethod.GET)
	public TheaterDTO getTheater(@PathVariable int theaterId) {
		return theaterFacade.getTheater(theaterId);
	}

	@RequestMapping(value = "/admin/theater/{theaterId}", method = RequestMethod.PUT)
	public void updateTheater(@PathVariable int theaterId, @RequestBody TheaterDTO updatedTheaterDTO) {
		theaterFacade.updateTheater(updatedTheaterDTO, theaterId);

	}

	@RequestMapping(value = "/admin/theater/{theaterId}", method = RequestMethod.DELETE)
	public void deleteTheater(@PathVariable int theaterId) {
		theaterFacade.deleteTheater(theaterId);
	}

	@RequestMapping(value = "/admin/allTheaters", method = RequestMethod.GET)
	public void getAllTheaters() {
		theaterFacade.getAllTheaters();
	}

	@Autowired
	TheaterService theaterService;

	@RequestMapping(value = "/allTheaters", method = RequestMethod.GET)
	public List<TheaterDTO> getAllTheatersForCity() {
		// get all movieShowSeats that have showseat booked as available(atleaast one)

		// get movieShow
		// get movieName
		// get get audi details
		// get theater Name

		List<Theater> allTheaters = theaterService.getAllTheatersForCustomer();
		List<TheaterDTO> theaterDTos = new ArrayList<>();
		for (Theater theater : allTheaters) {
			theaterDTos.add(theaterFacade.convertToTheaterDto(theater));

		}
		return theaterDTos;
	}

}
