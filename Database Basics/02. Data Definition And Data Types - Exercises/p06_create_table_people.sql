CREATE TABLE people(
	id INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(200) NOT NULL,
	picture BLOB,
	height DOUBLE,
	weight DOUBLE,
	gender CHAR(1) NOT NULL,
	birthdate DATE NOT NULL,
	biography TEXT 
);


INSERT INTO people(`name`, gender, birthdate) VALUES
('test', 'm', '1914-01-01'),
('test1', 'm', '1914-01-01'),
('test2', 'm', '1914-01-01'),
('test3', 'm', '1914-01-01'),
('test4', 'm', '1914-01-01');