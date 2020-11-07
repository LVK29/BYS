package com.tw.bookYourShow.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class ShowSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private ShowSeatStatusType showStatus = ShowSeatStatusType.AVAILABLE;
	
	private double price;
	
	@Temporal(TemporalType.DATE)
	private Date showDate;
	
	@Temporal(TemporalType.TIME)
	private Date showTime;

	@ManyToOne
	private AudiSeat audiSeat;

	@ManyToOne
	private Booking booking;

	@ManyToOne(cascade = { CascadeType.ALL })
	private MovieShow movieShow;

	
	public ShowSeat() {
		
	}
	public ShowSeat(int id, ShowSeatStatusType showStatus, double price, Date showDate, Date showTime,
			AudiSeat audiSeat, Booking booking, MovieShow movieShow) {
		super();
		this.id = id;
		this.showStatus = showStatus;
		this.price = price;
		this.showDate = showDate;
		this.showTime = showTime;
		this.audiSeat = audiSeat;
		this.booking = booking;
		this.movieShow = movieShow;
	}

	@Override
	public String toString() {
		return "ShowSeat [id=" + id + ", showStatus=" + showStatus + ", price=" + price + ", showDate=" + showDate
				+ ", showTime=" + showTime + ", audiSeat=" + audiSeat + ", booking=" + booking + ", movieShow="
				+ movieShow + "]";
	}

	public int getId() {
		return id;
	}

 

	public ShowSeatStatusType getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(ShowSeatStatusType showStatus) {
		this.showStatus = showStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public AudiSeat getAudiSeat() {
		return audiSeat;
	}

	public void setAudiSeat(AudiSeat audiSeat) {
		this.audiSeat = audiSeat;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public MovieShow getMovieShow() {
		return movieShow;
	}

	public void setMovieShow(MovieShow movieShow) {
		this.movieShow = movieShow;
	}

	
	
}
