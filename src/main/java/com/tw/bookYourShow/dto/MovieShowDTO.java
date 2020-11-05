package com.tw.bookYourShow.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
public class MovieShowDTO {

	private int id;
	private int audiId;
	private int movieId;
	private String movieName;
	private String theaterName;

	private String timingFrom;
	private String timingTo;
	// private String theaterAudiId;
	private List<ShowSeatDTO> showSeats = new ArrayList<>();

	public MovieShowDTO() {

	}

	public MovieShowDTO(int id, int audiId, int movieId, String timingFrom, String timingTo,
			List<ShowSeatDTO> showSeats) {
		super();
		this.id = id;
		this.audiId = audiId;
		this.movieId = movieId;
		this.timingFrom = timingFrom;
		this.timingTo = timingTo;
		this.showSeats = showSeats;
	}

	@Override
	public String toString() {
		return "MovieShowDTO [id=" + id + ", audiId=" + audiId + ", movieId=" + movieId + ", timingFrom=" + timingFrom
				+ ", timingTo=" + timingTo + ", showSeats=" + showSeats + "]";
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAudiId() {
		return audiId;
	}

	public void setAudiId(int audiId) {
		this.audiId = audiId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTimingFrom() {
		return timingFrom;
	}

	public void setTimingFrom(String timingFrom) {
		this.timingFrom = timingFrom;
	}

	public String getTimingTo() {
		return timingTo;
	}

	public void setTimingTo(String timingTo) {
		this.timingTo = timingTo;
	}

	public List<ShowSeatDTO> getShowSeats() {
		return showSeats;
	}

	public void setShowSeats(List<ShowSeatDTO> showSeats) {
		this.showSeats = showSeats;
	}

}
