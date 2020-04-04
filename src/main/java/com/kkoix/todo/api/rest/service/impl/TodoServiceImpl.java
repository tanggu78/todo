package com.kkoix.todo.api.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkoix.todo.api.rest.exception.TodoException;
import com.kkoix.todo.api.rest.mapper.TodoMapper;
import com.kkoix.todo.api.rest.model.CreateTodoModel;
import com.kkoix.todo.api.rest.model.MappingTodoRefModel;
import com.kkoix.todo.api.rest.model.ModifyTodoModel;
import com.kkoix.todo.api.rest.model.TodoModel;
import com.kkoix.todo.api.rest.model.TodoRefModel;
import com.kkoix.todo.api.rest.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper todoMapper;

	@Override
	public List<TodoModel> searchTodoList(TodoModel todoModel) {
		return todoMapper.selectTodoList(todoModel);
	}
	
	@Override
	public int getTodoListCount(TodoModel todoModel) {
		return todoMapper.selectTodoListCount(todoModel);
	}
	
	@Override
	public TodoModel getTodoDetail(TodoModel todoModel) {
		
		/* 상세 조회 - 참조하고 있는 Todo도 같이 조회됩니다. */
		TodoModel todoDetail = todoMapper.selectTodoDetail(todoModel);
		
		/* 이미 참조되었는지 확인. */
		if(getHasTodoRef(todoDetail.getTodoSeq())) {
			/* 이미 참조되었을때 */
			todoDetail.setTodoRefPosList(new ArrayList<TodoRefModel>());
			todoDetail.setHasRef(true);
		}else {
			/* 참조 가능 Todo리스트 조회 */
			todoDetail.setTodoRefPosList(getTodoRefPosList(todoModel));
			todoDetail.setHasRef(false);
		}

		return todoDetail;
	}	
	
	@Override
	public List<TodoRefModel> getTodoRefPosList(TodoModel todoModel) {
		return todoMapper.selectTodoRefPosList(todoModel);
	}
	
	@Override
	@Transactional
	public void modifyTodo(ModifyTodoModel modifyTodoModel) {

		/* Todo 업데이트 */
		todoMapper.updateTodo(modifyTodoModel);
		/* Todo 참조 삭제 */
		todoMapper.deleteTodoRef(modifyTodoModel);
		
		/* Todo 참조 등록 */
		TodoRefModel todoRefModel = null;
		for(MappingTodoRefModel todoRef : modifyTodoModel.getTodoRefList()) {
			todoRefModel = new TodoRefModel();
			todoRefModel.setParentTodoSeq(modifyTodoModel.getTodoSeq());
			todoRefModel.setTodoSeq(todoRef.getTodoSeq());
			todoRefModel.setTodoOrd(todoRef.getTodoOrd());
			
			todoMapper.insertTodoRef(todoRefModel);
		}
	}
	
	@Override
	public void removeTodo(ModifyTodoModel modifyTodoModel) {

		/* Todo 삭제 */
		todoMapper.updateRemoveTodo(modifyTodoModel);
		/* Todo 참조 삭제 */
		todoMapper.deleteTodoRef(modifyTodoModel);		
	}	
	
	@Override
	public void createTodo(CreateTodoModel createTodoModel){
		
		/* Todo 등록 */
		todoMapper.insertTodo(createTodoModel);
		
		/* Todo 참조 등록 */
		TodoRefModel todoRefModel = null;
		for(MappingTodoRefModel todoRef : createTodoModel.getTodoRefList()) {
			todoRefModel = new TodoRefModel();
			todoRefModel.setParentTodoSeq(createTodoModel.getTodoSeq());
			todoRefModel.setTodoSeq(todoRef.getTodoSeq());
			todoRefModel.setTodoOrd(todoRef.getTodoOrd());
			
			todoMapper.insertTodoRef(todoRefModel);
		}		
	}
	
	@Override
	public boolean getHasTodoRef(int todoSeq) {
		return todoMapper.selectHasTodoRef(todoSeq);
	}
	
	/**
	 * 개요: Todo진행 완료
	 * @Method Name : updateTodoComplete
	 * @author tanggu
	 * @param modifyTodoModel
	 */
	@Override
	public void modifyTodoCompleted(int todoSeq) {
		
		/* 완료 시 완료 안된 Todo가 존재한다면 완료 할 수 없음 */
		if(getIsNotTodoChildAllCompleted(todoSeq)) {
			throw new TodoException("참조하는 Todo 중 완료가 안된 Todo가 존재한다면, 완료 처리를 할 수 없어요.", "5000");
		}
		
		ModifyTodoModel modifyTodoModel = new ModifyTodoModel();
		modifyTodoModel.setTodoSts("C");
		modifyTodoModel.setTodoSeq(todoSeq);
		
		todoMapper.updateTodoSts(modifyTodoModel);
	}
	
	/**
	 * 개요: 해당Todo의 참조 된 Todo들이 전부 완료 되었는지 확인 
	 * @Method Name : getIsTodoChildAllCompleted
	 * @author tanggu
	 * @param todoSeq
	 */
	@Override
	public boolean getIsNotTodoChildAllCompleted(int todoSeq) {
		return todoMapper.selectIsNotTodoChildAllCompleted(todoSeq);
	}	
	
