package com.sprintstrickers.mycredit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintstrickers.mycredit.dto.CreditCardDetailsDto;
import com.sprintstrickers.mycredit.dto.CreditCardDetailsResponseDto;
import com.sprintstrickers.mycredit.dto.LoginRequestDto;
import com.sprintstrickers.mycredit.dto.LoginResponseDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardRequestDto;
import com.sprintstrickers.mycredit.dto.VerifyCreditCardResponseDto;
import com.sprintstrickers.mycredit.entity.CreditCardAccount;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.exception.InvalidCreditCard;
import com.sprintstrickers.mycredit.exception.InvalidUser;
import com.sprintstrickers.mycredit.repository.CreditCardAccountRepository;
import com.sprintstrickers.mycredit.repository.UserRepository;
import com.sprintstrickers.mycredit.util.MyCreditConstants;

import javassist.NotFoundException;

/**
 * @author Naresh
 *
 */
@Service
public class CreditCardAccountServiceImpl implements CreditCardAccountService {

	public static final Logger logger = LoggerFactory.getLogger(CreditCardAccountServiceImpl.class);

	@Autowired
	CreditCardAccountRepository creditCardAccountRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public CreditCardDetailsResponseDto fetchCreditCardDetails(Integer userId) throws NotFoundException {

		CreditCardDetailsResponseDto creditCardDetailsResponseDto = new CreditCardDetailsResponseDto();

		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			List<CreditCardAccount> creditCardAccounts = creditCardAccountRepository.findAllByUserUserId(userId);

			List<CreditCardDetailsDto> payeeAccountDto = creditCardAccounts.stream().map(this::convertEntityToDto)
					.collect(Collectors.toList());

			creditCardDetailsResponseDto.setCreditCardDetails(payeeAccountDto);
			creditCardDetailsResponseDto.setMessage(MyCreditConstants.SUCCESS);

		} else {
			logger.error("No Credit card details found");
			throw new NotFoundException(MyCreditConstants.NO_CREDIT_CARDS_FOUND);
		}
		return creditCardDetailsResponseDto;
	}

	private CreditCardDetailsDto convertEntityToDto(CreditCardAccount creditCardAccount) {
		CreditCardDetailsDto creditCardDetailsDto = new CreditCardDetailsDto();
		BeanUtils.copyProperties(creditCardAccount, creditCardDetailsDto);
		creditCardDetailsDto.setCreditHolderName(creditCardAccount.getUser().getUserName());
		return creditCardDetailsDto;

	}

	/**
	 *
	 */
	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidUser {
		logger.info("Entering into the userLogin method in CreditCardAccountServiceImpl");
		LoginResponseDto loginResponseDto = null;
		Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getUserEmail());
		if (optionalUser.isPresent()) {
			if (optionalUser.get().getEmail().equals(loginRequestDto.getUserEmail())
					&& optionalUser.get().getPassword().equals(loginRequestDto.getPassword())) {
				loginResponseDto = new LoginResponseDto();
				loginResponseDto.setMessage(MyCreditConstants.SUCCESS);
				loginResponseDto.setStatusCode(MyCreditConstants.SUCCESS_CODE);

			} else {
				logger.error(
						"Entering into the userLogin method and throwing invalid credential in CreditCardAccountServiceImpl");
				throw new InvalidUser(MyCreditConstants.LOGIN_FAILURE);
			}
		} else {
			logger.error(
					"Entering into the userLogin method and throwing user not found in CreditCardAccountServiceImpl");
			throw new InvalidUser(MyCreditConstants.NOT_FOUND);
		}
		return loginResponseDto;
	}

	/**
	 * @throws InvalidUser
	 *
	 */
	@Override
	public VerifyCreditCardResponseDto verifyCreditcard(Integer userId,
			VerifyCreditCardRequestDto verifyCreditCardRequestDto) throws InvalidCreditCard, InvalidUser {
		logger.info("Entering into the verifyCreditcard method in CreditCardAccountServiceImpl");
		VerifyCreditCardResponseDto verifyCreditCardResponseDto = null;
		Optional<CreditCardAccount> optionalUser = creditCardAccountRepository.findByUserUserId(userId);
		if (optionalUser.isPresent()) {
			if (optionalUser.get().getCreditCardNumber().equals(verifyCreditCardRequestDto.getCreditCardNo())
					&& optionalUser.get().getValidFrom().equals(verifyCreditCardRequestDto.getValidFrom())
					&& optionalUser.get().getValidTill().equals(verifyCreditCardRequestDto.getValidTill())
					&& optionalUser.get().getCvv().equals(verifyCreditCardRequestDto.getCvv()) && optionalUser.get()
							.getUser().getUserName().equals(verifyCreditCardRequestDto.getAccountHolderName())) {
				verifyCreditCardResponseDto = new VerifyCreditCardResponseDto();
				verifyCreditCardResponseDto.setMessage(MyCreditConstants.CARD_VERIFY);
				verifyCreditCardResponseDto.setStatusCode(200);

			} else {
				logger.error(
						"Entering into the userLogin method and throwing check your credit card details in CreditCardAccountServiceImpl");
				throw new InvalidCreditCard(MyCreditConstants.INVALID_CARD);
			}
		} else {
			throw new InvalidUser(MyCreditConstants.NOT_FOUND);
		}
		return verifyCreditCardResponseDto;
	}
}
