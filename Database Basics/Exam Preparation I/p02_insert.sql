INSERT INTO flights(departure_time, arrival_time, `status`, 
origin_airport_id, destination_airport_id, airline_id)
SELECT 
'2017-06-19 14:00:00' AS departure_time, 
'2017-06-21 11:00:00' AS arrival_time,
(CASE
	when airline_id % 4 = 0 then 'Departing'
	when airline_id % 4 = 1 then 'Delayed'
	when airline_id % 4 = 2 then 'Arrived'
	when airline_id % 4 = 3 then 'Canceled'
END
) AS `status`,
CEIL(SQRT(LENGTH(airline_name))) AS origin_airport_id,
CEIL(SQRT(LENGTH(nationality))) AS	destination_airport_id,
airline_id AS airline_id
FROM airlines
WHERE airline_id BETWEEN 1 AND 10;
 