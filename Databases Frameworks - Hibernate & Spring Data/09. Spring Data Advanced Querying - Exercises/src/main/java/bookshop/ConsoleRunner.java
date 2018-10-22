package bookshop;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.models.Author;
import bookshop.models.Book;
import bookshop.models.Category;
import bookshop.services.author.AuthorService;
import bookshop.services.book.BookService;
import bookshop.services.category.CategoryService;
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

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String AUTHORS_FILE = "authors.txt";
    private static final String BOOKS_FILE = "books.txt";
    private static final String CATEGORIES_FILE = "categories.txt";

    final private AuthorService authorService;
    final private BookService bookService;
    final private CategoryService categoryService;

    @Autowired
    public ConsoleRunner(final AuthorService authorService,
                         final BookService bookService,
                         final CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
        seedAuthors();
        seedCategories();
        seedBooks();

        //----------1----------
        System.out.println(String.join("\r\n",
                this.bookService.booksByAgeRestriction("teEN")));

        //----------2----------
        System.out.println(String.join("\r\n",
                this.bookService.booksWithEditionTypeAndCopiesCount("golD", 5000)));

        //----------3----------
        System.out.println(String.join("\r\n",
                this.bookService.booksWithPriceLowerAndHigherThan(new BigDecimal(5), new BigDecimal(40))));

        //----------4----------
        System.out.println(String.join("\r\n",
                this.bookService.booksNotReleasedInGivenYear(1998)));

        //----------5----------
        System.out.println(String.join("\r\n",
                this.bookService.getBooksReleasedAfter("12-04-1992")));

        //----------6----------
        System.out.println(String.join("\r\n",
                this.authorService.authorsByFirstNameStartingWith("dy")));

        //----------7----------
        System.out.println(String.join("\r\n",
                this.bookService.getBooksContainingStr("WOR")));

        //----------8----------
        System.out.println(String.join("\r\n",
                this.bookService.booksByAuthorLastNameStartingWith("R")));

        //----------9----------
        System.out.println(this.bookService.booksWithTitleLengthShorterThan(12));

        //----------10----------
        System.out.println(String.join("\r\n",
                this.authorService.authorsByNumberOfBookCopies()));

        //----------11----------
        System.out.println(this.bookService.bookByTitle("Thrones"));

        //----------12----------
        System.out.println(this.bookService.increaseBookCopiesAfterGivenDate("06 Jun 2013", 44));

        //----------13----------
        System.out.println(String.format("%d books were deleted",
                this.bookService.deleteBooksWithCopiesLowerThan(300)));

        //----------14----------
        //todo Says it can't find the function for some reason and blows up.
        System.out.println(this.authorService.bookCountByAuthorName("Amanda", "Rice"));
    }

    private void seedAuthors() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(RESOURCES_PATH + AUTHORS_FILE));
        List<Author> authors = lines.stream().map(a -> {
            String[] names = a.split("\\s+");
            return new Author(names[0], names[1]);
        }).collect(Collectors.toList());

        this.authorService.save(authors);
    }

    private void seedCategories() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(RESOURCES_PATH + CATEGORIES_FILE));
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

        BufferedReader booksReader = new BufferedReader(new FileReader(RESOURCES_PATH + BOOKS_FILE));
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
}