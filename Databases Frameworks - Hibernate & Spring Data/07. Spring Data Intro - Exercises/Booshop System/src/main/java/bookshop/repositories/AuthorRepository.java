package bookshop.repositories;

import bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value =
            "SELECT concat(authors.first_name, ' ', authors.last_name) AS full_name\n" +
                    "FROM (SELECT a.first_name, a.last_name, count(b.id)\n" +
                    "      FROM authors AS a\n" +
                    "             JOIN books b ON a.id = b.author_id AND b.release_date < :date\n" +
                    "      GROUP BY a.first_name, a.last_name, a.id) AS authors\n" +
                    "ORDER BY authors.first_name ASC;", nativeQuery = true)
    List<String> authorNamesWithBooksBefore(@Param("date") Date date);

    @Query(value =
            "SELECT authors.id, authors.first_name, authors.last_name\n" +
                    "FROM (SELECT a.id, a.first_name, a.last_name, count(b.id) AS count\n" +
                    "      FROM authors AS a\n" +
                    "             LEFT JOIN books AS b ON a.id = b.author_id\n" +
                    "      GROUP BY a.id, a.first_name, a.last_name) AS authors\n" +
                    "ORDER BY authors.count DESC", nativeQuery = true)
    List<Author> authorsOrderedByBookCountDescending();
}
