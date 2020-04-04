package com.kkoix.todo.api.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * error_406
* <pre>
* com.skcc.aibril.framework.exception
*   |_ NotAcceptableException.java
* </pre>
* 
* 클래스 역할 설명 : 
* @Company : BD. Inc
* @Author  : taihyun
* @Date    : 2017. 3. 20. 오전 10:23:30
* @Version :
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException{

	private static final long serialVersionUID = 2888611440472593325L;

	public NotAcceptableException() {
		super();
	}

	public NotAcceptableException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotAcceptableException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAcceptableException(String message) {
		super(message);
	}

	public NotAcceptableException(Throwable cause) {
		super(cause);
	}

}
