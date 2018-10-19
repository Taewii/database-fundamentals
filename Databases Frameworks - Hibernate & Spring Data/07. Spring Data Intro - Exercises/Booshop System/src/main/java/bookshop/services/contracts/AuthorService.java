package bookshop.services.contracts;

import bookshop.models.Author;

import java.text.ParseException;
import java.util.List;

public interface AuthorService {

    void save(List<Author> authors);

    List<Author> getAll();

    List<String> getAllTitlesAfterYear(String dateStr) throws ParseException;

    List<String> authorsOrderedByBookCountDescending();
}
