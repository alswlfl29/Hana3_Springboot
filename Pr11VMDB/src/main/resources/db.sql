use mydb;

drop table product;

create table product(
	id bigint primary key auto_increment,
    `name` varchar(255) not null,
    price int not null,
    limit_date date default (current_date)
);

desc product;