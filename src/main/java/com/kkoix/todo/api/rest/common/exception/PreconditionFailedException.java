package com.kkoix.todo.api.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * error code 412
* <pre>
* com.skcc.aibril.framework.exception
*   |_ PreconditionFailedException.java
* </pre>
* 
* 클래스 역할 설명 : 
* @Company : BD. Inc
* @Author  : taihyun
* @Date    : 2017. 3. 20. 오전 10:24:04
* @Version :
 */
@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
public class PreconditionFailedException extends RuntimeException {

	private static final long serialVersionUID = 767005752623306428L;

	public PreconditionFailedException() {
		super();
	}

	public PreconditionFailedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PreconditionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreconditionFailedException(String message) {
		super(message);
	}

	public PreconditionFailedException(Throwable cause) {
		super(cause);
	}
}
