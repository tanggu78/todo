package com.kkoix.todo.api.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * error_code 400
* <pre>
* com.skcc.aibril.framework.exception
*   |_ BadRequestException.java
* </pre>
* 
* 클래스 역할 설명 : 
* @Company : BD. Inc
* @Author  : taihyun
* @Date    : 2017. 3. 20. 오전 10:23:08
* @Version :
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3534963433654350994L;

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

	
}
