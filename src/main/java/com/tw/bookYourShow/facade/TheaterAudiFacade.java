package com.tw.bookYourShow.facade;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.MovieDTO;
import com.tw.bookYourShow.dto.TheaterAudiDTO;
import com.tw.bookYourShow.dto.TheaterDTO;
import com.tw.bookYourShow.model.Theater;
import com.tw.bookYourShow.model.TheaterAudi;
import com.tw.bookYourShow.service.TheaterAudiService;
import com.tw.bookYourShow.service.TheaterService;

/**
 *  Facade layer for converting TheaterAudi to TheaterAudiDTO and vice versa  for various CRUD api calls
 * @author LVK
 *
 */
@Component
public class TheaterAudiFacade {

	Logger log = LoggerFactory.getLogger(MovieController.class);

	
	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	TheaterAudiService theaterAudiService;

	@Autowired
	TheaterService theaterService;

	public void createTheaterAudi(TheaterAudiDTO theaterAudiDTO,int theaterId) {		
		TheaterAudi theaterAudi = convertToTheaterEntity(theaterAudiDTO);
		Theater theater = theaterService.getTheater(theaterId);
		theaterAudi.setTheater(theater);
		theaterAudiService.createTheaterAudi(theaterAudi);
	}

	public TheaterAudiDTO getTheaterAudi(int theaterAudiId) {
		TheaterAudi theaterAudi = theaterAudiService.getTheaterAudi(theaterAudiId);
		return convertToTheaterAudiDto(theaterAudi);
	}

	public void updateTheaterAudi(TheaterAudiDTO theaterAudiDTO, int theaterAudiId) {
		TheaterAudi updatedTheaterAudi = convertToTheaterEntity(theaterAudiDTO);
		theaterAudiService.updateTheaterAudi(updatedTheaterAudi, theaterAudiId);
	}

	public void deleteTheaterAudi(int theaterAudiId) {
		theaterAudiService.deleteTheaterAudi(theaterAudiId);
	}

	public TheaterAudiDTO convertToTheaterAudiDto(TheaterAudi theater) {
		TheaterAudiDTO theaterDTO = modelMapper.map(theater, TheaterAudiDTO.class);
		return theaterDTO;
	}

	public TheaterAudi convertToTheaterEntity(TheaterAudiDTO theaterDTO) {
		TheaterAudi theater = modelMapper.map(theaterDTO, TheaterAudi.class);
		return theater;
	}
}
