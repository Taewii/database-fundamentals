DELIMITER $$
CREATE TRIGGER delete_user
BEFORE DELETE ON users
FOR EACH ROW
BEGIN
	DELETE FROM messages WHERE user_id = OLD.id;
	DELETE FROM users_chats WHERE user_id = OLD.id;
	DELETE FROM messages_log WHERE user_id = OLD.id;
END $$
DELIMITER ;