package com.kkoix.todo.api.rest.common.constants;

public enum ServiceConstEnum {
	
	/* 성공 */
	SUCCESS("0000", "정상 처리되었습니다."),
	
	/** Todo-1001 NOT_REFERENCE_TODO_COMPLETE **/
	TODO_ALREADY_REFERENCED_CANNOT_BE_REFERENCED("1001", "이미 참조 된 Todo는 참조가 불가 합니다."),
	
	/** Todo-1002 NOT_REFERENCE_TODO_COMPLETE **/
	NOT_REFERENCED_TODO_COMPLETE("1002", "완료가 안된 참조 Todo가 존재한다면, 완료 처리를 할 수 없습니다.");
	
	/** 코드 **/ 
	private String code;
	/** 메세지 **/
	private String msg;
	
	ServiceConstEnum( String code, String msg ){
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return this.code;
	}	
	
	public String getMsg() {
		return this.msg;
	}

}
