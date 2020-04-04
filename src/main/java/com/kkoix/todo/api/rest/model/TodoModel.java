package com.kkoix.todo.api.rest.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kkoix.todo.api.rest.common.model.DefaultPagingModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoModel extends DefaultPagingModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int todoSeq;
	private String todoNm;
	private String todoCont;
	private String todoSts;
	private String todoImptTyp;
	private boolean hasRef;
	
	private List<TodoRefModel> todoRefList;
	private List<TodoRefModel> todoRefPosList;

}
