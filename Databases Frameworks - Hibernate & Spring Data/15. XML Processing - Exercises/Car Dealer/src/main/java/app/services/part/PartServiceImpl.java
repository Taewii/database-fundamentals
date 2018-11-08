package app.services.part;

import app.models.dto.binding.PartDto;
import app.models.entity.Part;
import app.models.entity.Supplier;
import app.repositories.PartRepository;
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
public class PartServiceImpl implements PartService {

	private final PartRepository partRepository;
	private final SupplierRepository supplierRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public PartServiceImpl(PartRepository partRepository,
						   SupplierRepository supplierRepository,
						   ModelMapper modelMapper) {
		this.partRepository = partRepository;
		this.supplierRepository = supplierRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void saveAll(PartDto[] partDtos) {
		List<Part> parts = Arrays.stream(partDtos)
				.map(p -> this.modelMapper.map(p, Part.class))
				.collect(Collectors.toList());

		for (Part part : parts) {
			Supplier supplier = this.supplierRepository.getRandomSupplier();
			part.setSupplier(supplier);
		}

		this.partRepository.saveAll(parts);
	}
}