package com.tw.bookYourShow.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
public class MovieDTO {
	private int id;
	private String name;

	private String director;

	private String summary;

	private String genre;

	private List<MovieShowDTO> shows = new ArrayList<>();

	public MovieDTO() {

	}

	public MovieDTO(int id, String name, String director, String summary, String genre, List<MovieShowDTO> shows) {
		super();
		this.id = id;
		this.name = name;
		this.director = director;
		this.summary = summary;
		this.genre = genre;
		this.shows = shows;
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", name=" + name + ", director=" + director + ", summary=" + summary + ", genre="
				+ genre + ", shows=" + shows + "]";
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<MovieShowDTO> getShows() {
		return shows;
	}

	public void setShows(List<MovieShowDTO> shows) {
		this.shows = shows;
	}

}
