drop database if exists the_nerd_herd;
create database the_nerd_herd;
use the_nerd_herd;

create table locations(
	id int(11) primary key auto_increment,
	latitude float not null,
	longitude float not null
);

create table credentials(
	id int(11) primary key auto_increment,
	email varchar(30) not null,
	`password` varchar(20) not null
);

create table users(
	id int(11) primary key auto_increment,
	nickname varchar(25) not null,
	gender char(1) not null,
	age int(11) not null,
	location_id int(11),
	credential_id int(11) unique,
	constraint fk_location_id foreign key (location_id)
	references locations(id),
	constraint fk_credential_id foreign key (credential_id)
	references credentials(id)
);

create table chats(
	id int(11) primary key auto_increment,
	title varchar(32) not null,
	start_date date not null,
	is_active bit(1) not null
);

create table messages(
	id int(11) primary key auto_increment,
	content varchar(200) not null,
	sent_on date not null,
	chat_id int(11),
	user_id int(11),
	constraint fk_messages_chat_id foreign key (chat_id)
	references chats(id),
	constraint fk_messages_user_id foreign key (user_id)
	references users(id)
);

create table users_chats(
	user_id int(11),
	chat_id int(11),
	primary key (user_id, chat_id),
	constraint fk_user_id foreign key (user_id)
	references users(id),
	constraint fk_chat_id foreign key (chat_id)
	references chats(id)
);