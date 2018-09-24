SELECT c.country_name, r.river_name
FROM countries AS c
LEFT JOIN countries_rivers AS cr ON c.country_code = cr.country_code
LEFT JOIN rivers AS r ON r.id = cr.river_id
JOIN continents AS cn ON c.continent_code = cn.continent_code
WHERE cn.continent_name = 'Africa'
ORDER BY c.country_name ASC
LIMIT 5;