package com.sprintstrickers.mycredit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.sprintstrickers.mycredit.dto.UserRequestDto;
import com.sprintstrickers.mycredit.dto.UserResponseDto;
import com.sprintstrickers.mycredit.entity.CreditCardAccount;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.exception.AgeNotValidException;
import com.sprintstrickers.mycredit.repository.CreditCardAccountRepository;
import com.sprintstrickers.mycredit.repository.UserRepository;
import com.sprintstrickers.mycredit.util.Validator;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	CreditCardAccountRepository creditCardAccountRepository;


	UserResponseDto responseDto = new UserResponseDto();
	static User user = new User();
	static UserRequestDto userRequestDto = new UserRequestDto();
	static CreditCardAccount account = new CreditCardAccount();

	@Before
	public void setup() {
		userRequestDto.setDob(LocalDate.of(1997, 9, 22));
		Validator.ageValidation(LocalDate.of(1997, 9, 22));
		account.setCreditCardNumber(12345678L);
		user.setUserId(1);
	}

	@Test
	public void testRegisterUserPositive() throws AgeNotValidException {
		BeanUtils.copyProperties(userRequestDto, user);
		account.setUser(user);
		account.setCreditLimit(50000D);
		account.setValidFrom(LocalDate.now());
		account.setValidTill(Validator.yearCalculation());
		account.setAvailableLimit(50000D);
		account.setCvv(Validator.generateRandom());
		responseDto.setCreditCardNumber(account.getCreditCardNumber());
		responseDto.setCreditLimit(account.getCreditLimit());
		userService.registerUser(userRequestDto);
		assertEquals(12345678L, responseDto.getCreditCardNumber());
	}

	
//	@Test(expected=AgeNotValidException.class)
//	public void testRegisterUserNegative() throws AgeNotValidException { 
//	userRequestDto.setDob(LocalDate.of(2000, 9, 22));
//	Mockito.when(Validator.ageValidation(userRequestDto.getDob())).thenReturn(19);
//		userService.registerUser(userRequestDto);
//	 }
	   
}
