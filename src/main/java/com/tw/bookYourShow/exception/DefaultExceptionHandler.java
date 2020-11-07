package com.tw.bookYourShow.exception;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tw.bookYourShow.model.ErrorMessage;

/*
 * Class is for exception handling 
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	private static final long serialVersionUID = -8891959764862530715L;

	@ExceptionHandler(BYSException.class)
	public ResponseEntity<ErrorMessage> handleBYSExceptionException(BYSException ex, WebRequest webRequest) {
		// create payload containing exception details
		ErrorMessage errMsg = new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		log.error(errMsg.toString());
		// return response entry
		return new ResponseEntity<ErrorMessage>(errMsg, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}
}