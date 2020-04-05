<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
       <div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" data-dismiss="modal" aria-hidden="true" class="close">
                       &times;</button>
                   <h4 class="modal-title">TODO 상세</h4>
               </div>
               <div class="modal-body">
                    <div class="col-lg-12">
						<div class="row">
	                        <div class="col-md-2 todoImptTyp" style="padding-left: 15px; padding-right: 5px">
	                            <div class="form-group">
	                                <label for="todoImptTyp" class="control-label">중요도</label>
									<select name="todoImptTyp" class="form-control">
                                        <option value="">선택</option>
                                        <option value="MNR">낮음</option>
                                        <option value="NOR">일반</option>
                                        <option value="MJR">중요</option>
                                    </select>
	                            </div>
	                        </div>						
	                        <div class="col-md-2 todoSts" style="padding-left: 5px; padding-right: 5px">
	                            <div class="form-group">
	                                <label for="todoSts" class="control-label">진행상태</label>
									<select name="todoSts" class="form-control">
                                        <option value="">선택</option>
                                        <option value="I">진행중</option>
                                        <option value="C">완료</option>
                                    </select>
	                            </div>
	                        </div>						
	                        <div class="col-md-8 todoNm" style="padding-left: 5px; padding-right: 15px">
								<div class="form-group">
								    <label for="todoNm" class="control-label">TODO 명</label>
								    <div class="input-icon right">
								        <i class="fa fa-tag"></i>
								        <input name="todoNm" type="text" placeholder="" class="form-control" maxlength="100">
								    </div>
								</div>
	                        </div>
 
	                        <div class="col-md-12">
								<div class="form-group">
	                               	<label for="todoCont" class="control-label">내용</label>
	                                <textarea name="todoCont" rows="3" class="form-control" style="width:100%" maxlength="350"></textarea>
	                            </div>	                        
		                    </div>
		            	</div>      
                    </div>        
                   <div class="col-lg-12 refPos" style="margin-bottom:10px;color:red;">
                    	* 완료된 참조 TODOS는 참조에서 제외할 수 없으며, 다른 TODO에참조관계에 있는 TODO는 참조 목록에서 제외 됩니다.
                   </div>                                       
                    <div class="col-lg-6 refPos">
                        <div class="panel">
                            <div class="panel-body"><h4 class="block-heading">참조된 TODO</h4>
								<ul id="sortable1" class="connectedSortable"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 refPos">
                        <div class="panel">
                            <div class="panel-body"><h4 class="block-heading">참조 할 수 있는 TODO</h4>                    
								<ul id="sortable2" class="connectedSortable"></ul>
                            </div>
                        </div>
                   </div>
                   <div class="col-lg-12 refIms" style="color:red;">
                    	* 이미 참조상태인 TODO 입니다. 해당 TODO는 참조 되거나 참조 할 수 없습니다.
                   </div>                        
               </div>     
               <div class="modal-footer">
               	   <button type="button" id="deleteBtn" class="btn btn-primary">삭제</button>
                   <button type="button" id="updateBtn" class="btn btn-primary">수정</button>
                   <button type="button" id="createBtn" class="btn btn-primary">등록</button>
                   <button type="button" data-dismiss="modal" class="btn btn-default">닫기</button>                   
               </div>
           </div>
       </div>