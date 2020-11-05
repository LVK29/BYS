package com.tw.bookYourShow.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
public class TheaterAudiDTO {
	private int id;

	private String audiName;
	private List<AudiSeatDTO> audiSeats = new ArrayList<>();
	private List<MovieShowDTO> movieShows = new ArrayList<>();
	private int theaterId;

	public TheaterAudiDTO() {

	}

	public TheaterAudiDTO(int id, String audiName, List<AudiSeatDTO> audiSeats, List<MovieShowDTO> movieShows,
			int theaterId) {
		super();
		this.id = id;
		this.audiName = audiName;
		this.audiSeats = audiSeats;
		this.movieShows = movieShows;
		this.theaterId = theaterId;
	}

	@Override
	public String toString() {
		return "TheaterAudiDTO [id=" + id + ", audiName=" + audiName + ", audiSeats=" + audiSeats + ", movieShows="
				+ movieShows + ", theaterId=" + theaterId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAudiName() {
		return audiName;
	}

	public void setAudiName(String audiName) {
		this.audiName = audiName;
	}

	public List<AudiSeatDTO> getAudiSeats() {
		return audiSeats;
	}

	public void setAudiSeats(List<AudiSeatDTO> audiSeats) {
		this.audiSeats = audiSeats;
	}

	public List<MovieShowDTO> getMovieShows() {
		return movieShows;
	}

	public void setMovieShows(List<MovieShowDTO> movieShows) {
		this.movieShows = movieShows;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

}
