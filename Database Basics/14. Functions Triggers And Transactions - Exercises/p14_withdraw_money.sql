DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
	
		UPDATE accounts
		SET balance = balance - money_amount
		WHERE id = account_id;
			
		IF money_amount < 0 
			THEN ROLLBACK;
		END IF;

		IF (SELECT a.balance 
			FROM accounts AS a
			WHERE a.id = account_id) < 0
			THEN ROLLBACK;
		END IF;	
		
	COMMIT;	
END $$
DELIMITER ;

