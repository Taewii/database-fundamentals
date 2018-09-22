SELECT COUNT(employee_id) AS COUNT
FROM employees 
WHERE salary > (
	SELECT AVG(salary) FROM employees
);