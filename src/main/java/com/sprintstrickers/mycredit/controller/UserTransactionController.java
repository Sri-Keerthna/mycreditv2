package com.sprintstrickers.mycredit.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.mycredit.dto.UserTransactionResponseDto;
import com.sprintstrickers.mycredit.service.UserTransactionService;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * UserTransaction Controller - we can implement the transaction operations like
 * get the transactions for monthly and last five transactions as of now are
 * implementing only for monthly wise transactions
 * 
 * 
 * @author akuthota.raghu
 * @version V1.1
 * @created date - 23-12-2019
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserTransactionController {

	@Autowired
	UserTransactionService userTransactionService;

	/**
	 * This method is used for to get all the transactions for the given month, the
	 * end user will going to provide user id, month and year
	 * 
	 * @param userId
	 * @param month
	 * @param year
	 * @return ResponseEntity<UserTransactionResponseDto> contain the transaction
	 *         details
	 * @throws NotFoundException
	 */
	@GetMapping("/{userId}/transactions")
	public ResponseEntity<UserTransactionResponseDto> getUserTransactionsByMonth(@PathVariable Integer userId,
			@RequestParam("month") Integer month, @RequestParam("year") Integer year) throws NotFoundException {

		log.info("getting user transaction by monthly wise");

		UserTransactionResponseDto userTransactionResponseDto = userTransactionService
				.findUserTransactionsByMonth(userId, month, year);

		if (StringUtils.isNotEmpty(userTransactionResponseDto.getMessage())) {
			userTransactionResponseDto.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(userTransactionResponseDto, HttpStatus.OK);
		} else {
			userTransactionResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(userTransactionResponseDto, HttpStatus.BAD_REQUEST);
		}
	}

}
