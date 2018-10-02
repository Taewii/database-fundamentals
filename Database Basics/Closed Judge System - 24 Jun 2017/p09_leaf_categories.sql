SELECT c.id, c.`name`
FROM categories AS c
LEFT JOIN categories AS c2 ON c.id = c2.parent_id
WHERE c2.id IS NULL 
ORDER BY c.`name` ASC, c.id ASC;