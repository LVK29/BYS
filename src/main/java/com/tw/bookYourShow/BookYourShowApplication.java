package com.tw.bookYourShow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class BookYourShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookYourShowApplication.class, args);
		System.out.println("Server started");
	}

	
}
