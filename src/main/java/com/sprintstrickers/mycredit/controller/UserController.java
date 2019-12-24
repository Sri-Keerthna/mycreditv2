package com.sprintstrickers.mycredit.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.mycredit.dto.UserRequestDto;
import com.sprintstrickers.mycredit.dto.UserResponseDto;
import com.sprintstrickers.mycredit.exception.AgeException;
import com.sprintstrickers.mycredit.service.UserService;
import com.sprintstrickers.mycredit.util.ApiConstant;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/users")
public class UserController {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23 In this method user will register their details for credit
	 *        card which are taken through userRequestDto and stored in database
	 * @param userRequestDto which has user details that are given as input
	 * @return userResponseDto where message with account number and credited amount
	 *         will be displayed.
	 * @throws AgeException if age is less than 21 it will throw an error
	 */
	@PostMapping
	public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto)
			throws AgeException {
		logger.debug("registration success");
		UserResponseDto responseDto = userService.registerUser(userRequestDto);
		responseDto.setMessage(ApiConstant.RESPONSE_MESSAGE);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
