var _$table;	

/* 화면 준비 후 이벤트 */
$(document).ready(function(){

	/* Todo 리스트 조회 */
	fncGetTodoList();
	
	/* Todo 상세 클릭 이벤트 */
	fncTodoDetailEvent();
	
	/* 조회 이벤트 */
	fncSearchEvent();
});		

/* 조회 이벤트 */
function fncSearchEvent(){
	/* 조회 클릭 */
	$('#searchBtn').on('click', function(){
		fncTodoSearchDraw();
	});
	
	/* 등록 클릭 */
	$('#insertBtn').on('click', function(){
		fncInsertTodoForm();
	});	
	
	/* 검색어 입력에서 엔터키 입력 */
	$('#v_searchKeyword').on('keypress', function(_event){
		if ( event.keyCode == 13 ) {
			fncTodoSearchDraw();
		}
	});
}

/* Todo 참조 정렬 기능 이벤트 부여 */
function fncTodoRefSortableEvent(){
	
	/* 이미 참조된 테이블 정렬 가능 이벤트 */
	$("#sortable1").sortable({
		connectWith: ".connectedSortable",  	
		change: function(event, ui) {
			//fncRefTodoPopupHeight();			
		},
        update: function(event, ui) {    
        	fncRefTodoMakeNodata();
        	fncRefTodoPopupHeight();
        }
	});
	  
	/* 참조 할수 있는 테이블 정렬 가능 이벤트 */
	$("#sortable2").sortable({
		connectWith: ".connectedSortable"
	});
   
	/* 아이템 내부 글자 드래그 방지 */
	$("#sortable1 li, #sortable2 li").disableSelection();
}

/* nodata 제어 */
function fncRefTodoMakeNodata(){
	
	$('#sortable1').children('li.ui-state-disabled.nodata').remove();
	$('#sortable2').children('li.ui-state-disabled.nodata').remove();
	
	var _sortableLength1 = $('#sortable1').children('li').length;
	var _sortableLength2 = $('#sortable2').children('li').length;
	
	if(_sortableLength1 == 0){
		$('#sortable1').append('<li class="ui-state-focus ui-state-disabled nodata">참조된 Todo가 없습니다.</li>');
	}
	
	if(_sortableLength2 == 0){
		$('#sortable2').append('<li class="ui-state-focus ui-state-disabled nodata">참조 할 수 있는 Todo가 없습니다.</li>');
	}
}

/* 정렬 가능 기능으로 요소가 옴겨다닐때 팝업의 크기를 동적으로 변경 해주는 기능을 처리 */
function fncRefTodoPopupHeight(){

	var _pos, _sortablePos1, _sortablePos2;

	_sortablePos1 = $('#sortable1').offset().top + $('#sortable1').height();
	_sortablePos2 = $('#sortable2').offset().top + $('#sortable2').height();;

	/* 두 테이블중 큰 테이블을 적용합니다. */
	if(_sortablePos1 >= _sortablePos2)
		_pos = _sortablePos1;
	else if(_sortablePos1 < _sortablePos2)
		_pos = _sortablePos2;

	$('#todoRefModal .modal-body').height(_pos-90);
}

var _$todoRefModal = $('#todoRefModal');

/* Todo 상세 정보 호출 후 팝업 호출 */
function fncTodoDetail(_todoSeq){

	$.ajax({
		url:'/v1/todo/'+_todoSeq,
		type:'GET',
		dataType:'JSON',
		cache:false,
		async:false,
		data:{},
		success: function(_jsonData){
			/* 팝업 호출 */
			$("#todoRefModal").modal({backdrop: 'static'});
	    	fncTodoDetailMapping(_jsonData);
	    	fncTodoRefSortableEvent();
	    	fncModalControll('detail', _jsonData.todoDetail.todoSts);
	    	fncRefTodoPopupHeight();
	    	
		    setTimeout(function(){
		    	fncRefTodoPopupHeight();
		    }, 500);
		}
	});		
}

