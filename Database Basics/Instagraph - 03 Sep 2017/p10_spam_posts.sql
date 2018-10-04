SELECT p.id, p.caption, COUNT(c.id) AS `comments`
FROM posts AS p
LEFT JOIN comments AS c ON c.post_id = p.id
GROUP BY c.post_id
ORDER BY `comments` DESC, p.id ASC
LIMIT 5; 
-- 7/10