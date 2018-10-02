SELECT p.id, CONCAT(ct.`name`, ' - ', c.`name`, ' - ', p.`name`) AS full_path
FROM problems AS p
JOIN contests AS c ON c.id = p.contest_id
JOIN categories AS ct ON ct.id = c.category_id
ORDER BY p.id ASC