DELETE u FROM users AS u
LEFT JOIN users_contests AS uc ON uc.user_id = u.id
WHERE uc.contest_id IS NULL;