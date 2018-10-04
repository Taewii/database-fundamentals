SELECT * FROM
(
	SELECT c.id, c.`name`, COUNT(uc.user_id) AS contestants
	FROM contests AS c
	LEFT JOIN users_contests AS uc ON c.id = uc.contest_id
	GROUP BY uc.contest_id
	ORDER BY contestants DESC
	LIMIT 5
) AS r
ORDER BY r.contestants ASC, r.id 