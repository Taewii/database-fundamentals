package bookshop.services.author;

import bookshop.models.Author;

import java.util.List;

public interface AuthorService {

    void save(List<Author> authors);

    List<Author> getAll();

    List<String> authorsByFirstNameStartingWith(String prefix);

    List<String> authorsByNumberOfBookCopies();

    String bookCountByAuthorName(String firstName, String lastName);
}
