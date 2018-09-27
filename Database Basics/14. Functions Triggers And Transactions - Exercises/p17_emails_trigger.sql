CREATE TABLE `logs` (
    log_id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INT(11) NOT NULL,
    old_sum DECIMAL(19, 4) NOT NULL,
    new_sum DECIMAL(19, 4) NOT NULL
);

CREATE TABLE notification_emails(
	id INT PRIMARY KEY AUTO_INCREMENT,
	recipient INT,
	subject VARCHAR(50),
	body TEXT
);

DELIMITER $$
CREATE TRIGGER `tr_notification_emails`
AFTER INSERT ON `logs`
FOR EACH ROW
BEGIN
    INSERT INTO `notification_emails`(`recipient`, `subject`, `body`)
    VALUES (
        NEW.account_id, 
        CONCAT('Balance change for account: ', NEW.account_id), 
        CONCAT('On ', DATE_FORMAT(NOW(), '%b %d %Y at %r'), ' your balance was changed from ', ROUND(NEW.old_sum, 2), ' to ', ROUND(NEW.new_sum, 2), '.'));
END $$
DELIMITER ;