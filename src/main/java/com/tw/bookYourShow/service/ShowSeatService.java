package com.tw.bookYourShow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.AudiSeat;
import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.model.TheaterAudi;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.MovieShowRepository;
import com.tw.bookYourShow.repository.ShowSeatRepository;
import com.tw.bookYourShow.repository.TheaterAudiRepository;
import com.tw.bookYourShow.repository.TheaterRepository;

/**
 * Service class consists of methods related to ShowSeat entitiy
 * 
 * @author LVK
 *
 */
@Service
public class ShowSeatService {

	Logger log = LoggerFactory.getLogger(ShowSeatService.class);

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	TheaterAudiRepository theaterAudiRepository;

	@Autowired
	MovieShowRepository movieShowRepository;

	@Autowired
	ShowSeatRepository showSeatRepository;

	/**
	 * ShowSeat is created for audiId and movieShow
	 * 
	 * @param showSeat
	 * @param audiId
	 * @param movieShowId
	 */
	public void createShowSeat(ShowSeat showSeat, int audiId, int movieShowId) {
		try {
			MovieShow movieShow = movieShowRepository.findById(movieShowId).get();
			TheaterAudi theaterAudi = theaterAudiRepository.findById(audiId).get();
			List<ShowSeat> showSeatList = new ArrayList<>();
			for (AudiSeat audiShowSeats : theaterAudi.getAudiSeats()) {
				ShowSeat eachShowSeat = new ShowSeat();
				eachShowSeat.setAudiSeat(audiShowSeats);
				eachShowSeat.setMovieShow(movieShow);
				eachShowSeat.setPrice(showSeat.getPrice());
				eachShowSeat.setShowDate(showSeat.getShowDate());
				eachShowSeat.setShowTime(movieShow.getTimingTo());
				showSeatList.add(eachShowSeat);
				log.debug("Show seat created in audi " + audiId + " with the showseat " + eachShowSeat.toString());
			}
			movieShow.getShowSeats().addAll(showSeatList);
			showSeatRepository.saveAll(showSeatList);
		} catch (Exception ex) {

		}
	}

	/**
	 * Gets ShowSeat based on its id
	 * 
	 * @param showSeatId
	 * @return
	 */
	public ShowSeat getShowSeat(int showSeatId) {
		Optional<ShowSeat> showSeats = showSeatRepository.findById(showSeatId);
		if (showSeats.isEmpty()) {
			throw new BYSException("Show seat with id " + showSeatId + " not found");
		}
		return showSeats.get();
	}

	/**
	 * Method updates a particular showseat price and its status
	 * 
	 * @param updatedShowSeat
	 * @param showSeatId
	 */
	public void updateShowSeat(ShowSeat updatedShowSeat, int showSeatId) {
		ShowSeat showSeat = getShowSeat(showSeatId);
		showSeat.setPrice(updatedShowSeat.getPrice());
		showSeat.setShowStatus(updatedShowSeat.getShowStatus());
		showSeatRepository.save(showSeat);
		log.info("Updated ShowSeat with id " + showSeatId);
	}

	/**
	 * Deletes showSeat if its not booked
	 * 
	 * @param showSeatId
	 */
	public void deleteShowSeat(int showSeatId) {
		ShowSeat showSeat = getShowSeat(showSeatId);
		if (showSeat.getBooking() != null) {
			log.debug("Cant delete show seat " + showSeatId + " as its booked");
			return;
		}
		showSeat.getMovieShow().getShowSeats().remove(showSeat);
		showSeatRepository.delete(showSeat);
		log.info("Deleted showSeat with id" + showSeatId);
	}

	/**
	 * Gets all available ShowSeats from currentday onwards
	 * 
	 * @return
	 */
	public List<ShowSeat> getAllAvailableShowSeats() {
		Date currentDate = new Date();
		return showSeatRepository.getAvailableShowSeatsForMovieShow(currentDate);

	}
}
