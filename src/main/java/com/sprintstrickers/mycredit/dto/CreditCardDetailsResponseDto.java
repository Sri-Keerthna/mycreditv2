package com.sprintstrickers.mycredit.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditCardDetailsResponseDto {
	
	private String message;
	private Integer statusCode;

	List<CreditCardDetailsDto> creditCardDetails;
	
}
