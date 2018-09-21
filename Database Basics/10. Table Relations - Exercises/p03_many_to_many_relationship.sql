CREATE DATABASE exercises;
USE exercises;

CREATE TABLE students(
	student_id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL
);

INSERT INTO students(`name`) VALUES
('Mila'), ('Toni'), ('Ron');

CREATE TABLE exams(
	exam_id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL
) AUTO_INCREMENT = 101;

INSERT INTO exams(`name`) VALUES
('Spring MVC'), ('Neo4j'), ('Oracle 11g');

CREATE TABLE students_exams(
	student_id INT NOT NULL,
	exam_id INT NOT NULL,
	PRIMARY KEY (student_id, exam_id),
	CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES students(student_id),
	CONSTRAINT fk_exam_id FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
);

INSERT INTO students_exams(student_id, exam_id) VALUES 
(1, 101), (1, 102), (2, 101), (3, 103), (2, 102), (2, 103);