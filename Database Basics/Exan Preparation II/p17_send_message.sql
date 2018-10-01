DELIMITER $$
CREATE PROCEDURE udp_send_message(user_id INT, chat_id INT, content VARCHAR(200))
BEGIN
	IF (SELECT COUNT(*) FROM users_chats AS uc WHERE uc.user_id = user_id) = 0
		THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'There is no chat with that user!';
   ELSE
   	INSERT INTO messages(content, sent_on, chat_id, user_id)
   	VALUES(content, '2016-12-15', chat_id, user_id);
   END IF;
END $$
DELIMITER ; 