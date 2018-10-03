UPDATE repositories_contributors
SET repository_id = (SELECT lowest_id.id FROM (SELECT r.id FROM repositories_contributors AS rc
							RIGHT  JOIN repositories AS r ON r.id = rc.repository_id
							WHERE rc.contributor_id IS NULL
							ORDER BY rc.repository_id ASC
							LIMIT 1) AS lowest_id)
WHERE repository_id = contributor_id