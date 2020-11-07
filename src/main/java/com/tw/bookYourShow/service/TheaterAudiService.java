package com.tw.bookYourShow.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.AudiSeat;
import com.tw.bookYourShow.model.Theater;
import com.tw.bookYourShow.model.TheaterAudi;
import com.tw.bookYourShow.repository.MovieShowRepository;
import com.tw.bookYourShow.repository.TheaterAudiRepository;
import com.tw.bookYourShow.repository.TheaterRepository;

/**
 * Service class contains business logic for related to TheaterAudi entity
 * 
 * @author LVK
 *
 */
@Service
public class TheaterAudiService {

	Logger log = LoggerFactory.getLogger(TheaterAudiService.class);

	@Autowired
	TheaterAudiRepository theaterAudiRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	MovieShowRepository movieShowRepository;

	/**
	 * Method creates theater audi
	 * 
	 * @param theaterAudi
	 */
	public void createTheaterAudi(TheaterAudi theaterAudi) {

		theaterAudi.getAudiSeats().forEach((audiSeat) -> audiSeat.setTheaterAudi(theaterAudi));
		theaterAudiRepository.save(theaterAudi);
		log.info("Theater " + theaterAudi + "and its seats saved ");

	}

	/**
	 * Gets theaterAudi and its seats based on audiId passed
	 * 
	 * @param theaterAudiId
	 * @return
	 */
	public TheaterAudi getTheaterAudi(int theaterAudiId) {
		Optional<TheaterAudi> theaterAudi = theaterAudiRepository.findById(theaterAudiId);
		if (theaterAudi.isEmpty()) {
			throw new BYSException("Theater Audi with id " + theaterAudiId + "not found");
		}
		return theaterAudi.get();
	}

	/**
	 * updates theaterAudi name
	 * 
	 * @param updatedTheaterAudi
	 * @param theaterAudiId
	 */
	public void updateTheaterAudi(TheaterAudi updatedTheaterAudi, int theaterAudiId) {
		TheaterAudi theaterAudi = getTheaterAudi(theaterAudiId);
		if (updatedTheaterAudi.getMovieShows().size() > 0 || updatedTheaterAudi.getAudiSeats().size() > 0) {
			throw new BYSException(
					"Please do not specify ant movie or movie related details, it should be only infrastructure details");

		}
		theaterAudi.setAudiName(updatedTheaterAudi.getAudiName());
		theaterAudiRepository.save(theaterAudi);
	}

	/**
	 * method deletes theateraudi granted that its movieShows are deleted
	 * 
	 * @param theaterAudiId
	 */
	public void deleteTheaterAudi(int theaterAudiId) {
		TheaterAudi theaterAudi = getTheaterAudi(theaterAudiId);
		// if theater audi's have movieshows then throw
		// exception saying cant delete audi untill movishow is delete
		if (theaterAudi.getMovieShows().size() > 0) {
			log.error("Cant delete audi " + theaterAudiId + " as audi has movieShows");

			throw new BYSException(
					"Cant delete audi as audi has movieShows, please delete movieShows before deleting audi");

		}
		for (AudiSeat audiSeat : theaterAudi.getAudiSeats()) {
			audiSeat.setTheaterAudi(null);
			log.debug("Setting AudiSeat's audi as null");
		}
		Theater theater = theaterAudi.getTheater();
		theater.getTheaterAudis().remove(theaterAudi);

		theaterRepository.save(theater);
		log.info("Theater audi " + theaterAudiId + " deleted");

	}
}
