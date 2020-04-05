package com.kkoix.todo.api.rest.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kkoix.todo.api.rest.common.annotation.ApiResponseCustom;
import com.kkoix.todo.api.rest.common.constants.AppConstants;
import com.kkoix.todo.api.rest.common.controller.BaseController;
import com.kkoix.todo.api.rest.model.CreateTodoModel;
import com.kkoix.todo.api.rest.model.ModifyTodoModel;
import com.kkoix.todo.api.rest.model.TodoModel;
import com.kkoix.todo.api.rest.model.TodoRefModel;
import com.kkoix.todo.api.rest.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/todo")
@Api( tags = { "TODO" })
public class TodoController extends BaseController{

	@Autowired
	private TodoService todoService;

	/**
	 * Todo 리스트 조회
	 * @param pageNo
	 * @param p
	 * @param pageNo
	 * @return 
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ApiOperation(value = "Todo 리스트", notes = "Todo 리스트를 조회합니다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum", value="Page Number", required=false, dataType="int", paramType="query", defaultValue="1"),
        @ApiImplicitParam(name="todoSts", value="Todo 진행 상태(I:진행중, C:완료)", required=false, dataType="String", paramType="query", defaultValue=AppConstants.EMPTY),
        @ApiImplicitParam(name="todoImptTyp", value="Todo 중요도(MNR:낮음, NOR:일반, MJR:높음)", required=false, dataType="String", paramType="query", defaultValue=AppConstants.EMPTY),
        @ApiImplicitParam(name="searchTyp", value="검색 타입(NM:Todo명 검색, CONT:TODO 내용 검색)", required=false, dataType="String", paramType="query", defaultValue=AppConstants.EMPTY),
        @ApiImplicitParam(name="searchKeyword", value="검색어 검색", required=false, dataType="String", paramType="query", defaultValue=AppConstants.EMPTY)
      })
	@ApiResponseCustom
	public ResponseEntity<?> getList(@ApiIgnore HttpServletRequest request, 
							@ApiIgnore HttpServletResponse response, @ApiIgnore TodoModel todoModel){

		// get Total count
		Integer listTotalCount = todoService.getTodoListCount(todoModel);
		
		// retrieve list
		List<TodoModel> todoList = todoService.searchTodoList(todoModel);

		/* 응답 결과물을 담을 객체 생성 */
	    Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
	   
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);	    
	    
	    /* DataTable에서 사용하는 value 세팅 */
	    reponseMap.put("draw", todoModel.getDraw());
	    reponseMap.put("recordsTotal", listTotalCount);
	    reponseMap.put("recordsFiltered", listTotalCount);

	    /* todo 리스트 */
	    reponseMap.put("data", todoList);

	    return new ResponseEntity<>(reponseMap, HttpStatus.OK);
	}
	
	/**
	 * Todo 상세
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}", method = RequestMethod.GET)	
	@ApiOperation(value = "Todo 상세", notes = "Todo의 상세를 조회합니다.")	
	@ApiResponseCustom
	public ResponseEntity<?> get(@PathVariable(value="todoSeq", required = true) int todoSeq) {

		TodoModel todoModel = new TodoModel();
	
		/* 상세 조회 */
		todoModel.setTodoSeq(todoSeq);			
		TodoModel todoDetail = todoService.getTodoDetail(todoModel);

	    Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
	    
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);	    
	    
	    reponseMap.put("todoDetail", todoDetail);

	    return new ResponseEntity<>(reponseMap, HttpStatus.OK);
	}	
	
	/**
	 * Todo 수정
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}", method = RequestMethod.PUT)	
	@ApiOperation(value = "Todo 수정", notes = "Todo를 수정 합니다.")	
	@ApiResponseCustom
	public ResponseEntity<?> update(@PathVariable(value="todoSeq", required = true) int todoSeq,
			@RequestBody ModifyTodoModel modifyTodoModel) {

		/* Todo 수정 */
		modifyTodoModel.setTodoSeq(todoSeq);
		todoService.modifyTodo(modifyTodoModel);
		
		Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);	
	    
		return new ResponseEntity<>(reponseMap, HttpStatus.OK);
	}
	
	/**
	 * Todo 삭제
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}", method = RequestMethod.DELETE)	
	@ApiOperation(value = "Todo 삭제", notes = "Todo를 삭제 합니다.")	
	@ApiResponseCustom
	public ResponseEntity<?> delete(@PathVariable(value="todoSeq", required = true) int todoSeq) {

		ModifyTodoModel modifyTodoModel = new ModifyTodoModel();
		
		/* Todo 수정 */
		modifyTodoModel.setTodoSeq(todoSeq);			
		todoService.removeTodo(modifyTodoModel);

		Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);	
	    
		return new ResponseEntity<>(reponseMap, HttpStatus.OK);
	}	
	
	/**
	 * Todo 참조 리스트 조회
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ref-list", method = RequestMethod.GET)
	@ApiOperation(value = "참조 가능 Todo 리스트", notes = "참조 가능Todo 리스트를 조회합니다.")
	@ApiResponseCustom
	public ResponseEntity<?> getRefList(@ApiIgnore HttpServletRequest request, 
							@ApiIgnore HttpServletResponse response){

		TodoModel todoModel = new TodoModel();

		//todoModel.getUserSeq(userSeq)
		/* 참조 가능 리스트 */
		List<TodoRefModel> todoRefPosList = todoService.getTodoRefPosList(todoModel);

		/* 응답 결과물을 담을 객체 생성 */
	    Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
	    
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);		    
	    
	    reponseMap.put("todoRefPosList", todoRefPosList);

	    return new ResponseEntity<>(reponseMap, HttpStatus.OK);
	}
	
	/**
	 * Todo 등록
	 * @param createTodoModel
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Todo 등록", notes = "Todo를 등록 합니다.")
	@ApiResponseCustom
	public ResponseEntity<?> create(@RequestBody CreateTodoModel createTodoModel) {

		/* Todo 등록 */
		todoService.createTodo(createTodoModel);
		
		Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
		
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);		
	    
		return new ResponseEntity<>(reponseMap, HttpStatus.OK);
	}
	
	/**
	 * Todo 완료
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}/completed", method = RequestMethod.PUT)
	@ApiOperation(value = "Todo 완료", notes = "Todo를 완료 합니다.")
	@ApiResponseCustom
	public ResponseEntity<?> completed(@PathVariable(value="todoSeq", required = true) int todoSeq) {
		
		/* Todo 진행상태 완료 */
		todoService.modifyTodoCompleted(todoSeq);
		
		Map<String, Object> reponseMap = new LinkedHashMap<String, Object>();
		
	    /* 성공 코드 세팅 0000 : 정상처리되었습니다. */
	    resultSuccess(reponseMap);		
	    
		return new ResponseEntity<>(reponseMap, HttpStatus.OK);

	}		
}
