package com.kkoix.todo.api.rest.common.exception;

public class CloudfoundryException extends Exception {

	private static final long serialVersionUID = -7994799839577715293L;

	public CloudfoundryException(String msg){
		super(msg);
	}
}
