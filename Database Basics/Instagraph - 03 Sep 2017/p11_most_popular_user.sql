SELECT u.id, u.username, COUNT(p.id), cn.followers
FROM 
	(
		SELECT uf.user_id, COUNT(uf.follower_id) AS followers
		FROM users_followers AS uf
		GROUP BY uf.user_id
	) AS cn
JOIN users AS u ON u.id = cn.user_id
JOIN posts AS p ON p.user_id = u.id
GROUP BY cn.user_id
ORDER BY cn.followers DESC
LIMIT 1;