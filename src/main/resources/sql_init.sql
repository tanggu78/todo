--
--    Copyright 2015-2017 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.

-- TODO리스트

drop table if exists ts_todo;

CREATE TABLE ts_todo (
  todo_seq int AUTO_INCREMENT,
  todo_nm varchar(200) NOT NULL,
  todo_cont varchar(2000) NOT NULL,
  todo_sts char(1) NOT NULL,
  todo_impt_typ char(3) NOT NULL,
  obsts char(1) NOT NULL,
  upd_dt timestamp NULL DEFAULT NULL,
  crt_dt timestamp NULL DEFAULT NULL,
  PRIMARY KEY (todo_seq)
);

INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('카카오 과제 하기','카카오 과제 2020-04-05 23:5959까지 완료 하기','I','MJR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('이번주내로 맥주 먹기','시원하게 하게 치킨과 함께 맥주 먹기','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('카카오 프렌즈샵에서 캐릭터 상품 사기','노트와 인형 그리고 조카 선물 구매 하기','I','NOR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('콘서트 티켓 구매해서 공연 보러가기','주연, 소찬휘, 공연장소, 한국소리문화의전당 연지홀. 예매수수료, 장당 1,000원 - 언능 가서 신나게 놀구 싶다. 소찬휘가 짱이야~ ','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('하루 종일 넷플릭스 보기','이번 주 안에 꼭 실천하기, 집 콕해서 하루종일 넷플릭스 보기 - 브루클린 나인, 그레이트 뉴스, 테라스 하우스, 셰프의 테이블, 보디가드, 오티스의 비밀 상담소, 데리 걸스, 빌어먹을 세상 따위 등등 꼭!! 하기!!','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

-- TODO 참조 리스트
drop table if exists ts_todo_ref;

CREATE TABLE ts_todo_ref (
  todo_seq int NOT NULL DEFAULT '0',
  parent_todo_seq int NOT NULL DEFAULT '0',
  todo_ord int DEFAULT '0',
  upd_dt timestamp NULL DEFAULT NULL,
  crt_dt timestamp NULL DEFAULT NULL,
  PRIMARY KEY (todo_seq, parent_todo_seq)
);
