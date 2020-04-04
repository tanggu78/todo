package com.kkoix.todo.api.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkoix.todo.api.rest.model.CreateTodoModel;
import com.kkoix.todo.api.rest.model.ModifyTodoModel;
import com.kkoix.todo.api.rest.model.TodoModel;
import com.kkoix.todo.api.rest.model.TodoRefModel;

@Mapper
public interface TodoMapper {
	
	/**
	 * 개요: Todo 리스트 조회
	 * @Method Name : selectTodoList
	 * @author tanggu
	 * @param todoModel
	 * @return
	 */
	public List<TodoModel> selectTodoList(TodoModel todoModel);
	
	/**
	 * 개요: Todo 리스트의 총 카운트 조회
	 * @Method Name : selectTodoListCount
	 * @author tanggu
	 * @param todoModel
	 * @return
	 */
	public int selectTodoListCount(TodoModel todoModel);
	
	/**
	 * 개요: Todo 상세 조회
	 * @Method Name : selectTodoDetail
	 * @author tanggu
	 * @param todoModel
	 * @return
	 */
	public TodoModel selectTodoDetail(TodoModel todoModel);	
	
	/**
	 * 개요: Todo 참조 가능 리스트
	 * @Method Name : selectTodoRefPosList
	 * @author tanggu
	 * @param todoModel
	 * @return
	 */
	public List<TodoRefModel> selectTodoRefPosList(TodoModel todoModel);
	
	/**
	 * 개요: Todo 삭제
	 * @Method Name : deleteTodo
	 * @author tanggu
	 * @param modifyTodoModel
	 */
	public void deleteTodoRef(ModifyTodoModel modifyTodoModel);

	/**
	 * 개요: Todo 수정
	 * @Method Name : updateTodo
	 * @author tanggu
	 * @param modifyTodoModel
	 */
	public void updateTodo(ModifyTodoModel modifyTodoModel);
	
	/**
	 * 개요: Todo 등록
	 * @Method Name : insertTodoRef
	 * @author tanggu
	 * @param modifyTodoModel
	 */	
	public void insertTodoRef(TodoRefModel todoRefModel);
	
	/**
	 * 개요: Todo 삭제
	 * @Method Name : updateRemoveTodo
	 * @author tanggu
	 * @param modifyTodoModel
	 * @return
	 */
	public void updateRemoveTodo(ModifyTodoModel modifyTodoModel);
	
	/**
	 * 개요: Todo 등록
	 * @Method Name : insertTodo
	 * @author tanggu
	 * @param createTodoModel
	 * @return
	 */
	public void insertTodo(CreateTodoModel createTodoModel);

	/**
	 * 개요: Todo가 이미 참조 되었는지 확인
	 * @Method Name : selectHasTodoRef
	 * @author tanggu
	 * @param todoSeq
	 * @return
	 */
	public boolean selectHasTodoRef(int todoSeq);
	
	/**
	 * 개요: Todo진행 완료
	 * @Method Name : updateTodoSts
	 * @author tanggu
	 * @param modifyTodoModel
	 */
	public void updateTodoSts(ModifyTodoModel modifyTodoModel);
	
	/**
	 * 해당Todo의 참조 된 Todo들이 전부 완료 되었는지 확인
	 * @param todoSeq
	 * @return
	 */
	public boolean selectIsNotTodoChildAllCompleted(int todoSeq);
}
