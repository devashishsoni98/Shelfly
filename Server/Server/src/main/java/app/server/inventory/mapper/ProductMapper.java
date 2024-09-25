package app.server.inventory.mapper;

import app.server.inventory.dtos.ProductDto;
import app.server.inventory.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product mapToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .build();
    }

    public static ProductDto mapToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
