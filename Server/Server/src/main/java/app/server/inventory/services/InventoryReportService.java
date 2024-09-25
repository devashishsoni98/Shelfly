package app.server.inventory.services;

import app.server.inventory.entities.Product;

import java.util.List;

public interface InventoryReportService {
    List<Product> generateInventoryReport();
    List<Product> generateLowStockReport(int threshold);
    List<Product> generateSortedInventoryReport();
}