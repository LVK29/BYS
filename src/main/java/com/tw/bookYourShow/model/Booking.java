package com.tw.bookYourShow.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Date bookingTimeStamp;

	private Double totalPrice;

	private boolean paid = false;

	@OneToMany(mappedBy = "booking")
	private List<ShowSeat> showSeats = new ArrayList<>();
	@ManyToOne
	private BYSUser bookedBy;

	public Booking() {

	}

	public Booking(int id, Date bookingTimeStamp, Double totalPrice, List<ShowSeat> showSeats, BYSUser bookedBy) {
		super();
		this.id = id;
		this.bookingTimeStamp = bookingTimeStamp;
		this.totalPrice = totalPrice;
		this.showSeats = showSeats;
		this.bookedBy = bookedBy;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBookingTimeStamp() {
		return bookingTimeStamp;
	}

	public void setBookingTimeStamp(Date bookingTimeStamp) {
		this.bookingTimeStamp = bookingTimeStamp;
	}

	public List<ShowSeat> getShowSeats() {
		return showSeats;
	}

	public void setShowSeats(List<ShowSeat> showSeats) {
		this.showSeats = showSeats;
	}

	public BYSUser getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(BYSUser bookedBy) {
		this.bookedBy = bookedBy;
	}

}
