package app.server.inventory.services;

import app.server.inventory.dtos.ProductDto;
import app.server.inventory.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product updateProduct(Long productId, ProductDto productDto);
    void deleteProduct(Long productId);
}