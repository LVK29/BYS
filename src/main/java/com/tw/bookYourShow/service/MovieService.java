package com.tw.bookYourShow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.Movie;
import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.MovieShowRepository;
import com.tw.bookYourShow.repository.TheaterAudiRepository;

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

	public void createMovie(Movie movie) {

		movieRepository.save(movie);
		log.info("Movie saved with id " + movie.getId());
	}

	public Movie getMovie(int movieId) {
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (movie.isEmpty()) {
			throw new BYSException("Movie with id " + movieId + " is not found");
		}
		return movieRepository.findById(movieId).get();
	}


	public void updateMovie(Movie updatedMovie, int movieId) {
		Movie movie = movieRepository.findById(movieId).get();
		movie.setDirector(updatedMovie.getDirector());
		movie.setGenre(updatedMovie.getGenre());
		movie.setName(updatedMovie.getName());
		movie.setSummary(updatedMovie.getSummary());
		movieRepository.save(movie);
		log.info("Movie " + movie.getId() + "updated with  " + movie.toString());
	}

	public void deleteMovie(int movieId) {
		Movie movie = getMovie(movieId);
		// get movie

		// get movie shows of movie

		// remove those movieshows - first remove movieShow from audi

		// put movies as null in movieshow and then remove movieShow

		// remove movie
//TODO
		List<MovieShow> movieShows = movie.getMovieShows();
		for (MovieShow movieShow : movieShows) {
			movieShow.getTheaterAudi().getMovieShows().remove(movieShow);

			movieShow.setMovie(null);
			log.debug(
					"Movie" + movie.getId() + " " + movie.getName() + " removed from movie show  " + movieShow.getId());
			log.debug("MovieShow " + movieShow.getId() + " will be deleted");
		}
		movieShowRepository.deleteAll(movieShows);

		movie.setMovieShows(null);
		movieRepository.delete(movie);
		log.info("Movie " + movie.getId() + " deleted and all its movieShows");
	}
}
