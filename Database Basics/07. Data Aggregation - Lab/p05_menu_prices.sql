SELECT category_id, ROUND(AVG(price), 2) AS 'avg', ROUND(MIN(price), 2) AS 'min', ROUND(MAX(price), 2) AS 'max'
FROM products AS e
GROUP BY e.category_id;