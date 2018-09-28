SELECT c.customer_id, CONCAT(first_name, ' ', last_name) AS full_name,
	  	 t.price AS ticket_price, a.airport_name AS destination
FROM customers AS c
JOIN tickets AS t ON c.customer_id = t.customer_id
JOIN flights AS f ON f.flight_id = t.flight_id
JOIN airports AS a ON a.airport_id = f.destination_airport_id
WHERE f.`status` = 'Delayed'
ORDER BY t.price DESC
LIMIT 3;