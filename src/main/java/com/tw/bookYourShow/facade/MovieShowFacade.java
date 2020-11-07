package com.tw.bookYourShow.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.MovieShowDTO;
import com.tw.bookYourShow.dto.TheaterAudiDTO;
import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.repository.ShowSeatRepository;
import com.tw.bookYourShow.service.CommonUtils;
import com.tw.bookYourShow.service.MovieShowService;
import com.tw.bookYourShow.service.ShowSeatService;

/**
 * Facade layer for converting MovieShow to MovieShowDTO and vice versa for
 * various CRUD api calls
 * 
 * @author LVK
 *
 */
@Component
public class MovieShowFacade {

	Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	MovieShowService movieShowService;

	@Autowired
	ShowSeatService showSeatService;

	public void createMovieShow(int audiId, MovieShowDTO movieShowDTO) {

		MovieShow movieShow = convertToMovieShowEntity(movieShowDTO);

		movieShowService.createMovieShow(movieShow, movieShowDTO.getMovieId(), audiId);

	}

	public MovieShowDTO getMovieShow(int movieShowId) {

		MovieShow movieShow = movieShowService.getMovieShow(movieShowId);
		MovieShowDTO movieShowDTO = convertToMovieShowDto(movieShow);
		return movieShowDTO;
	}

	public void deleteMovieShow(int audiId, int movieShowId) {
		movieShowService.deleteMovieShow(audiId, movieShowId);
	}

	@Autowired
	ShowSeatRepository showSeatRepository;

	public List<MovieShowDTO> getAllAvailableMovieShows() {
		List<ShowSeat> availableMovieShowSeats = showSeatService.getAllAvailableShowSeats();
		List<MovieShowDTO> movieShowDTOList = new ArrayList<>();
		if (availableMovieShowSeats != null) {
			for (ShowSeat availableMovieShowSeat : availableMovieShowSeats) {
				MovieShow movieShow = availableMovieShowSeat.getMovieShow();
				MovieShowDTO movieShowDTO = convertToMovieShowDto(movieShow);
				movieShowDTOList.add(movieShowDTO);
			}
		}
		return movieShowDTOList;
	}

	public MovieShowDTO convertToMovieShowDto(MovieShow movieShow) {
		MovieShowDTO movieShowDTO = modelMapper.map(movieShow, MovieShowDTO.class);
		movieShowDTO.setAudiId(movieShow.getTheaterAudi().getId());
		movieShowDTO.setMovieName(movieShow.getMovie().getName());
		movieShowDTO.setTheaterName(movieShow.getTheaterAudi().getTheater().getName());
		return movieShowDTO;
	}

	public MovieShow convertToMovieShowEntity(MovieShowDTO movieShowDTO) {

		TypeMap<String, Date> typeMap = modelMapper.getTypeMap(String.class, java.util.Date.class);
		if (typeMap == null) { // if not already added
			modelMapper.createTypeMap(String.class, Date.class);
			modelMapper.addConverter(commonUtils.toStringTime);

		}

		MovieShow movieShow = modelMapper.map(movieShowDTO, MovieShow.class);
		return movieShow;
	}

}
