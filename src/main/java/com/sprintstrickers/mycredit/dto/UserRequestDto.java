package com.sprintstrickers.mycredit.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/** 
 * @author Sri Keerthna 
 * @since 2019-12-23
 */

@Getter
@Setter
public class UserRequestDto {

	private String userName;
	private String password;
	private LocalDate dob;
	private String profession;
	private String email;
	private String location;
	private Long phoneNumber;
	private Long income;
}
