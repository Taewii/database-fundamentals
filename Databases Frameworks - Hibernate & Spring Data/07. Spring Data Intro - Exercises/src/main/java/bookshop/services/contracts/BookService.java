package bookshop.services.contracts;

import bookshop.models.Book;

import java.text.ParseException;
import java.util.List;

public interface BookService {

    void save(Book book);

    List<String> getAllTitlesAfterYear(String date) throws ParseException;

    List<String> findBooksByAuthor(String firstName, String lastName);
}
