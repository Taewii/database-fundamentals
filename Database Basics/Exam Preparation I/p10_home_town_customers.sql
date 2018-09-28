SELECT DISTINCT c.customer_id, CONCAT(first_name, ' ', last_name) AS full_name, tw.town_name AS home_town
FROM customers AS c
JOIN tickets AS t ON t.customer_id = c.customer_id
JOIN flights AS f ON t.flight_id = f.flight_id
JOIN airports AS a ON a.airport_id = f.origin_airport_id
JOIN towns AS tw ON tw.town_id = a.town_id
WHERE f.`status` = 'Departing' AND f.origin_airport_id = a.airport_id AND a.town_id = c.home_town_id
ORDER BY c.customer_id