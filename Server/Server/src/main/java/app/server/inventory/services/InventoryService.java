package app.server.inventory.services;

import app.server.inventory.entities.Product;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    Optional<Product> checkInventoryLevel(Long productId);
    List<Product> checkAllInventoryLevels();
    List<Product> getLowStockProducts();
    void notifyLowStockProducts();
}