/* 버튼 제어 */
function fncModalControll(_type, _todoSts){
	
	_$todoRefModal.find('.btn').show();
	
	/* 상세 팝업일경우 타이틀, 진행상태와 Todo명 크기 조절 및 업데이트 버튼(완료일경우 노출 X) 노출, 등록 버튼 노출  */
	if(_type == 'detail'){
		_$todoRefModal.find('.modal-title').text('TODO 상세 - 진행');
		_$todoRefModal.find('.todoSts').show();
		_$todoRefModal.find('.todoNm').removeClass('col-md-10').addClass('col-md-8');
		
		if(_todoSts == 'C'){
			_$todoRefModal.find('.modal-title').text('TODO 상세 - 완료');
			$('#updateBtn').hide();
		}
		$('#createBtn').hide();
	
	/* 등록 팝업일경우 타이틀, Todo명 크기 조절 및 등록 버튼만 노출  */
	}else if(_type == 'insert'){
		_$todoRefModal.find('.modal-title').text('TODO 등록');
		_$todoRefModal.find('.todoSts').hide();
		_$todoRefModal.find('.todoNm').removeClass('col-md-8').addClass('col-md-10');
		$('#deleteBtn').hide();
		$('#updateBtn').hide();
	}
}

/* Todo 매핑 */
function fncTodoDetailMapping(_jsonData){

	/* Todo 상세 팝업 초기 enabled */
	_$todoRefModal.find('input, select, textarea').prop('disabled', false);		
	
	/* 상세 조회 후 모달 팝업의 전역 el에 값 세팅해놓습니다. */
	$('.modal-dialog').data('todoSeq', _jsonData.todoDetail.todoSeq);
	
	_$todoRefModal.find('[name="todoSts"]').val(_jsonData.todoDetail.todoSts);
	_$todoRefModal.find('[name="todoImptTyp"]').val(_jsonData.todoDetail.todoImptTyp);
	_$todoRefModal.find('[name="todoNm"]').val(_jsonData.todoDetail.todoNm);
	_$todoRefModal.find('[name="todoCont"]').val(_jsonData.todoDetail.todoCont);

	var _$sortable1 = $('#sortable1');
	
	/* 참조한 Todo */
	_$sortable1.empty();
	if(_jsonData.todoDetail.todoRefList.length == 0){
		_$sortable1.append('<li class="ui-state-focus ui-state-disabled nodata">참조된 Todo가 없습니다.</li>');
	}else{
		$.each(_jsonData.todoDetail.todoRefList, function(_i, _todoRef){
			if(_todoRef.todoSts == 'C')
				_$sortable1.append('<li class="ui-state-focus ui-state-disabled" data-todo-seq="'+_todoRef.todoSeq+'">'+
										'<span class="badge badge-default">완료</span> '+ _todoRef.todoNm +
										'<span class="ui-icon ui-icon-closethick" style="float:right;"></span>'+
									'</li>');
			if(_todoRef.todoSts == 'I')
				_$sortable1.append('<li class="ui-state-focus" data-todo-seq="'+_todoRef.todoSeq+'"><span class="badge badge-default">진행</span> '+ _todoRef.todoNm +'</li>');
		});		
	}
	
	/* 참조 가능 Todo 매핑 */
	var _$sortable2 = fncRefSortableMapping(_jsonData.todoDetail.todoRefPosList);
	
	/* Todo가 완료 일경우 */
	if(_jsonData.todoDetail.todoSts == 'C'){
		_$todoRefModal.find('input, select, textarea').prop('disabled', true);
		_$sortable1.add(_$sortable2).children('li').not('.ui-state-disabled').addClass('ui-state-disabled');
	}
	
	/* 참조 가능 Todo */
	if(_jsonData.todoDetail.hasRef){
		_$todoRefModal.find('.refPos').hide();
		_$todoRefModal.find('.refIms').show();
	/* 참조 불가능 Todo */
	}else{
		_$todoRefModal.find('.refPos').show();
		_$todoRefModal.find('.refIms').hide();
	}	
}

/* 참조 테이블 매핑 */
function fncRefSortableMapping(_todoRefPosList){
	
	var _$sortable2 = $('#sortable2');
	/* 참조할 Todo */
	_$sortable2.empty();
	if(_todoRefPosList.length == 0){
		_$sortable2.append('<li class="ui-state-focus ui-state-disabled">참조할 수 있는 Todo가 없습니다.</li>');
	}else{
		$.each(_todoRefPosList, function(_i, _todoRefPos){
			_$sortable2.append('<li class="ui-state-highlight" data-todo-seq="'+_todoRefPos.todoSeq+'">'+ _todoRefPos.todoNm +'</li>');
		});
	}
	
	return _$sortable2;
}

