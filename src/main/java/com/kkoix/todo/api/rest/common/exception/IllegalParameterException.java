package com.kkoix.todo.api.rest.common.exception;

/**
 * <pre>
 * com.ckci.admin.common.exception
 * IllegalParameterException.java
 * </pre>
 * 
 * Created on 2014. 8. 20.
 * @author: kachoko78@gmail.com, Bluedigm
 */
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
