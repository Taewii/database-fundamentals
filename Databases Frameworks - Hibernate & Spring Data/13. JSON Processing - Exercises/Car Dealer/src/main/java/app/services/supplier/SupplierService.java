package app.services.supplier;

import app.models.dto.binding.SupplierDto;

public interface SupplierService {
    void saveAll(SupplierDto[] supplierDto);
}