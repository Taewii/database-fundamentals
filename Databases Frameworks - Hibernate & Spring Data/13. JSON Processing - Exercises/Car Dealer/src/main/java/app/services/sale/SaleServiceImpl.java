package app.services.sale;

import app.models.entity.Car;
import app.models.entity.Customer;
import app.models.entity.Sale;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

	private final SaleRepository saleRepository;
	private final CarRepository carRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public SaleServiceImpl(SaleRepository saleRepository,
						   CarRepository carRepository,
						   CustomerRepository customerRepository) {
		this.saleRepository = saleRepository;
		this.carRepository = carRepository;
		this.customerRepository = customerRepository;
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
}