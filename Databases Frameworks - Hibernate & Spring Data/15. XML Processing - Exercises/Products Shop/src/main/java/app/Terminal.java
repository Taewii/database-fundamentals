package app;

import app.models.dtos.binding.wrappers.CategoriesWrapper;
import app.models.dtos.binding.wrappers.ProductsWrapper;
import app.models.dtos.binding.wrappers.UsersWrapper;
import app.models.dtos.view.categories.CategoriesByProductsDto;
import app.models.dtos.view.wrappers.CategoriesWrapperViewModel;
import app.models.dtos.view.products.ProductCountAndProductsDto;
import app.models.dtos.view.products.ProductNameAndPriceDto;
import app.models.dtos.view.products.ProductsInRangeDto;
import app.models.dtos.view.wrappers.ProductsInRangeWrapper;
import app.models.dtos.view.users.SuccessfulUserDto;
import app.models.dtos.view.wrappers.SuccessfulUsersWrapper;
import app.models.dtos.view.users.UserWithProductsSoldDto;
import app.models.dtos.view.users.UsersCountAndUsersDto;
import app.models.entities.Product;
import app.models.entities.User;
import app.services.category.CategoryService;
import app.services.product.ProductService;
import app.services.user.UserService;
import app.utils.FileIO;
import app.utils.JsonParser;
import app.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";

    private final FileIO fileIO;
    private final JsonParser jsonParser;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public Terminal(FileIO fileIO,
                    JsonParser jsonParser,
                    XmlParser xmlParser,
                    ModelMapper modelMapper,
                    UserService userService,
                    CategoryService categoryService,
                    ProductService productService) {
        this.fileIO = fileIO;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
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
        exportUsersWithAtleastOneProductSold();
    }

    private void extractCategoriesByProductCount() {
        List<CategoriesByProductsDto> categoriesByProductsDtos = this.categoryService.categoriesByProductCount();
//        this.jsonParser.objectToFile(categoriesByProductsDtos, RESOURCES_PATH + "outputJson/categories-by-products.json");
        CategoriesWrapperViewModel categories = new CategoriesWrapperViewModel(categoriesByProductsDtos);
        this.xmlParser.objectToFile(categories, RESOURCES_PATH + "outputXml/categories-by-products.xml");
    }

    private void extractSuccessfulUsers() {
        List<SuccessfulUserDto> userDtos = this.userService.usersWithAtleastOneProductSoldDto();
//        this.jsonParser.objectToFile(userDtos, RESOURCES_PATH + "outputJson/users-sold-products.json");
        SuccessfulUsersWrapper users = new SuccessfulUsersWrapper(userDtos);
        this.xmlParser.objectToFile(users, RESOURCES_PATH + "outputXml/users-sold-products.xml");
    }

    private void exportProductsInRange() {
        List<ProductsInRangeDto> productsInRangeDtos = this.productService.productsInRange(500, 1000);
//        this.jsonParser.objectToFile(productsInRangeDtos, RESOURCES_PATH + "outputJson/products-in-range.json");
        ProductsInRangeWrapper products = new ProductsInRangeWrapper(productsInRangeDtos);
        this.xmlParser.objectToFile(products, RESOURCES_PATH + "outputXml/products-in-range.xml");
    }

    private void exportUsersWithAtleastOneProductSold() {
        List<User> users = this.userService.usersWithAtleastOneProductSold().stream().sorted((u1, u2) -> {
            int result = Integer.compare(u2.getProductsSold().size(), u1.getProductsSold().size());
            if (result == 0) {
                result = u1.getLastName().compareTo(u2.getLastName());
            }
            return result;
        }).collect(Collectors.toList());

        Set<UserWithProductsSoldDto> usersWithProducts = new LinkedHashSet<>();

        for (User user : users) {
            Set<ProductNameAndPriceDto> productsDto = new LinkedHashSet<>();
            for (Product product : user.getProductsSold()) {
                ProductNameAndPriceDto productDto = this.modelMapper.map(product, ProductNameAndPriceDto.class);
                productsDto.add(productDto);
            }

            ProductCountAndProductsDto productCount = new ProductCountAndProductsDto();
            productCount.setProducts(productsDto);
            UserWithProductsSoldDto userDto = new UserWithProductsSoldDto();
            userDto.setAge(user.getAge());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setProductsSold(productCount);
            usersWithProducts.add(userDto);
        }

        UsersCountAndUsersDto result = new UsersCountAndUsersDto();
        result.setUsers(usersWithProducts);

        this.xmlParser.objectToFile(result, RESOURCES_PATH + "outputXml/users-and-products.xml");
    }

    private void seedDatabase() {
        UsersWrapper users = this.xmlParser.objectFromFile(UsersWrapper.class, RESOURCES_PATH + "inputXml/users.xml");
        this.userService.saveAll(users.getUsers());

        CategoriesWrapper categories =
                this.xmlParser.objectFromFile(CategoriesWrapper.class, RESOURCES_PATH + "inputXml/categories.xml");
        this.categoryService.saveAll(categories.getCategories());

        ProductsWrapper products =
                this.xmlParser.objectFromFile(ProductsWrapper.class, RESOURCES_PATH + "inputXml/products.xml");
        this.productService.saveAll(products.getProducts());
    }
}
