DROP TABLE IF EXISTS NOTICE;
DROP TABLE IF EXISTS USER;

CREATE table user(
	ID BIGINT auto_increment primary key,
	EMAIL VARCHAR(255),
	PASSWORD VARCHAR(255),
	PHONE VARCHAR(255),
	REG_DATE TIMESTAMP,
	UPDATE_DATE TIMESTAMP,
	USER_NAME VARCHAR(255),
 	STATUS	INTEGER,
	LOCK_YN	BOOLEAN
);
create table NOTICE
(
	ID	BIGINT auto_increment primary key,
	TITLE	VARCHAR(255),
	CONTENTS	VARCHAR(255),

	HITS	INTEGER,
	LIKES	INTEGER,
	REG_DATE	TIMESTAMP,
	UPDATE_DATE	TIMESTAMP,
	DELETED_DATE TIMESTAMP,
	DELETED	BOOLEAN,
	USER_ID	BIGINT,
	constraint FK_NOTICE_USER_ID foreign key(USER_ID) references user(ID)
);

create table notice_like(
	ID BIGINT auto_increment primary key,
	NOTICE_ID	BIGINT,
	USER_ID	BIGINT,
	constraint FK_NOTICELIKE_NOTICE_ID foreign key(NOTICE_ID) references notice(ID),
	constraint FK_NOTICELIKE_USER_ID foreign key(USER_ID) references user(ID)
);

create table user_login_history (
	ID	BIGINT auto_increment primary key,
	USER_ID	BIGINT,
	EMAIL	VARCHAR(255),
	USER_NAME	VARCHAR(255),
	LOGIN_DATE	TIMESTAMP,
	IP_ADDR	VARCHAR(255)
);

create table BOARD_TYPE(
	ID	BIGINT auto_increment primary key,
	BOARD_NAME	VARCHAR(255),
	REG_DATE	TIMESTAMP,
	UPDATE_DATE	TIMESTAMP
);