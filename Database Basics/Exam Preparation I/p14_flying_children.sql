SELECT DISTINCT c.customer_id, CONCAT(first_name, ' ', last_name) AS full_name, 
					(2016 - YEAR(date_of_birth)) AS age
FROM customers AS c
JOIN tickets AS t ON t.customer_id = c.customer_id
JOIN flights AS f ON f.flight_id = t.flight_id
WHERE (2016 - YEAR(date_of_birth)) < 21 AND f.`status` = 'Arrived'
ORDER BY age DESC, c.customer_id ASC;