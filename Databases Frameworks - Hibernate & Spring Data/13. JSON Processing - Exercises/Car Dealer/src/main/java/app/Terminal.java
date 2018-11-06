package app;

import app.models.dto.binding.CarDto;
import app.models.dto.binding.CustomerDto;
import app.models.dto.binding.PartDto;
import app.models.dto.binding.SupplierDto;
import app.models.dto.view.car.CarViewModel;
import app.models.dto.view.car.CarViewModelWithParts;
import app.models.dto.view.customer.CustomerTotalSalesViewModel;
import app.models.dto.view.customer.CustomerViewModel;
import app.models.dto.view.sale.SaleDiscountsViewModel;
import app.models.dto.view.supplier.SupplierViewModel;
import app.services.car.CarService;
import app.services.customer.CustomerService;
import app.services.part.PartService;
import app.services.sale.SaleService;
import app.services.supplier.SupplierService;
import app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources";

    private final JsonParser jsonParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Terminal(JsonParser jsonParser,
                    SupplierService supplierService,
                    PartService partService,
                    CarService carService,
                    CustomerService customerService,
                    SaleService saleService) {
        this.jsonParser = jsonParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) {
        //importing data
        insertSuppliers();
        insertParts();
        insertCars();
        insertCustomers();
        this.saleService.insertSales();

        //extracting data
        exportOrderedCustomers();
        exportToyotaCars();
        exportLocalSuppliers();
        exportCarsWithParts();
        exportCustomersTotalSales();
        exportSalesWithAndWithoutDiscount();
    }

    private void exportSalesWithAndWithoutDiscount() {
        List<SaleDiscountsViewModel> sales = this.saleService.salesWithDiscount();
        this.jsonParser.objectToFile(sales, RESOURCES_PATH + "/output/sales-discounts.json");
    }

    private void exportCustomersTotalSales() {
        List<CustomerTotalSalesViewModel> customersTotalSales = this.customerService.customersTotalSales();
        this.jsonParser.objectToFile(customersTotalSales, RESOURCES_PATH + "/output/customers-total-sales.json");
    }

    private void exportCarsWithParts() {
        List<CarViewModelWithParts> cars = this.carService.carsWithParts();
        this.jsonParser.objectToFile(cars, RESOURCES_PATH + "/output/cars-and-parts.json");
    }

    private void exportLocalSuppliers() {
        List<SupplierViewModel> localSuppliers = this.supplierService.getLocalSuppliers();
        this.jsonParser.objectToFile(localSuppliers, RESOURCES_PATH + "/output/local-suppliers.json");
    }

    private void exportToyotaCars() {
        List<CarViewModel> cars = this.carService.carsByBrandName("Toyota");
        this.jsonParser.objectToFile(cars, RESOURCES_PATH + "/output/toyota-cars.json");
    }

    private void exportOrderedCustomers() {
        List<CustomerViewModel> orderedCustomers = this.customerService.getOrderedCustomers();
        this.jsonParser.objectToFile(orderedCustomers, RESOURCES_PATH + "/output/ordered-customers.json");
    }

    private void insertCustomers() {
        CustomerDto[] customerDtos = this.jsonParser.objectFromFile(CustomerDto[].class,
                RESOURCES_PATH + "/input/customers.json");
        this.customerService.saveAll(customerDtos);
    }

    private void insertCars() {
        CarDto[] carDtos = this.jsonParser.objectFromFile(CarDto[].class,
                RESOURCES_PATH + "/input/cars.json");
        this.carService.saveAll(carDtos);
    }

    private void insertParts() {
        PartDto[] partDtos = this.jsonParser.objectFromFile(PartDto[].class,
                RESOURCES_PATH + "/input/parts.json");
        this.partService.saveAll(partDtos);
    }

    private void insertSuppliers() {
        SupplierDto[] supplierDto = this.jsonParser.objectFromFile(SupplierDto[].class,
                RESOURCES_PATH + "/input/suppliers.json");
        this.supplierService.saveAll(supplierDto);
    }
}
