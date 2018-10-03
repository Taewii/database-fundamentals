SELECT i.id, CONCAT(u.username, ' : ', i.title)
FROM issues AS i
JOIN users AS u ON u.id = i.assignee_id
ORDER BY i.id DESC;