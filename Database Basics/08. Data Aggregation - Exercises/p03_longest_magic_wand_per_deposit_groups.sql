SELECT deposit_group, MAX(magic_wand_size) AS 'longest_magic_wand' 
FROM wizzard_deposits AS e
GROUP BY e.deposit_group
ORDER BY longest_magic_wand ASC, e.deposit_group ASC;