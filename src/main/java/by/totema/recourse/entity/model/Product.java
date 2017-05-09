package by.totema.recourse.entity.model;

import by.totema.recourse.entity.dto.OrderReportDto;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "`product`")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ProductOrderMapping",
                classes = @ConstructorResult(
                        targetClass = OrderReportDto.class,
                        columns = {
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "totalPrice", type = Double.class),
                                @ColumnResult(name = "totalCost", type = Double.class),
                                @ColumnResult(name = "totalGrossMargin", type = Double.class),
                                @ColumnResult(name = "currency", type = String.class),
                                @ColumnResult(name = "product", type = String.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "ProductDetailedOrderMapping",
                classes = @ConstructorResult(
                        targetClass = OrderReportDto.class,
                        columns = {
                                @ColumnResult(name = "product", type = String.class),
                                @ColumnResult(name = "year", type = Integer.class),
                                @ColumnResult(name = "country", type = String.class),
                                @ColumnResult(name = "office", type = String.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "totalPrice", type = Double.class),
                                @ColumnResult(name = "totalCost", type = Double.class),
                                @ColumnResult(name = "totalGrossMargin", type = Double.class),
                                @ColumnResult(name = "currency", type = String.class)
                        }
                )
        ),
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Product.getOrderReport",
                query = "SELECT \n" +
                        "CONCAT(P.NAME, ' ', P.SIZE, ' ', P.COLOR) AS product,\n" +
                        "SUM(O.QUANTITY) AS quantity,\n" +
                        "TRUNCATE(SUM(O.PRICE*CR.CONVERSION_TO_LOCAL/100), 2) AS totalPrice,\n" +
                        "TRUNCATE(SUM(O.COST*CR.CONVERSION_TO_LOCAL/100), 2) AS totalCost,\n" +
                        "TRUNCATE(SUM(O.GROSS_MARGIN*CR.CONVERSION_TO_LOCAL/100), 2) AS totalGrossMargin,\n" +
                        "C.CURRENCY_NAME AS currency\n" +
                        "FROM `totema`.product P\n" +
                        "LEFT JOIN `totema`.product_list PL\n" +
                        "ON P.ID = PL.PRODUCT_KEY\n" +
                        "LEFT JOIN `totema`.order O \n" +
                        "ON PL.ORDER_KEY = O.ID\n" +
                        "LEFT JOIN `totema`.office OF \n" +
                        "ON O.OFFICE_KEY = OF.ID\n" +
                        "LEFT JOIN `totema`.country C\n" +
                        "ON OF.COUNTRY_KEY = C.ID\n" +
                        "LEFT JOIN `totema`.conversion_rate CR\n" +
                        "ON OF.COUNTRY_KEY = CR.COUNTRY_KEY\n" +
                        "AND O.DATE_KEY = CR.PERIOD_KEY\n" +
                        "GROUP BY P.ID, C.ID\n" +
                        "ORDER BY P.ID;",
                resultSetMapping = "ProductOrderMapping"

        ),
        @NamedNativeQuery(
                name = "Product.getDetailedOrderReport",
                query = "SELECT \n" +
                        "CONCAT(P.NAME, ' ', P.SIZE, ' ', P.COLOR) AS product,\n" +
                        "D.YEAR AS year,\n" +
                        "CONCAT(OF.CITY, ', ', OF.ADDRESS) AS office,\n" +
                        "C.NAME AS country,\n" +
                        "SUM(O.QUANTITY) AS quantity,\n" +
                        "TRUNCATE(SUM(O.PRICE*CR.CONVERSION_TO_LOCAL/100), 2) AS totalPrice,\n" +
                        "TRUNCATE(SUM(O.COST*CR.CONVERSION_TO_LOCAL/100), 2) AS totalCost,\n" +
                        "TRUNCATE(SUM(O.GROSS_MARGIN*CR.CONVERSION_TO_LOCAL/100), 2) AS totalGrossMargin,\n" +
                        "C.CURRENCY_NAME AS currency\n" +
                        "FROM `totema`.product P\n" +
                        "LEFT JOIN `totema`.product_list PL\n" +
                        "ON P.ID = PL.PRODUCT_KEY\n" +
                        "LEFT JOIN `totema`.order O \n" +
                        "ON PL.ORDER_KEY = O.ID\n" +
                        "LEFT JOIN `totema`.date D \n" +
                        "ON D.ID = O.DATE_KEY\n" +
                        "LEFT JOIN `totema`.office OF \n" +
                        "ON O.OFFICE_KEY = OF.ID\n" +
                        "LEFT JOIN `totema`.country C\n" +
                        "ON OF.COUNTRY_KEY = C.ID\n" +
                        "LEFT JOIN `totema`.conversion_rate CR\n" +
                        "ON OF.COUNTRY_KEY = CR.COUNTRY_KEY\n" +
                        "AND O.DATE_KEY = CR.PERIOD_KEY\n" +
                        "GROUP BY P.ID, D.YEAR, OF.ID, C.ID\n" +
                        "ORDER BY P.ID;",
                resultSetMapping = "ProductDetailedOrderMapping"

        ),

})
public class Product extends BaseEntity<Integer> {

    @NotNull
    private int code;

    @NotNull
    @ManyToOne(targetEntity = ProductType.class)
    @JoinColumn(name = "typeKey")
    private ProductType type;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String name;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String size;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String color;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        return Objects.equals(type, product.type) &&
                Objects.equals(name, product.name) &&
                Objects.equals(size, product.size) &&
                Objects.equals(color, product.color) &&
                (code == product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, type, name, size, color);
    }

}

