package com.sprintstrickers.mycredit.util;

import java.time.LocalDate;

import java.time.Period;
import java.time.ZoneId;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Validator {

	private Validator() {
	}

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23 Using this current year this method will calculate and give
	 *        year after 10 years
	 * @return Year is calculated and sent in localdate format
	 */
	public static LocalDate yearCalculation() {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.YEAR, 10);
		calender.add(Calendar.DATE, 10);
		calender.add(Calendar.MONTH, 10);
		calender.getTime();
		Date input = calender.getTime();
		return input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23
	 * @return return an auto increment value
	 */
	public static Integer generateRandom() {

		AtomicInteger value = new AtomicInteger(100);
		return value.getAndIncrement();
	}

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23 This method will calculate age from given year
	 * @return age with integer
	 */

	public static Integer ageValidation(LocalDate date) {
		LocalDate today = LocalDate.now();
		LocalDate dob = date;
		return Period.between(dob, today).getYears();
	}
}
