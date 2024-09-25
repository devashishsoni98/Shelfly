package app.server.inventory.controller;

import app.server.inventory.dtos.SupplierDto;
import app.server.inventory.entities.Supplier;
import app.server.inventory.mapper.SupplierMapper;
import app.server.inventory.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Create a new supplier
    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier createdSupplier = supplierService.createSupplier(supplierDto);
        return new ResponseEntity<>(SupplierMapper.mapToSupplierDto(createdSupplier), HttpStatus.CREATED);
    }

    // Get a specific supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable("id") Long supplierId) {
        Supplier foundSupplier = supplierService.getSupplierById(supplierId);
        return (foundSupplier != null) ? ResponseEntity.ok(SupplierMapper.mapToSupplierDto(foundSupplier)) : ResponseEntity.notFound().build();
    }

    // Get all suppliers
    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<SupplierDto> suppliersDTO = suppliers.stream()
                .map(SupplierMapper::mapToSupplierDto)
                .toList();
        return ResponseEntity.ok(suppliersDTO);
    }

    // Update a specific supplier by ID
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable("id") Long id, @RequestBody SupplierDto supplierDto) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplierDto);
        return (updatedSupplier != null) ? ResponseEntity.ok(SupplierMapper.mapToSupplierDto(updatedSupplier)) : ResponseEntity.notFound().build();
    }

    // Delete a specific supplier by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable("id") Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}