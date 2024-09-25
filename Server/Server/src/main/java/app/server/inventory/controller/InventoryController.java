package app.server.inventory.controller;

import app.server.inventory.entities.Product;
import app.server.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint to check inventory level for a specific product by ID
    @GetMapping("/check/{id}")
    public ResponseEntity<Product> checkInventoryLevel(@PathVariable("id") Long productId) {
        return inventoryService.checkInventoryLevel(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to check all products' inventory levels
    @GetMapping("/check/all")
    public ResponseEntity<List<Product>> checkAllInventoryLevels() {
        List<Product> products = inventoryService.checkAllInventoryLevels();
        return ResponseEntity.ok(products);
    }

    // Endpoint to check low stock products
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> checkLowStockProducts() {
        List<Product> lowStockProducts = inventoryService.getLowStockProducts();
        return ResponseEntity.ok(lowStockProducts);
    }

    // Endpoint to trigger notifications for low stock products
    @PostMapping("/notify-low-stock")
    public ResponseEntity<String> notifyLowStock() {
        inventoryService.notifyLowStockProducts();
        return ResponseEntity.ok("Low stock notifications triggered.");
    }
}