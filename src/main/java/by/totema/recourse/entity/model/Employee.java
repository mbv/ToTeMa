package by.totema.recourse.entity.model;

import by.totema.recourse.entity.dto.EmployeeOrderReportDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "`employee`")
@SqlResultSetMapping(
        name = "EmployeeOrderMapping",
        classes = @ConstructorResult(
                targetClass = EmployeeOrderReportDto.class,
                columns = {
                        @ColumnResult(name = "employee", type = String.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "totalPrice", type = Integer.class),
                        @ColumnResult(name = "totalCost", type = Integer.class),
                        @ColumnResult(name = "totalGrossMargin", type = Integer.class),
                        @ColumnResult(name = "currency", type = String.class)
                }
        )
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Employee.getOrderReport",
                query = "SELECT \n" +
                        "E.NAME AS employee,\n" +
                        "SUM(O.QUANTITY) AS quantity,\n" +
                        "TRUNCATE(SUM(O.PRICE*CR.CONVERSION_TO_LOCAL/100), 2) AS totalPrice,\n" +
                        "TRUNCATE(SUM(O.COST*CR.CONVERSION_TO_LOCAL/100), 2) AS totalCost,\n" +
                        "TRUNCATE(SUM(O.GROSS_MARGIN*CR.CONVERSION_TO_LOCAL/100), 2) AS totalGrossMargin,\n" +
                        "C.CURRENCY_NAME AS currency\n" +
                        "FROM `totema`.ORDER O \n" +
                        "JOIN `totema`.office OF \n" +
                        "ON O.OFFICE_KEY = OF.ID\n" +
                        "JOIN `totema`.country C\n" +
                        "ON OF.COUNTRY_KEY = C.ID\n" +
                        "JOIN `totema`.conversion_rate CR\n" +
                        "ON OF.COUNTRY_KEY = CR.COUNTRY_KEY\n" +
                        "AND O.DATE_KEY = CR.PERIOD_KEY\n" +
                        "JOIN `totema`.employee E\n" +
                        "ON E.ID = O.EMPLOYEE_KEY\n" +
                        "GROUP BY E.ID, C.ID;",
                resultSetMapping = "EmployeeOrderMapping"

        ),

})
public class Employee extends BaseEntity<Integer> {
    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String passwordHash;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @Past
    @Column(columnDefinition = "DATE")
    private Timestamp hireDate;

    @NotNull
    @ManyToOne(targetEntity = Office.class)
    @JoinColumn(name = "officeKey")
    private Office office;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String title;

    @NotNull
    private int yearSalary;

    @Column(columnDefinition = "DATE")
    private Timestamp contractTill;

    @NotNull
    private int reportsTo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('SALESMAN', 'ANALYST', 'ADMIN', 'DISABLED')")
    private Role role;

    public Employee() {

    }

    public Employee(Employee employee) {
        username = employee.username;
        passwordHash = employee.passwordHash;
        name = employee.name;
        hireDate = employee.hireDate;
        office = employee.office;
        title = employee.title;
        yearSalary = employee.yearSalary;
        contractTill = employee.contractTill;
        reportsTo = employee.reportsTo;
        role = employee.role;
        setId(employee.getId());
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Timestamp getContractTill() {
        return contractTill;
    }

    public void setContractTill(Timestamp contractTill) {
        this.contractTill = contractTill;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(int yearSalary) {
        this.yearSalary = yearSalary;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        return Objects.equals(username, employee.username) &&
                Objects.equals(passwordHash, employee.passwordHash) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(office, employee.office) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(title, employee.title) &&
                Objects.equals(contractTill, employee.contractTill) &&
                yearSalary == employee.yearSalary &&
                reportsTo == employee.reportsTo &&
                role == employee.role;

    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, passwordHash, name, hireDate, office, title, yearSalary, contractTill, reportsTo);
    }

    public enum Role {
        SALESMAN, ANALYST, ADMIN, DISABLED
    }
}
