package com.kkoix.todo.api.rest.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	
	private String todoImptTyp;
	private String todoNm;
	private String todoCont;

	private List<MappingTodoRefModel> todoRefList;
}
