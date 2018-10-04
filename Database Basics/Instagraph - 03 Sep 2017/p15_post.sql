DELIMITER $$
CREATE PROCEDURE udp_post(username VARCHAR(30), `password` VARCHAR(30), caption VARCHAR(255), path VARCHAR(255))
BEGIN
	IF (SELECT `password` FROM users AS u WHERE u.username = username) != `password`
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Password is incorrect!';
	END IF;
	
	IF (SELECT EXISTS(SELECT * FROM pictures AS p WHERE p.path = path)) = 0
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The picture does not exist!';
	END IF;
	
	INSERT INTO posts(caption, user_id, picture_id)
	SELECT
	caption AS caption,
	(SELECT id FROM users AS u WHERE u.username = username) AS id,
	(SELECT id FROM pictures AS p WHERE p.path = path) AS picture_id;
END $$
DELIMITER ;