package app.server.inventory.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private int quantity;
    private double price;
}
