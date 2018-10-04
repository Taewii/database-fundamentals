SELECT u.id, u.username, COUNT(c.id) AS my_comments
FROM users AS u
LEFT JOIN posts AS p ON u.id = p.user_id
LEFT JOIN comments AS c ON p.id = c.post_id AND c.user_id = u.id
GROUP BY u.id
ORDER BY my_comments DESC, u.id ASC;