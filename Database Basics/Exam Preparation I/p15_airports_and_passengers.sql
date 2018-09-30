SELECT a.airport_id, a.airport_name, COUNT(t.ticket_id) AS passengers
FROM airports AS a
JOIN flights AS f ON f.origin_airport_id = a.airport_id
JOIN tickets AS t ON t.flight_id = f.flight_id
WHERE f.`status` = 'Departing'
GROUP BY a.airport_id, a.airport_name
HAVING passengers > 0
ORDER BY a.airport_id ASC;