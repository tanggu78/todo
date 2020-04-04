package com.kkoix.todo.api.rest.service;

import java.util.List;

import com.kkoix.todo.api.rest.model.CreateTodoModel;
import com.kkoix.todo.api.rest.model.ModifyTodoModel;
import com.kkoix.todo.api.rest.model.TodoModel;
import com.kkoix.todo.api.rest.model.TodoRefModel;

public interface TodoService {
	
	/**
	 * Todo 목록 조회
	 * @param todoModel
	 * @return
	 */
	List<TodoModel> searchTodoList(TodoModel todoModel);
	
	/**
	 * Todo 목록 Row Count 조회
	 * @param todoModel
	 * @return
	 */
	public int getTodoListCount(TodoModel todoModel);
	
	/**
	 * Todo 목록 Row Count 조회
	 * @param todoModel
	 * @return
	 */
	public TodoModel getTodoDetail(TodoModel todoModel); 		
	
	/**
	 * 참조 가능 Todo 리스트 조회
	 * @param todoModel
	 * @return
	 */
	public List<TodoRefModel> getTodoRefPosList(TodoModel todoModel);
	
	/**
	 * Todo 수정
	 * @param modifyTodoModel
	 */
	public void modifyTodo(ModifyTodoModel modifyTodoModel);
	
	/**
	 * Todo 삭제
	 * @param todoSeq
	 */
	public void removeTodo(ModifyTodoModel modifyTodoModel);
	
	/**
	 * Todo 등록
	 * @param createTodoModel
	 */
	public void createTodo(CreateTodoModel createTodoModel);
	
	/**
	 * Todo가 이미 참조 되었는지 확인
	 * @param todoSeq
	 * @return
	 */
	public boolean getHasTodoRef(int todoSeq);	
	
	/**
	 * Todo진행 완료
	 * @param todoSeq
	 */
	public void modifyTodoCompleted(int todoSeq);
	
	/**
	 * 해당Todo의 참조 된 Todo들이 전부 완료 되었는지 확인
	 * @param todoSeq
	 * @return
	 */
	public boolean getIsNotTodoChildAllCompleted(int todoSeq);
	
}
