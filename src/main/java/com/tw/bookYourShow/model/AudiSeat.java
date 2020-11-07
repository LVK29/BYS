package com.tw.bookYourShow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AudiSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	private String rowNumber;

	private String seatNumber;

	@ManyToOne
	private TheaterAudi theaterAudi;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "audiSeat", orphanRemoval = true)
	private List<ShowSeat> showSeats = new ArrayList<>();

	public AudiSeat() {

	}

	public AudiSeat(int id, String rowNumber, String seatNumber, TheaterAudi theaterAudi, List<ShowSeat> showSeats) {
		super();
		this.id = id;
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
		this.theaterAudi = theaterAudi;
		this.showSeats = showSeats;
	}

	@Override
	public String toString() {
		return "AudiSeat [id=" + id + ", rowNumber=" + rowNumber + ", seatNumber=" + seatNumber + ", theaterAudi="
				+ theaterAudi + ", showSeats=" + showSeats + "]";
	}

	public int getId() {
		return id;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
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