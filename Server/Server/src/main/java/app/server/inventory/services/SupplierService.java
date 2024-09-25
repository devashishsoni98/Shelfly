package app.server.inventory.services;

import app.server.inventory.dtos.SupplierDto;
import app.server.inventory.entities.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier createSupplier(SupplierDto supplierDto);
    Supplier getSupplierById(Long supplierId);
    List<Supplier> getAllSuppliers();
    Supplier updateSupplier(Long supplierId, SupplierDto supplierDto);
    void deleteSupplier(Long supplierId);
}