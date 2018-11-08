package app;

import app.models.dto.binding.wrappers.CarsWrapper;
import app.models.dto.binding.wrappers.CustomersWrapper;
import app.models.dto.binding.wrappers.PartsWrapper;
import app.models.dto.binding.wrappers.SupplierWrapper;
import app.models.dto.view.car.CarViewModel;
import app.models.dto.view.car.CarViewModelWithParts;
import app.models.dto.view.car.CarViewModelWithPartsWrapper;
import app.models.dto.view.car.CarViewWrapper;
import app.models.dto.view.customer.CustomerTotalSalesViewModel;
import app.models.dto.view.customer.CustomerTotalSalesViewModelWrapper;
import app.models.dto.view.customer.CustomerViewModel;
import app.models.dto.view.customer.CustomerViewWrapper;
import app.models.dto.view.sale.SaleDiscountsViewModel;
import app.models.dto.view.sale.SaleDiscountsViewModelWrapper;
import app.models.dto.view.supplier.SupplierViewModel;
import app.models.dto.view.supplier.SuppliersViewWrapper;
import app.services.car.CarService;
import app.services.customer.CustomerService;
import app.services.part.PartService;
import app.services.sale.SaleService;
import app.services.supplier.SupplierService;
import app.utils.JsonParser;
import app.utils.XmlParser;
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
    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Terminal(JsonParser jsonParser,
                    XmlParser xmlParser,
                    SupplierService supplierService,
                    PartService partService,
                    CarService carService,
                    CustomerService customerService,
                    SaleService saleService) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) {

        //configured to work with xml's

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
//        this.jsonParser.objectToFile(sales, RESOURCES_PATH + "/outputJson/sales-discounts.json");
        SaleDiscountsViewModelWrapper salesDto = new SaleDiscountsViewModelWrapper(sales);
        this.xmlParser.objectToFile(salesDto, RESOURCES_PATH + "/outputXml/sales-discounts.xml");
    }

    private void exportCustomersTotalSales() {
        List<CustomerTotalSalesViewModel> customersTotalSales = this.customerService.customersTotalSales();
//        this.jsonParser.objectToFile(customersTotalSales, RESOURCES_PATH + "/outputJson/customers-total-sales.json");
        CustomerTotalSalesViewModelWrapper customers = new CustomerTotalSalesViewModelWrapper(customersTotalSales);
        this.xmlParser.objectToFile(customers, RESOURCES_PATH + "/outputXml/customers-total-sales.xml");
    }

    private void exportCarsWithParts() {
        List<CarViewModelWithParts> cars = this.carService.carsWithParts();
//        this.jsonParser.objectToFile(cars, RESOURCES_PATH + "/outputJson/cars-and-parts.json");
        CarViewModelWithPartsWrapper carsDto = new CarViewModelWithPartsWrapper(cars);
        this.xmlParser.objectToFile(carsDto, RESOURCES_PATH + "/outputXml/cars-and-parts.xml");
    }

    private void exportLocalSuppliers() {
        List<SupplierViewModel> localSuppliers = this.supplierService.getLocalSuppliers();
//        this.jsonParser.objectToFile(localSuppliers, RESOURCES_PATH + "/outputJson/local-suppliers.json");
        SuppliersViewWrapper suppliers = new SuppliersViewWrapper(localSuppliers);
        this.xmlParser.objectToFile(suppliers, RESOURCES_PATH + "/outputXml/local-suppliers.xml");
    }

    private void exportToyotaCars() {
        List<CarViewModel> cars = this.carService.carsByBrandName("Toyota");
//        this.jsonParser.objectToFile(cars, RESOURCES_PATH + "/outputJson/toyota-cars.json");
        CarViewWrapper carsDto = new CarViewWrapper(cars);
        this.xmlParser.objectToFile(carsDto, RESOURCES_PATH + "/outputXml/toyota-cars.xml");
    }

    private void exportOrderedCustomers() {
        List<CustomerViewModel> orderedCustomers = this.customerService.getOrderedCustomers();
//        this.jsonParser.objectToFile(orderedCustomers, RESOURCES_PATH + "/outputJson/ordered-customers.json");
        CustomerViewWrapper customers = new CustomerViewWrapper(orderedCustomers);
        this.xmlParser.objectToFile(customers, RESOURCES_PATH + "/outputXml/ordered-customers.xml");
    }

    private void insertCustomers() {
//        CustomerDto[] customerDtos = this.jsonParser.objectFromFile(CustomerDto[].class,
//                RESOURCES_PATH + "/inputJson/customers.json");
        CustomersWrapper customers =
                this.xmlParser.objectFromFile(CustomersWrapper.class, RESOURCES_PATH + "/inputXml/customers.xml");
        this.customerService.saveAll(customers.getCustomers());
    }

    private void insertCars() {
//        CarDto[] carDtos = this.jsonParser.objectFromFile(CarDto[].class,
//                RESOURCES_PATH + "/inputJson/cars.json");
        CarsWrapper cars =
                this.xmlParser.objectFromFile(CarsWrapper.class, RESOURCES_PATH + "/inputXml/cars.xml");
        this.carService.saveAll(cars.getCars());
    }

    private void insertParts() {
//        PartDto[] partDtos = this.jsonParser.objectFromFile(PartDto[].class,
//                RESOURCES_PATH + "/inputJson/parts.json");
        PartsWrapper parts =
                this.xmlParser.objectFromFile(PartsWrapper.class, RESOURCES_PATH + "/inputXml/parts.xml");
        this.partService.saveAll(parts.getParts());
    }

    private void insertSuppliers() {
//        SupplierDto[] supplierDto = this.jsonParser.objectFromFile(SupplierDto[].class,
//                RESOURCES_PATH + "/inputJson/suppliers.json");
        SupplierWrapper supplier =
                this.xmlParser.objectFromFile(SupplierWrapper.class, RESOURCES_PATH + "/inputXml/suppliers.xml");
        this.supplierService.saveAll(supplier.getSuppliers());
    }
}
