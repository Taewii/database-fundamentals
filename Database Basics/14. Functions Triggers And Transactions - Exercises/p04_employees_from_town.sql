DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town VARCHAR(20))
BEGIN
	SELECT e.first_name, e.last_name
	FROM employees AS e
	JOIN addresses AS a ON a.address_id = e.address_id
	JOIN towns AS t ON t.town_id = a.town_id
	AND t.NAME = town
	ORDER BY e.first_name ASC, e.last_name ASC, e.employee_id ASC;
END $$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');