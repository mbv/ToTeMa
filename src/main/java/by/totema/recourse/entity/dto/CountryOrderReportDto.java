package by.totema.recourse.entity.dto;

public class CountryOrderReportDto {

    private String country;

    private Integer quantity;

    private Double totalPrice;

    private Double totalCost;

    private Double totalGrossMargin;

    private String currency;

    public CountryOrderReportDto(String country, Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency) {
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
        return (quantity == null) ? 0 : quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return (totalPrice == null) ? 0 : totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalCost() {
        return (totalCost == null) ? 0 : totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalGrossMargin() {
        return (totalGrossMargin == null) ? 0 : totalGrossMargin;
    }

    public void setTotalGrossMargin(Double totalGrossMargin) {
        this.totalGrossMargin = totalGrossMargin;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
