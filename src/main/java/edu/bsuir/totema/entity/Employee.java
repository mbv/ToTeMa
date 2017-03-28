package edu.bsuir.totema.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import edu.bsuir.totema.util.serialization.DateAdapter;

import static edu.bsuir.totema.util.NullUtil.nullableEquals;
import static edu.bsuir.totema.util.NullUtil.nullableHashCode;

public class Employee extends Entity {
    /**
     * Represents that this Employee entity has status Active.
     * Default value
     */
    public static final int STATUS_ACTIVE = 1;
    /**
     * Represents that this Employee entity has status Banned.
     * It means that this user cannot bet and create lots
     */
    public static final int STATUS_BANNED = 0;
    /**
     * Represents that this Employee entity is deleted.
     * Setting this status equals to deleting this user from the app.
     */
    public static final int STATUS_DELETED = -1;

    @Expose
    private String username;
    private String passwordHash;
    @Expose
    private String name;
    @Expose
    @JsonAdapter(DateAdapter.class)
    private java.sql.Date hireDate;
    @Expose
    private long officeKey;
    @Expose
    private String title;
    @Expose
    private long yearSalary;
    @Expose
    @JsonAdapter(DateAdapter.class)
    private java.sql.Date contractTill;
    @Expose
    private long reportsTo;
    @Expose
    private int status;


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

    public java.sql.Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(java.sql.Date hireDate) {
        this.hireDate = hireDate;
    }

    public long getOfficeKey() {
        return officeKey;
    }

    public void setOfficeKey(long officeKey) {
        this.officeKey = officeKey;
    }

    public java.sql.Date getContractTill() {
        return contractTill;
    }

    public void setContractTill(java.sql.Date contractTill) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return super.equals(o) &&
                officeKey == employee.officeKey &&
                yearSalary == employee.yearSalary &&
                reportsTo == employee.reportsTo &&
                status == employee.status &&
                nullableEquals(username, employee.getUsername()) &&
                nullableEquals(passwordHash, employee.getPasswordHash()) &&
                nullableEquals(name, employee.getName()) &&
                nullableEquals(hireDate, employee.getHireDate()) &&
                nullableEquals(title, employee.getTitle()) &&
                nullableEquals(contractTill, employee.getContractTill());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nullableHashCode(username);
        result = 17 * result + nullableHashCode(passwordHash);
        result = 31 * result + nullableHashCode(name);
        result = 17 * result + nullableHashCode(hireDate);
        result = 31 * result + (int) (officeKey ^ (officeKey >>> 32));
        result = 17 * result + nullableHashCode(title);
        result = 31 * result + (int) (yearSalary ^ (yearSalary >>> 32));
        result = 17 * result + nullableHashCode(contractTill);
        result = 31 * result + (int) (reportsTo ^ (reportsTo >>> 32));
        result = 17 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", officeKey='" + officeKey + '\'' +
                ", title='" + title + '\'' +
                ", yearSalary='" + yearSalary + '\'' +
                ", contractTill='" + contractTill + '\'' +
                ", reportsTo='" + reportsTo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
