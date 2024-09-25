package app.server.inventory.services.impl;

import app.server.inventory.dtos.SupplierDto;
import app.server.inventory.entities.Supplier;
import app.server.inventory.repository.SupplierRepository;
import app.server.inventory.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier createSupplier(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setContactInfo(supplierDto.getContactInfo());
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        return optionalSupplier.orElse(null);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier updateSupplier(Long supplierId, SupplierDto supplierDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (optionalSupplier.isPresent()) {
            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setSupplierName(supplierDto.getSupplierName());
            existingSupplier.setContactInfo(supplierDto.getContactInfo());
            return supplierRepository.save(existingSupplier);
        }
        return null; // Return null if the supplier was not found for update
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        if (supplierRepository.existsById(supplierId)) {
            supplierRepository.deleteById(supplierId);
        }
    }
}