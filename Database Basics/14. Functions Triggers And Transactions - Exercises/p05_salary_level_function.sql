CREATE FUNCTION ufn_get_salary_level(salary DOUBLE)
RETURNS VARCHAR(10)
RETURN (
	CASE
		WHEN salary < 30000 THEN 'Low'
		WHEN salary <= 50000 THEN 'Average'
		ELSE 'High'
	END
);

SELECT ufn_get_salary_level(10000) AS salary;
SELECT ufn_get_salary_level(45000) AS salary;
SELECT ufn_get_salary_level(90000) AS salary; 