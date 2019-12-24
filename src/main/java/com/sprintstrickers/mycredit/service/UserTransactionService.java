package com.sprintstrickers.mycredit.service;

import com.sprintstrickers.mycredit.dto.UserTransactionResponseDto;

import javassist.NotFoundException;

/**
 * This UserTransactionService is used to for the implementation logic of User
 * transactions, as of now we are implanting the business logic for get the
 * transactions for monthly wise
 * 
 * @author akuthota.raghu
 * @since 23-12-2019
 * @version V1.1
 */
public interface UserTransactionService {


	// this method is for getting the transactions monthly wise
	public UserTransactionResponseDto findUserTransactionsByMonth(Integer userAccountId, Integer month, Integer year) throws NotFoundException;
}
