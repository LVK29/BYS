package com.tw.bookYourShow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tw.bookYourShow.dto.TheaterAudiDTO;
import com.tw.bookYourShow.facade.TheaterAudiFacade;

@RestController
//@RequestMapping("/admin/theater/{theaterId}")
public class TheaterAudiController {

	Logger log = LoggerFactory.getLogger(TheaterAudiController.class);

	
	@Autowired
	TheaterAudiFacade theaterAudiFacade;

	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi", method = RequestMethod.POST)
	public void createTheaterAudi(@PathVariable int theaterId,@RequestBody TheaterAudiDTO theaterAudiDTO) {
		theaterAudiFacade.createTheaterAudi(theaterAudiDTO,theaterId);
	}

	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi/{theaterAudiId}", method = RequestMethod.GET)
	public TheaterAudiDTO getTheaterAudi(@PathVariable int theaterId,@PathVariable int theaterAudiId) {
		return theaterAudiFacade.getTheaterAudi(theaterAudiId);
	}

	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi/{theaterAudiId}", method = RequestMethod.PUT)
	public void updateTheaterAudi(@PathVariable int theaterId,@PathVariable int theaterAudiId, @RequestBody TheaterAudiDTO theaterAudiDTO) {
		theaterAudiFacade.updateTheaterAudi(theaterAudiDTO, theaterAudiId);

	}

	@RequestMapping(value = "/admin/theater/{theaterId}/theaterAudi/{theaterAudiId}", method = RequestMethod.DELETE)
	public void deleteTheaterAudi(@PathVariable int theaterId,@PathVariable int theaterAudiId) {
		theaterAudiFacade.deleteTheaterAudi(theaterAudiId);
	}

}
