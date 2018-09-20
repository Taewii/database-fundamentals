SELECT 
	starting_point, 
	end_point, 
	leader_id,
	CONCAT(first_name, ' ', last_name) AS leader_name
FROM routes AS a
JOIN campers AS b ON a.leader_id = b.id;