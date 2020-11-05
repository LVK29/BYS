package com.tw.bookYourShow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.BookingDTO;
import com.tw.bookYourShow.exception.BYSException;
import com.tw.bookYourShow.model.BYSUser;
import com.tw.bookYourShow.model.Booking;
import com.tw.bookYourShow.model.ShowSeat;
import com.tw.bookYourShow.model.ShowSeatStatusType;
import com.tw.bookYourShow.repository.BYSUserRepository;
import com.tw.bookYourShow.repository.BookingRepository;
import com.tw.bookYourShow.repository.MovieRepository;
import com.tw.bookYourShow.repository.ShowSeatRepository;

@Service
public class BookingService {

	Logger log = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	ShowSeatRepository showSeatRepository;

	@Autowired
	BYSUserService userService;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	EmailService emailService;

	public void createBooking(List<Integer> bookingShowSeatIds, String userEmail) {

		Booking booking = new Booking();
		BYSUser user = userService.getBYSUserByEmail(userEmail);
		booking.setBookedBy(user);
		booking.setBookingTimeStamp(new Date());
		Iterable<ShowSeat> showShows = showSeatRepository.findAllById(bookingShowSeatIds);
		List<ShowSeat> showSeatsList = new ArrayList<ShowSeat>();
		Double totalPrice = 0.0;
		for (ShowSeat showSeat : showShows) {
			showSeat.setShowStatus(ShowSeatStatusType.BOOKED);
			showSeat.setBooking(booking);
			showSeatsList.add(showSeat);
			totalPrice += showSeat.getPrice();
		}
		booking.setTotalPrice(totalPrice);
		booking.setShowSeats(showSeatsList);

		bookingRepository.save(booking);
		log.info(booking.getBookedBy().getEmail() + " created booking with bookingId " + booking.getId());
//SENDING MAIL TO CUSOTOMER ABOUT BOOKING DETAILS WOULD BE NICE TO HAVE

//		String emailSubject = "Book Your Show: Booking Seats";
//		String emailBody = "Following are the booking details: " + System.lineSeparator() + "Booking is"
//				+ booking.getId() + System.lineSeparator() + "Movie name"
//				+ booking.getShowSeats().get(0).getMovieShow().getMovie().getName() + System.lineSeparator() + "Seat Id"
//				+ booking.getShowSeats() + System.lineSeparator() + "Total price" + booking.getTotalPrice();
//		
//		String emailBody = "Following are the booking seatId: ";
//		emailService.sendMail("lvkartikeyan@gmail.com", emailSubject, emailBody);
	}

	public Booking getBookingById(int bookingId) {
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if (booking.isEmpty()) {
			 
			throw new BYSException("BookingId " + bookingId + " not found");
		}
		return booking.get();
	}

	public void deleteBooking(int bookingId) {
		Booking booking = getBookingById(bookingId);
		List<ShowSeat> showSeats = booking.getShowSeats();
		for (ShowSeat showSeat : showSeats) {
			showSeat.setShowStatus(ShowSeatStatusType.AVAILABLE);
			showSeat.setBooking(null);
			log.debug("Setting booking as null for showSeat " + showSeat.getId());

		}

		showSeatRepository.saveAll(showSeats);
		booking.setShowSeats(null);
		bookingRepository.delete(booking);
		log.info("Deleted booking : " + bookingId);
	}

	public void confirmBookingPayment(int bookingId) {
		Booking booking = getBookingById(bookingId);
		booking.setPaid(true);
		bookingRepository.save(booking);
		log.info(booking.getBookedBy().getEmail() + " has paid for bookingId " + booking.getId());
	}

}
