package com.kkoix.todo.api.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3534963433654350994L;

	private String code;
	
	public BadRequestException() {
		super();
	}

	public BadRequestException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}
	
	public BadRequestException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
