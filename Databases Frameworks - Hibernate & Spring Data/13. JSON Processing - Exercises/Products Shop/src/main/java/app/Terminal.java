package app;

import app.models.dtos.binding.CategoryDto;
import app.models.dtos.binding.ProductDto;
import app.models.dtos.binding.UserDto;
import app.models.dtos.view.CategoriesByProductsDto;
import app.models.dtos.view.ProductsInRangeDto;
import app.models.dtos.view.SuccessfulUserDto;
import app.services.category.CategoryService;
import app.services.product.ProductService;
import app.services.user.UserService;
import app.utils.FileIO;
import app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";

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
    public void run(String... args) {
        seedDatabase();
        exportProductsInRange();
        extractSuccessfulUsers();
        extractCategoriesByProductCount();
        //TODO Query 4
    }

    private void extractCategoriesByProductCount() {
        List<CategoriesByProductsDto> categoriesByProductsDtos = this.categoryService.categoriesByProductCount();
        this.jsonParser.objectToFile(categoriesByProductsDtos, RESOURCES_PATH + "output/categories-by-products.json");
    }

    private void extractSuccessfulUsers() {
        List<SuccessfulUserDto> userDtos = this.userService.usersWithAtleastOneProductSold();
        this.jsonParser.objectToFile(userDtos, RESOURCES_PATH + "output/users-sold-products.json");
    }

    private void exportProductsInRange() {
        List<ProductsInRangeDto> productsInRangeDtos = this.productService.productsInRange(new BigDecimal(500), new BigDecimal(1000));
        this.jsonParser.objectToFile(productsInRangeDtos, RESOURCES_PATH + "output/products-in-range.json");
    }

    private void seedDatabase() {
        String usersContent = this.fileIO.read(RESOURCES_PATH + "input/users.json");
        UserDto[] users = this.jsonParser.objectFromJson(UserDto[].class, usersContent);
        this.userService.saveAll(users);

        String categoriesContent = this.fileIO.read(RESOURCES_PATH + "input/categories.json");
        CategoryDto[] categories = this.jsonParser.objectFromJson(CategoryDto[].class, categoriesContent);
        this.categoryService.saveAll(categories);

        String productsContent = this.fileIO.read(RESOURCES_PATH + "input/products.json");
        ProductDto[] products = this.jsonParser.objectFromJson(ProductDto[].class, productsContent);
        this.productService.saveAll(products);
    }
}
