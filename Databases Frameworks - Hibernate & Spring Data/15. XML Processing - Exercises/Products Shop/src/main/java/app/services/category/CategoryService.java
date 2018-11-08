package app.services.category;

import app.models.dtos.binding.CategoryDto;
import app.models.dtos.view.categories.CategoriesByProductsDto;

import java.util.List;

public interface CategoryService {
    void saveAll(CategoryDto[] categories);

    List<CategoriesByProductsDto> categoriesByProductCount();
}