package com.tw.bookYourShow.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.Movie;
import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.MovieShowRepository;
import com.tw.bookYourShow.repository.ShowSeatRepository;
import com.tw.bookYourShow.repository.TheaterAudiRepository;

/**
 * Movie service class contains business logic related to MovieEntities
 * 
 * @author LVK
 *
 */

@Service
public class MovieService {

	Logger log = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieShowRepository movieShowRepository;

	@Autowired
	MovieShowService movieShowService;

	@Autowired
	TheaterAudiRepository theaterAudiRepository;

	@Autowired
	ShowSeatRepository showSeatRepository;

	/**
	 * Creates a movie entity
	 * 
	 * @param movie
	 */
	public void createMovie(Movie movie) {

		movieRepository.save(movie);
		log.info("Movie saved with id " + movie.getId());
	}

	/**
	 * Gets movie based on movieId
	 * 
	 * @param movieId
	 * @return
	 */
	public Movie getMovie(int movieId) {
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (movie.isEmpty()) {
			throw new BYSException("Movie with id " + movieId + " is not found");
		}
		return movieRepository.findById(movieId).get();
	}

	/**
	 * Updates movie entity
	 * 
	 * @param updatedMovie
	 * @param movieId
	 */
	public void updateMovie(Movie updatedMovie, int movieId) {
		Movie movie = movieRepository.findById(movieId).get();
		movie.setDirector(updatedMovie.getDirector());
		movie.setGenre(updatedMovie.getGenre());
		movie.setName(updatedMovie.getName());
		movie.setSummary(updatedMovie.getSummary());
		movieRepository.save(movie);
		log.info("Movie " + movie.getId() + "updated with  " + movie.toString());
	}

	/**
	 * Deletes movie entity and its movieSHows
	 * 
	 * @param movieId
	 */
	@Transactional
	public void deleteMovie(int movieId) {
		// get movie
		Movie movie = getMovie(movieId);
		// get movie shows of movie
		List<MovieShow> movieShows = movie.getMovieShows();
		for (MovieShow movieShow : movieShows) {
			Date today = new Date();
			// if movieShow has bookings,cancel deletion
			List<ShowSeat> showSeats = showSeatRepository.getBookedShowSeatsForMovieShow(movieShow.getId(), today);
			if (showSeats.size() > 0) {
				throw new BYSException(
						"There are showSeats booked for the movie you are deleting you cant delete this now");

			}
			// remove those movieshows - first remove movieShow from audi
			movieShow.getTheaterAudi().getMovieShows().remove(movieShow);
			// put movies as null in movieshow and then remove movieShow
			movieShow.setMovie(null);
			log.debug(
					"Movie" + movie.getId() + " " + movie.getName() + " removed from movie show  " + movieShow.getId());
			log.debug("MovieShow " + movieShow.getId() + " will be deleted");
		}
		movieShowRepository.deleteAll(movieShows);

		movie.setMovieShows(null);
		// remove movie
		movieRepository.delete(movie);
		log.info("Movie " + movie.getId() + " deleted and all its movieShows");
	}
}
