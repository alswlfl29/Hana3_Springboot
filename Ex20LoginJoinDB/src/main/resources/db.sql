use mydb;

desc member;

-- 회원가입 테이블 만들기
drop table member;
create table member (
	id bigint primary key auto_increment,
    user_id varchar(255) not null,
    user_pw varchar(255) not null,
    user_name varchar(255),
    user_role varchar(255) default 'ROLE_USER',
    join_date date default (current_date)
);
-- date default now()는 버전호환성(5버전, 8버전 이슈)

-- 데이터 추가하기
insert into member values (0, 'hong', '1234', '홍길동', 'ROLE_USER', '2024-01-01');
insert into member values (0, 'tom', '1234', '톰아저씨', 'ROLE_USER', '2024-02-01');
insert into member values (0, 'admin', '1234', '관리자', 'ROLE_ADMIN', '2024-03-01');