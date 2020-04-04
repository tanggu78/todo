package com.kkoix.todo.api.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * error code 403
* <pre>
* com.skcc.aibril.framework.exception
*   |_ ForbiddenException.java
* </pre>
* 
* 클래스 역할 설명 : 
* @Company : BD. Inc
* @Author  : taihyun
* @Date    : 2017. 3. 20. 오전 10:25:34
* @Version :
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = -3362767119489177936L;

	public ForbiddenException() {
		super();
	}

	public ForbiddenException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException(Throwable cause) {
		super(cause);
	}

}
