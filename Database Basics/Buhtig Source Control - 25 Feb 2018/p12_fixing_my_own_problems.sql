SELECT u.id, u.username, SUM(IF(c.contributor_id = u.id, 1, 0)) AS `commits`
FROM users AS u
LEFT JOIN issues AS i ON u.id = i.assignee_id
LEFT JOIN commits AS c ON i.id = c.issue_id
GROUP BY u.id
ORDER BY `commits` DESC, u.id ASC;