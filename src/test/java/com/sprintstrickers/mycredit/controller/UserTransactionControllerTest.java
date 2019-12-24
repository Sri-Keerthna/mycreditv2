package com.sprintstrickers.mycredit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprintstrickers.mycredit.dto.ResponseDto;
import com.sprintstrickers.mycredit.dto.UserTransactionResponseDto;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.entity.UserTransaction;
import com.sprintstrickers.mycredit.service.UserTransactionService;
import com.sprintstrickers.mycredit.util.MyCreditConstants;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTransactionControllerTest {

	@InjectMocks
	UserTransactionController userTransactionController;

	@Mock
	UserTransactionService userTransactionService;

	ResponseDto fundTransferResponseDto = new ResponseDto();

	UserTransaction userTransaction = new UserTransaction();
	UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		userTransaction.setTransactionId(1);
		userTransaction.setTransactionDate(LocalDate.now());

		User user = new User();
		user.setUserId(1);
		user.setIncome(28000L);
		user.setUserName("Raghu");

		userTransaction.setUser(user);

	}

	@Test
	public void testFindUserTransactionsByMonth() throws NotFoundException {

		userTransactionResponseDto.setMessage(MyCreditConstants.SUCCESS);
		userTransactionResponseDto.setStatusCode(MyCreditConstants.SUCCESS_CODE);

		when(userTransactionService.findUserTransactionsByMonth(userTransaction.getUser().getUserId(), 12, 2019))
				.thenReturn(userTransactionResponseDto);

		when(userTransactionService.findUserTransactionsByMonth(userTransaction.getUser().getUserId(), 12, 2019))
				.thenReturn(userTransactionResponseDto);

		ResponseEntity<UserTransactionResponseDto> response = userTransactionController
				.getUserTransactionsByMonth(userTransaction.getUser().getUserId(), 12, 2019);

		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testFindUserTransactionsByMonthForFailure() throws NotFoundException {
		userTransactionResponseDto.setMessage(MyCreditConstants.FAILURE);
		userTransactionResponseDto.setStatusCode(400);

		when(userTransactionService.findUserTransactionsByMonth(5, 02, 2019))
				.thenReturn(userTransactionResponseDto);

		ResponseEntity<UserTransactionResponseDto> response = userTransactionController
				.getUserTransactionsByMonth(5, 02, 2019);
		assertEquals("FAILURE", response.getBody().getMessage());
	}

}
