SELECT r.id, r.`name`, COUNT(DISTINCT c.contributor_id) AS `users`
FROM repositories AS r
left JOIN commits AS c ON c.repository_id = r.id
GROUP BY r.id
ORDER BY `users` DESC, r.id ASC;