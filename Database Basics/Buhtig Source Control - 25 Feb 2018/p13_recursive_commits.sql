SELECT 
SUBSTRING_INDEX(f.name, '.', 1) AS `file`,
COUNT(nullif(LOCATE(f.name, c.message), 0)) AS recursive_count
FROM files AS f
JOIN files AS p ON f.parent_id = p.id
JOIN commits AS c
WHERE f.id <> p.id
AND f.parent_id = p.id
AND p.parent_id = f.id
GROUP BY f.name
ORDER BY f.name;