package com.kkoix.todo.api.rest.common.exception;

public class IllegalParameterException extends RuntimeException {

    public IllegalParameterException() {
	super();
    }

    public IllegalParameterException(String s) {
	super(s);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public IllegalParameterException(Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = -5365630128856068164L;
}
