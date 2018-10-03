DELIMITER $$
CREATE trigger tr_insert_problem
BEFORE INSERT ON problems
FOR EACH ROW 
BEGIN	
	IF NEW.`name` NOT LIKE '% %'
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The given name is invalid!';
	END IF;
	
	IF NEW.`points` <= 0
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The problem’s points cannot be less or equal to 0!';
	END IF;
	
	IF NEW.`tests` <= 0
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The problem’s tests cannot be less or equal to 0!';
	END IF;
END $$ 
DELIMITER ;