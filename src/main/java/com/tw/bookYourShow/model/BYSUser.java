package com.tw.bookYourShow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BYSUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String email;
	private String saltedHashedPassword;
	private String name;
	private String phoneNumber;
	private String dateOfBirth;
	private UserType userType;
	private boolean active;
	private String activationCode;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "bookedBy")
	private List<Booking> bookings = new ArrayList<>();

	public BYSUser() {

	}

	public BYSUser(int id, String email, String saltedHashedPassword, String name, String phoneNumber,
			String dateOfBirth, UserType userType, boolean active) {
		super();
		this.id = id;
		this.email = email;
		this.saltedHashedPassword = saltedHashedPassword;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
		this.active = active;

	}

	@Override
	public String toString() {
		return "BYSUser [id=" + id + ", email=" + email + ", saltedHashedPassword=" + saltedHashedPassword + ", name="
				+ name + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", userType=" + userType
				+ "]";
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	 

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
