package app.services.customer;

import app.models.dto.binding.CustomerDto;
import app.models.dto.view.customer.CustomerTotalSalesViewModel;
import app.models.dto.view.customer.CustomerViewModel;

import java.util.List;

public interface CustomerService {
    void saveAll(CustomerDto[] customerDtos);

    List<CustomerViewModel> getOrderedCustomers();

    List<CustomerTotalSalesViewModel> customersTotalSales();
}