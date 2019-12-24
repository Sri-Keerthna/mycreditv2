package com.sprintstrickers.mycredit.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprintstrickers.mycredit.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {

	List<UserTransaction> findByUserUserIdAndTransactionDateBetween(Integer userId, LocalDate startDate, LocalDate endDate);
}