package bookshop.services.author;

import bookshop.models.Author;
import bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    final private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void save(List<Author> authors) {
        this.authorRepository.save(authors);
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<String> authorsByFirstNameStartingWith(final String prefix) {
        return this.authorRepository.getAuthorsByFirstNameEndsWith(prefix).stream()
                .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> authorsByNumberOfBookCopies() {
        return this.authorRepository.authorsBySoldCopies();
    }

    @Override
    public String bookCountByAuthorName(final String firstName, final String lastName) {
        int booksByAuthor = this.authorRepository.getAuthorBooksCount(firstName, lastName);

        String result;
        if (booksByAuthor == 0) {
            result = String.format("%s %s has not written any books yet", firstName, lastName);
        } else if (booksByAuthor == 1) {
            result = String.format("%s %s has written 1 book", firstName, lastName);
        } else {
            result = String.format("%s %s has written 1 books", firstName, lastName);
        }
        return result;
    }
}
