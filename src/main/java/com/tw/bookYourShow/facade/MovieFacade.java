package com.tw.bookYourShow.facade;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.MovieDTO;
import com.tw.bookYourShow.dto.MovieShowDTO;
import com.tw.bookYourShow.model.Movie;
import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.service.MovieService;

@Component
public class MovieFacade {

	Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	MovieService movieService;

	@Autowired
	MovieShowFacade movieShowFacade;

	public void createMovie(MovieDTO movieDTO) {

		Movie movie = convertToMovieEntity(movieDTO);
		movieService.createMovie(movie);
	}

	public MovieDTO getMovie(int movieId) {
		Movie movie = movieService.getMovie(movieId);
		return convertToMovieDto(movie);
	}

	public void updateMovie(MovieDTO movieDTO, int movieId) {
		Movie movie = convertToMovieEntity(movieDTO);
		movieService.updateMovie(movie, movieId);
	}

	public void deleteMovie(int movieId) {
		movieService.deleteMovie(movieId);
	}

	private MovieDTO convertToMovieDto(Movie movie) {
		MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
		List<MovieShowDTO> movieShowDTOList = new ArrayList<>();
		for (MovieShow movieShow : movie.getMovieShows()) {
			movieShowDTOList.add(movieShowFacade.convertToMovieShowDto(movieShow));
		}
		movieDTO.setShows(movieShowDTOList);
		return movieDTO;
	}

	private Movie convertToMovieEntity(MovieDTO movieDTO) {
		Movie movie = modelMapper.map(movieDTO, Movie.class);
		return movie;
	}
}
