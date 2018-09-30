DELIMITER $$
CREATE TRIGGER t_updated_arrivals
BEFORE UPDATE ON flights
FOR EACH ROW
BEGIN
    DECLARE passengers INT;
    DECLARE origin VARCHAR(50);
    DECLARE destination VARCHAR(50);
    SET passengers := (SELECT COUNT(t.ticket_id) FROM tickets AS t
    INNER JOIN flights AS f ON t.flight_id = new.flight_id AND f.flight_id = new.flight_id);
   
    SET origin := (SELECT airport_name FROM airports AS a
        where new.origin_airport_id = a.airport_id);
 
    SET destination := (SELECT airport_name FROM airports AS a
        where new.destination_airport_id = a.airport_id);
 
    IF(old.`status` = 'Departing' OR old.`status` = 'Delayed') THEN
        INSERT INTO arrived_flights(flight_id, arrival_time,origin, destination, passengers) 
		  VALUES (new.flight_id, new.arrival_time, origin, destination, passengers);
    END IF;
END $$
DELIMITER ;