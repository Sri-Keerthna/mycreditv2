package com.sprintstrickers.mycredit.service;

import com.sprintstrickers.mycredit.dto.UserRequestDto;
import com.sprintstrickers.mycredit.dto.UserResponseDto;
import com.sprintstrickers.mycredit.exception.AgeException;

public interface UserService {

	public UserResponseDto registerUser(UserRequestDto userRequestDto) throws AgeException;

}
