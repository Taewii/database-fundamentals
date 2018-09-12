CREATE TABLE people(
	id INT AUTO_INCREMENT NOT NULL,
	`name` VARCHAR(200) NOT NULL,
	picture BLOB,
	height DOUBLE,
	weight DOUBLE,
	gender CHAR(1) NOT NULL,
	birthdate DATE NOT NULL,
	biography TEXT, 
	CONSTRAINT pk_people PRIMARY KEY (id)
);


INSERT INTO people(`name`, gender, birthdate) users
VALUES
('test', 'm', '1914-01-01'),
('test1', 'm', '1914-01-01'),
('test2', 'm', '1914-01-01'),
('test3', 'm', '1914-01-01'),
('test4', 'm', '1914-01-01')