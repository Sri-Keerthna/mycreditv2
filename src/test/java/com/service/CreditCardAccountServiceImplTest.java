package com.sprintstrickers.mycredit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sprintstrickers.mycredit.dto.VerifyCreditCardRequestDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardResponseDto;
import com.sprintstrickers.mycredit.entity.CreditCardAccount;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.exception.InvalidCreditCard;
import com.sprintstrickers.mycredit.exception.InvalidUser;
import com.sprintstrickers.mycredit.repository.CreditCardAccountRepository;
import com.sprintstrickers.mycredit.repository.UserRepository;
import com.sprintstrickers.mycredit.util.MyCreditConstants;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CreditCardAccountServiceImplTest {
	@InjectMocks
	CreditCardAccountServiceImpl creditCardAccountServiceImpl;
	@Mock
	UserRepository userRepository;
	@Mock
	CreditCardAccountRepository creditCardAccountRepository;
	VerifyCreditCardRequestDto verifyCreditCardRequestDto=null;
	VerifyCreditCardRequestDto verifyCreditCardRequestDto1=null;
	VerifyCreditCardResponseDto verifyCreditCardResponseDto=null;
	User user=null;
	
	CreditCardAccount creditCardAccount=null;

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
		user.setUserId(250);
		user.setUserName("Naresh");
		creditCardAccount=new CreditCardAccount();
		creditCardAccount.setUser(user);
		creditCardAccount.setCreditCardNumber(2334678924562893L);
		creditCardAccount.setCvv(233);
		creditCardAccount.setValidFrom(LocalDate.parse("2019-12-22"));
		creditCardAccount.setValidTill(LocalDate.parse("2022-12-23"));
		verifyCreditCardRequestDto1=new VerifyCreditCardRequestDto();
		verifyCreditCardRequestDto1.setCreditCardNo(4356223567899066L);
		
		
	}
	@Test
	public void testVerifyCreditcard() throws InvalidCreditCard, InvalidUser {
		Mockito.when(creditCardAccountRepository.findByUserUserId(250)).thenReturn(Optional.of(creditCardAccount));
		VerifyCreditCardResponseDto response=creditCardAccountServiceImpl.verifyCreditcard(250, verifyCreditCardRequestDto);
	assertEquals(MyCreditConstants.CARD_VERIFY, response.getMessage());
	}
	@Test(expected = InvalidCreditCard.class)
	public void testVerifyCreditCardForInvalidCard()throws InvalidCreditCard, InvalidUser  {
		Mockito.when(creditCardAccountRepository.findByUserUserId(250)).thenReturn(Optional.of(creditCardAccount));
		creditCardAccountServiceImpl.verifyCreditcard(250, verifyCreditCardRequestDto1);
	}
	@Test(expected = InvalidUser.class)
	public void testVerifyCreditCardForInvalidUser()throws InvalidCreditCard, InvalidUser  {
		Optional<CreditCardAccount> nullUser=Optional.ofNullable(null);
		Mockito.when(creditCardAccountRepository.findByUserUserId(300)).thenReturn(nullUser);
		creditCardAccountServiceImpl.verifyCreditcard(300, verifyCreditCardRequestDto);
	}
}
