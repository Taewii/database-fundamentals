SELECT r.id, r.`name`, COUNT(i.id) AS `issues`
FROM repositories AS r
JOIN issues AS i ON i.repository_id = r.id
GROUP BY i.repository_id
ORDER BY `issues` DESC, r.id ASC
LIMIT 5;