package com.kkoix.todo.api.rest.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CreateTodoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private int todoSeq;
	
	@ApiModelProperty(value = "Todo 중요도 (MNR:낮음, NOR:일반, MJR:높음")
	private String todoImptTyp;
	
	@ApiModelProperty(value = "Todo 명")
	private String todoNm;
	
	@ApiModelProperty(value = "Todo 내용")
	private String todoCont;

	@ApiModelProperty(value = "Todo 참조 리스트")
	private List<MappingTodoRefModel> todoRefList;
}
