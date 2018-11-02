package app.services.customer;

import app.models.dto.binding.CustomerDto;

public interface CustomerService {
    void saveAll(CustomerDto[] customerDtos);
}