package app.server.inventory.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplierDto {
    private Long supplierId;
    private String supplierName;
    private String contactInfo;
}