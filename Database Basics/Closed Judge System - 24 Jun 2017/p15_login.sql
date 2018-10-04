DELIMITER $$
CREATE PROCEDURE udp_login(username VARCHAR(30), `password` VARCHAR(30))
BEGIN
	IF (SELECT EXISTS(SELECT * FROM users AS u WHERE u.username = `username`)) = 0
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Username does not exist!';
	END IF;
	
	IF `password` != (SELECT `password` FROM users AS u WHERE u.username = `username`)
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '‘Password is incorrect!';
	END IF;
	
	IF (SELECT EXISTS(SELECT * FROM logged_in_users AS u WHERE u.username = `username`)) = 1
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User is already logged in!';
	END IF;
	
	INSERT INTO logged_in_users(id, username, `password`, email)
	SELECT u.id, username, `password`, u.email
	FROM users AS u
	WHERE u.username = username;
END $$
DELIMITER ; 