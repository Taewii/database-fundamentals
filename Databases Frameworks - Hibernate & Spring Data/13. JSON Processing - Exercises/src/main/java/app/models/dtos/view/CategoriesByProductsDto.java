package app.models.dtos.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class CategoriesByProductsDto implements Serializable {

    private String category;
    private Integer productsCount;
    private double averagePrice;
    private BigDecimal totalRevenue;

    public CategoriesByProductsDto() {
    }

    public CategoriesByProductsDto(String category, Integer productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProductsCount() {
        return this.productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
