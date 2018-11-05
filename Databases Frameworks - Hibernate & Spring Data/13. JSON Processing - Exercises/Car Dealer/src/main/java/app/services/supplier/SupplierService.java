package app.services.supplier;

import app.models.dto.binding.SupplierDto;
import app.models.dto.view.SupplierViewModel;

import java.util.List;

public interface SupplierService {
    void saveAll(SupplierDto[] supplierDto);

    List<SupplierViewModel> getLocalSuppliers();
}