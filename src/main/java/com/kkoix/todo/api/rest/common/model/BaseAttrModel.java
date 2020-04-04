package com.kkoix.todo.api.rest.common.model;

import lombok.Data;

@Data
public class BaseAttrModel {
	
	private String searchTyp;														/* 검색 타입 */
	private String searchKeyword;													/* 검색어 */
	private String ordCol;															/* 정렬 컬럼 */
	private String ordTyp;															/* 정렬 타입 */

	private String updId;															/* 생성일 */
	private String updDt;															/* 수정일 */									
	private String crtId;															/* 생성자 */
	private String crtDt;															/* 수정자 */
	private String obsts;															/* 상태(N/D) */
	
}