/* Todo 리스트 조회 */
function fncGetTodoList(){
	
	_$table = $('#todoList').DataTable({
		processing : false,
		serverSide : true,
		retrieve: true,
		stateSave: false,
		autoWidth: false,		
		paging: true,
		searching: false,			/* 검색 기능 */
		sort: true,					/* 정렬 기능 */
		pageLength: 5,
		lengthChange: false,
	 	order: [0, 'desc'],	
	    createdRow: function( _row, _data, _dataIndex ) {
	    	$(_row).addClass("tbl-item");
	    	
	    	if(_data.todoSts == 'C'){
	    		$(_row).addClass("active");
	    	}
	    	
	    },
	    columnDefs: [
		  { targets: [0], className: 'img' },
		  { targets: [1], className: 'td-block', orderable: false  }
	    ],
		ajax : {
			url : '/v1/todo/list',
			type : 'get',
			dataType : 'json',
			data : function(_d){
				
				var _json = {};
				/* 그리드 세팅 값 */
			 	_json.recordsFiltered = _d.recordsFiltered || 0;
			 	_json.recordsTotal = _d.recordsTotal || 0;
				_json.draw = _d.draw;
				_json.pageNum = (_d.start/5)+1;

				_json.ordCol = _d.columns[_d.order[0].column].data;		//정렬 컬럼				
				_json.ordTyp = _d.order[0].dir;							//정렬 Type

				/* 검색 조건 */
				_json.todoSts= $('#todoSts').val();        							/* Todo 상태  */
				_json.todoImptTyp= $('#todoImptTyp').val();        					/* Todo 중요도 */
				_json.searchTyp= $('#searchTyp').val();        						/* 검색 타입 */
				_json.searchKeyword= $('#searchKeyword').val();        				/* 검색어 */

				return _json;
			}
		},
		columns : [
			{ data : 'todoSeq'},
			{ data : 'todoNm' ,
				render : function ( _data, _type, _row ) {

					var _todoNmHtml, _todoChildHtml, _stsTag, _lineTxt, _iconTxt;

					if(_row.todoImptTyp == 'MNR') _stsTag = '<span class="label label-sm label-success">낮음</span>&nbsp;';
					else if(_row.todoImptTyp == 'NOR') _stsTag = '<span class="label label-sm label-info">일반</span>&nbsp;';
					else if(_row.todoImptTyp == 'MJR') _stsTag = '<span class="label label-sm label-danger">중요</span>&nbsp;';
					
					_iconTxt = '<span class="ui-icon ui-icon-check" data-todo-seq="'+ _row.todoSeq +'"></span>&nbsp;&nbsp;&nbsp;'+
								'<span class="ui-icon ui-icon-closethick" data-todo-seq="'+ _row.todoSeq +'"></span>';
					
					if(_row.todoSts == 'C')
						_lineTxt='style="text-decoration:line-through;"';
					else
						_lineTxt='';
					
					_todoNmHtml =   '<p class="date">'+ _row.crtDt +'</p>'+
									'<p class="insert" style="float:right;">'+ _iconTxt +'</p>'+
									'<p class="title" '+ _lineTxt +'>' + _stsTag + _row.todoNm +'</p>' +
									'<p class="desc" '+ _lineTxt +'>'+ _row.todoCont +'</p>';
					
					if(_row.todoRefList.length != 0){

						_todoChildHtml = '';
						$.each(_row.todoRefList, function(_i, _todoRef){

							if(_todoRef.todoSts == 'C')
								_lineTxt='style="text-decoration:line-through;color:blue;"';
							else
								_lineTxt='';
							
							if(_i > 0) { _todoChildHtml += ', ' }
							_todoChildHtml +='<span class="refTodo" '+ _lineTxt +' title="'+ _todoRef.todoNm +'" data-todo-seq="'+ _todoRef.todoSeq +'">@'+
												_todoRef.todoSeq + 
											 '</span>';
						});
						_todoNmHtml += '<p class="child">'+ _todoChildHtml +'</p>';
					}else{
						_todoNmHtml += '<p class="child">&nbsp;</p>';
					}
					
            		return _todoNmHtml;
            	}		
            }
		],
		initComplete: function( _settings, _json ) {}
	});
}

