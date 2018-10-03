DELIMITER $$
CREATE PROCEDURE udp_findbyextension (extension VARCHAR(30))
BEGIN
	SELECT f.id, f.`name`, CONCAT(f.size, 'KB') AS size
	FROM files AS f
	WHERE f.`name` LIKE CONCAT('%', extension)
	ORDER BY f.id ASC;
END $$
DELIMITER ;