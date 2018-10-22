package bookshop.services.category;

import bookshop.models.Category;

import java.util.List;

public interface CategoryService {

    void save(List<Category> categories);

    List<Category> getAll();
}
