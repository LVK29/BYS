package com.tw.bookYourShow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.Nullable;

@JsonInclude(value = Include.NON_NULL)
public class ShowSeatDTO {
	private int id;
	// private Booking booking;

	// private MovieShowDTO show;
	private String showStatus;
	private int audiId;
	private int audiSeatId;
	private double price;
	private int movieShowId;
	@Nullable
	private String showDate;
	// private String showTime;

	public ShowSeatDTO() {

	}

	public ShowSeatDTO(int id, String showStatus, int audiId, int audiSeatId, double price, int movieShowId,
			String showDate) {
		super();
		this.id = id;
		this.showStatus = showStatus;
		this.audiId = audiId;
		this.audiSeatId = audiSeatId;
		this.price = price;
		this.movieShowId = movieShowId;
		this.showDate = showDate;
	}

	@Override
	public String toString() {
		return "ShowSeatDTO [id=" + id + ", showStatus=" + showStatus + ", audiId=" + audiId + ", audiSeatId="
				+ audiSeatId + ", price=" + price + ", movieShowId=" + movieShowId + ", showDate=" + showDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public int getAudiId() {
		return audiId;
	}

	public void setAudiId(int audiId) {
		this.audiId = audiId;
	}

	public int getAudiSeatId() {
		return audiSeatId;
	}

	public void setAudiSeatId(int audiSeatId) {
		this.audiSeatId = audiSeatId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMovieShowId() {
		return movieShowId;
	}

	public void setMovieShowId(int movieShowId) {
		this.movieShowId = movieShowId;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

}
