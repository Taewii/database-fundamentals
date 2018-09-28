DROP DATABASE IF EXISTS airport_management_system;
CREATE DATABASE airport_management_system;
USE airport_management_system;

CREATE TABLE towns(
	town_id INT PRIMARY KEY AUTO_INCREMENT,
	town_name VARCHAR(30) NOT NULL
);

CREATE TABLE airports(
	airport_id INT PRIMARY KEY AUTO_INCREMENT,
	airport_name VARCHAR(50) NOT NULL,
	town_id INT,
	CONSTRAINT fk_town_id FOREIGN KEY (town_id)
	REFERENCES towns(town_id)
);

CREATE TABLE airlines(
	airline_id INT PRIMARY KEY AUTO_INCREMENT,
	airline_name VARCHAR(30) NOT NULL,
	nationality VARCHAR(30) NOT NULL,
	rating INT DEFAULT  0
);

CREATE TABLE customers(
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	date_of_birth DATE NOT NULL,
	gender VARCHAR(1) NOT NULL,
	home_town_id INT,
	CONSTRAINT fk_home_town_id FOREIGN KEY (home_town_id)
	REFERENCES towns(town_id)
);

CREATE TABLE flights(
	flight_id INT PRIMARY KEY AUTO_INCREMENT,
	departure_time DATETIME NOT NULL,
	arrival_time DATETIME NOT NULL,
	`status` VARCHAR(9) NOT NULL,
	origin_airport_id INT,
	destination_airport_id INT,
	airline_id INT,
	CONSTRAINT fk_origin_airport_id FOREIGN KEY (origin_airport_id)
	REFERENCES airports(airport_id),
	CONSTRAINT fk_destination_airport_id FOREIGN KEY (destination_airport_id)
	REFERENCES airports(airport_id),
	CONSTRAINT fk_airline_id FOREIGN KEY (airline_id)
	REFERENCES airlines(airline_id)
);

CREATE TABLE tickets(
	ticket_id INT PRIMARY KEY AUTO_INCREMENT,
	price DECIMAL(8, 2) NOT NULL,
	class VARCHAR(6) NOT NULL,
	seat VARCHAR(5) NOT NULL,
	customer_id INT,
	flight_id INT,
	CONSTRAINT fk_customer_id FOREIGN KEY (customer_id)
	REFERENCES customers(customer_id),
	CONSTRAINT fk_flight_id FOREIGN KEY (flight_id)
	REFERENCES flights(flight_id)
);