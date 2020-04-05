package com.kkoix.todo.api.rest.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kkoix.todo.api.rest.common.model.ErrorResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;  
	
@Target(ElementType.METHOD) @Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
	@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
	@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
	@ApiResponse(code = 405, message = "Method Not Allowed", response = ErrorResponse.class),
	@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
})

public @interface ApiResponseCustom {}