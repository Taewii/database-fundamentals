SELECT id, path, size
FROM pictures
WHERE size > 50000 AND (path LIKE '%jpeg%' OR path LIKE '%png%')
ORDER BY size DESC;