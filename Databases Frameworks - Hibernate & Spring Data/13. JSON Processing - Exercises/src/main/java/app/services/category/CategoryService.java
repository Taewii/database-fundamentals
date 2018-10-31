package app.services.category;

import app.models.dtos.CategoryDto;

public interface CategoryService {
    void saveAll(CategoryDto[] categories);
}