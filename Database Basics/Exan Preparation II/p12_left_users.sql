SELECT m.id, m.chat_id, m.user_id
FROM messages AS m
LEFT JOIN users_chats AS uc ON m.user_id = uc.user_id AND m.chat_id = uc.chat_id
WHERE m.chat_id = 17 AND uc.chat_id IS NULL
ORDER BY m.id DESC;