SELECT id, `name`
FROM categories
WHERE parent_id IS NULL
ORDER BY id ASC; 