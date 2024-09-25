package app.server.inventory.services.impl;

import app.server.inventory.dtos.OrderDto;
import app.server.inventory.dtos.OrderItemDto;
import app.server.inventory.entities.Order;
import app.server.inventory.entities.OrderItem;
import app.server.inventory.entities.Product;
import app.server.inventory.mapper.OrderMapper;
import app.server.inventory.repository.OrderItemRepository;
import app.server.inventory.services.OrderItemService;
import app.server.inventory.services.OrderService;
import app.server.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public OrderItem createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();

        // Fetching the product by ID from ProductService
        Product product = productService.getProductById(orderItemDto.getProductId());
        if (product != null) {
            orderItem.setProduct(product);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + orderItemDto.getProductId());
        }

        // Fetching the order by ID from OrderService
        OrderDto order = orderService.getOrderById(orderItemDto.getOrderId());
        if (order != null) {
            Order existOrder = OrderMapper.mapToOrder(order);
            orderItem.setOrder(existOrder);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + orderItemDto.getOrderId());
        }

        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());

        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId) {
        Optional<OrderItem> optionalOrderitem = orderItemRepository.findById(orderItemId);
        return optionalOrderitem.orElse(null);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem updateOrderItem(Long orderitemId, OrderItemDto orderitemDto) {
        Optional<OrderItem> optionalOrderitem = orderItemRepository.findById(orderitemId);
        if (optionalOrderitem.isPresent()) {
            OrderItem existingOrderitem = optionalOrderitem.get();

            // Fetching the product by ID from ProductService for updating if necessary
            Product product = productService.getProductById(orderitemDto.getProductId());
            if (product != null) {
                existingOrderitem.setProduct(product);
            }

            // Fetching the order by ID from OrderService for updating if necessary
            OrderDto order = orderService.getOrderById(orderitemDto.getOrderId());

            if (order != null) {
                Order existOrder = OrderMapper.mapToOrder(order);
                existingOrderitem.setOrder(existOrder);
            }

            existingOrderitem.setQuantity(orderitemDto.getQuantity());
            existingOrderitem.setPrice(orderitemDto.getPrice());

            return orderItemRepository.save(existingOrderitem);
        }
        return null; // Return null if the order item was not found for update
    }




    @Override
    public void deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
        }
    }
}