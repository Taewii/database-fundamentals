DELIMITER $$
CREATE PROCEDURE udp_filter(hashtag VARCHAR(255))
BEGIN
	SELECT p.id, p.caption, u.username
	FROM posts AS p
	JOIN users AS u ON u.id = p.user_id
	WHERE p.caption LIKE CONCAT('%#', hashtag, '%')
	ORDER BY p.id ASC; 
END $$
DELIMITER ;