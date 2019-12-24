package com.sprintstrickers.mycredit.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintstrickers.mycredit.controller.UserController;
import com.sprintstrickers.mycredit.dto.UserRequestDto;
import com.sprintstrickers.mycredit.dto.UserResponseDto;
import com.sprintstrickers.mycredit.entity.CreditCardAccount;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.exception.AgeException;
import com.sprintstrickers.mycredit.repository.CreditCardAccountRepository;
import com.sprintstrickers.mycredit.repository.UserRepository;
import com.sprintstrickers.mycredit.util.ApiConstant;
import com.sprintstrickers.mycredit.util.Validator;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CreditCardAccountRepository creditCardAccountRepository;

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23 In this method user will register their details for credit
	 *        card which are taken through userRequestDto and stored in database
	 * @param userRequestDto which has user details that are given as input
	 * @return userResponseDto where message with account number and credited amount
	 *         will be displayed.
	 * @throws AgeException if age is less than 21 it will throw an error
	 */
	@Transactional
	public UserResponseDto registerUser(UserRequestDto userRequestDto) throws AgeException {
		UserResponseDto responseDto = new UserResponseDto();
		User user = new User();
		if (Validator.ageValidation(userRequestDto.getDob()) < 21) {
			logger.error("Age is less than 21");
			throw new AgeException(ApiConstant.AGE_VALIDATION);
		} else {
			BeanUtils.copyProperties(userRequestDto, user);
			userRepository.save(user);
			CreditCardAccount account = new CreditCardAccount();
			account.setUser(user);
			account.setCreditLimit(50000D);
			account.setValidFrom(LocalDate.now());
			account.setValidTill(Validator.yearCalculation());
			account.setAvailableLimit(50000D);
			account.setCvv(Validator.generateRandom());
			creditCardAccountRepository.save(account);
			responseDto.setCreditCardNumber(account.getCreditCardNumber());
			responseDto.setCreditLimit(account.getCreditLimit());
			return responseDto;
		}

	}
}
