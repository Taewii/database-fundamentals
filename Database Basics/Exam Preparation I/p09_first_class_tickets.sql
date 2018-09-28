SELECT t.ticket_id, a.airport_name, CONCAT(first_name, ' ', last_name) AS customer_name
FROM tickets AS t
JOIN flights AS f ON f.flight_id = t.flight_id
JOIN airports AS a ON a.airport_id = f.destination_airport_id
JOIN customers AS c ON c.customer_id = t.customer_id
WHERE t.class = 'First' AND t.price < 5000
ORDER BY t.ticket_id ASC;