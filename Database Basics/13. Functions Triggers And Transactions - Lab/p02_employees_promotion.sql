DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(20))
BEGIN
	DECLARE dep_id INTEGER;
	SET dep_id = (SELECT department_id 	FROM departments WHERE NAME = department_name);
	UPDATE employees
	SET salary = salary * 1.05
	WHERE department_id = dep_id;
END $$
DELIMITER ;

CALL usp_raise_salaries('Production');