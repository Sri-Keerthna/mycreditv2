package com.sprintstrickers.mycredit.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDetailsDto {
	
	private Long creditCardNumber;
	private LocalDate validFrom;
	private LocalDate validTill;
	private Double creditLimit;
	private Double availableLimit;
	private Integer cvv;
	private String creditHolderName;
}
