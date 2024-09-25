//package app.server.inventory.services.impl;
//
//import app.server.inventory.entities.Product;
//import app.server.inventory.repository.ProductRepository;
//import app.server.inventory.services.InventoryReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class InventoryReportServiceImpl implements InventoryReportService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    // Generate a report of all products with their stock levels
//    @Override
//    public List<Product> generateInventoryReport() {
//        return productRepository.findAll();
//    }
//
//    // Generate a report of low stock products
//    @Override
//    public List<Product> generateLowStockReport(int threshold) {
//        return productRepository.findAll().stream()
//                .filter(product -> product.getQuantity() < threshold)
//                .collect(Collectors.toList());
//    }
//
//    // Generate a report of products sorted by quantity
//    @Override
//    public List<Product> generateSortedInventoryReport() {
//        return productRepository.findAll().stream()
//                .sorted((p1, p2) -> Integer.compare(p2.getQuantity(), p1.getQuantity())) // Sort by quantity descending
//                .collect(Collectors.toList());
//    }
//}

package app.server.inventory.services;

import app.server.inventory.entities.Product;
import app.server.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryReportServiceImpl implements InventoryReportService {

    @Autowired
    private ProductRepository productRepository;

    // Generate a report of all products with their stock levels
    @Override
    public List<Product> generateInventoryReport() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error generating inventory report: " + e.getMessage());
            return List.of(); // Return an empty list in case of error
        }
    }

    // Generate a report of low stock products
    @Override
    public List<Product> generateLowStockReport(int threshold) {
        try {
            return productRepository.findAll().stream()
                    .filter(product -> product.getQuantity() < threshold)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error generating low stock report: " + e.getMessage());
            return List.of(); // Return an empty list in case of error
        }
    }

    // Generate a report of products sorted by quantity
    @Override
    public List<Product> generateSortedInventoryReport() {
        try {
            return productRepository.findAll().stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getQuantity(), p1.getQuantity())) // Sort by quantity descending
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error generating sorted inventory report: " + e.getMessage());
            return List.of(); // Return an empty list in case of error
        }
    }
}