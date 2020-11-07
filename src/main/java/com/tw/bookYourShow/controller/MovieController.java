package com.tw.bookYourShow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.MovieDTO;
import com.tw.bookYourShow.facade.MovieFacade;

/**
 * Controller used by ADMINs for performing CRUD operations on Movie Entity
 * 
 * @author LVK
 *
 */
@RestController
public class MovieController {

	@Autowired
	MovieFacade movieFacade;

	Logger log = LoggerFactory.getLogger(MovieController.class);

	/**
	 * Creates movie based on MovieDTO passed
	 * 
	 * @param movieDTO
	 */
	@RequestMapping(value = "/admin/movie", method = RequestMethod.POST)
	public void createMovie(@RequestBody MovieDTO movieDTO) {
		movieFacade.createMovie(movieDTO);
	}

	/**
	 * Gets the movieDTO for movieId passed
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/movie/{id}", method = RequestMethod.GET)
	public MovieDTO getMovieDetails(@PathVariable int id) {
		return movieFacade.getMovie(id);
	}

	/**
	 * Deletes movie based on the movieId passed
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/admin/movie/{id}", method = RequestMethod.DELETE)
	public void deleteMovieDetails(@PathVariable int id) {
		movieFacade.deleteMovie(id);
	}

	/**
	 * Updates the movie based on MovieDTO passed
	 * 
	 * @param movieDTO
	 * @param id
	 */
	@RequestMapping(value = "/admin/movie/{id}", method = RequestMethod.PUT)
	public void updateMovie(@RequestBody MovieDTO movieDTO, @PathVariable int id) {

		movieFacade.updateMovie(movieDTO, id);
	}

}
