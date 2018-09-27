CREATE PROCEDURE usp_get_holders_full_name()
	SELECT CONCAT(first_name, ' ', last_name) AS full_name
	FROM account_holders
	ORDER BY full_name ASC
	
	
CALL usp_get_holders_full_name