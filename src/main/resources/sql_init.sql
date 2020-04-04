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
VALUES('안녕 제목1','안녕 본문1','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('안녕 제목2','안녕 본문2','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('안녕 제목3','안녕 본문3','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('안녕 제목4','안녕 본문4','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO ts_todo(todo_nm,todo_cont,todo_sts,todo_impt_typ,obsts,upd_dt,crt_dt)
VALUES('안녕 제목5','안녕 본문5','I','MNR','N',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

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
