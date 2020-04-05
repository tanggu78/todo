package com.kkoix.todo.api.rest.common.model;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private Error error;
	
	public ErrorResponse() {};
	public ErrorResponse(String code, String reason, String comment) {
		this.setError(new Error(code, reason, comment));
	}
	
	@Data
	public class Error {

		private String code;
		private String reason;
		private String comment;

		public Error(String code, String reason, String comment) {
			this.code = code;
			this.reason = reason;
			this.comment = comment;
		}
	}

}