//	@Override
//	public List<TodoModel> findReferenceListById(int todoId) {
//		return todoMapper.selectTodoListForChcekBox(todoId);
//	}
//	
//	@Override
//	@Transactional
//	public int addTodoItem(TodoModel todoItem){
//		// Todo명 중복 체크
//		int countList = todoMapper.selectTodoListCount( new PageBean( 0, 0, todoItem.getWhatTodo() ) );
//		
//		if(countList > 0){
//			throw new TodoException("이미 등록된 Todo명 입니다. ", "0909");
//		}
//
//		List<TodoReference> todoReferenceList = todoItem.getTodoReferenceList();
//		
//		// 등록할 todoReferenceList 가 존재 한다면 등록
//		if(todoReferenceList != null && todoReferenceList.size() > 0) {
//			todoMapper.insertReferenceList(todoItem.getTodoReferenceList());
//		}
//		return todoMapper.insertTodoItem(todoItem);
//	}
//	
//	@Override
//	@Transactional
//	public int modTodoItem(TodoModel todoItem){
//		int todoId = todoItem.getTodoId();
//		String status = todoItem.getStatus();
//		String whatTodo = todoItem.getWhatTodo();
//
//		logger.warn("#1 할일 명 중복 체크 ");
//		
//		// 1. 할일 명 중복 체크  
//		//Duplicate Checking by whatTodo
//		int countList = todoMapper.selectTodoListCount(new PageBean(0, 0, whatTodo));
//		if(countList > 1) {
//			throw new TodoException("이미 등록된 Todo명 입니다. ", "0909");
//		}
//		
//		logger.warn("#2 완료 처리 시 체크해야 할 항목 ");
//		
//		// 2. 완료 처리 시 체크해야 할 항목
//		// #1. 내가 참조하고 있는 ID 중 진행 중인 Todo가 존재 하는지 체크 필요
//		if(todoItem.getParentTodoId() != null && todoItem.getParentTodoId().size() > 0) {
//			if(Status.Completed.getValue().equals(status)) {
//				int proceedingTodoCount = todoMapper.selectProceedingReferenceListCount(todoId);
//				
//				if(proceedingTodoCount > 0) {
//					throw new TodoException("참조하는 Todo 중 완료가 안된 Todo가 존재한다면, 완료 처리를 할 수 없어요.", "5000");
//				}
//			}
//		}
//		
//		logger.warn("#3 교차 참조금지 ");
//		
//		// 3. 교차 참조금지(단, 이미 양쪽중 하나의 todo상태가 완료일 경우 허용) 
//		//  : 나를 추가한 진행중인 todo를 내가 또 추가한다면 서로 참조하면서 진행중이기 때문에 둘다 완료가 불가능하다. 
//		if(todoItem.getParentTodoId() != null && todoItem.getParentTodoId().size() > 0) {
//			int proceedingRefCount = todoMapper.selectReferenceMeCount(todoItem);
//			if(proceedingRefCount > 0) {
//				throw new TodoException("서로 진행중인 Todo는 추가 할 수 없어요.(교차 참조금지 위배)", "5001");
//			}
//		}
//
//		logger.warn("#4. 참조 목록 등록 ");
//		// 4. 참조 목록 등록  (삭제 후 등록)
//		// #1. 기존등록된 데이터 삭제
//		todoMapper.deleteReferenceListByTodoId(todoItem.getTodoId());
//
//		// #2. 신규등록될 참조 TodoId 등록
//		if(todoItem.getParentTodoId() != null && todoItem.getParentTodoId().size() > 0) {
//			todoMapper.insertReferenceList(todoItem);
//		}
//		// 5. update todo_list table
//		return todoMapper.updateTodoItem(todoItem);
//	}
//	
//	@Override
//	@Transactional
//	public int modStatus(TodoModel todoItem){
//		int todoId = todoItem.getTodoId();
//		String status = todoItem.getStatus();
//		
//		// '진행' -> '완료' 참조하고 있는 ID 중 진행 중인 Todo가 존재 하는지 체크
//		if(Status.Completed.getValue().equals(status)) {
//			int proceedingTodoCount = todoMapper.selectProceedingReferenceListCount(todoId);
//			
//			if(proceedingTodoCount > 0) {
//				throw new TodoException("참조하는 Todo 중 완료가 안된 Todo가 존재한다면, 완료 처리를 할 수 없어요.", "5000");
//			}
//		}
//		return todoMapper.updateTodoItem(todoItem);
//	}
//	
//	@Override
//	@Transactional
//	public int removeTodoItem(int todoId) throws TodoException{
//		// #1. 내가 참조하는 todo 삭제
//		int delRefCount = todoMapper.deleteReferenceListByTodoId(todoId);
//
//		// #2. 나를 참조하는 todo 삭제
//		int delParentCount = todoMapper.deleteReferenceListByParentTodoId(todoId);
//
//		Map<String, Object> dtoMap = new HashMap<>();
//		dtoMap.put("todoId", todoId);
//		dtoMap.put("dbsts", Status.Deleted.getValue());
//
//		// #3. todoItem 삭제
//		return todoMapper.updateTodoItem(dtoMap);
//	}
}
