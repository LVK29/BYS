package com.tw.bookYourShow.repository;

import org.springframework.data.repository.CrudRepository;

import com.tw.bookYourShow.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
