package com.tw.bookYourShow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tw.bookYourShow.model.Theater;

public interface TheaterRepository extends CrudRepository<Theater, Integer> {
// get theaters in which there are shows and show dates are > today
	@Query(value = "from Theater t JOIN TheaterAudi ta ON t.id=ta.theater JOIN MovieShow ms ON ms.theaterAudi=ta.id GROUP BY t.id, ta.id ")
	public List<Theater> getAllTheatersForCustomer();

}
