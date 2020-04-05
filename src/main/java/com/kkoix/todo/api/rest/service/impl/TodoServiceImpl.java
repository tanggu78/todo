package com.kkoix.todo.api.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkoix.todo.api.rest.common.constants.EnumCodes;
import com.kkoix.todo.api.rest.common.constants.ServiceConstEnum;
import com.kkoix.todo.api.rest.common.exception.BadRequestException;
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

		/* Todo 참조 삭제 */
		todoMapper.deleteTodoRef(modifyTodoModel);
		
		/* Todo 참조 등록  */
		TodoRefModel todoRefModel = null;
		for(MappingTodoRefModel todoRef : modifyTodoModel.getTodoRefList()) {
			todoRefModel = new TodoRefModel();
			todoRefModel.setParentTodoSeq(modifyTodoModel.getTodoSeq());
			todoRefModel.setTodoSeq(todoRef.getTodoSeq());
			todoRefModel.setTodoOrd(todoRef.getTodoOrd());
			
			/* 참조할 Todo가 이미 참조 된 Todo인지 확인  */
			if(!getHasTodoRef(todoRef.getTodoSeq())) {
				todoMapper.insertTodoRef(todoRefModel);
			}else {
				/* 5001, 이미 참조 된 Todo는 참조가 불가 합니다. */
				throw new BadRequestException(ServiceConstEnum.TODO_ALREADY_REFERENCED_CANNOT_BE_REFERENCED.getCode(), 
						ServiceConstEnum.TODO_ALREADY_REFERENCED_CANNOT_BE_REFERENCED.getMsg());
			}
		}
		
		/* 만일 완료로 수정할경우 하위에 완료 안된 Todo가 존재하는지 확인합니다. */
		if(EnumCodes.ProgressStatus.COMPLETE.getValue().equals(modifyTodoModel.getTodoSts())) {
			/* 완료 시 완료 안된 Todo가 존재한다면 완료 할 수 없음 */
			if(getIsNotTodoChildAllCompleted(modifyTodoModel.getTodoSeq())) {
				/* 5002, 참조하는 Todo 중 완료가 안된 Todo가 존재한다면, 완료 처리를 할 수 없습니다. */
				throw new BadRequestException(ServiceConstEnum.NOT_REFERENCED_TODO_COMPLETE.getCode(), 
						ServiceConstEnum.NOT_REFERENCED_TODO_COMPLETE.getMsg());
			}
		}

		/* Todo 업데이트 */
		todoMapper.updateTodo(modifyTodoModel);
	}
	
	@Override
	public void removeTodo(ModifyTodoModel modifyTodoModel) {

		/* Todo 삭제 */
		todoMapper.updateRemoveTodo(modifyTodoModel);
		/* Todo 참조 삭제 */
		todoMapper.deleteTodoRef(modifyTodoModel);		
	}	
	
	@Override
	@Transactional
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
			
			/* 참조할 Todo가 이미 참조 된 Todo인지 확인  */
			if(!getHasTodoRef(todoRef.getTodoSeq())) {			
				todoMapper.insertTodoRef(todoRefModel);
			}else {
				/* 5001, 이미 참조 된 Todo는 참조가 불가 합니다. */
				throw new BadRequestException(ServiceConstEnum.TODO_ALREADY_REFERENCED_CANNOT_BE_REFERENCED.getCode(), 
						ServiceConstEnum.TODO_ALREADY_REFERENCED_CANNOT_BE_REFERENCED.getMsg());
			}				
		}		
	}
	
	@Override
	public boolean getHasTodoRef(int todoSeq) {
		return todoMapper.selectHasTodoRef(todoSeq);
	}
	
	@Override
	public void modifyTodoCompleted(int todoSeq) {
		
		/* 완료 시 완료 안된 Todo가 존재한다면 완료 할 수 없음 */
		if(getIsNotTodoChildAllCompleted(todoSeq)) {
			/* 5002, 참조하는 Todo 중 완료가 안된 Todo가 존재한다면, 완료 처리를 할 수 없습니다. */
			throw new BadRequestException(ServiceConstEnum.NOT_REFERENCED_TODO_COMPLETE.getCode(), 
					ServiceConstEnum.NOT_REFERENCED_TODO_COMPLETE.getMsg());
		}
		
		ModifyTodoModel modifyTodoModel = new ModifyTodoModel();
		modifyTodoModel.setTodoSts(EnumCodes.ProgressStatus.COMPLETE.getValue());		//완료
		modifyTodoModel.setTodoSeq(todoSeq);
		
		todoMapper.updateTodoSts(modifyTodoModel);
	}
	
	@Override
	public boolean getIsNotTodoChildAllCompleted(int todoSeq) {
		return todoMapper.selectIsNotTodoChildAllCompleted(todoSeq);
	}
}
