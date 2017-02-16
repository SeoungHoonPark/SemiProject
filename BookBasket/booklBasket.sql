
/* Drop Triggers */

DROP TRIGGER TRI_B_Member_bm_no;



/* Drop Tables */

DROP TABLE b_book CASCADE CONSTRAINTS;
DROP TABLE b_msg CASCADE CONSTRAINTS;
DROP TABLE bms_info CASCADE CONSTRAINTS;
DROP TABLE B_Rsrv CASCADE CONSTRAINTS;
DROP TABLE B_Member CASCADE CONSTRAINTS;
DROP TABLE b_mstatus CASCADE CONSTRAINTS;
DROP TABLE brs_code CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_B_Member_bm_no;




/* Create Sequences */

CREATE SEQUENCE SEQ_B_Member_bm_no INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE B_Member
(
	-- 회원 관리를 위한 일련번호
	bm_no number(5) NOT NULL UNIQUE,
	bm_id varchar2(50) UNIQUE,
	bm_pw varchar2(50),
	bm_name varchar2(50),
	bm_email varchar2(100),
	bm_phone varchar2(15),
	bm_jdate date,
	PRIMARY KEY (bm_no)
);


CREATE TABLE b_mstatus
(
	bms_code number(2) NOT NULL UNIQUE,
	bms_name varchar2(20) NOT NULL UNIQUE,
	PRIMARY KEY (bms_code)
);


CREATE TABLE bms_info
(
	bmsi_no number(10) NOT NULL,
	bmsi_id varchar2(50) NOT NULL,
	bmsi_code number(2) NOT NULL,
	bmsi_mdate date,
	PRIMARY KEY (bmsi_no)
);


CREATE TABLE b_book
(
	bb_no number(10) NOT NULL UNIQUE,
	bb_name varchar2(200),
	bb_writer varchar2(200),
	bb_ownerid varchar2(50),
	bb_company varchar2(200),
	bb_date date,
	bb_status char(4),
	bb_visibleYN char,
	bb_buyd varchar2(50),
	PRIMARY KEY (bb_no)
);


CREATE TABLE B_Rsrv
(
	br_no number(10) NOT NULL UNIQUE,
	br_bno number(10),
	-- 회원 관리를 위한 일련번호
	br_rid varchar2(50) NOT NULL,
	br_date date,
	br_status number(2),
	PRIMARY KEY (br_no)
);


CREATE TABLE brs_code
(
	brs_code number(2) NOT NULL UNIQUE,
	brs_name varchar2(20),
	PRIMARY KEY (brs_code)
);


CREATE TABLE b_msg
(
	ms_no number(10) NOT NULL UNIQUE,
	ms_sendid varchar2(50),
	ms_receiveid varchar2(50),
	ms_date date,
	ms_text varchar2(2000),
	ms_check char,
	PRIMARY KEY (ms_no)
);



/* Create Foreign Keys */

ALTER TABLE b_book
	ADD FOREIGN KEY (bb_ownerid)
	REFERENCES B_Member (bm_id)
;


ALTER TABLE b_msg
	ADD FOREIGN KEY (ms_sendid)
	REFERENCES B_Member (bm_id)
;


ALTER TABLE b_msg
	ADD FOREIGN KEY (ms_receiveid)
	REFERENCES B_Member (bm_id)
;


ALTER TABLE bms_info
	ADD FOREIGN KEY (bmsi_id)
	REFERENCES B_Member (bm_id)
;


ALTER TABLE B_Rsrv
	ADD FOREIGN KEY (br_rid)
	REFERENCES B_Member (bm_id)
;


ALTER TABLE bms_info
	ADD FOREIGN KEY (bmsi_code)
	REFERENCES b_mstatus (bms_code)
;


ALTER TABLE B_Rsrv
	ADD FOREIGN KEY (br_status)
	REFERENCES brs_code (brs_code)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_B_Member_bm_no BEFORE INSERT ON B_Member
FOR EACH ROW
BEGIN
	SELECT SEQ_B_Member_bm_no.nextval
	INTO :new.bm_no
	FROM dual;
END;

/




/* Comments */

COMMENT ON COLUMN B_Member.bm_no IS '회원 관리를 위한 일련번호';
COMMENT ON COLUMN B_Rsrv.br_rid IS '회원 관리를 위한 일련번호';



