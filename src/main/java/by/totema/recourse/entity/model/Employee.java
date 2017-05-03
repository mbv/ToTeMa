package by.totema.recourse.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "`employee`")
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
