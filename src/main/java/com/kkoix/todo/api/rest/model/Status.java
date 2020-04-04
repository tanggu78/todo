package com.kkoix.todo.api.rest.model;

public enum Status {
	
	Ing("I"),
	Completed("C"),
	Normal("N"),
	Deleted("D");
	
	private String value;

	Status(String v) {
		this.value = v;
	}
	
	public String getValue() {
		return value;
	}
}
