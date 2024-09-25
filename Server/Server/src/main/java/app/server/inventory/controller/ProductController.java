package app.server.inventory.controller;

import app.server.inventory.dtos.ProductDto;
import app.server.inventory.entities.Product;
import app.server.inventory.mapper.ProductMapper;
import app.server.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(productDto);
        ProductDto createdProduct = ProductMapper.mapToProductDto(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Get a product by ID
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            ProductDto foundProduct = ProductMapper.mapToProductDto(product);
            return ResponseEntity.ok(foundProduct);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = products.stream()
                .map(ProductMapper::mapToProductDto)
                .toList();
        return ResponseEntity.ok(productDtos);
    }

    // Update a product by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product updatedProduct = productService.updateProduct(id, productDto);
        if (updatedProduct != null) {
            ProductDto updatedProductDto = ProductMapper.mapToProductDto(updatedProduct);
            return ResponseEntity.ok(updatedProductDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete successfully");
    }
}