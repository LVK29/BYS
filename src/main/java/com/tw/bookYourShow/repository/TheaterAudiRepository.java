package com.tw.bookYourShow.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tw.bookYourShow.model.TheaterAudi;

public interface TheaterAudiRepository extends CrudRepository<TheaterAudi, Integer> {

//	@Query("from TheaterAudi ta join t.category c where c.name=:categoryName")
//	public Iterable<TheaterAudi> findByAudi(@Param("categoryName") String categoryName);

}
