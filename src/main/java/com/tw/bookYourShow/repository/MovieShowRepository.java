package com.tw.bookYourShow.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tw.bookYourShow.model.MovieShow;
import com.tw.bookYourShow.model.TheaterAudi;

public interface MovieShowRepository extends CrudRepository<MovieShow, Integer> {
	@Query(value = "from MovieShow where theater_audi_id=:theaterAudiId AND timing_from<=:timingFrom AND timing_to>=:timingTo")
	public List<MovieShow> getMovieShowsBetweenDates(@Param("timingFrom") Date timingFrom,
			@Param("timingTo") Date timingTo, @Param("theaterAudiId") int theaterAudiId);

	@Query(value = "from MovieShow ms JOIN ShowSeat ss ON ms.id=ss.movieShow JOIN TheaterAudi ta ON ta.id=ms.theaterAudi WHERE ss.showStatus='BOOKED' AND ta.id=:theaterAudiId")
	public List<MovieShow> getMovieShowsForWithBookedSeats(@Param("theaterAudiId") int theaterAudiId);

	List<MovieShow> findByTheaterAudi(TheaterAudi theaterAudi);
}
