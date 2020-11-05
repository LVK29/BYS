package com.tw.bookYourShow.facade;

import java.text.ParseException;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.ShowSeatDTO;
import com.tw.bookYourShow.dto.TheaterAudiDTO;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.model.TheaterAudi;
import com.tw.bookYourShow.service.CommonUtils;
import com.tw.bookYourShow.service.ShowSeatService;

//TODO
@Component
public class ShowSeatFacade {

	Logger log = LoggerFactory.getLogger(ShowSeatFacade.class);

	
	@Autowired
	ShowSeatService showSeatService;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	public ModelMapper modelMapper;

	public void createShowSeat(ShowSeatDTO showSeatDto, int audiId) throws ParseException {
		ShowSeat showSeat = convertToShowSeatEntity(showSeatDto);
		showSeatService.createShowSeat(showSeat, audiId, showSeatDto.getMovieShowId());
	}

	public ShowSeatDTO getShowSeat(int showSeatId) {
		ShowSeat showSeat = showSeatService.getShowSeat(showSeatId);
		ShowSeatDTO showSeatDto = convertToShowSeatDto(showSeat);
		return showSeatDto;
	}

	public void updateShowSeat(ShowSeatDTO updatedShowSeatDto, int showSeatId) {
		ShowSeat updatedShowSeat = convertToShowSeatEntity(updatedShowSeatDto);
		showSeatService.updateShowSeat(updatedShowSeat, showSeatId);
	}

	public void deleteShowSeat(int showSeatId) {
		showSeatService.deleteShowSeat(showSeatId);
	}

	public ShowSeatDTO convertToShowSeatDto(ShowSeat showSeat) {
		ShowSeatDTO showSeatDTO = modelMapper.map(showSeat, ShowSeatDTO.class);
		return showSeatDTO;
	}

	public ShowSeat convertToShowSeatEntity(ShowSeatDTO showSeatDTO) {
		TypeMap<String, Date> typeMap = modelMapper.getTypeMap(String.class, java.util.Date.class);
		if (typeMap == null) { // if not already added
			modelMapper.createTypeMap(String.class, Date.class);
			modelMapper.addConverter(commonUtils.toStringDate);

		}
		ShowSeat showSeat = modelMapper.map(showSeatDTO, ShowSeat.class);
		return showSeat;
	}
}
