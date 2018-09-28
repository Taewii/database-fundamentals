UPDATE tickets AS t
JOIN flights AS f ON f.flight_id = t.flight_id
JOIN airlines AS a ON a.airline_id = f.airline_id 
SET price = price * 1.5
WHERE a.airline_id = (SELECT airline_id AS max_rating
							 FROM airlines
						 	 WHERE rating = (SELECT MAX(rating) 
												  FROM airlines));