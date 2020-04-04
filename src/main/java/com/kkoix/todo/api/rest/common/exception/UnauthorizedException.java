package com.kkoix.todo.api.rest.common.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -7994799839577715293L;

	public UnauthorizedException(String msg){
		super(msg);
	}
}
