package com.tw.bookYourShow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class TheaterAudi {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String audiName;
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "theaterAudi")
	private List<AudiSeat> audiSeats = new ArrayList<>();
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "theaterAudi")
	private List<MovieShow> movieShows = new ArrayList<>();


	@ManyToOne
	private Theater theater;

	public TheaterAudi() {

	}

	public TheaterAudi(int id, String audiName, List<AudiSeat> audiSeats, List<MovieShow> movieShows, Theater theater) {
		super();
		this.id = id;
		this.audiName = audiName;
		this.audiSeats = audiSeats;
		this.movieShows = movieShows;
		this.theater = theater;
	}

	@Override
	public String toString() {
		return "TheaterAudi [id=" + id + ", audiName=" + audiName + ", audiSeats=" + audiSeats + ", movieShows="
				+ movieShows + ", theater=" + theater + "]";
	}

	public int getId() {
		return id;
	}

	public String getAudiName() {
		return audiName;
	}

	public void setAudiName(String audiName) {
		this.audiName = audiName;
	}

	public List<AudiSeat> getAudiSeats() {
		return audiSeats;
	}

	public void setAudiSeats(List<AudiSeat> audiSeats) {
		this.audiSeats = audiSeats;
	}

	public List<MovieShow> getMovieShows() {
		return movieShows;
	}

	public void setMovieShows(List<MovieShow> movieShows) {
		this.movieShows = movieShows;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

}
