package app.services.sale;

import app.models.dto.view.sale.SaleDiscountsViewModel;
import app.models.entity.Car;
import app.models.entity.Customer;
import app.models.entity.Sale;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           CarRepository carRepository,
                           CustomerRepository customerRepository,
                           ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insertSales() {
        Random random = new Random();
        double[] discounts = {0, 5, 10, 15, 20, 30, 40, 50};
        int count = 20;
        Set<Sale> sales = new HashSet<>();

        while (count-- > 0) {
            Car car = this.carRepository.getRandomCar();
            Customer customer = this.customerRepository.getRandomCustomer();

            Sale sale = new Sale();
            sale.setCar(car);
            sale.setCustomer(customer);
            sale.setDiscount(discounts[random.nextInt(discounts.length)]);
            customer.getSales().add(sale);
            sales.add(sale);
        }

        this.saleRepository.saveAll(sales);
    }

    public List<SaleDiscountsViewModel> salesWithDiscount() {
        List<Sale> all = this.saleRepository.findAll();

        return all.stream()
                .map(s -> this.modelMapper.map(s, SaleDiscountsViewModel.class))
                .collect(Collectors.toList());
    }
}