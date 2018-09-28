SELECT a.airline_id, a.airline_name, a.nationality, a.rating
FROM airlines AS a
WHERE
(
	SELECT COUNT(*)
	FROM flights
	WHERE a.airline_id = airline_id
) > 0
ORDER BY rating DESC, airline_id ASC
LIMIT 5; 