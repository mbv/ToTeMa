package by.totema.recourse.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.security.Timestamp;
import java.util.Objects;

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
    private long officeKey;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String title;

    @NotNull
    private long yearSalary;

    @Column(columnDefinition = "DATE")
    private Timestamp contractTill;

    @NotNull
    private long reportsTo;

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
        officeKey = employee.officeKey;
        title = employee.title;
        yearSalary = employee.yearSalary;
        contractTill = employee.contractTill;
        reportsTo = employee.reportsTo;
        role = employee.role;
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

    public long getOfficeKey() {
        return officeKey;
    }

    public void setOfficeKey(long officeKey) {
        this.officeKey = officeKey;
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

    public long getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(long yearSalary) {
        this.yearSalary = yearSalary;
    }

    public long getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(long reportsTo) {
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

        return super.equals(o) &&
                officeKey == employee.officeKey &&
                yearSalary == employee.yearSalary &&
                reportsTo == employee.reportsTo &&
                role == employee.role &&
                username.equals(employee.getUsername()) &&
                passwordHash.equals(employee.getPasswordHash()) &&
                name.equals(employee.getName()) &&
                hireDate.equals(employee.getHireDate()) &&
                title.equals(employee.getTitle()) &&
                contractTill.equals(employee.getContractTill());

    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, passwordHash, name, hireDate, officeKey, title, yearSalary, contractTill, reportsTo);
    }

    public enum Role {
        SALESMAN, ANALYST, ADMIN, DISABLED
    }
}
