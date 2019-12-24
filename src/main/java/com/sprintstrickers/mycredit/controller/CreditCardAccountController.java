package com.sprintstrickers.mycredit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.mycredit.dto.LoginRequestDto;
import com.sprintstrickers.mycredit.dto.LoginResponseDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardRequestDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardResponseDto;
import com.sprintstrickers.mycredit.exception.InvalidCreditCard;
import com.sprintstrickers.mycredit.exception.InvalidUser;
import com.sprintstrickers.mycredit.service.CreditCardAccountService;

/**
 * @author Naresh
 *
 */
@RestController
public class CreditCardAccountController {

	@Autowired
	CreditCardAccountService creditCardAccountService;

	/**
	 * @param loginRequestDto
	 * @return loginResponseDto
	 * @throws InvalidUser
	 */
	
	@PostMapping("/users/login")
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidUser {
		LoginResponseDto loginResponseDto=creditCardAccountService.userLogin(loginRequestDto);
		return loginResponseDto;

	}

	/**
	 * @param userId
	 * @param verifyCreditCardRequestDto
	 * @return response
	 * @throws InvalidCreditCard
	 * @throws InvalidUser 
	 */
	@PostMapping("/users/{userId}/accounts/verifies")
	public VerifyCreditCardResponseDto verifyCreditcard(@PathVariable Integer userId,
			@RequestBody VerifyCreditCardRequestDto verifyCreditCardRequestDto) throws InvalidCreditCard, InvalidUser {
		VerifyCreditCardResponseDto response = creditCardAccountService.verifyCreditcard(userId, verifyCreditCardRequestDto);
		return response;

	}
}
