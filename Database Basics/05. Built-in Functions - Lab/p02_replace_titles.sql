UPDATE books SET title = REPLACE(title, "The", "***") WHERE LEFT(title, 3) = "The";
SELECT title FROM books WHERE LEFT(title, 3) = "***" ORDER BY id ASC;