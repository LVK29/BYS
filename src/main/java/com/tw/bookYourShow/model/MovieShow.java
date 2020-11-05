package com.tw.bookYourShow.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class MovieShow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Movie movie;

	@Temporal(TemporalType.TIME)
	private Date timingFrom;
	
	@Temporal(TemporalType.TIME)
	private Date timingTo;

	@ManyToOne
	private TheaterAudi theaterAudi;

//	@OneToMany(cascade = { CascadeType.ALL })
//	private List<Booking> bookings = new ArrayList<>();
	
	@OneToMany(cascade = { CascadeType.ALL },mappedBy ="movieShow",orphanRemoval = true )
	private List<ShowSeat> showSeats = new ArrayList<>();

	
	public MovieShow() {
		
	}
	public MovieShow(int id, Movie movie, Date timingFrom, Date timingTo, TheaterAudi theaterAudi,
		List<ShowSeat> showSeats) {
	super();
	this.id = id;
	this.movie = movie;
	this.timingFrom = timingFrom;
	this.timingTo = timingTo;
	this.theaterAudi = theaterAudi;
	this.showSeats = showSeats;
}

	 

	@Override
	public String toString() {
		return "MovieShow [id=" + id + ", movie=" + movie + ", timingFrom=" + timingFrom + ", timingTo=" + timingTo
				+ ", theaterAudi=" + theaterAudi + ", showSeats=" + showSeats + "]";
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Date getTimingFrom() {
		return timingFrom;
	}

	public void setTimingFrom(Date timingFrom) {
		this.timingFrom = timingFrom;
	}

	public Date getTimingTo() {
		return timingTo;
	}

	public void setTimingTo(Date timingTo) {
		this.timingTo = timingTo;
	}

	public TheaterAudi getTheaterAudi() {
		return theaterAudi;
	}

	public void setTheaterAudi(TheaterAudi theaterAudi) {
		this.theaterAudi = theaterAudi;
	}

	public List<ShowSeat> getShowSeats() {
		return showSeats;
	}

	public void setShowSeats(List<ShowSeat> showSeats) {
		this.showSeats = showSeats;
	}

	
	
}
