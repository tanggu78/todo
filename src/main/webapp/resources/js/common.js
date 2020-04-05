/**
 * AJAX 공통 value 세팅
 */
$.ajaxSetup({
	cache : false,
	beforeSend : function(xhr){
		xhr.setRequestHeader("ajax",true);
		_$todoRefModal.modal("hide");
	},
	error: function(xhr, textStatus, errorThrown){
		if(xhr.status == "400"){
			alert(xhr.responseJSON.error.comment);
		}else{
			alert('시스템 오류가 발생하였습니다. 잠시 후에 다시 이용해 주세요.')
		}
	}	
});