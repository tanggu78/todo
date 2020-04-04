package com.kkoix.todo.api.rest.common.exception;

public class SolutionUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -7994799839577715293L;

	public SolutionUnauthorizedException(String msg){
		super(msg);
	}
}
