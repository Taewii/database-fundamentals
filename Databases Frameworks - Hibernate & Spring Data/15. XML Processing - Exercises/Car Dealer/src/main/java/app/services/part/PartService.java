package app.services.part;

import app.models.dto.binding.PartDto;

public interface PartService {
    void saveAll(PartDto[] partDtos);
}