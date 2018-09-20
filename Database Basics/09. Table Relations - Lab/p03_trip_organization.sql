SELECT driver_id, vehicle_type, CONCAT(first_name, ' ', last_name) AS driver_name
FROM vehicles AS a
JOIN campers AS b ON a.driver_id = b.id;