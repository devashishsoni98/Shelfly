package app.server.inventory.services.impl;

import app.server.inventory.entities.Product;
import app.server.inventory.repository.ProductRepository;
import app.server.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ProductRepository productRepository;

    private static final int LOW_STOCK_THRESHOLD =8; // Set your low stock threshold

    // Check inventory level for a specific product by ID
    @Override
    public Optional<Product> checkInventoryLevel(Long productId) {
        return productRepository.findById(productId);
    }

    // Check all products' inventory levels
    @Override
    public List<Product> checkAllInventoryLevels() {
        return productRepository.findAll();
    }

    // Get low stock products
    @Override
    public List<Product> getLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getQuantity() < LOW_STOCK_THRESHOLD)
                .collect(Collectors.toList());
    }

    // Notify about low stock products
    @Override
    public void notifyLowStockProducts() {
        List<Product> lowStockProducts = getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            for (Product product : lowStockProducts) {
                sendAlert(product);
            }
        }
    }

    // Simulated alert notification (could be email, SMS, etc.)
    private void sendAlert(Product product) {
        System.out.println("ALERT: Product '" + product.getName() + "' is low on stock! Current quantity: " + product.getQuantity());
        // Here you can implement actual notification logic (e.g., email, SMS)
    }
}