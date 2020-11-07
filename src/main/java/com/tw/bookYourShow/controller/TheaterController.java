package com.tw.bookYourShow.controller;

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

/**
 * Controller for theater related CRUD operations
 * 
 * @author LVK
 *
 */
@RestController
public class TheaterController {

	Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	TheaterFacade theaterFacade;

	/**
	 * Creates theater based on the theaterDTO passed
	 * 
	 * @param theaterDTO
	 */
	@RequestMapping(value = "/admin/theater/", method = RequestMethod.POST)
	public void createTheater(@RequestBody TheaterDTO theaterDTO) {
		theaterFacade.createTheater(theaterDTO);
	}

	/**
	 * Gets theater data based on theaterId
	 * 
	 * @param theaterId
	 * @return
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}", method = RequestMethod.GET)
	public TheaterDTO getTheater(@PathVariable int theaterId) {
		return theaterFacade.getTheater(theaterId);
	}

	/**
	 * Updates theaterData
	 * 
	 * @param theaterId
	 * @param updatedTheaterDTO
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}", method = RequestMethod.PUT)
	public void updateTheater(@PathVariable int theaterId, @RequestBody TheaterDTO updatedTheaterDTO) {
		theaterFacade.updateTheater(updatedTheaterDTO, theaterId);

	}

	/**
	 * Deletes theater based on theaterId
	 * 
	 * @param theaterId
	 */
	@RequestMapping(value = "/admin/theater/{theaterId}", method = RequestMethod.DELETE)
	public void deleteTheater(@PathVariable int theaterId) {
		theaterFacade.deleteTheater(theaterId);
	}

	/**
	 * Gets all theaters
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/allTheaters", method = RequestMethod.GET)
	public List<TheaterDTO> getAllTheaters() {
		return theaterFacade.getAllTheaters();
	}

}
