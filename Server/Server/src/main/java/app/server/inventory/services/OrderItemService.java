package app.server.inventory.services;

import app.server.inventory.dtos.OrderItemDto;
import app.server.inventory.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemDto orderItemDto);
    OrderItem getOrderItemById(Long orderItemId);
    List<OrderItem> getAllOrderItems();
    OrderItem updateOrderItem(Long orderItemId, OrderItemDto orderItemDto);
    void deleteOrderItem(Long orderItemId);
}