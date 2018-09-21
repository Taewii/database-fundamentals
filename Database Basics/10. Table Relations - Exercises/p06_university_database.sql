CREATE DATABASE university_db;
USE university_db;

CREATE TABLE majors(
	major_id INT(11) PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL
);

CREATE TABLE students(
	student_id INT(11) PRIMARY KEY AUTO_INCREMENT,
	student_number VARCHAR(12) NOT NULL UNIQUE,
	student_name VARCHAR(50),
	major_id INT(11),
	CONSTRAINT fk_major_id
	FOREIGN KEY (major_id)
	REFERENCES majors(major_id)
);

CREATE TABLE payments(
	payment_id INT(11) PRIMARY KEY AUTO_INCREMENT,
	payment_date DATE NOT NULL,
	payment_amount DECIMAL(8,2) NOT NULL,
	student_id INT(11),
	CONSTRAINT fk_student_id
	FOREIGN KEY (student_id)
	REFERENCES students(student_id)
);

CREATE TABLE subjects(
	subject_id INT(11) PRIMARY KEY AUTO_INCREMENT,
	subject_name VARCHAR(50) NOT NULL
);

CREATE TABLE agenda(
	student_id INT(11) NOT NULL,
	subject_id INT(11) NOT NULL,
	PRIMARY KEY (student_id, subject_id),
	
	CONSTRAINT fk_subject_id
	FOREIGN KEY (subject_id)
	REFERENCES subjects(subject_id),
	
 	CONSTRAINT fk_student_agenda_id
	FOREIGN KEY (student_id)
	REFERENCES students(student_id)
);

