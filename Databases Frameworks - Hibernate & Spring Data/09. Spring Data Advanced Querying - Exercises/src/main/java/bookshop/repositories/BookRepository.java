package bookshop.repositories;

import bookshop.dtos.ReducedBook;
import bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByAgeRestriction(String ageRestriction);

    List<Book> getBooksByEditionTypeAndCopiesBefore(String editionType, int copies);

    List<Book> getBooksByPriceBeforeOrPriceAfter(BigDecimal before, BigDecimal after);

    @Query(value = "SELECT b FROM Book AS b WHERE FUNCTION('YEAR', b.releaseDate) <> :year")
    List<Book> getBooksByReleaseDateNotIn(@Param("year") int year);

    List<Book> getBooksByReleaseDateBefore(Date date);

    List<Book> getBooksByTitleContains(String str);

    @Query(value = "SELECT b FROM Book AS b WHERE b.author.lastName LIKE :str%")
    List<Book> booksWithAuthorLastNameStartingWith(@Param("str") String str);

    @Query(value = "SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > :lngth")
    int booksWithTitleLengthShorterThan(@Param("lngth") int length);

    @Query(value =
            "SELECT new bookshop.dtos.ReducedBook(b.title, b.editionType, b.ageRestriction, b.price) " +
            "FROM Book AS b WHERE b.title = :title")
    ReducedBook bookByTitle(@Param("title") String title);

    @Modifying
    @Query(value = "UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int increaseBookCopiesAfterDate(@Param("date") Date date, @Param("copies") int copies);

    @Modifying
    int removeBookByCopiesBefore(int copyCount);
}
