package com.sprintstrickers.mycredit.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintstrickers.mycredit.dto.UserTransactionRequestDto;
import com.sprintstrickers.mycredit.dto.UserTransactionResponseDto;
import com.sprintstrickers.mycredit.entity.UserTransaction;
import com.sprintstrickers.mycredit.repository.UserRepository;
import com.sprintstrickers.mycredit.repository.UserTransactionRepository;
import com.sprintstrickers.mycredit.util.MyCreditConstants;

import javassist.NotFoundException;

/**
 * @description UserTransactionServiceImpl - We are using this class to
 *              implement business logic to get the transactions by monthly wise
 * 
 * @author akuthota.raghu
 * @since 23-12-2019
 *
 */

@Service
@Transactional
public class UserTransactionServiceImpl implements UserTransactionService {

	public static final Logger logger = LoggerFactory.getLogger(UserTransactionServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTransactionRepository userTransactionRepository;

	/**
	 * this method is to get entire month transactions to respective userAccountId
	 * 
	 * @param userAccountId Integer, month Integer, year Integer - providing
	 *                      required user id, month and year to search monthly
	 *                      transactions
	 * @return UserTransactionResponseDto object contain with status and message
	 *         along with response object
	 * @throws NotFoundException
	 */

	@Override
	public UserTransactionResponseDto findUserTransactionsByMonth(Integer userId, Integer month, Integer year)
			throws NotFoundException {

		logger.info("Getting monthly transactions fot the given user ");

		UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();

		String inputMonth = String.format("%02d", month);

		LocalDate startDate = LocalDate.parse(year + "-" + inputMonth + "-" + MyCreditConstants.MONTH_START_DATE);

		Integer lastDayOfMonth = YearMonth.of(year, month).atEndOfMonth().getDayOfMonth();

		LocalDate endDate = LocalDate.parse(year + "-" + inputMonth + "-" + lastDayOfMonth);

		List<UserTransaction> userTransactionResponse = userTransactionRepository
				.findByUserUserIdAndTransactionDateBetween(userId, startDate, endDate);

		if (!userTransactionResponse.isEmpty()) {
			List<UserTransactionRequestDto> userTransactionRequestDto = userTransactionResponse.stream()
					.map(this::convertEntityToDto).collect(Collectors.toList());

			userTransactionResponseDto.setMessage(MyCreditConstants.SUCCESS);
			userTransactionResponseDto.setTransactionDetails(userTransactionRequestDto);
		} else {
			logger.error("No transaction details found the given user for the given period");
			throw new NotFoundException(MyCreditConstants.NO_TRANSACTION_FOUND);
		}

		return userTransactionResponseDto;
	}

	/**
	 * This is private method it is used for UserTransaction Entity to
	 * UserTransactionRequestDto converts - written in single separate method for
	 * code re-use
	 * 
	 * @param userTransactionResponse object
	 * @return UserTransactionRequestDto return the object
	 */

	private UserTransactionRequestDto convertEntityToDto(UserTransaction userTransactionResponse) {
		UserTransactionRequestDto userTransactionRequestDto = new UserTransactionRequestDto();
		BeanUtils.copyProperties(userTransactionResponse, userTransactionRequestDto);
		return userTransactionRequestDto;
	}

}
