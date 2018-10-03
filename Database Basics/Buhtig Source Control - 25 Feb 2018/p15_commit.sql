DELIMITER $$
CREATE PROCEDURE udp_commit(username VARCHAR(30), `password` VARCHAR(30), message VARCHAR(255), issue_id INT)
BEGIN
	IF (SELECT EXISTS(SELECT u.username FROM users AS u WHERE u.username = username)) = 0 
	THEN  SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No such user!';
	END IF;
	
	IF `password` != (SELECT u.`password` FROM users AS u WHERE u.username = username)
	THEN  SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Password is incorrect!';
	END IF;
	
	IF (SELECT EXISTS(SELECT i.id FROM issues AS i WHERE i.id = issue_id)) = 0 
	THEN  SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The issue does not exist!';
	END IF;
	
	INSERT INTO commits(message, issue_id, repository_id, contributor_id)
	SELECT message, issue_id,
	(SELECT repository_id FROM issues WHERE id = issue_id) AS repository_id,
	(SELECT id FROM users AS u WHERE u.username = username) AS contributor_id;
	
	UPDATE issues SET issue_status = 'closed' WHERE id = issue_id;
END $$
DELIMITER ;