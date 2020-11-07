package com.tw.bookYourShow.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.TheaterAudiDTO;
import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.Movie;
import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.model.TheaterAudi;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.MovieShowRepository;
import com.tw.bookYourShow.repository.ShowSeatRepository;
import com.tw.bookYourShow.repository.TheaterAudiRepository;
import com.tw.bookYourShow.repository.TheaterRepository;

/**
 * Class contains business logic related to MovieShow
 * 
 * @author LVK
 *
 */
@Service
public class MovieShowService {

	Logger log = LoggerFactory.getLogger(MovieController.class);

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
	 * 
	 * Method creates movie show for movieId, and audiId specified
	 * 
	 * @param movieShow
	 * @param movieId
	 * @param audiId
	 */
	public void createMovieShow(MovieShow movieShow, int movieId, int audiId) {
		int exisitingMovieShowsAtTimeSlot = getNumberOfMovieShowForTimeSlotDate(movieShow.getTimingFrom(),
				movieShow.getTimingTo(), audiId);

		if (exisitingMovieShowsAtTimeSlot > 0) {
			throw new BYSException("There already exists movie shows between the selected times");

		}
		Movie movie = movieRepository.findById(movieId).get();
		TheaterAudi theaterAudi = theaterAudiRepository.findById(audiId).get();
		movieShow.setTheaterAudi(theaterAudi);
		movieShow.setMovie(movie);

		theaterAudi.getMovieShows().add(movieShow);
		movie.getMovieShows().add(movieShow);
		movieShowRepository.save(movieShow);
	}

	/**
	 * Method gets number of movieShows for given time slot
	 * 
	 * @param timingFrom
	 * @param timingTo
	 * @param audiId
	 * @return
	 */
	public int getNumberOfMovieShowForTimeSlotDate(Date timingFrom, Date timingTo, int audiId) {

		List<MovieShow> movieShows = movieShowRepository.getMovieShowsBetweenDates(timingFrom, timingFrom, audiId);
		return movieShows.size();
	}

	/**
	 * Method gets movieShow based on its id
	 * 
	 * @param movieShowId
	 * @return
	 */
	public MovieShow getMovieShow(int movieShowId) {
		Optional<MovieShow> movieShow = movieShowRepository.findById(movieShowId);
		if (movieShow.isEmpty()) {
			throw new BYSException("MovieShow with id " + movieShowId + " not found");

		}
		return movieShow.get();
	}

	/**
	 * Method is used to delete a movieShow, granted that there are no bookings for
	 * that show and removed its references in other entities as well
	 * 
	 * @param audiId
	 * @param movieShowId
	 */
	public void deleteMovieShow(int audiId, int movieShowId) {
		// get movieShow's showSeat where booking is not null
		Date currentDate = new Date();
		// if shows are booked for movieShow, you cant delete it
		List<ShowSeat> showSeats = showSeatRepository.getBookedShowSeatsForMovieShow(movieShowId, currentDate);
		if (showSeats.size() > 0) {
			throw new BYSException(
					"There are showSeats booked for the movie show that you are deleting you cant delete this now");

		}
		try {
			TheaterAudi theaterAudi = theaterAudiRepository.findById(audiId).get();
			MovieShow movieShow = movieShowRepository.findById(movieShowId).get();
			movieShow.getMovie().getMovieShows().remove(movieShow);
			theaterAudi.getMovieShows().remove(movieShow);
			theaterAudiRepository.save(theaterAudi);
		} catch (Exception e) {
			throw new BYSException("Movie show deletion failed");

		}

	}

}
