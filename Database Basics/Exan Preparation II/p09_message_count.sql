SELECT c.id, COUNT(m.id) AS total_messages
FROM chats AS c
JOIN messages AS m ON m.chat_id = c.id
WHERE m.id < 90
GROUP BY m.chat_id
ORDER BY total_messages DESC, c.id ASC
LIMIT 5