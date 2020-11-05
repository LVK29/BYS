package com.tw.bookYourShow.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tw.bookYourShow.model.ShowSeat;

public interface ShowSeatRepository extends CrudRepository<ShowSeat, Integer> {
	@Query(value = "from ShowSeat ss JOIN MovieShow ms ON ms.id=ss.movieShow where ss.showStatus='BOOKED' AND ms.id=:movieShowId AND ss.showDate>=:todaysDate")
	public List<ShowSeat> getBookedShowSeatsForMovieShow(@Param("movieShowId") int movieShowId,
			@Param("todaysDate") Date todaysDate);

	@Query(value = "from ShowSeat ss JOIN MovieShow ms ON ms.id=ss.movieShow where ss.showStatus='AVAILABLE' AND ss.showDate>=:todaysDate")
	public List<ShowSeat> getAvailableShowSeatsForMovieShow(@Param("todaysDate") Date todaysDate);
	// SELECT * FROM show_seat as ss JOIN movie_show as ms ON ms.id=ss.movie_show_id
	// WHERE ss.show_status=0 AND ms.id=44;

}
