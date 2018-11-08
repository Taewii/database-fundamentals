package app.services.sale;

import app.models.dto.view.sale.SaleDiscountsViewModel;

import java.util.List;

public interface SaleService {
    void insertSales();

    List<SaleDiscountsViewModel> salesWithDiscount();
}