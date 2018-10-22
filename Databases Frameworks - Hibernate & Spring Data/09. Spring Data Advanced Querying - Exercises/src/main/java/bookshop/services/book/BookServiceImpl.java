package bookshop.services.book;

import bookshop.dtos.ReducedBook;
import bookshop.models.Book;
import bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    final private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public List<String> booksByAgeRestriction(final String restrictionType) {
        return this.bookRepository.getBooksByAgeRestriction(restrictionType.toUpperCase())
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> booksWithEditionTypeAndCopiesCount(final String editionType, final int copyCount) {
        return this.bookRepository.getBooksByEditionTypeAndCopiesBefore(editionType.toUpperCase(), copyCount)
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> booksWithPriceLowerAndHigherThan(final BigDecimal lower, final BigDecimal higher) {
        return this.bookRepository.getBooksByPriceBeforeOrPriceAfter(lower, higher)
                .stream().map(b -> String.format("%s - $%s", b.getTitle(), b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> booksNotReleasedInGivenYear(final int year) {
        return this.bookRepository.getBooksByReleaseDateNotIn(year)
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksReleasedAfter(final String date) {
        Date dt = null;
        try {
            dt = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.bookRepository.getBooksByReleaseDateBefore(dt).stream()
                .map(b -> String.format("%s - %s - $%s", b.getTitle(), b.getEditionType(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksContainingStr(final String str) {
        return this.bookRepository.getBooksByTitleContains(str).stream()
                .map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> booksByAuthorLastNameStartingWith(final String str) {
        return this.bookRepository.booksWithAuthorLastNameStartingWith(str).stream()
                .map(b -> String.format("%s (%s %s)",
                        b.getTitle().trim(),
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int booksWithTitleLengthShorterThan(final int length) {
        return this.bookRepository.booksWithTitleLengthShorterThan(length);
    }

    @Override
    public String bookByTitle(final String title) {
        ReducedBook book = this.bookRepository.bookByTitle(title);
        return String.format("%s %s %s %s",
                book.getTitle().trim(),
                book.getEditionType(),
                book.getAgeRegistration(),
                book.getPrice());
    }

    @Override
    public int increaseBookCopiesAfterGivenDate(final String dateStr, final int copies) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(dateStr);
            System.out.println();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int affectedBooks = this.bookRepository.increaseBookCopiesAfterDate(date, copies);
        return affectedBooks * copies;
    }

    @Override
    public int deleteBooksWithCopiesLowerThan(final int copyCount) {
        return this.bookRepository.removeBookByCopiesBefore(copyCount);
    }
}
