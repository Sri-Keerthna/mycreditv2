package com.sprintstrickers.mycredit.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sprintstrickers.mycredit.dto.ResponseDto;
import com.sprintstrickers.mycredit.dto.UserTransactionResponseDto;
import com.sprintstrickers.mycredit.entity.User;
import com.sprintstrickers.mycredit.entity.UserTransaction;
import com.sprintstrickers.mycredit.repository.UserRepository;
import com.sprintstrickers.mycredit.repository.UserTransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTransactionServiceImplTest {

	@InjectMocks
	UserTransactionServiceImpl userTransactionServiceImpl;

	@Mock
	UserTransactionRepository userTransactionRepository;

	@Mock
	UserRepository userRepository;

	ResponseDto fundTransferResponseDto = new ResponseDto();
	User user = new User();

	UserTransaction userTransaction1 = new UserTransaction();
	UserTransaction userTransaction2 = new UserTransaction();
	UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();

	List<UserTransaction> userTransactions = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);


		user.setDob(LocalDate.now());
		user.setEmail("raghu@gmail.com");
		user.setIncome(30000L);
		user.setUserName("Raghu");
		user.setPassword("Raghu1202");

		userTransaction1.setTransactionAmount(2500.00);
		userTransaction1.setTransactionDate(LocalDate.now());
		userTransaction1.setTransactionId(1);
		userTransaction1.setUser(user);
		
		userTransaction2.setTransactionAmount(2500.00);
		userTransaction2.setTransactionDate(LocalDate.now());
		userTransaction2.setTransactionId(1);
		userTransaction2.setUser(user);

		userTransactions.add(userTransaction1);
		userTransactions.add(userTransaction2);
	}

	@Test
	public void testFindUserTransactionsByMonth() {

		when(userTransactionRepository.findByUserUserIdAndTransactionDateBetween(user.getUserId(),
				LocalDate.of(2019, 12, 01), LocalDate.of(2019, 12, 31))).thenReturn(userTransactions);
		List<UserTransaction> userTransactions = userTransactionRepository
				.findByUserUserIdAndTransactionDateBetween(user.getUserId(), LocalDate.of(2019, 12, 01),
						LocalDate.of(2019, 12, 31));
		Assert.assertNotNull(userTransactions);
		Assert.assertEquals(2, userTransactions.size());
	}

}
