package com.sprintstrickers.mycredit.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprintstrickers.mycredit.entity.CreditCardAccount;

@Repository
public interface CreditCardAccountRepository extends JpaRepository<CreditCardAccount, Integer>{

public Optional<CreditCardAccount> findByUserUserId(Integer userId);

	

		public List<CreditCardAccount> findAllByUserUserId(Integer userId);

}
