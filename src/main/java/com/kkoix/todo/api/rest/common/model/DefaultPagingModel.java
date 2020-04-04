package com.kkoix.todo.api.rest.common.model;

import com.kkoix.todo.api.rest.common.constants.AppConstants;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefaultPagingModel extends BaseAttrModel {
	
	private int draw = AppConstants.NUMBER_ONE;
	
	private int totalCount = AppConstants.NUMBER_ZERO;			/* 전체 개수 */
    private int pageNum = AppConstants.NUMBER_ONE;				/* 현재 페이지 번호 */
    private int recordPerPage = AppConstants.NUMBER_FIVE;		/* 페이지당 레코드 */

    /** 시작 페이지 */
    public int getStartIndex() {
		return (((this.pageNum - 1) * this.recordPerPage));
    }
    
    /** 종료 페이지 */
	public int getEndIndex() {
		//return  this.pageNum * this.recordPerPage;
		return  this.recordPerPage;
	}

}
