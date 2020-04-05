package com.kkoix.todo.api.rest.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kkoix.todo.api.rest.common.model.DefaultPagingModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoModel extends DefaultPagingModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Todo Seq")
	private int todoSeq;
	
	@ApiModelProperty(value = "Todo 명")
	private String todoNm;
	
	@ApiModelProperty(value = "Todo 내용")
	private String todoCont;
	
	@ApiModelProperty(value = "Todo 진행 상태 (I:진행중, C:완료)")
	private String todoSts;
	
	@ApiModelProperty(value = "Todo 중요도 (MNR:낮음, NOR:일반, MJR:높음")
	private String todoImptTyp;
	
	@ApiModelProperty(value = "참조된 Todo가 있으면 TRUE")
	private boolean hasRef;
	
	@ApiModelProperty(value = "참조된 Todo 리스트")
	private List<TodoRefModel> todoRefList;
	
	@ApiModelProperty(value = "참조된 가능한 Todo 리스트")
	private List<TodoRefModel> todoRefPosList;

}
