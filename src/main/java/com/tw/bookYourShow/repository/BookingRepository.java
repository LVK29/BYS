package com.tw.bookYourShow.repository;

import org.springframework.data.repository.CrudRepository;

import com.tw.bookYourShow.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

}
