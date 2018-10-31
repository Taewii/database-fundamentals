package app.services.product;

import app.models.dtos.ProductDto;
import app.models.entities.Category;
import app.models.entities.Product;
import app.models.entities.User;
import app.repositories.CategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
							  UserRepository userRepository,
							  CategoryRepository categoryRepository,
							  ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void saveAll(ProductDto[] productsDto) {
		Product[] products = this.modelMapper.map(productsDto, Product[].class);
		this.productRepository.saveAll(Arrays.asList(products));

		for (int i = 0; i < products.length; i++) {
			Product product = products[i];
			User seller = this.userRepository.getRandomUser();
			User buyer = this.userRepository.getRandomUser();
			Category category = this.categoryRepository.getRandomCategory();

			product.getCategories().add(category);
			product.setSeller(seller);
			if (i % 5 != 0 && seller != buyer) {
				product.setBuyer(buyer);
			}
		}
	}
}