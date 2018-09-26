DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INTEGER)
BEGIN
	UPDATE employees
	SET salary = salary * 1.05
	WHERE employee_id = id;
END $$
DELIMITER ; 

CALL usp_raise_salary_by_id(178);