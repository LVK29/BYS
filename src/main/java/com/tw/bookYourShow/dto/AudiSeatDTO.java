package com.tw.bookYourShow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class AudiSeatDTO {

	// private List<ShowSeatDTO> showSeats = new ArrayList<>();
	private int id;
	private String rowNumber;

	private String seatNumber;

	public AudiSeatDTO() {

	}

	public AudiSeatDTO(int id, String rowNumber, String seatNumber) {
		super();
		this.id = id;
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return "AudiSeatDTO [id=" + id + ", rowNumber=" + rowNumber + ", seatNumber=" + seatNumber + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	// private TheaterAudiDTO theaterAudi;

}
