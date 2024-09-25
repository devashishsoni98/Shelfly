package app.server.inventory.controller;

import app.server.inventory.dtos.OrderItemDto;
import app.server.inventory.entities.OrderItem;
import app.server.inventory.mapper.OrderItemMapper;
import app.server.inventory.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Create a new order item
    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItemDto);
        return new ResponseEntity<>(OrderItemMapper.mapToOrderItemDto(createdOrderItem), HttpStatus.CREATED);
    }

    // Get a specific order item by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("id") Long orderItemId) {
        OrderItem foundOrderItem = orderItemService.getOrderItemById(orderItemId);
        return (foundOrderItem != null) ? ResponseEntity.ok(OrderItemMapper.mapToOrderItemDto(foundOrderItem)) : ResponseEntity.notFound().build();
    }

    // Get all order items
    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        List<OrderItemDto> orderItemsDTO = orderItems.stream()
                .map(OrderItemMapper::mapToOrderItemDto)
                .toList();
        return ResponseEntity.ok(orderItemsDTO);
    }

    // Update a specific order item by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable("id") Long id, @RequestBody OrderItemDto orderItemDto) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDto);
        return (updatedOrderItem != null) ? ResponseEntity.ok(OrderItemMapper.mapToOrderItemDto(updatedOrderItem)) : ResponseEntity.notFound().build();
    }

    // Delete a specific order item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderitem(@PathVariable("id") Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}