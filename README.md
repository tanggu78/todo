#TODO 관리

## 개요
 * 일정 관리 프로그램을 하기 스펙으로 개발 하였습니다.
	- SpringBoot
	- JQuery 
	- H2
	- Mybatis

## 실행 필수 내용
	- lombok 필요
	- GIT URL : https://github.com/tanggu78/todo
	- GIT Clone하여 소스를 받아 주세요. 특별한 사항은 없습니다.
	- 초기 포트 : 8097(application.properties에서 변경 가능)

## 사용 설명
	- TODO 등록 : TODO를 등록합니다. 중요도, 내용, 참조할 TODO를 선택 가능합니다.
	- TODO 수정 : TODO를 수정합니다. 진행상태, 중요도, 내용, 참조할 TODO를 변경 가능합니다.
	- TODO 삭제 : TODO가 삭제 됩니다.
	- TODO 리스트 : 중요도, 진행상태, TODO명, TODO내용으로 조회가 가능하며, TODO번호와, TODO 명으로 정렬이 가능합니다.

### 참조 제약 조건 :
	- 참조된 TODO나 참조를 하고 있는 TODO는 또 다른 TODO의 참조 목록에 노출 되지 않습니다.
    - 완료된 TODO는 참조 목록에서 제외됩니다.
    - TODO완료시 참조된 TODO가 전부다 완료가 되어야 완료로 변경 할 수 있습니다. 	    


