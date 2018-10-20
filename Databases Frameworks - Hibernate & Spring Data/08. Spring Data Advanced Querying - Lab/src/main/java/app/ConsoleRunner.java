package app;

import app.models.enums.Size;
import app.models.ingredients.BasicIngredient;
import app.models.ingredients.Ingredient;
import app.repositories.IngredientRepository;
import app.repositories.LabelRepository;
import app.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    //Im not using Services cus this is just for testing.
    private ShampooRepository shampooRepo;
    private LabelRepository labelRepo;
    private IngredientRepository ingredientRepo;

    @Autowired
    public ConsoleRunner(ShampooRepository shampooRepo,
                         LabelRepository labelRepo,
                         IngredientRepository ingredientRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.shampooRepo.findAllBySizeOrderById(Size.MEDIUM)
                .forEach(s -> System.out.printf("%s %s %slv.%n", s.getBrand(), s.getSize(), s.getPrice()));

        this.shampooRepo.findAllBySizeOrLabelOrderByPriceAsc(Size.MEDIUM, this.labelRepo.findOne(10L))
                .forEach(s -> System.out.printf("%s %s %slv.%n", s.getBrand(), s.getSize(), s.getPrice()));

        this.shampooRepo.findAllByPriceAfterOrderByPriceDesc(new BigDecimal(5))
                .forEach(s -> System.out.printf("%s %s %slv.%n", s.getBrand(), s.getSize(), s.getPrice()));

        this.ingredientRepo.findAllByNameStartingWith("M")
                .forEach(i -> System.out.println(i.getName()));

        this.ingredientRepo.findAllByNameIn(Arrays.asList("Lavender", "Herbs", "Apple"))
                .forEach(i -> System.out.println(i.getName()));

        System.out.println(this.shampooRepo.countAllByPriceBefore(new BigDecimal(8.50)));

        this.shampooRepo.shampoosWithIngredientsIn(Arrays.asList("Berry", "Mineral-Colagen"))
                .forEach(s -> System.out.println(s.getBrand()));

        this.shampooRepo.shampoosWithIngredientsSizeLessThan(2)
                .forEach(s -> System.out.println(s.getBrand()));

        System.out.println(this.shampooRepo.priceOfIngredientsOfShampoo("Fresh it up!"));

        this.ingredientRepo.deleteIngredientByName("Nettle");

        this.ingredientRepo.increaseAllIngredientsPriceBy10Percents();

        this.ingredientRepo.increaseIngredientsPriceBy10PercentsFromList(Arrays.asList("Lavender", "Raspberry"));
    }
}
