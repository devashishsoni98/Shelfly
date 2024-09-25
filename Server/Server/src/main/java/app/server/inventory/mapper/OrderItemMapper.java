package app.server.inventory.mapper;

import app.server.inventory.dtos.OrderItemDto;
import app.server.inventory.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    // Convert OrderItemDto to OrderItem entity
    public static OrderItem mapToOrderItem(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        return OrderItem.builder()
                .orderItemId(orderItemDto.getOrderItemId())
                .quantity(orderItemDto.getQuantity())
                .price(orderItemDto.getPrice())
                .build();
    }

    // Convert OrderItem entity to OrderItemDto
    public static OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return OrderItemDto.builder()
                .orderItemId(orderItem.getOrderItemId())
                .orderId(orderItem.getOrder() != null ? orderItem.getOrder().getOrderId() : null) // Get order ID if available
                .productId(orderItem.getProduct() != null ? orderItem.getProduct().getProductId() : null) // Get product ID if available
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
}