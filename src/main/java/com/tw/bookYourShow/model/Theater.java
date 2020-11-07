package com.tw.bookYourShow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Theater {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String address;
	private String rating;
	 

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "theater")
	private List<TheaterAudi> theaterAudis = new ArrayList<>();

	public Theater() {

	}

	public Theater(int id, String name, String address, String rating, List<TheaterAudi> theaterAudis) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.theaterAudis = theaterAudis;
	}

	@Override
	public String toString() {
		return "Theater [id=" + id + ", name=" + name + ", address=" + address + ", rating=" + rating
				+ ", theaterAudis=" + theaterAudis + "]";
	}

	public int getId() {
		return id;
	}

 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<TheaterAudi> getTheaterAudis() {
		return theaterAudis;
	}

	public void setTheaterAudis(List<TheaterAudi> theaterAudis) {
		this.theaterAudis = theaterAudis;
	}

}
