package com.tw.bookYourShow.facade;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.TheaterDTO;
import com.tw.bookYourShow.model.AudiSeat;
import com.tw.bookYourShow.model.Theater;
import com.tw.bookYourShow.model.TheaterAudi;
import com.tw.bookYourShow.service.TheaterService;

@Component
public class TheaterFacade {

	Logger log = LoggerFactory.getLogger(MovieController.class);

	
	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	TheaterAudiFacade theaterAudiFacade;

	@Autowired
	TheaterService theaterService;

	public void createTheater(TheaterDTO theaterDTO) {
		Theater theater = convertToTheaterEntity(theaterDTO);
		theaterService.createTheater(theater);
	}

	public TheaterDTO getTheater(int theaterId) {
		Theater theater = theaterService.getTheater(theaterId);
		TheaterDTO theaterDTO = convertToTheaterDto(theater);
		return theaterDTO;
	}

	public List<TheaterDTO> getAllTheaters() {
		List<Theater> theaters = theaterService.getAllTheaters();
		List<TheaterDTO> theaterDTOs = new ArrayList<>();
		for (Theater theater : theaters) {
			theaterDTOs.add(convertToTheaterDto(theater));
		}
		return theaterDTOs;
	}

	public void updateTheater(TheaterDTO theaterDTO, int theaterId) {
		Theater updatedTheater = convertToTheaterEntity(theaterDTO);
		theaterService.updateTheater(updatedTheater, theaterId);
	}

	public void deleteTheater(int theaterId) {
		theaterService.deleteTheater(theaterId);
	}

	public TheaterDTO convertToTheaterDto(Theater theater) {
		TheaterDTO theaterDTO = modelMapper.map(theater, TheaterDTO.class);
		return theaterDTO;
	}

	public Theater convertToTheaterEntity(TheaterDTO theaterDTO) {
		Theater theater = modelMapper.map(theaterDTO, Theater.class);
		if (theater.getTheaterAudis() != null && theater.getTheaterAudis().size() > 0) {
			for (TheaterAudi audi : theater.getTheaterAudis()) {
				audi.setTheater(theater);
				for (AudiSeat audiSeat : audi.getAudiSeats()) {
					audiSeat.setTheaterAudi(audi);
				}
			}
		}
		return theater;
	}
}
