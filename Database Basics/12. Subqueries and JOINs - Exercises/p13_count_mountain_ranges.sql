SELECT c.country_code, COUNT(c.country_code) AS mountain_range
FROM countries AS c
JOIN mountains_countries AS mc ON c.country_code = mc.country_code
GROUP BY mc.country_code
HAVING mc.country_code IN('BG', 'US', 'RU')
ORDER BY mountain_range DESC;
