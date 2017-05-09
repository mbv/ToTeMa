package by.totema.recourse.entity.dto;

public class CountryOrderReportDto {

    private String country;

    private Integer quantity;

    private Integer totalPrice;

    private Integer totalCost;

    private Integer totalGrossMargin;

    private String currency;

    public CountryOrderReportDto(String country, Integer quantity, Integer totalPrice, Integer totalCost, Integer totalGrossMargin, String currency) {
        this.country = country;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getTotalGrossMargin() {
        return totalGrossMargin;
    }

    public void setTotalGrossMargin(Integer totalGrossMargin) {
        this.totalGrossMargin = totalGrossMargin;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
