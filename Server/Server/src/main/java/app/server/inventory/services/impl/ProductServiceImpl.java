package app.server.inventory.services.impl;

import app.server.inventory.dtos.ProductDto;
import app.server.inventory.entities.Product;
import app.server.inventory.repository.ProductRepository;
import app.server.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null); // Return null if not found
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setQuantity(productDto.getQuantity());
            existingProduct.setPrice(productDto.getPrice());
            return productRepository.save(existingProduct);
        }
        return null; // Return null if the product was not found for update
    }

    @Override
    public void deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) { // Check if the product exists before deleting
            productRepository.deleteById(productId);
        }
    }
}