SELECT deposit_group, SUM(deposit_amount) AS total_sum
FROM wizzard_deposits AS e
WHERE magic_wand_creator = "Ollivander family"
GROUP BY e.deposit_group
ORDER BY deposit_group ASC;