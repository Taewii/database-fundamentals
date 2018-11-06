package app.services.customer;

import app.models.dto.binding.CustomerDto;
import app.models.dto.view.customer.CustomerTotalSalesViewModel;
import app.models.dto.view.customer.CustomerViewModel;
import app.models.entity.Customer;
import app.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(CustomerDto[] customerDtos) {
        List<Customer> customers = Arrays.stream(customerDtos)
                .map(c -> this.modelMapper.map(c, Customer.class))
                .collect(Collectors.toList());

        this.customerRepository.saveAll(customers);
    }

    @Override
    public List<CustomerViewModel> getOrderedCustomers() {
        List<Customer> customers = this.customerRepository.getCustomersOrderByBirthDateAndAndYoungDriver();
        System.out.println();

        return customers.stream()
                .map(c -> this.modelMapper.map(c, CustomerViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerTotalSalesViewModel> customersTotalSales() {
        List<Customer> all = this.customerRepository.findAll();
        all.removeIf(c -> c.getSales().size() == 0);

        List<CustomerTotalSalesViewModel> customers = all.stream()
                .map(c -> this.modelMapper.map(c, CustomerTotalSalesViewModel.class))
                .collect(Collectors.toList());

        return customers.stream().sorted((a, b) -> {
            int result = b.getSpentMoney().compareTo(a.getSpentMoney());

            if (result == 0) {
                result = Integer.compare(b.getBoughtCars(), a.getBoughtCars());
            }

            return result;
        }).collect(Collectors.toList());
    }
}