SELECT COUNT(id) AS `appetizers` 
FROM products AS e 
WHERE category_id = 2 AND price > 8 
GROUP BY e.category_id;