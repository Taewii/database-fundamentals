package app;

import app.models.dtos.CategoryDto;
import app.models.dtos.ProductDto;
import app.models.dtos.UserDto;
import app.services.category.CategoryService;
import app.services.product.ProductService;
import app.services.user.UserService;
import app.utils.FileIO;
import app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final JsonParser jsonParser;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public Terminal(FileIO fileIO,
                    JsonParser jsonParser,
                    UserService userService,
                    CategoryService categoryService,
                    ProductService productService) {
        this.fileIO = fileIO;
        this.jsonParser = jsonParser;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        String usersContent = this.fileIO.read("/input/users.json");
        UserDto[] users = this.jsonParser.objectFromJson(UserDto[].class, usersContent);
        this.userService.saveAll(users);

        String categoriesContent = this.fileIO.read("/input/categories.json");
        CategoryDto[] categories = this.jsonParser.objectFromJson(CategoryDto[].class, categoriesContent);
        this.categoryService.saveAll(categories);

        String productsContent = this.fileIO.read("/input/products.json");
        ProductDto[] products = this.jsonParser.objectFromJson(ProductDto[].class, productsContent);
        this.productService.saveAll(products);
    }
}
