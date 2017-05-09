package by.totema.recourse.entity.dto;

public class OrderReportDto {

    private String employee;

    private String office;

    private String country;

    private Integer quantity;

    private Double totalPrice;

    private Double totalCost;

    private Double totalGrossMargin;

    private String currency;

    private Integer year;

    private String product;

    public OrderReportDto(String employee, String office, String country, Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency) {
        this.employee = employee;
        this.office = office;
        this.country = country;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
    }

    public OrderReportDto(String office, String country, Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency) {
        this.office = office;
        this.country = country;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
    }

    public OrderReportDto(String country, Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency) {
        this.country = country;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
    }

    public OrderReportDto(Integer year, Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
        this.year = year;
    }

    public OrderReportDto(Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency, String product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
        this.product = product;
    }

    public OrderReportDto(String product, Integer year, String country, String office, Integer quantity, Double totalPrice, Double totalCost, Double totalGrossMargin, String currency) {
        this.country = country;
        this.office = office;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalGrossMargin = totalGrossMargin;
        this.currency = currency;
        this.year = year;
        this.product = product;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }


    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
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

    public Integer getYear() {
        return (year == null) ? 0 : year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
