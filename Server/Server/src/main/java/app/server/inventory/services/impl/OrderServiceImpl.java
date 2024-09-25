package app.server.inventory.services.impl;

import app.server.inventory.dtos.OrderDto;
import app.server.inventory.entities.Order;
import app.server.inventory.entities.User; // Import User entity
import app.server.inventory.mapper.OrderMapper;
import app.server.inventory.repository.OrderRepository;
import app.server.inventory.services.OrderService;
import app.server.inventory.services.UserService; // Import UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService; // Inject UserService to fetch user details

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();

        // Fetching the user by ID from UserService
        User user = userService.getUserById(orderDto.getUserId());
        if (user != null) {
            order.setUser(user); // Set the user for the order
        } else {
            throw new IllegalArgumentException("User not found with ID: " + orderDto.getUserId());
        }

        order.setType(orderDto.getType());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());

        return OrderMapper.mapToOrderDto(orderRepository.save(order));
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.map(OrderMapper::mapToOrderDto).orElse(null);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::mapToOrderDto)
                .toList();
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            // Fetching the user by ID from UserService for updating if necessary
            User user = userService.getUserById(orderDto.getUserId());
            if (user != null) {
                existingOrder.setUser(user); // Set the updated user for the order if needed
            }

            existingOrder.setType(orderDto.getType());
            existingOrder.setStatus(orderDto.getStatus());
            existingOrder.setTotalAmount(orderDto.getTotalAmount());

            return OrderMapper.mapToOrderDto(orderRepository.save(existingOrder));
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        }
    }
}