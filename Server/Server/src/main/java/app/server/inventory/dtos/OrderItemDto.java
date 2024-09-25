package app.server.inventory.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItemDto {
    private Long orderItemId;
    private Long orderId; // Reference to the order ID.
    private Long productId; // Reference to the product ID.
    private int quantity;
    private double price;
}