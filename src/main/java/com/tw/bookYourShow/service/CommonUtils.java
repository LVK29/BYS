package com.tw.bookYourShow.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {


	public Date getTimeFromString(String strTime) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date d = dateFormat.parse(strTime);
		return d;
	}

	public Date getDateFromString(String strDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date d = dateFormat.parse(strDate);
		return d;
	}

	public Converter<String, Date> toStringTime = new AbstractConverter<String, Date>() {
		@Override
		public Date convert(String source) {
			try {
				return getTimeFromString(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
	};

	public Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
		@Override
		public Date convert(String source) {
			try {
				return getDateFromString(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
	};

	protected String generateRandomAlphaNumericValue() {
		String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // length of the random string.
			int index = (int) (rnd.nextFloat() * allChars.length());
			salt.append(allChars.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
