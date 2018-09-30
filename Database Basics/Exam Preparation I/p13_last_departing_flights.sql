SELECT * FROM
(SELECT f.flight_id, f.departure_time, f.arrival_time, orgn.airport_name AS 'origin', dest.airport_name AS 'destination'
FROM flights AS f
JOIN airports AS orgn ON f.origin_airport_id = orgn.airport_id
JOIN airports AS dest ON f.destination_airport_id = dest.airport_id
WHERE f.`status` = 'Departing'
ORDER BY f.departure_time DESC
LIMIT 5) AS result
ORDER BY result.departure_time, result.flight_id;