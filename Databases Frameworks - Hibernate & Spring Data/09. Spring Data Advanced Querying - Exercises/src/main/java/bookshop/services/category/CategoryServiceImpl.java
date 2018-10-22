package bookshop.services.category;

import bookshop.models.Category;
import bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(List<Category> categories) {
        this.categoryRepository.save(categories);
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
