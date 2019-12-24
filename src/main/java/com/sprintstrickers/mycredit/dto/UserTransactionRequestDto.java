package com.sprintstrickers.mycredit.dto;

import java.time.LocalDate;

import com.sprintstrickers.mycredit.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @author  AKUTHOTA.RAGHU 
 * @version 1.0
 * @since   2019-12-05
 * Here, Constants - UserTransactionRequestDto for input values
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserTransactionRequestDto {

	private Integer transactionId;
	private User user;
	private LocalDate transactionDate;
	private Double transactionAmount;
	
}
