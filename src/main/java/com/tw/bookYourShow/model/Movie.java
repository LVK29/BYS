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
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String director;

	private String summary;

	@OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.ALL})
	private List<MovieShow> movieShows = new ArrayList<>();

	private String genre;

	public Movie() {

	}

	public Movie(int id, String name, String director, String summary, List<MovieShow> movieShows, String genre) {
		super();
		this.id = id;
		this.name = name;
		this.director = director;
		this.summary = summary;
		this.movieShows = movieShows;
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", director=" + director + ", summary=" + summary
				+ ", movieShows=" + movieShows + ", genre=" + genre + "]";
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

	public List<MovieShow> getMovieShows() {
		return movieShows;
	}

	public void setMovieShows(List<MovieShow> movieShows) {
		this.movieShows = movieShows;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
