package bookshop.services.impl;

import bookshop.models.Author;
import bookshop.repositories.AuthorRepository;
import bookshop.services.contracts.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
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
    public List<String> getAllTitlesAfterYear(String dateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        return this.authorRepository.authorNamesWithBooksBefore(date);
    }

    @Override
    public List<String> authorsOrderedByBookCountDescending() {
        return this.authorRepository
                .authorsOrderedByBookCountDescending()
                .stream()
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()))
                .collect(Collectors.toList());
    }
}
