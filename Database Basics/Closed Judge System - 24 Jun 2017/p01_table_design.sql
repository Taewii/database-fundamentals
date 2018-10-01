create database open_judge_system;
use open_judge_system;

create table users(
	id int primary key auto_increment,
	username varchar(30) unique not null,
	`password` varchar(30) not null,
	email varchar(50)
);

create table categories(
	id int primary key auto_increment,
	name varchar(50) not null,
	parent_id int,
	constraint fk_parent_id foreign key (parent_id)
	references categories(id)
);

create table contests(
	id int primary key auto_increment,
	name varchar(50) not null,
	category_id int,
	constraint fk_category_id foreign key (category_id)
	references categories(id)
);

create table problems(
	id int primary key auto_increment,
	name varchar(100) not null,
	points int not null,
	tests int default 0,
	contest_id int,
	constraint fk_contest_id foreign key (contest_id)
	references contests(id)
);

create table submissions(
	id int primary key auto_increment,
	passed_tests int not null,
	problem_id int,
	user_id int,
	constraint fk_problem_id foreign key (problem_id)
	references problems(id),
	constraint fk_user_id foreign key (user_id)
	references users(id)
);

create table users_contests(
	user_id int,
	contest_id int,
	constraint pk_users_contests primary key (user_id, contest_id),
	constraint fk_uc_user_id foreign key (user_id)
	references users(id),
	constraint fk_uc_contest_id foreign key (contest_id)
	references contests(id)
);