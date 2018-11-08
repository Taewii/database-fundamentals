package app.services.supplier;

import app.models.dto.binding.SupplierDto;
import app.models.dto.view.supplier.SupplierViewModel;
import app.models.entity.Supplier;
import app.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(SupplierDto[] supplierDto) {
        List<Supplier> suppliers = Arrays.stream(supplierDto)
                .map(s -> this.modelMapper.map(s, Supplier.class))
                .collect(Collectors.toList());
        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public List<SupplierViewModel> getLocalSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.getLocalSuppliers();

        return suppliers.stream()
                .map(s -> this.modelMapper.map(s, SupplierViewModel.class))
                .collect(Collectors.toList());
    }
}