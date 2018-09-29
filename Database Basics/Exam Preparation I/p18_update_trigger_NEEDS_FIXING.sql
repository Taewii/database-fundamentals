DELIMITER $$
CREATE TRIGGER tr_arrived_flights
AFTER UPDATE ON flights
FOR EACH ROW
BEGIN
	IF NEW.`status` <> OLD.`status` THEN
		IF NEW.`status` = 'Arrived' AND OLD.`status` = 'Departing'
		THEN
		INSERT INTO arrived_flights(flight_id, arrival_time, origin, destination, passengers )
		VALUES(NEW.flight_id, NEW.arrival_time, 
			(
				SELECT a.airport_name
				FROM airports AS a
				WHERE a.airport_id = NEW.origin_airport_id
			), 
			(
				SELECT a.airport_name
				FROM airports AS a
				WHERE a.airport_id = NEW.destination_airport_id
			), 
			(
				SELECT COUNT(c.customer_id)
				FROM customers AS c
				JOIN tickets AS t ON c.customer_id = t.customer_id
				JOIN flights AS f ON f.flight_id = t.flight_id
				WHERE f.flight_id = NEW.flight_id ));
		END IF;
	END IF;
END $$
DELIMITER ;	