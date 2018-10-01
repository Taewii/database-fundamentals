DELIMITER $$
CREATE PROCEDURE udp_change_password(email VARCHAR(100), `password` VARCHAR(100))
BEGIN 
	IF
		(SELECT COUNT(*) FROM credentials AS c WHERE c.email = email) = 0 
		THEN
			SIGNAL SQLSTATE '45000'
	      SET MESSAGE_TEXT = 'The email does\'t exist!';
	ELSE
		UPDATE credentials AS c SET c.`password` = `password` WHERE c.email = email;
	END IF;
END $$
DELIMITER ;