/* Todo 상세 클릭 이벤트 */
function fncTodoDetailEvent(){
	
	/* 그리드 draw 후 호출 이벤트 */
	_$table.on('draw', function () {
		
		var _$todoListTr = $('#todoList tbody tr');
		/* 리스트 클릭 상세 이벤트 */
		_$todoListTr.on('click', function() {
			
			/* row가 데이터가 있으면 */
			if(!ParamValidate.isEmpty(_$table.row(this).data())){			
				var _todoSeq = _$table.row(this).data().todoSeq;		/* SEQ */
			
				/* 상세 조회 */
				fncTodoDetail(_todoSeq);
			}	
		});
		
		/* 삭제 클릭 */
		_$todoListTr.find('.ui-icon-closethick').on('click', function(_e){
			_e.stopPropagation();
			/* 리스트에서의 삭제 */
			var _todoSeq = $(this).data('todoSeq');
			fncDeleteTodo(_todoSeq);
		});
		
		/* 완료 클릭 */
		_$todoListTr.find('.ui-icon-check').on('click', function(_e){
			_e.stopPropagation();
			/* 리스트에서의 삭제 */
			var _todoSeq = $(this).data('todoSeq');
			fncCompletedTodo(_todoSeq);
		});
		
		/* 참조 Todo 클릭 */
		_$todoListTr.find('span.refTodo').on('click', function(_e){
			_e.stopPropagation();
			/* 리스트에서의 참조 Todo 상세 */
			var _todoSeq = $(this).data('todoSeq');
			fncTodoDetail(_todoSeq);
		});		
	});
	
	/* 수정 클릭 */
	$('#updateBtn').on('click', function(){
		fncUpdateTodo();
	});
	
	/* 삭제 클릭 */
	$('#deleteBtn').on('click', function(){
		/* 모달 팝업에서의 삭제 */
		var _todoSeq = $('.modal-dialog').data('todoSeq');
		fncDeleteTodo(_todoSeq);
	});
	
	/* 등록 클릭 */
	$('#createBtn').on('click', function(){
		fncCreateTodo();
	});
}

/* Todo 등록 */
function fncCreateTodo(){
	
	/* 등록 유효성 체크 */
	if(!fncIsValidCheck('create')){
		return
	}
	
	if(!confirm('TODO를 등록 하시겠습니까?')){
		return;
	}

	$.ajax({
		url:'/v1/todo',
		contentType : 'application/json; charset=utf-8',
		type:'POST',
		dataType:'JSON',
		cache:false,
		async:false,
		data: JSON.stringify(makeUpdateParamBody('create')),
		success: function(_jsonData){
			alert('TODO가 등록 되었습니다.');
			_$todoRefModal.modal("hide");
			
			/* 검색 초기화 */
			$('#v_todoSts, #v_todoImptTyp, #v_searchTyp, #v_searchKeyword').val('');

			fncTodoSearchDraw();
		}
	});
}

/* 등록 유효성 체크 */
function fncIsValidCheck(_tranType){
	
	var _$todoImptTypObj=_$todoRefModal.find('[name="todoImptTyp"]');
	var _$todoNmObj=_$todoRefModal.find('[name="todoNm"]');
	var _$todoContObj=_$todoRefModal.find('[name="todoCont"]');

	/* 중요도 필수 체크 */
	if(ParamValidate.isEmpty(_$todoImptTypObj.val())){
		alert('중요도를 선택해 주세요.');
		_$todoStsObj.focus();
		return false;
	}	
	
	/* 등록일 경우 */
	if(_tranType == 'update'){
		var _$todoStsObj=_$todoRefModal.find('[name="todoSts"]');
		
		/* 중요도 필수 체크 */
		if(ParamValidate.isEmpty(_$todoStsObj.val())){
			alert('진행상태를 선택해 주세요.');
			_$todoStsObj.focus();
			return false;
		}		
	}	
	
	/* 중요도 필수 체크 */
	if(ParamValidate.isEmpty(_$todoNmObj.val())){
		alert('TODO명을 입력해 주세요.');
		_$todoNmObj.focus();
		return false;
	}
	
	/* 중요도 필수 체크 */
	if(ParamValidate.isEmpty(_$todoContObj.val())){
		alert('내용을 입력해 주세요.');
		_$todoContObj.focus();
		return false;
	}	
	
	return true;
}

/* Todo 수정 */
function fncUpdateTodo(){
	
	/* 수정 유효성 체크 */
	if(!fncIsValidCheck('update')){
		return
	}
	
	if(!confirm('TODO를 수정하시겠습니까?')){
		return;
	}
	
	var _todoSeq = $('.modal-dialog').data('todoSeq');
	
	$.ajax({
		url:'/v1/todo/'+_todoSeq,
		contentType : 'application/json; charset=utf-8',
		type:'PUT',
		dataType:'JSON',
		cache:false,
		async:false,
		data: JSON.stringify(makeUpdateParamBody('update')),
		success: function(_jsonData){
			alert('TODO가 수정되었습니다.');
			_$todoRefModal.modal("hide");
			fncTodoSearchDraw(false);
		}
	});
}

