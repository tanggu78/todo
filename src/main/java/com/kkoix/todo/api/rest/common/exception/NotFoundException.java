package com.kkoix.todo.api.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * error code 404
* <pre>
* com.skcc.aibril.framework.exception
*   |_ NotFoundException.java
* </pre>
* 
* 클래스 역할 설명 : 
* @Company : BD. Inc
* @Author  : taihyun
* @Date    : 2017. 3. 20. 오전 10:23:41
* @Version :
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5393120618090206380L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
