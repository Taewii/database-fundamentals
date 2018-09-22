SELECT t.town_id, t.NAME, a.address_text
FROM towns AS t
JOIN addresses AS a ON t.town_id = a.town_id
WHERE t.NAME = "Sofia" OR t.NAME = "San Francisco" OR t.NAME = "Carnation"
ORDER BY town_id ASC, a.address_id ASC;