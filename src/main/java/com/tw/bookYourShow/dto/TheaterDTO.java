package com.tw.bookYourShow.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
public class TheaterDTO {
	private int id;
	private String name;
	private String address;
	private String rating;
	private Set<TheaterAudiDTO> theaterAudis;
	
	
	public TheaterDTO() {
		
	}
	public TheaterDTO(int id, String name, String address, String rating, Set<TheaterAudiDTO> theaterAudis) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.theaterAudis = theaterAudis;
	}
	@Override
	public String toString() {
		return "TheaterDTO [id=" + id + ", name=" + name + ", address=" + address + ", rating=" + rating
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
	public Set<TheaterAudiDTO> getTheaterAudis() {
		return theaterAudis;
	}
	public void setTheaterAudis(Set<TheaterAudiDTO> theaterAudis) {
		this.theaterAudis = theaterAudis;
	}
	
	
	
	
}
