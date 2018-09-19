SELECT DISTINCT left(first_name, 1) AS first_letter 
FROM wizzard_deposits 
WHERE deposit_group = "troll chest"
ORDER BY first_letter ASC;