DROP FUNCTION if EXISTS ufn_calculate_future_value;
CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(19, 4), interest_rate DECIMAL(19, 4), years INT)
RETURNS DOUBLE(19, 2)
RETURN initial_sum * (POW((1 + interest_rate), years));

SELECT ufn_calculate_future_value(1000, 0.1, 5) AS outout;