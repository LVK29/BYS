package com.tw.bookYourShow.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class BookingDTO {
	private int id;
	private String bookingTime;
	private String bookingDate;

	private List<ShowSeatDTO> showSeats = new ArrayList<>();
	private List<Integer> showSeatIds = new ArrayList<>();
	private int bookedByUserId;
	private boolean paid;

	public BookingDTO() {

	}

	public BookingDTO(int id, String bookingTime, String bookingDate, List<ShowSeatDTO> showSeats,
			List<Integer> showSeatIds, int bookedByUserId) {
		super();
		this.id = id;
		this.bookingTime = bookingTime;
		this.bookingDate = bookingDate;
		this.showSeats = showSeats;
		this.showSeatIds = showSeatIds;
		this.bookedByUserId = bookedByUserId;
	}

	@Override
	public String toString() {
		return "BookingDTO [id=" + id + ", bookingTime=" + bookingTime + ", bookingDate=" + bookingDate + ", showSeats="
				+ showSeats + ", showSeatIds=" + showSeatIds + ", bookedByUserId=" + bookedByUserId + "]";
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<ShowSeatDTO> getShowSeats() {
		return showSeats;
	}

	public void setShowSeats(List<ShowSeatDTO> showSeats) {
		this.showSeats = showSeats;
	}

	public List<Integer> getShowSeatIds() {
		return showSeatIds;
	}

	public void setShowSeatIds(List<Integer> showSeatIds) {
		this.showSeatIds = showSeatIds;
	}

	public int getBookedByUserId() {
		return bookedByUserId;
	}

	public void setBookedByUserId(int bookedByUserId) {
		this.bookedByUserId = bookedByUserId;
	}

}
