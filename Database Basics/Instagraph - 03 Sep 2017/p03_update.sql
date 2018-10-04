UPDATE users AS u
SET profile_picture_id = if((SELECT COUNT(follower_id)FROM users_followers WHERE user_id = id) > 0, (SELECT COUNT(follower_id)FROM users_followers WHERE user_id = id), id)
WHERE u.profile_picture_id IS NULL;