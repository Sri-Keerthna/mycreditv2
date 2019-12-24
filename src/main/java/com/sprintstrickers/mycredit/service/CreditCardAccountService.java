package com.sprintstrickers.mycredit.service;

import com.sprintstrickers.mycredit.dto.CreditCardDetailsResponseDto;
import com.sprintstrickers.mycredit.dto.LoginRequestDto;
import com.sprintstrickers.mycredit.dto.LoginResponseDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardRequestDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardResponseDto;
import com.sprintstrickers.mycredit.exception.InvalidCreditCard;
import com.sprintstrickers.mycredit.exception.InvalidUser;

import javassist.NotFoundException;

/**
 * This is a interface which holds the all the required methods which we are
 * going to implement in business logic
 * 
 * @author akuthota.raghu
 * @since 23-12-2019
 * @version V1.1
 *
 */
public interface CreditCardAccountService {


public	LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidUser;
public VerifyCreditCardResponseDto verifyCreditcard(Integer userId, VerifyCreditCardRequestDto verifyCreditCardRequestDto) throws InvalidCreditCard, InvalidUser;

	CreditCardDetailsResponseDto fetchCreditCardDetails(Integer userId) throws NotFoundException;



}
