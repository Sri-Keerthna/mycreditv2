package com.sprintstrickers.mycredit.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.mycredit.dto.CreditCardDetailsResponseDto;
import com.sprintstrickers.mycredit.service.CreditCardAccountService;

import javassist.NotFoundException;

/**
 * CreditCardController code controller, In this Controller we can able get the
 * credit card details actions of get list of credit cards details.
 * 
 * @author akuthota.raghu
 * @since 23-12-2019
 * @version V1.1
 * 
 */
@RestController
@RequestMapping("/creditcards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CreditCardController {

	@Autowired
	CreditCardAccountService creditCardAccountService;

	public static final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

	/**
	 * Get the credit card details based on the user enter the user id.
	 * 
	 * @return CreditCardDetailsResponseDto values we can set the detail of the
	 *         credit card number, validFrom, validTill, CVV success message and
	 *         status code.
	 * @throws NotFoundException
	 */

	@GetMapping("/users/{userId}/details")
	public ResponseEntity<CreditCardDetailsResponseDto> getCreditCardDetails(@PathVariable Integer userId)
			throws NotFoundException {

		logger.info("fetch creadit card details...");

		CreditCardDetailsResponseDto response = creditCardAccountService.fetchCreditCardDetails(userId);

		if (StringUtils.isNotEmpty(response.getMessage())) {
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
