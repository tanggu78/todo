package com.kkoix.todo.api.rest.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkoix.todo.api.rest.common.constants.AppConstants;

import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

@Setter
@Getter
public class DefaultPagingModel extends BaseAttrModel {
	
	@JsonIgnore
	private int draw = AppConstants.NUMBER_ONE;
	@JsonIgnore
	private int totalCount = AppConstants.NUMBER_ZERO;			/* 전체 개수 */
	@JsonIgnore
    private int pageNum = AppConstants.NUMBER_ONE;				/* 현재 페이지 번호 */
	@JsonIgnore
    private int recordPerPage = AppConstants.NUMBER_FIVE;		/* 페이지당 레코드 */

	@JsonIgnore
    /** 시작 페이지 */
    public int getStartIndex() {
		return (((this.pageNum - 1) * this.recordPerPage));
    }
    
	@JsonIgnore
    /** 종료 페이지 */
	public int getEndIndex() {
		//return  this.pageNum * this.recordPerPage;
		return  this.recordPerPage;
	}

}
