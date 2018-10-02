SELECT s.id, u.username, p.`name`, CONCAT(s.passed_tests, ' / ', p.tests) AS result
FROM submissions AS s
JOIN users AS u ON s.user_id = u.id 
AND u.id = 
	(
		SELECT uc.user_id
		FROM users_contests AS uc
		GROUP BY uc.user_id
		ORDER BY COUNT(uc.user_id) DESC
		LIMIT 1
	)
JOIN problems AS p ON s.problem_id = p.id
ORDER BY s.id DESC;