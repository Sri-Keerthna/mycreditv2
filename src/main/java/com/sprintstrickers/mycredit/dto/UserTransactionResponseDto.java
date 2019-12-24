package com.sprintstrickers.mycredit.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * @author  AKUTHOTA.RAGHU 
 * @version 1.0
 * @since   2019-12-23
 * Here, Constants - UserTransactionRequestDto for output response
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserTransactionResponseDto {

	private String message;
	private Integer statusCode;

	private List<UserTransactionRequestDto> transactionDetails;

}
