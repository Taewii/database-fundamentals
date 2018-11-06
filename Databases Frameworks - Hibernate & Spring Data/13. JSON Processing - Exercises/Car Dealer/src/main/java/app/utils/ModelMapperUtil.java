package app.utils;

import app.models.dto.view.customer.CustomerTotalSalesViewModel;
import app.models.dto.view.sale.SaleDiscountsViewModel;
import app.models.dto.view.supplier.SupplierViewModel;
import app.models.entity.Customer;
import app.models.entity.Part;
import app.models.entity.Sale;
import app.models.entity.Supplier;
import org.modelmapper.*;

import java.math.BigDecimal;
import java.util.Set;

public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize() {
        partCountConfig();
        customersTotalSalesConfig();
        priceWithAndWithoutDiscountsConfig();
    }

    private void partCountConfig() {
        this.modelMapper.addMappings(new PropertyMap<Supplier, SupplierViewModel>() {
            @Override
            protected void configure() {
                map().setPartsCount(source.getParts().size());
            }
        });
    }

    private void customersTotalSalesConfig() {
        Converter<Set<Sale>, BigDecimal> calculateTotalPrice = new AbstractConverter<Set<Sale>, BigDecimal>() {
            @Override
            protected BigDecimal convert(Set<Sale> sales) {
                return sales == null ? null : calcTotalPrice(sales);
            }
        };

        this.modelMapper.addMappings(new PropertyMap<Customer, CustomerTotalSalesViewModel>() {
            @Override
            protected void configure() {
                map().setFullName(source.getName());
                map().setBoughtCars(source.getSales().size());
                using(calculateTotalPrice).map(source.getSales()).setSpentMoney(null);
            }
        });
    }

    private BigDecimal calcTotalPrice(Set<Sale> sales) {
        BigDecimal total = BigDecimal.ZERO;

        for (Sale sale : sales) {
            Set<Part> parts = sale.getCar().getParts();
            for (Part part : parts) {
                total = total.add(part.getPrice());
            }
        }

        return total;
    }

    private BigDecimal calcPriceWithoutDiscount(Set<Part> parts) {
        BigDecimal total = BigDecimal.ZERO;

        for (Part part : parts) {
            total = total.add(part.getPrice());
        }

        return total;
    }

    private BigDecimal calcPriceWithDiscount(Set<Part> parts, Double discount) {
        BigDecimal total = BigDecimal.ZERO;

        for (Part part : parts) {
            total = total.add(part.getPrice());
        }

        return total.multiply(new BigDecimal(1 - (discount / 100)));
    }

    private void priceWithAndWithoutDiscountsConfig() {
        Converter<Set<Part>, BigDecimal> priceWithoutDiscount = new AbstractConverter<Set<Part>, BigDecimal>() {
            @Override
            protected BigDecimal convert(Set<Part> parts) {
                return parts == null ? null : calcPriceWithoutDiscount(parts);
            }
        };

        Converter<Sale, BigDecimal> priceWithDiscount = new AbstractConverter<Sale, BigDecimal>() {
            @Override
            protected BigDecimal convert(Sale sale) {
                return sale == null ? null : calcPriceWithDiscount(sale.getCar().getParts(), sale.getDiscount());
            }
        };

        this.modelMapper.addMappings(new PropertyMap<Sale, SaleDiscountsViewModel>() {
            @Override
            protected void configure() {
                using(priceWithoutDiscount).map(source.getCar().getParts()).setPrice(null);
                using(priceWithDiscount).map(source).setPriceWithDiscount(null);
            }
        });
    }
}
