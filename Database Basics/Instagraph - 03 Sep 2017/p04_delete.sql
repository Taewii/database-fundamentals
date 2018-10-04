DELETE u FROM users AS u
WHERE u.id NOT IN (SELECT user_id FROM users_followers) 
AND u.id NOT IN (SELECT follower_id FROM users_followers)