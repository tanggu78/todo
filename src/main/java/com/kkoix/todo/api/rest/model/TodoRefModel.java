package com.kkoix.todo.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kkoix.todo.api.rest.common.model.BaseAttrModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoRefModel extends BaseAttrModel {
	
	@ApiModelProperty(value = "Todo Seq")
	private int todoSeq;
	
	@ApiModelProperty(value = "Todo 부모 Seq")
	private int parentTodoSeq;
	
	@ApiModelProperty(value = "Todo 명")
	private String todoNm;
	
	@ApiModelProperty(value = "Todo 내용")
	private String todoCont;
	
	@ApiModelProperty(value = "Todo 진행 상태 (I:진행중, C:완료)")
	private String todoSts;
	
	@ApiModelProperty(value = "Todo 중요도 (MNR:낮음, NOR:일반, MJR:높음")
	private String todoImptTyp;
	
	@ApiModelProperty(value = "Todo 순서")
	private int todoOrd;
	

}
