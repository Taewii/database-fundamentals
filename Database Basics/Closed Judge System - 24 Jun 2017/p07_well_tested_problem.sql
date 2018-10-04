SELECT id, `name`, tests
FROM problems
WHERE tests > points AND  `name` LIKE  '% %'
ORDER BY id DESC; 