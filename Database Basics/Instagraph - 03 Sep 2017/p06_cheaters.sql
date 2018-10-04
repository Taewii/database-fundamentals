SELECT u.id, u.username
FROM users AS u
JOIN users_followers AS uc ON uc.user_id = u.id
WHERE uc.user_id = uc.follower_id
ORDER BY u.id ASC;