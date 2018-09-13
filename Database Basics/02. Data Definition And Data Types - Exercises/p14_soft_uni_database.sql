CREATE DATABASE IF NOT EXISTS soft_uni;
USE soft_uni;

CREATE TABLE towns(
	id INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(30) NOT NULL
);

CREATE TABLE addresses(
	id INT AUTO_INCREMENT PRIMARY KEY,
	address_text VARCHAR(30) NOT NULL,
	town_id INT,
	CONSTRAINT fk_town_id FOREIGN KEY (town_id) REFERENCES towns(id)
);

CREATE TABLE departments(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(30) NOT NULL
);
--
CREATE TABLE employees(
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL,
	middle_name VARCHAR(30),
	last_name VARCHAR(30) NOT NULL,
	job_title VARCHAR(30),
	department_id INT,
	hire_date DATE,
	salary DOUBLE,
	address_id INT,
	CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES departments(id),
	CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES addresses(id)
);

