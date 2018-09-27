DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
	
		UPDATE accounts
		SET balance = balance - money_amount
		WHERE id = from_account_id;
		
		UPDATE accounts
		SET balance = balance + money_amount
		WHERE id = to_account_id;
			
		IF money_amount < 0 
			THEN ROLLBACK;
		END IF;
		
		IF from_account_id = to_account_id 
			THEN ROLLBACK;
		END IF;
		
		IF (SELECT COUNT(*) FROM accounts WHERE id = from_account_id OR id = to_account_id) < 2 
			THEN ROLLBACK;
		END IF;

		IF (SELECT a.balance 
			FROM accounts AS a
			WHERE a.id = from_account_id) < money_amount
			THEN ROLLBACK;
		END IF;	
		
	COMMIT;	
END $$
DELIMITER ;

