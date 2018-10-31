package app.services.category;

import app.models.dtos.CategoryDto;
import app.models.entities.Category;
import app.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void saveAll(CategoryDto[] categoriesDto) {
		Category[] categories = this.modelMapper.map(categoriesDto, Category[].class);
		this.categoryRepository.saveAll(Arrays.asList(categories));
	}
}