/* Todo 완료 */
function fncCompletedTodo(_todoSeq){
	
	if(!confirm('TODO를 완료 하시겠습니까?')){
		return;
	}
	
	$.ajax({
		url:'/v1/todo/'+_todoSeq +'/completed',
		type:'PUT',
		dataType:'JSON',
		cache:false,
		async:false,
		success: function(_jsonData){
			alert('TODO가 완료되었습니다.');
			_$todoRefModal.modal("hide");
			fncTodoSearchDraw(false);
		}
	});	
}

/* Todo 삭제 */
function fncDeleteTodo(_todoSeq){

	if(!confirm('TODO를 삭제 하시겠습니까?')){
		return;
	}
	
	$.ajax({
		url:'/v1/todo/'+_todoSeq,
		type:'DELETE',
		dataType:'JSON',
		cache:false,
		async:false,
		//data: {},
		success: function(_jsonData){
			alert('TODO가 삭제되었습니다.');
			_$todoRefModal.modal("hide");
			fncTodoSearchDraw();
		}
	});
}

/* Todo 등록 폼 */
function fncInsertTodoForm(){

	/* Todo 등록 팝업 초기 enabled */
	_$todoRefModal.find('input, select, textarea').prop('disabled', false);	
	
	_$todoRefModal.find('[name="todoImptTyp"]').val('');
	_$todoRefModal.find('[name="todoNm"]').val('');
	_$todoRefModal.find('[name="todoCont"]').val('');
	
	var _$sortable1 = $('#sortable1');
	
	/* 참조한 Todo */
	_$sortable1.empty();
	_$sortable1.append('<li class="ui-state-focus ui-state-disabled nodata">참조된 Todo가 없습니다.</li>');

	/* 참조할 대상 리스트 조회 */
	$.ajax({
		url:'/v1/todo/ref-list',
		type:'GET',
		dataType:'json',
		cache:false,
		async:false,
		data:{},
		success: function(_jsonData){
			/* 팝업 호출 */
			$("#todoRefModal").modal({backdrop: 'static'});
			fncRefSortableMapping(_jsonData.todoRefPosList);
	    	fncTodoRefSortableEvent();
	    	fncModalControll('insert');
	    	
		    setTimeout(function(){
		    	fncRefTodoPopupHeight();
		    }, 500);			
			//console.log(_jsonData);
		}
	});			
}

/* param body 생성 */
function makeUpdateParamBody(_type){
	
	var _updateRequestParam = new Object();
	
	if(_type == 'update'){
		_updateRequestParam.todoSts = _$todoRefModal.find('[name="todoSts"]').val();
	}
	_updateRequestParam.todoImptTyp = _$todoRefModal.find('[name="todoImptTyp"]').val();
	_updateRequestParam.todoNm = _$todoRefModal.find('[name="todoNm"]').val();
	_updateRequestParam.todoCont = _$todoRefModal.find('[name="todoCont"]').val();

	var _todoRefList= new Array();
	var _todoRef = new Object();
	
	$('#sortable1 > li').each(function(_i, _item){
		
		if($(_item).hasClass('nodata'))
			return true;
		
		_todoRef = new Object();
		
		_todoRef.todoSeq = $(_item).data('todoSeq');
		_todoRef.todoOrd = (_i+1);
		
		_todoRefList.push(_todoRef);
	});
	
	_updateRequestParam.todoRefList = _todoRefList;
	
	return _updateRequestParam;
}

/* 검색 폼 파라메터 세팅 */
function fncSearchFormParamSet(){

	var _v_todoSts = $('#v_todoSts').val();						/* Todo 상태 */
	var _v_todoImptTyp = $('#v_todoImptTyp').val();					/* Todo 중요도 */
	var _v_searchTyp = $('#v_searchTyp').val();					/* 검색 Type */
	var _v_searchKeyword = $('#v_searchKeyword').val();					/* 검색 내용 */
	
	$('#todoSts').val(_v_todoSts);
	$('#todoImptTyp').val(_v_todoImptTyp);
	$('#searchTyp').val(_v_searchTyp);
	$('#searchKeyword').val(_v_searchKeyword);

}

/* draw fnc */
function fncTodoSearchDraw(_isPagingReset){
	
	if(_isPagingReset == undefined)
		_isPagingReset = true;
	
	/* param 세팅 */
	fncSearchFormParamSet();
	
	/* 그리드 draw (draw 기본 : true) */
	_$table.draw(_isPagingReset);
}