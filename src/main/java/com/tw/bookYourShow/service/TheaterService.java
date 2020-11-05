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
import com.tw.bookYourShow.model.Theater;
import com.tw.bookYourShow.repository.TheaterAudiRepository;
import com.tw.bookYourShow.repository.TheaterRepository;

@Service
public class TheaterService {

	Logger log = LoggerFactory.getLogger(TheaterService.class);

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	TheaterAudiRepository theaterAudiRepository;

	public void createTheater(Theater theater) {

		theaterRepository.save(theater);
		log.info("Theater saved successfully with id" + theater.getId());
	}

	public Theater getTheater(int theaterId) {
		Optional<Theater> theater = theaterRepository.findById(theaterId);
		if (theater.isEmpty()) {
			throw new BYSException("Theater with id " + theaterId + " not found");
		}
		return theater.get();
	}

	public List<Theater> getAllTheaters() {
		List<Theater> theaters = new ArrayList<>();
		theaterRepository.findAll().forEach(theaters::add);
		return theaters;
	}

	public void updateTheater(Theater updatedTheater, int theaterId) {
		Theater theater = getTheater(theaterId);
		theater.setAddress(updatedTheater.getAddress());
		theater.setName(updatedTheater.getName());
		theater.setRating(updatedTheater.getRating());
		theaterRepository.save(theater);
	}

	public void deleteTheater(int theaterId) {
		Theater theater = getTheater(theaterId);
		if (!theater.getTheaterAudis().isEmpty()) {
			throw new BYSException("Theater contains audis please delete them first");
		}
		theaterRepository.deleteById(theaterId);
		log.info("Theater with id " + theaterId + " deleted successfully");

	}

	public List<Theater> getAllTheatersForCustomer() {

		return theaterRepository.getAllTheatersForCustomer();
	}
}
