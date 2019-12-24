package com.sprintstrickers.mycredit.dto;

import lombok.Getter;
import lombok.Setter;

/** 
 * @author Sri Keerthna 
 * @since 2019-12-23
 */
@Getter
@Setter
public class UserResponseDto {

	private String message;
	private Long creditCardNumber;
	private Double creditLimit;
}
