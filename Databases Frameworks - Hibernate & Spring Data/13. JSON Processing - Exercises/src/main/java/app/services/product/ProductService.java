package app.services.product;

import app.models.dtos.ProductDto;

public interface ProductService {
    void saveAll(ProductDto[] products);
}