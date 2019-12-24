package com.sprintstrickers.mycredit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sprintstrickers.mycredit.dto.VerifyCreditCardRequestDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardResponseDto;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.exception.InvalidCreditCard;
import com.sprintstrickers.mycredit.exception.InvalidUser;
import com.sprintstrickers.mycredit.service.CreditCardAccountService;
import com.sprintstrickers.mycredit.util.MyCreditConstants;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CrediCardAccountControllerTest {

	@InjectMocks
	CreditCardAccountController creditCardAccountController;
	@Mock
	CreditCardAccountService creditCardAccountService;
	VerifyCreditCardRequestDto verifyCreditCardRequestDto=null;
	VerifyCreditCardResponseDto verifyCreditCardResponseDto=null;
	User user=null;
	@Before
	public void setup() {
		verifyCreditCardRequestDto=new VerifyCreditCardRequestDto();
		verifyCreditCardRequestDto.setAccountHolderName("Naresh");
		verifyCreditCardRequestDto.setCreditCardNo(2334678924562893L);
		verifyCreditCardRequestDto.setCvv(233);
		verifyCreditCardRequestDto.setValidFrom(LocalDate.parse("2019-12-22"));
		verifyCreditCardRequestDto.setValidTill(LocalDate.parse("2022-12-23"));
		verifyCreditCardResponseDto=new VerifyCreditCardResponseDto();
		verifyCreditCardResponseDto.setMessage(MyCreditConstants.CARD_VERIFY);
		verifyCreditCardResponseDto.setStatusCode(200);
		user=new User();
		user.setUserId(200);
		
	}
	@Test
	public void testVerifyCreditcard() throws InvalidCreditCard, InvalidUser {
		Mockito.when(creditCardAccountService.verifyCreditcard(200, verifyCreditCardRequestDto)).thenReturn(verifyCreditCardResponseDto);
		VerifyCreditCardResponseDto response=creditCardAccountController.verifyCreditcard(200, verifyCreditCardRequestDto);
	assertEquals(MyCreditConstants.CARD_VERIFY, response.getMessage());
	}

}
