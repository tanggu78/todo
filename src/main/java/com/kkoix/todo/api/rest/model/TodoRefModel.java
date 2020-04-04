package com.kkoix.todo.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kkoix.todo.api.rest.common.model.BaseAttrModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoRefModel extends BaseAttrModel {
	
	private int todoSeq;
	private int parentTodoSeq;
	private String todoNm;
	private String todoCont;
	private String todoSts;
	private String todoImptTyp;
	private String todoOrd;
	

}
