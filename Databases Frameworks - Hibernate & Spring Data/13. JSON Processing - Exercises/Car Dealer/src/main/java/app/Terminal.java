package app;

import app.models.dto.binding.CarDto;
import app.models.dto.binding.CustomerDto;
import app.models.dto.binding.PartDto;
import app.models.dto.binding.SupplierDto;
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
        insertSuppliers();
        insertParts();
        insertCars();
        insertCustomers();
        this.saleService.insertSales();
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
