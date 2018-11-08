package app.services.product;

import app.models.dtos.binding.ProductDto;
import app.models.dtos.view.products.ProductsInRangeDto;

import java.util.List;

public interface ProductService {
    void saveAll(ProductDto[] products);

    List<ProductsInRangeDto> productsInRange(int lower, int upper);
}