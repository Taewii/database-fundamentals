DELIMITER $$
CREATE PROCEDURE udp_submit_review(customer_id INT, review_content VARCHAR(255), review_grade INT, airline_name VARCHAR(30))
BEGIN
	DECLARE specialty CONDITION FOR SQLSTATE '45000';
	
	IF
		(SELECT COUNT(a.airline_name) FROM airlines AS a WHERE a.airline_name = airline_name) = 0
	THEN 
		SIGNAL SQLSTATE '45000'
   	SET MESSAGE_TEXT = 'Airline does not exist.';
   ELSE
   	INSERT INTO customer_reviews(review_content, review_grade, airline_id, customer_id)
  	 	VALUES (review_content, review_grade, (SELECT a.airline_id FROM airlines AS a WHERE a.airline_name = airline_name), customer_id);  
   END IF;
END $$
DELIMITER ;