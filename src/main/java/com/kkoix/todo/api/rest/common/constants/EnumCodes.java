package com.kkoix.todo.api.rest.common.constants;

public class EnumCodes {
	
	/**
	 * DB Object 상태값
	 */
	public enum ObjectStatus {
		
		Normal("N"),
		Deleted("D");
		
		private String value;

		ObjectStatus(String v) {
			this.value = v;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * Todo 중요도 상태값
	 */
	public enum ImportantType {
		
		MINOR("MNR"),
		NORMAL("NOR"),
		MAJOR("MJR");
		
		private String value;

		ImportantType(String v) {
			this.value = v;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * Todo 진행 상태
	 */
	public enum ProgressStatus {
		
		ING("I"),
		COMPLETE("C");
		
		private String value;

		ProgressStatus(String v) {
			this.value = v;
		}
		
		public String getValue() {
			return value;
		}
	}	
	
}
