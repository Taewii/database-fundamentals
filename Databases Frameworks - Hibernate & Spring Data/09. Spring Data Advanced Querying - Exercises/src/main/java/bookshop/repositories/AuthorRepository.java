package bookshop.repositories;

import bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAuthorsByFirstNameEndsWith(String prefix);

    @Query(value =
            "SELECT CONCAT(a.firstName, ' ', a.lastName, ' - ', SUM (b.copies)) " +
            "FROM Author AS a " +
            "JOIN a.books AS b " +
            "GROUP BY a.id " +
            "ORDER BY SUM (b.copies) DESC")
    List<String> authorsBySoldCopies();

    //it can't find the function for some reason
    @Procedure(name = "usp_total_books_written")
    int getAuthorBooksCount(@Param("first_name") String firstName, @Param("last_name") String lastName);
}
