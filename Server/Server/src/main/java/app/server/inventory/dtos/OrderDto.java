package app.server.inventory.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {
    private Long orderId;
    private Long userId; // Reference to the user ID.
    private String type; // e.g., Purchase or Sales Order.
    private String status; // e.g., Pending, Completed.
    private double totalAmount;
}