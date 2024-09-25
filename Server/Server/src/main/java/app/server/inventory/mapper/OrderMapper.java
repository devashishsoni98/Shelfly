package app.server.inventory.mapper;

import app.server.inventory.dtos.OrderDto;
import app.server.inventory.entities.Order;
import app.server.inventory.entities.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        return Order.builder()
                .orderId(orderDto.getOrderId())
                // Assuming you have a method to fetch the User entity by ID.
                // For now, just set the user ID directly.
                // user(User.builder().userId(orderDTO.getUserId()).build())
                // Set other fields directly
                .type(orderDto.getType())
                .status(orderDto.getStatus())
                .user(User.builder().userId(orderDto.getUserId()).build())
                .totalAmount(orderDto.getTotalAmount())
                .build();
    }

    public static OrderDto mapToOrderDto(Order order) {
        if (order == null) {
            return null;
        }

        return OrderDto.builder()
                // Assuming you have a method to fetch the User ID.
                // userID(orderService.findByUser(order))
                // For now, just set a placeholder.
                // userID(order.getUser() != null ? order.getUser().getUserId() : null)
                // Set other fields directly
                .orderId(order.getOrderId())
                .userId(order.getUser().getUserId())
                .type(order.getType())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
