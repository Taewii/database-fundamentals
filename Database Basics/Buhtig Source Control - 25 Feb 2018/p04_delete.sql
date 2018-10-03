DELETE r FROM repositories AS r
LEFT JOIN issues AS i ON i.repository_id = r.id
WHERE i.id IS NULL;