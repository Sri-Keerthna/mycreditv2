package com.sprintstrickers.mycredit.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Credit Card Details - In this response dto's, we can set the response
 * details of the api. either success/failure values
 * 
 * @author akuthota.raghu
 * @version V1.1
 * @created date - 23-12-2019
 */
@Getter
@Setter
public class ResponseDto {

	private String status;
	private Integer statusCode;
	private String message;

}
