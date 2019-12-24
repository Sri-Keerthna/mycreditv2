package com.sprintstrickers.mycredit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.sprintstrickers.mycredit.dto.UserRequestDto;
import com.sprintstrickers.mycredit.dto.UserResponseDto;
import com.sprintstrickers.mycredit.exception.AgeNotValidException;
import com.sprintstrickers.mycredit.service.UserService;
import com.sprintstrickers.mycredit.util.ApiConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	static UserResponseDto responseDto = new UserResponseDto(); 
	static UserRequestDto userRequestDto = new UserRequestDto(); 
	
	@Before public void setup() {
		responseDto.setCreditCardNumber(12345678L);
		responseDto.setCreditLimit(40000D);
		responseDto.setMessage(ApiConstant.RESPONSE_MESSAGE);
		
		userRequestDto.setUserName("abc");
	}

	@Test
	public void testregisterUserPositive() throws AgeNotValidException {
		logger.debug("registration success");
		Mockito.when(userService.registerUser(userRequestDto)).thenReturn(responseDto);
		HttpStatus result = userController.registerUser(userRequestDto).getStatusCode();
		assertEquals(HttpStatus.OK, result);
	}
}
