package com.kkoix.todo.api.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kkoix.todo.api.rest.common.constants.AppConstants;
import com.kkoix.todo.api.rest.exception.TodoException;
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
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@ExceptionHandler
	public ResponseEntity<?> exceptionHndler(TodoException e){
		// 에러 메세지 중앙처리 - 가능하지만..
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", e.getCode());
		resultMap.put("message", e.getMessage());
		
		return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
	}

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
//    @ApiResponses(value = { 
//            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found"),
//            @ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<?> getList(@ApiIgnore HttpServletRequest request, 
							@ApiIgnore HttpServletResponse response, @ApiIgnore TodoModel todoModel){

		try {

			// get Total count
			Integer listTotalCount = todoService.getTodoListCount(todoModel);
			
			// retrieve list
			List<TodoModel> todoList = todoService.searchTodoList(todoModel);
			
			// 헤더?
			//HttpHeaders headers = new HttpHeaders();
		    //headers.add("Content-Type", "application/json; charset=UTF-8");
		    //headers.add("X-list-total-count", listTotalCount.toString());

			/* 응답 결과물을 담을 객체 생성 */
		    Map<String, Object> resultReponseMap = new HashMap<>();
		    
		    /* DataTable에서 사용하는 value 세팅 */
		    resultReponseMap.put("draw", todoModel.getDraw());
		    resultReponseMap.put("recordsTotal", listTotalCount);
		    resultReponseMap.put("recordsFiltered", listTotalCount);

		    /* todo 리스트 */
		    resultReponseMap.put("data", todoList);

		    return new ResponseEntity<>(resultReponseMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Todo 상세
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}", method = RequestMethod.GET)	
	@ApiOperation(value = "Todo 상세", notes = "Todo의 상세를 조회합니다.")	
	public ResponseEntity<?> get(@PathVariable(value="todoSeq", required = true) int todoSeq) {
		
		System.out.print("/api/todos/");
		
		try {
			
			TodoModel todoModel = new TodoModel();
		
			todoModel.setTodoSeq(todoSeq);			
			TodoModel todoDetail = todoService.getTodoDetail(todoModel);

		    Map<String, Object> resultMap = new HashMap<>();
		    resultMap.put("todoDetail", todoDetail);

		    return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	
	
	/**
	 * Todo 수정
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}", method = RequestMethod.PUT)	
	@ApiOperation(value = "Todo 수정", notes = "Todo를 수정 합니다.")	
	public ResponseEntity<?> update(@PathVariable(value="todoSeq", required = true) int todoSeq,
			@RequestBody ModifyTodoModel modifyTodoModel) {
		
		try {

			/* Todo 수정 */
			modifyTodoModel.setTodoSeq(todoSeq);
			todoService.modifyTodo(modifyTodoModel);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상적으로 수정되었습니다.");
		    
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Todo 삭제
	 * @param todoSeq
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}", method = RequestMethod.DELETE)	
	@ApiOperation(value = "Todo 삭제", notes = "Todo를 삭제 합니다.")	
	public ResponseEntity<?> delete(@PathVariable(value="todoSeq", required = true) int todoSeq) {
		
		try {
			
			ModifyTodoModel modifyTodoModel = new ModifyTodoModel();
			
			/* Todo 수정 */
			modifyTodoModel.setTodoSeq(todoSeq);			
			todoService.removeTodo(modifyTodoModel);

			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상입니다.");
//			resultMap.put("currentListSize", todoList.size());
//		    resultMap.put("lists", todoList);
		    
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	
	
	/**
	 * Todo 참조 리스트 조회
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ref-list", method = RequestMethod.GET)
	@ApiOperation(value = "참조 가능 Todo 리스트", notes = "참조 가능Todo 리스트를 조회합니다. 사용자 세션 정보 seq를 획득 하여 진행합니다.")
	public ResponseEntity<?> getRefList(@ApiIgnore HttpServletRequest request, 
							@ApiIgnore HttpServletResponse response){

		try {

			TodoModel todoModel = new TodoModel();

			// retrieve list
			List<TodoRefModel> todoRefPosList = todoService.getTodoRefPosList(todoModel);
			
			// 헤더?
			//HttpHeaders headers = new HttpHeaders();
		    //headers.add("Content-Type", "application/json; charset=UTF-8");
		    //headers.add("X-list-total-count", listTotalCount.toString());

			/* 응답 결과물을 담을 객체 생성 */
		    Map<String, Object> resultReponseMap = new HashMap<>();
		    
		    /* todo 리스트 */
		    resultReponseMap.put("todoRefPosList", todoRefPosList);

		    return new ResponseEntity<>(resultReponseMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Todo 등록
	 * @param todoSeq
	 * @param modifyTodoModel
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Todo 수정", notes = "Todo를 수정 합니다.")	
	public ResponseEntity<?> create(@RequestBody CreateTodoModel createTodoModel) {
		
		try {

			/* Todo 등록 */
			todoService.createTodo(createTodoModel);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상적으로 수정되었습니다.");
		    
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Todo 완료
	 * @param todoSeq
	 * @param modifyTodoModel
	 * @return
	 */
	@RequestMapping(value="/{todoSeq}/completed", method = RequestMethod.PUT)
	@ApiOperation(value = "Todo 완료", notes = "Todo를 완료 합니다.")	
	public ResponseEntity<?> completed(@PathVariable(value="todoSeq", required = true) int todoSeq) {
		
		try {

			/* Todo 진행상태 완료 */
			todoService.modifyTodoCompleted(todoSeq);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상적으로 수정되었습니다.");
		    
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}		
}
