package com.tw.bookYourShow.facade;

import java.text.SimpleDateFormat;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tw.bookYourShow.controller.MovieController;
import com.tw.bookYourShow.dto.BookingDTO;
import com.tw.bookYourShow.model.Booking;
import com.tw.bookYourShow.service.BookingService;
import com.tw.bookYourShow.service.CommonUtils;

/**
 * Facade layer for converting Booking to BookingDTO for various CRUD api calls
 * 
 * @author LVK
 *
 */
@Component
public class BookingFacade {

	Logger log = LoggerFactory.getLogger(BookingFacade.class);

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	BookingService bookingService;

	@Autowired
	CommonUtils commonUtils;

	public void createBooking(List<Integer> bookingShowSeatIds, String userEmail) {
		bookingService.createBooking(bookingShowSeatIds, userEmail);
	}

	public BookingDTO getBooking(int bookingId) {
		Booking booking = bookingService.getBookingById(bookingId);
		return convertToBookingDto(booking);
	}

	public void deleteBooking(int bookingId) {
		bookingService.deleteBooking(bookingId);
	}

	public void confirmBookingPayment(int bookingId) {
		bookingService.confirmBookingPayment(bookingId);
	}

	protected BookingDTO convertToBookingDto(Booking booking) {
		BookingDTO bookingDto = modelMapper.map(booking, BookingDTO.class);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		bookingDto.setBookingDate(dateFormat.format(booking.getBookingTimeStamp()));
		bookingDto.setBookingTime(timeFormat.format(booking.getBookingTimeStamp()));
		return bookingDto;
	}

	protected Booking convertToBookingEntity(BookingDTO bookingDto) {
		Booking booking = modelMapper.map(bookingDto, Booking.class);
		return booking;
	}
}
