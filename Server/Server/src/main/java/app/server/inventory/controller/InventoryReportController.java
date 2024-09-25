package app.server.inventory.controller;

import app.server.inventory.entities.Product;
import app.server.inventory.services.InventoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/reports")
public class InventoryReportController {

    @Autowired
    private InventoryReportService inventoryReportService;

    // Endpoint to get a full inventory report
    @GetMapping("/full")
    public ResponseEntity<List<Product>> getFullInventoryReport() {
        List<Product> report = inventoryReportService.generateInventoryReport();
        return ResponseEntity.ok(report);
    }

    // Endpoint to get low stock report
    @GetMapping("/low-stock/{threshold}")
    public ResponseEntity<List<Product>> getLowStockReport(@PathVariable int threshold) {
        List<Product> lowStockProducts = inventoryReportService.generateLowStockReport(threshold);
        return ResponseEntity.ok(lowStockProducts);
    }

    // Endpoint to get sorted inventory report by quantity
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedInventoryReport() {
        List<Product> sortedReport = inventoryReportService.generateSortedInventoryReport();
        return ResponseEntity.ok(sortedReport);
    }
}