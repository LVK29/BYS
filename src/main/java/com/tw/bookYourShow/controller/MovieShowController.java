package com.tw.bookYourShow.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.MovieShowDTO;
import com.tw.bookYourShow.facade.MovieShowFacade;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.repository.ShowSeatRepository;

/**
 * Controller used for performing various CRUD operations on MovieShow Entity
 * 
 * @author LVK
 *
 */
@RestController
public class MovieShowController {

	Logger log = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	ShowSeatRepository showSeatRepository;

	@Autowired
	MovieShowFacade movieShowFacade;

	/**
	 * Creates MovieShow and assigns it to the theaterAudi id passed
	 * 
	 * @param theaterAudiId
	 * @param movieShowDTO
	 * @throws ParseException
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/movieShow/", method = RequestMethod.POST)
	public void createMovieShow(@PathVariable int theaterAudiId, @RequestBody MovieShowDTO movieShowDTO)
			throws ParseException {

		movieShowFacade.createMovieShow(theaterAudiId, movieShowDTO);

	}

	/**
	 * Gets the movieShow for the movieShowId passed
	 * 
	 * @param movieShowId
	 * @return
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/movieShow/{movieShowId}", method = RequestMethod.GET)
	public MovieShowDTO getMovieShow(@PathVariable int movieShowId) {
		return movieShowFacade.getMovieShow(movieShowId);
	}

	/**
	 * Deletes the movieShow from theaterAudi id specified
	 * 
	 * @param theaterAudiId
	 * @param movieShowId
	 */
	@RequestMapping(value = "/admin/theaterAudi/{theaterAudiId}/movieShow/{movieShowId}", method = RequestMethod.DELETE)
	public void deleteMovieShow(@PathVariable int theaterAudiId, @PathVariable int movieShowId) {
		movieShowFacade.deleteMovieShow(theaterAudiId, movieShowId);
	}

	/**
	 * Gets all the Movie Shows that have available showSeats
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allAvailableMovieShows", method = RequestMethod.GET)
	public List<MovieShowDTO> getAllAvailableMovieShows() {
		return movieShowFacade.getAllAvailableMovieShows();
	}
}
