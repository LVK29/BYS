package com.tw.bookYourShow.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tw.bookYourShow.model.BYSUser;

public interface BYSUserRepository extends CrudRepository<BYSUser, Integer> {
	Optional<BYSUser> findByEmail(String email);
}
