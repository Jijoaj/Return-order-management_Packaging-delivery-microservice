package com.packagingandelivery.exceptions;

import java.util.Date;

import lombok.Getter;

@Getter
public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String componentType;
	private String count;

	public ExceptionResponse(Date timeStamp, String message, String componentType, String count) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.componentType = componentType;
		this.count = count;
	}

}
