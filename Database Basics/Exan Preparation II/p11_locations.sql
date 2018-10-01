SELECT u.id, u.nickname, u.age
FROM users AS u
LEFT JOIN locations AS l ON l.id = u.location_id
WHERE l.id IS NULL
ORDER BY u.id ASC;
