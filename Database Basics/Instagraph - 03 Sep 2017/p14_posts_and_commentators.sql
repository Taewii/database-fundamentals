SELECT p.id, p.caption, COUNT(DISTINCT u.id) AS `users`
FROM posts AS p
LEFT JOIN comments AS c ON c.post_id = p.id
LEFT JOIN users AS u ON c.user_id = u.id
GROUP BY p.id
ORDER BY `users` DESC, p.id ASC;