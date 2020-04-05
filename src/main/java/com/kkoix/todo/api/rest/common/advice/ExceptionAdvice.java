package com.kkoix.todo.api.rest.common.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kkoix.todo.api.rest.common.exception.BadRequestException;
import com.kkoix.todo.api.rest.common.exception.NotFoundException;
import com.kkoix.todo.api.rest.common.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	/**
	 * 정의되지 않은 서버 오류
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> ExceptionHandler(Exception ex) {
	   ex.printStackTrace();

	   ErrorResponse errorResponse = new ErrorResponse("500", "INTERNAL_SERVER_ERROR", "알수없는 오류가 발생하였습니다.");
	   return new ResponseEntity<Object>( errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Invalid Value
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> BadRequestExceptionHandler(BadRequestException ex) {
		ex.printStackTrace();

		ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), "BAD_REQUEST", ex.getLocalizedMessage());
		return new ResponseEntity<Object>( errorResponse, HttpStatus.BAD_REQUEST);
	}
	    
	/**
	 * 존재하지 않는 요청
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> NotFoundExceptionHandler(NotFoundException ex) {
		ex.printStackTrace();
		
		ErrorResponse errorResponse = new ErrorResponse("404", "NOT_FOUND", ex.getLocalizedMessage());
		return new ResponseEntity<Object>( errorResponse, HttpStatus.NOT_FOUND);
	}	
	
	
	/**
	 * 허용되지 않은 메서드 예외
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();

		ErrorResponse errorResponse = new ErrorResponse("405", "METHOD_NOT_ALLOWED", ex.getMethod());
		
		return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}
   
}
