SELECT u.id, u.username, p.caption
FROM users AS u
JOIN posts AS p ON p.user_id = u.id
WHERE p.id =  (SELECT r.post_id FROM (SELECT c.post_id, COUNT(c.id) AS `count`
					FROM comments AS c
					JOIN posts AS p ON c.post_id = p.id
					WHERE p.user_id = 1
					GROUP BY c.post_id
					ORDER BY `count` DESC, p.id ASC
					LIMIT 1) AS r)
					
					-- cant figure out how to use u.id in p.user_id = 1... still gives 7/10 for some reason tho