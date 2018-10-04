SELECT DISTINCT  u1.id, u1.username, CONCAT(p.size, 'KB') AS size
FROM users AS u1
JOIN users AS u2
JOIN pictures AS p ON p.id = u1.profile_picture_id
WHERE u1.profile_picture_id = u2.profile_picture_id 
AND u1.id <> u2.id
ORDER BY u1.id ASC;