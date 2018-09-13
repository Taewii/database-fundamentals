CREATE DATABASE IF NOT EXISTS hotel;
USE hotel;

CREATE TABLE employees(
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL, 
	last_name VARCHAR(30), 
	title VARCHAR(30),
	notes TEXT
);

INSERT INTO employees(first_name) VALUES ('asd'), ('asd'), ('asd');

CREATE TABLE customers(
	account_number INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL, 
	last_name VARCHAR(30), 
	phone_number VARCHAR(30),
	emergency_name VARCHAR(30),
	emergency_number VARCHAR(30),
	notes TEXT
);

INSERT INTO customers(first_name) VALUES ('asd'), ('asd'), ('asd');

CREATE TABLE room_status(
	`room_status` VARCHAR(20) NOT NULL PRIMARY KEY , 
	notes TEXT
);

INSERT INTO room_status(`room_status`) VALUES ('asd'), ('asdd'), ('asddd');

CREATE TABLE room_types(
	room_type VARCHAR(20) NOT NULL PRIMARY KEY , 
	notes TEXT
);

INSERT INTO room_types(room_type) VALUES ('asd'), ('asdd'), ('asddd');

CREATE TABLE bed_types(
	bed_type VARCHAR(20) NOT NULL PRIMARY KEY , 
	notes TEXT
);

INSERT INTO bed_types(bed_type) VALUES ('asd'), ('asdd'), ('asddd');

CREATE TABLE rooms(
	room_number INT PRIMARY KEY AUTO_INCREMENT,
	room_type INT,
	bed_type INT,
   rate DOUBLE,
   `room_status` INT, 
	notes TEXT
);

INSERT INTO rooms(room_number) VALUES (1), (2), (3);

CREATE TABLE payments(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	employee_id INT NOT NULL, 
	payment_date DATE, 
	account_number VARCHAR(20), 
	first_date_occupied DATE, 
	last_date_occupied DATE, 
	total_days INT, 
	amount_charged DOUBLE, 
	tax_rate DOUBLE, 
	tax_amount DOUBLE, 
	payment_total DOUBLE, 
	notes TEXT
);

INSERT INTO payments(employee_id) VALUES (1), (2), (3);

CREATE TABLE occupancies(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	employee_id INT NOT NULL, 
	date_occupied DATE, 
	account_number VARCHAR(20), 
	room_number INT, 
	rate_applied DOUBLE, 
	phone_charge VARCHAR(20), 
	notes TEXT
);

INSERT INTO occupancies(employee_id) VALUES (1), (2), (3);
