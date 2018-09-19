SELECT 
	SUM(`host`.`deposit_amount` - `guest`.`deposit_amount`) AS 'sum_difference'
FROM
	`wizzard_deposits` AS `host`,
	`wizzard_deposits` AS `guest`
WHERE
	`guest`.`id` - `host`.`id` = 1;