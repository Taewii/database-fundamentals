CREATE DATABASE project_management_db;
USE project_management_db;

CREATE TABLE clients(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	client_name VARCHAR(100) NOT NULL,
	project_id INT(11) NOT NULL 
);

CREATE TABLE projects(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	client_id INT(11) NOT NULL,
	project_lead_id INT(11) NOT NULL
);

CREATE TABLE employees(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	project_id INT(11) NOT NULL
);

ALTER TABLE clients 
ADD CONSTRAINT fk_client_project_id FOREIGN KEY (project_id) REFERENCES projects(id);

ALTER TABLE projects
ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id),
ADD CONSTRAINT fk_project_lead_id FOREIGN KEY (project_lead_id) REFERENCES employees(id);

ALTER TABLE employees
ADD CONSTRAINT fk_employee_project_id FOREIGN KEY (project_id) REFERENCES projects(id);