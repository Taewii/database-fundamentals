DELIMITER $$
CREATE PROCEDURE udp_evaluate(id INT)
BEGIN	
	IF (SELECT EXISTS(SELECT * FROM submissions AS s WHERE s.id = id)) = 0
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Submission does not exist!';
	END IF;
	
	INSERT INTO evaluated_submissions(id, problem, `user`, result)
	SELECT s.id, p.`name`, u.username, CEIL(p.points / p.tests * s.passed_tests)
	FROM
	(SELECT * FROM submissions AS s1 WHERE s1.id = id) AS s
	JOIN problems AS p ON s.problem_id = p.id
	JOIN users AS u ON s.user_id = u.id;
END $$
DELIMITER ;