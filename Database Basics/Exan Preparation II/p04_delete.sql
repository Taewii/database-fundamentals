delete l from locations as l
WHERE l.id NOT IN
						(SELECT u.location_id FROM users as u
						WHERE u.location_id IS NOT NULL)