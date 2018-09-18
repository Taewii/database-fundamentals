SELECT department_id, ROUND(MIN(salary), 2) AS `min` FROM employees AS e
GROUP BY e.department_id HAVING min > 800 ORDER BY e.department_id;