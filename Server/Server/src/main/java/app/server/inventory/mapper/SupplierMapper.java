package app.server.inventory.mapper;

import app.server.inventory.dtos.SupplierDto;
import app.server.inventory.entities.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public static Supplier mapToSupplier(SupplierDto supplierDto) {
        if (supplierDto == null) {
            return null;
        }

        return Supplier.builder()
                .supplierId(supplierDto.getSupplierId())
                .supplierName(supplierDto.getSupplierName())
                .contactInfo(supplierDto.getContactInfo())
                .build();
    }

    public static SupplierDto mapToSupplierDto(Supplier supplier) {
        if (supplier == null) {
            return null;
        }

        return SupplierDto.builder()
                .supplierId(supplier.getSupplierId())
                .supplierName(supplier.getSupplierName())
                .contactInfo(supplier.getContactInfo())
                .build();
    }
}