package bookshop.services.book;

import bookshop.models.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    void save(Book book);

    List<String> booksByAgeRestriction(String restrictionType);

    List<String> booksWithEditionTypeAndCopiesCount(String editionType, int copyCount);

    List<String> booksWithPriceLowerAndHigherThan(BigDecimal lower, BigDecimal higher);

    List<String> booksNotReleasedInGivenYear(int year);

    List<String> getBooksReleasedAfter(String date);

    List<String> getBooksContainingStr(String str);

    List<String> booksByAuthorLastNameStartingWith(String str);

    int booksWithTitleLengthShorterThan(int length);

    String bookByTitle(String title);

    int increaseBookCopiesAfterGivenDate(String dateStr, int copies);

    int deleteBooksWithCopiesLowerThan(int copyCount);
}
