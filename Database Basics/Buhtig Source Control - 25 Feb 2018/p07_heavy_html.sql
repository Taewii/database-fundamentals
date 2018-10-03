SELECT id, `name`, size
FROM files AS f
WHERE f.size > 1000 AND f.`name` LIKE '%html%'
ORDER BY f.size DESC;