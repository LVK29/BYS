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

//	@RequestMapping(value = "/createTheater", method = RequestMethod.POST)
//	public void createTheater(@RequestBody TheaterDTO theaterDTO) throws ParseException {
//		theaterDTO.getAddress();
//		Theater theater = new Theater();
//		theater.setAddress("Mantri Mall");
//		theater.setName("Inox");
//		theater.setRating("5");
//
//		// set theater audi
//		List<TheaterAudi> audiList = new ArrayList<TheaterAudi>();
//		TheaterAudi audi = new TheaterAudi();
//		audi.setAudiName("Audi 5");
//
//		List<AudiSeat> audiSeats = new ArrayList<AudiSeat>();
//
//		AudiSeat audiSeat = new AudiSeat();
//		audiSeat.setRowNumber("A");
//		audiSeat.setSeatNumber("3");
//
//		audiSeat.setSeats(null); // showSeats will be null when creating a new audi
//		audiSeats.add(audiSeat);
//		audi.setAudiSeat(audiSeats);
//
//		// add audi list to theater
//		List<MovieShow> movieShowList = new ArrayList<MovieShow>();
//
//		MovieShow movieShow = new MovieShow();
//		Date startTime = getTimeFromString("10:15:00");
//		Date endTime = getTimeFromString("12:00:00");
//		movieShow.setTimingFrom(startTime);
//		movieShow.setTimingTo(endTime);
//		movieShowList.add(movieShow);
//
//		MovieShow movieShow2 = new MovieShow();
//		Date startTime2 = getTimeFromString("12:15:00");
//		Date endTime2 = getTimeFromString("14:30:00");
//		movieShow2.setTimingFrom(startTime2);
//		movieShow.setTimingTo(endTime2);
//		movieShowList.add(movieShow2);
//
//		MovieShow movieShow3 = new MovieShow();
//		Date startTime3 = getTimeFromString("14:45:00");
//		Date endTime3 = getTimeFromString("17:00:00");
//		movieShow3.setTimingFrom(startTime3);
//		movieShow3.setTimingTo(endTime3);
//		movieShowList.add(movieShow3);
//
//		audi.setMovieShow(movieShowList);
//
//		// add audi to audi list
//		audiList.add(audi);
//
//		theater.setTheaterAudi(audiList);
//	//	theaterRepository.save(theater);
//	}

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
