package com.sprintstrickers.mycredit.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyCreditCardRequestDto {
	private Long creditCardNo;
	private LocalDate validFrom;
	private LocalDate validTill;
	private Integer cvv;
	private String accountHolderName;

}
