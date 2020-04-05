package com.kkoix.todo.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MappingTodoRefModel {
	
	@ApiModelProperty(value = "참조 Todo Seq")
	private int todoSeq;
	
	@ApiModelProperty(value = "참조 Todo 순서")
	private int todoOrd;
}
