SELECT DISTINCT u.id, u.username, u.`password`
FROM users AS u
WHERE u.`password` IN (SELECT u1.`password`
        					  FROM users AS u1
        					  WHERE u.id <> u1.id)
ORDER BY u.username, u.id;