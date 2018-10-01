SELECT c.title, m.content
FROM chats AS c
LEFT JOIN messages AS m ON m.chat_id = c.id
WHERE c.id = (SELECT id FROM chats ORDER BY start_date DESC LIMIT 1)
ORDER BY m.sent_on ASC, m.id ASC;