package com.kkoix.todo.api.rest.common.controller;

import java.util.Map;

import com.kkoix.todo.api.rest.common.constants.ServiceConstEnum;

/** 기본 컨트롤러 **/
public class BaseController {

	/**
	 * 성공 코드 세팅
	 * @param resultMap
	 * @throws Exception
	 */
	protected void resultSuccess(Map<String, Object> reponseMap){
		reponseMap.put("code", ServiceConstEnum.SUCCESS.getCode());
		reponseMap.put("message", ServiceConstEnum.SUCCESS.getMsg());
	}
}
