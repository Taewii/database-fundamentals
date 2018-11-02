package app.services.product;

import app.models.dtos.binding.ProductDto;
import app.models.dtos.view.ProductsInRangeDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void saveAll(ProductDto[] products);

    List<ProductsInRangeDto> productsInRange(BigDecimal lower, BigDecimal upper);
}