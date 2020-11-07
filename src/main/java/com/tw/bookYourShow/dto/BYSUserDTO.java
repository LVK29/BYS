package com.tw.bookYourShow.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
public class BYSUserDTO {
	private int id;
	private String email;
	private String saltedHashedPassword;
	private String name;
	private String phoneNumber;
	private String dateOfBirth;
	private String userType;
	private List<BookingDTO> showsBooked = new ArrayList<>();

	public BYSUserDTO() {

	}

	public BYSUserDTO(int id, String email, String saltedHashedPassword, String name, String phoneNumber,
			String dateOfBirth, String userType) {
		super();
		this.id = id;
		this.email = email;
		this.saltedHashedPassword = saltedHashedPassword;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "BYSUserDTO [id=" + id + ", email=" + email + ", saltedHashedPassword=" + saltedHashedPassword
				+ ", name=" + name + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", userType="
				+ userType + "]";
	}

	public List<BookingDTO> getShowsBooked() {
		return showsBooked;
	}

	public void setShowsBooked(List<BookingDTO> showsBooked) {
		this.showsBooked = showsBooked;
	}

	public int getId() {
		return id;
	}

	 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSaltedHashedPassword() {
		return saltedHashedPassword;
	}

	public void setSaltedHashedPassword(String saltedHashedPassword) {
		this.saltedHashedPassword = saltedHashedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
