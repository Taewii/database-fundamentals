package bookshop;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.models.Author;
import bookshop.models.Book;
import bookshop.models.Category;
import bookshop.services.contracts.AuthorService;
import bookshop.services.contracts.BookService;
import bookshop.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String PATH = System.getProperty("user.dir") + "/src/main/resources/";

    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws IOException, ParseException {
        seedAuthors();
        seedCategories();
        seedBooks();
        booksAfter2000("2000-01-01");
        authorWithBooksBefore1990("1990-01-01");
        authorsOrderedByBookCountDescending();
        booksByAuthor("George", "Powell");
    }

    private void seedAuthors() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(PATH + "authors.txt"));
        List<Author> authors = lines.stream().map(a -> {
            String[] names = a.split("\\s+");
            return new Author(names[0], names[1]);
        }).collect(Collectors.toList());

        this.authorService.save(authors);
    }

    private void seedCategories() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(PATH + "categories.txt"));
        List<Category> categories = lines.stream()
                .filter(s -> !s.isEmpty())
                .map(Category::new)
                .collect(Collectors.toList());

        this.categoryService.save(categories);
    }

    private void seedBooks() throws IOException, ParseException {
        Random random = new Random();

        List<Author> authors = this.authorService.getAll();
        List<Category> categories = this.categoryService.getAll();

        BufferedReader booksReader = new BufferedReader(new FileReader(PATH + "books.txt"));
        String line;
        while ((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType.toString());
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction.toString());
            book.setTitle(title);
            book.getCategories().add(categories.get(random.nextInt(categories.size())));

            this.bookService.save(book);
        }
    }

    private void booksAfter2000(String date) throws ParseException {
        List<String> bookTitles = this.bookService.getAllTitlesAfterYear(date);
        System.out.print(String.join("\n", bookTitles));
    }

    private void authorWithBooksBefore1990(String date) throws ParseException {
        List<String> authors = this.authorService.getAllTitlesAfterYear(date);
        System.out.print(String.join("\n", authors));
    }

    private void authorsOrderedByBookCountDescending() {
        List<String> authors = this.authorService.authorsOrderedByBookCountDescending();
        System.out.print(String.join("\n", authors));
    }

    private void booksByAuthor(String firstName, String lastName) {
        List<String> books = this.bookService.findBooksByAuthor(firstName, lastName);
        System.out.print(String.join("\n", books));
    }
}
