package app.server.inventory.services;

import app.server.inventory.dtos.OrderDto;
import app.server.inventory.entities.Order;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders();
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    void deleteOrder(Long orderId);
}